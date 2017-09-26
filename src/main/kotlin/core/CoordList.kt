package core

/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/9/26 9:01
 * Description: 对象化的坐标组
 */
internal class CoordList{

    var coordList:ArrayList<Coord> = ArrayList();   //储存依赖坐标列表
    val mergeList:Array<String> = arrayOf("spring","struts2","hibernate","mybatis"); //需要合并的列表
    private var propList:ArrayList<ArrayList<String>> = ArrayList();    //抽取列表

    fun add(coord:Coord){
        coordList.add(coord);
    }

    /**生成XML文本
     * @param fetch 是否为提取参数模式，提取模式只针对MSSH框架进行合并，其余框架一律不合并
     */
    fun toXML(fetch:Boolean = false):String{
        if(fetch){
            var str = "<properties>\n";
            fetch();
            for(prop in propList){
                str += "    <${prop.get(0)+".version"}>${prop.get(1)}</${prop.get(0)+".version"}>\n";
            }
            for (coord in coordList) {
                if(check(coord) == null)
                    str += "    <${coord.artifactId+".version"}>${coord.version}</${coord.artifactId+".version"}>\n";
            }
            str += "</properties>\n\n"
            str +="<dependencies>\n";

            for(coord in coordList){
                val flag = check(coord);
                if(flag != null)
                    str += coord.toParamXML(flag)+"\n"
                else
                    str += coord.toParamXML(coord.artifactId)+"\n"
            }
            str += "</dependencies>";
            return str;
        }else{
            var str = "<dependencies>\n"
            for(coord in coordList){
                str += coord.toXML()+"\n";
            }
            str += "</dependencies>";
            return str;
        }

    }


    /**生成gradle文本
     * @param fetcg 是否提取参数，提取模式只针对MSSH框架进行合并，其余框架爱依一律不合并
     */
    fun toGradle(fetch:Boolean = false):String{
        if(fetch){
            var str = "ext{\n";
            fetch();
            for(prop in propList){
                str += "    ${prop.get(0)+"_version"} = '${prop.get(1)}'\n";
            }
            for (coord in coordList) {
                if(check(coord) == null)
                    str += "    ${coord.artifactId.replace('-','_')+"_version"} = '${coord.version}'\n";
            }
            str += "}\n\n"
            str +="dependencies{\n";

            for(coord in coordList){
                val flag = check(coord);
                if(flag != null)
                    str += coord.toParamGradle(flag)+"\n"
                else
                    str += coord.toParamGradle(coord.artifactId).replace('-','_')+"\n"
            }
            str += "}";
            return str;
        }else{
            var str = "dependencies{\n"
            for(coord in coordList){
                str += coord.toGradle()+"\n";
            }
            str += "}";
            return str;
        }
    }

    //确认coord是否包含在属性依赖列表中
    private fun check(coord:Coord):String?{
        for(prop in propList){
            if(coord.artifactId.startsWith(prop.get(0)) && coord.version == prop.get(1))
                return prop.get(0);
        }
        return null;
    }

    // 抓取依赖列表中包含 MSSH 的core版本号
    private fun fetch(){
        var flagList = ArrayList<String>();
        for(coord in coordList){
            if(propList.size == mergeList.size )
                return;
            val artifactId = coord.artifactId;
            for(merge in mergeList){
                if(merge in flagList)
                    continue;
                if(artifactId == merge+"-core"){
                    flagList.add(merge);
                    val list = ArrayList<String>();
                    list.add(merge);
                    list.add(coord.version);
                    propList.add(list);
                }
            }
        }
    }


}