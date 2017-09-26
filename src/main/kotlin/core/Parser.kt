package core

/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/9/26 9:24
 * Description:
 */
internal interface Parser {
    fun toXML(fetch:Boolean):String;
    fun toGradle(fetch:Boolean):String;
}