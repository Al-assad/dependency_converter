package core

/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/9/25 20:51
 * Description: 依赖坐标类
 * Version1.0 该版本强制要求输入版本号，下个版本提供版本属性智能生成
 */
internal class Coord(val groupId:String, val artifactId:String, val version:String ){

    //以XML输出（pom.xml依赖格式）
    fun toXML():String{
        val str:String = """    <dependency>
            |       <groupId>$groupId</groupId>
            |       <artifactId>$artifactId</artifactId>
            |       <version>$version</version>
            |   </dependency>
        """.trimMargin("|");
        return str;
    }
    fun toParamXML(versionFlag:String):String{
        val str:String = """    <dependency>
            |       <groupId>$groupId</groupId>
            |       <artifactId>$artifactId</artifactId>
            |       <version>${'$'}{$versionFlag.version}</version>
            |   </dependency>
        """.trimMargin("|");
        return str;
    }


    //以 build.gradle 依赖格式输出
    fun toGradle():String{
        val str:String = "  complie '$groupId:$artifactId:$version'";
        return str;
    }
    fun toParamGradle(versionFlag:String):String{
        val str:String = "  complie \"$groupId:$artifactId:${'$'}{${versionFlag}_version}\"";
        return str;
    }

}