package core

/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/9/26 10:08
 * Description: 利用groovy灵活的dsl语法，解析和生成 xml 文本
 */
class XMLHandle {

    //手动增加根元素
    private static String addRoot(String xmlString) {
        return '<root>' + xmlString + '</root>';
    }

    //将xml 文本解析为 CoordList 对象
    static CoordList parseToCoordList(String xmlstring) {
        xmlstring = addRoot(xmlstring);

        CoordList coordList = new CoordList();
        def root = new XmlParser().parseText(xmlstring);

        if (root.properties[0] == null) {    //非抽取式xml解析
            root.dependencies[0].dependency.each { node ->
                Coord coord = new Coord(node.groupId[0].text(), node.artifactId[0].text(), node.version[0].text());
                coordList.add(coord);
            }
        } else {            //抽取式xml解析
            //建立字典
            def directory = new HashMap<String, String>();
            Iterator iter = root.properties[0].iterator();
            def node = null;
            while (iter.hasNext()) {
                node = iter.next();
                directory.put(node.name(), node.text());
            }
            //通过字典还原version值；
            root.dependencies[0].dependency.each {
                def versionStr = it.version[0].text();
                def version = versionStr.substring(versionStr.indexOf('{') + 1, versionStr.indexOf('}')).trim();
                def versionVal = directory.get(version);
                Coord coord = new Coord(it.groupId[0].text(), it.artifactId[0].text(), versionVal);
                coordList.add(coord);
            }


        }
        return coordList;

    }
}
