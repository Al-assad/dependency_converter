package core

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/9/25 20:50
 * Description: 普通字符转换类:支持被匹配的字符串含有注释
 */

internal class StrParser(pattern:String):Parser{

    var coordCh:Array<String> = arrayOf("g","a","v");
    var splitCh:Array<String> = arrayOf(":",":");
    var coordList:CoordList = CoordList();



    init{
        var str = pattern.trim();
        if(str.isEmpty() || (!str.contains('g')||!str.contains('a')||!str.contains('v')) || str.contains('-'))
            throw IllegalArgumentException();

        val p:Pattern = Pattern.compile("^([gav])\\s*([^gav])\\s*([gav])\\s*([^gav])\\s*([gav])$");
        val m:Matcher = p.matcher(str);

        if(!m.matches())
            throw IllegalArgumentException();

        coordCh[0] = m.group(1);
        coordCh[1] = m.group(3);
        coordCh[2] = m.group(5);
        splitCh[0] = m.group(2);
        splitCh[1] = m.group(4);

    }



    fun complie(resource:String){
        var list:List<String> = resource.trim().split("\n");
        val sp = if(splitCh[0] == splitCh[1]) splitCh[0] else splitCh[0]+splitCh[1];

        val p:Pattern = Pattern.compile("^([^$sp\\s]+)\\s*[$sp]\\s*([^$sp\\s]+)\\s*[$sp]\\s*([^$sp\\s]+)")
        var m:Matcher ;
        for(str in list){
            var str2 = str.trim();
            if(str2.isEmpty() || str2.startsWith("//"))
                continue;

            m = p.matcher(str2);

            if(m.matches()){
                var g:String = "";
                var a:String = "";
                var v:String = "";
                for(i in coordCh.indices){
                    when(coordCh[i]){
                        "g" -> g = m.group(i+1);
                        "a" -> a = m.group(i+1);
                        else ->v = m.group(i+1);
                    }
                }

                if(!g.isEmpty() && !a.isEmpty() && !v.isEmpty())
                    coordList.add(Coord(g,a,v));
            }
        }

    }


    /**生成XML文本
     * @param fetch 是否为提取参数模式，提取模式只针对MSSH框架进行合并，其余框架一律不合并
     */
    override fun toXML(fetch:Boolean ):String{
        return coordList.toXML(fetch);
    }



    /**生成gradle文本
     * @param fetcg 是否提取参数，提取模式只针对MSSH框架进行合并，其余框架爱依一律不合并
     */
    override fun toGradle(fetch:Boolean ):String{
        return coordList.toGradle(fetch);
    }



}



