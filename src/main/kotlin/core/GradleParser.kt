package core

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/9/26 9:21
 * Description: 解析 build.gradle 依赖字段，生成CoordLIst 对象，再生成其他的文本
 *              version:1.0 目前只支持紧缩格式的 dependency ，如:'org.springframework:spring-core:4.11'
 */
internal class GradleParser:Parser{


 var coordList:CoordList = CoordList();


    //解析gradle文本，产生coordList对象
    fun compile(gradleString:String){

        var linesSrc:List<String> = gradleString.trim().split("\n");
        var propList = ArrayList<ArrayList<String>>();
        var depenList = ArrayList<String>();
        var fetch:Boolean = false;
        var pass:Boolean = false

        //格式清洗
        val iter = linesSrc.iterator();
        while(iter.hasNext()){
            var line = iter.next().trim();
            if(line.isEmpty() || line.startsWith("//")|| line.startsWith("/*")||line.startsWith("*/") || line.startsWith("*"))
                continue;
            if(!pass){
                if(line.startsWith("ext") || line.contains("{")) {     //当存在参数模式时，抓取参数属性键值
                    fetch = true;
                    pass = true;
                    //生成字典
                    line = iter.next().trim();
                    while(!line.startsWith("}") && iter.hasNext()){
//                    if(line.isEmpty() || line.startsWith("//")|| line.startsWith("/*")||line.startsWith("*/") || line.startsWith("*"))
//                        continue;
                        var str = line.split("//")[0].trim().split("/*")[0].trim();
                        val key = line.split("=")[0].trim();
                        var value = line.split("=")[1].trim();
                        value = value.substring(1..value.length-2);

                        val list = ArrayList<String>();
                        list.add(key);
                        list.add(value);
                        propList.add(list);
                        line = iter.next().trim();

                    }
                }
            }

            if(line.startsWith("dependencies") || line.contains("{")){
                line = iter.next().trim();
                while(!line.startsWith("}") && iter.hasNext()){
                    if(line.isEmpty() || line.startsWith("//")|| line.startsWith("/*")||line.startsWith("*/") || line.startsWith("*"))
                        continue;
                    var str = line.split("//")[0].trim().split("/+")[0].trim();
                    var pattern:Pattern = Pattern.compile("^\\w+\\s*'(.+)'");
                    var matcher: Matcher = pattern.matcher(str);
                    if(matcher.matches()){
                        str = matcher.group(1);
                    }else{
                        matcher = Pattern.compile("^\\w+\\s*\"(.+)\"").matcher(str);
                        if(matcher.matches())
                            str = matcher.group(1);
                        else{
                            continue;
                        }
                    }
                    str = str.trim();
                    depenList.add(str);

                    line = iter.next().trim();
                }
            }
        }
        //生成 CoordList 对象
        if(fetch){
            for(line in depenList){
                var list = line.split(":");
                val versionStr = list[2].trim();
                var version:String = versionStr.substring(versionStr.indexOf("{")+1,versionStr.indexOf("}"));
                for(list in propList){
                    if(list[0] == version){
                        version = list[1];
                        break;
                    }
                }
                coordList.add(Coord(list[0].trim(),list[1].trim(),version));
            }
        }else{
            for(line in depenList){
                var list = line.split(":");
                coordList.add(Coord(list[0].trim(),list[1].trim(),list[3].trim()));
            }
        }

    }



    override fun toXML(fetch: Boolean): String {
        return coordList.toXML(fetch);
    }

    override fun toGradle(fetch: Boolean): String {
        return coordList.toGradle(fetch);
    }

}
