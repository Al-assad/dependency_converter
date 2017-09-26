package core

/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/9/26 9:17
 * Description:  负责解析pom.xml文本，并转化为CroodList后，再转化为相应的文本
 */
internal class XMLParser:Parser{

    private var coordList:CoordList = CoordList();
    fun compile(xmlstring:String){
        coordList = XMLHandle.parseToCoordList(xmlstring);
    }

    override fun toXML(fetch: Boolean): String{
        return coordList.toXML(fetch);
    }
    override fun toGradle(fetch: Boolean): String {
        return coordList.toGradle(fetch);
    }

}


