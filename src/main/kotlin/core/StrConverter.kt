package core

/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/9/25 20:50
 * Description:
 */

class StrConverter(pattern:String){

    var pattern:String = "g:a:v:";  //单元格式
    var coordList:List<Coord> = ArrayList();   //储存依赖坐标列表

    init{
        this.pattern = pattern;
    }

    fun complie(resource:String){
        var list:List<String> = resource.trim().split("\n");

    }



}

