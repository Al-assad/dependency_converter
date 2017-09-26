package core

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/9/26 10:08
 * Description: 利用groovy灵活的dsl语法，解析和生成 xml 文本
 */
class XMLHandle {

    //手动增加根元素
    private static String addRoot(String xmlString){
        return '<root>'+xmlString+'</root>';
    }

    //将xml 文本解析为 CoordList 对象,需要将$预处理为\$
    static CoordList parseToCoordList(String xmlstring){
        xmlstring = addRoot(xmlstring);

        CoordList coordList = new CoordList();
        def root = new XmlParser().parseText(xmlstring);

        if(root.properties[0] == null ){    //非抽取式xml解析
            root.dependencies[0].dependency.each{ node ->
                Coord coord = new Coord(node.groupId[0].text(),node.artifactId[0].text(),node.version[0].text());
                coordList.add(coord);
            }
        }else{            //抽取式xml解析
            //建立字典
            def directory = new HashMap<String,String>();
            Iterator iter = root.properties[0].iterator();
            def node = null;
            while(iter.hasNext()){
                node = iter.next();
                directory.put(node.name(),node.text());
            }
            //通过字典还原version值；
            root.dependencies[0].dependency.each{
                def versionStr = it.version[0].text();
                def version = versionStr.substring(versionStr.indexOf('{')+1,versionStr.indexOf('}')).trim();
                def versionVal = directory.get(version);
                Coord coord = new Coord(it.groupId[0].text(),it.artifactId[0].text(),versionVal);
                coordList.add(coord);
            }


        }

        return coordList;

    }


   /* static void main(String[] args){
        def str = """
<properties>
    <spring.version>4.3.11.RELEASE</spring.version>
    <struts2.version>2.5.13</struts2.version>
    <struts-annotations.version>1.0.6</struts-annotations.version>
    <ognl.version>3.1.15</ognl.version>
    <commons-io.version>2.5</commons-io.version>
    <commons-fileupload.version>1.3.3</commons-fileupload.version>
    <commons-lang3.version>3.6</commons-lang3.version>
</properties>

<dependencies>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-core</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-beans</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-context</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-context-support</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-expression</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-aop</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-aspects</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-instrument</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-jdbc</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-tx</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-orm</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-oxm</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-jms</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-web</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-webmvc</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-webmvc-portlet</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-websocket</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-test</artifactId>
       <version>\${spring.version}</version>
   </dependency>
    <dependency>
       <groupId>org.apache.struts</groupId>
       <artifactId>struts2-core</artifactId>
       <version>\${struts2.version}</version>
   </dependency>
    <dependency>
       <groupId>org.apache.struts</groupId>
       <artifactId>struts-annotations</artifactId>
       <version>\${struts-annotations.version}</version>
   </dependency>
    <dependency>
       <groupId>ognl</groupId>
       <artifactId>ognl</artifactId>
       <version>\${ognl.version}</version>
   </dependency>
    <dependency>
       <groupId>org.apache.struts</groupId>
       <artifactId>struts2-spring-plugin</artifactId>
       <version>\${struts2.version}</version>
   </dependency>
    <dependency>
       <groupId>org.apache.struts</groupId>
       <artifactId>struts2-json-plugin</artifactId>
       <version>\${struts2.version}</version>
   </dependency>
    <dependency>
       <groupId>commons-io</groupId>
       <artifactId>commons-io</artifactId>
       <version>\${commons-io.version}</version>
   </dependency>
    <dependency>
       <groupId>commons-fileupload</groupId>
       <artifactId>commons-fileupload</artifactId>
       <version>\${commons-fileupload.version}</version>
   </dependency>
    <dependency>
       <groupId>org.apache.commons</groupId>
       <artifactId>commons-lang3</artifactId>
       <version>\${commons-lang3.version}</version>
   </dependency>
</dependencies>
    """;

        def list = parseToCoordList(str).getCoordList();
        for(def coord in list){
            println "${coord.groupId} ${coord.artifactId} ${coord.version}"
        }
    }*/
}
