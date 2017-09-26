package view

import core.GradleParser
import core.StrParser
import core.XMLParser
import javafx.fxml.FXML
import javafx.scene.control.*



/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/9/25 19:59
 * Description:
 */

class Controller{

    @FXML
    private lateinit  var ftPatternP1: TextField

    @FXML
    private lateinit var tb1P1:ToggleButton
    @FXML
    private lateinit var tb2P1:ToggleButton
    @FXML
    private lateinit var tb3P1:ToggleButton
    @FXML
    private lateinit var tb4P1:ToggleButton
    @FXML
    private lateinit var tb1P2:ToggleButton
    @FXML
    private lateinit var tb2P2:ToggleButton
    @FXML
    private lateinit var tb3P2:ToggleButton
    @FXML
    private lateinit var tb4P2:ToggleButton
    @FXML
    private lateinit var tb1P3:ToggleButton
    @FXML
    private lateinit var tb2P3:ToggleButton
    @FXML
    private lateinit var tb3P3:ToggleButton
    @FXML
    private lateinit var tb4P3:ToggleButton

    @FXML
    private lateinit var taInputP1:TextArea
    @FXML
    private lateinit var taInputP2:TextArea
    @FXML
    private lateinit var taInputP3:TextArea

    @FXML
    private lateinit var taOutputP1:TextArea
    @FXML
    private lateinit var taOutputP2:TextArea
    @FXML
    private lateinit var taOutputP3:TextArea


    private lateinit var btList1:Array<ToggleButton>;
    private lateinit var btList2:Array<ToggleButton>;
    private lateinit var btList3:Array<ToggleButton>;

    //初始化
    fun initialize(){
        btList1 = arrayOf(tb1P1,tb2P1,tb3P1,tb4P1);
        btList2 = arrayOf(tb1P2,tb2P2,tb3P2,tb4P2);
        btList3 = arrayOf(tb1P3,tb2P3,tb3P3,tb4P3);


        val group1  = ToggleGroup();
        for(button in btList1){
            button.setSelected(false);
            button.setToggleGroup(group1);
        }
        val group2 = ToggleGroup();
        for(button in btList2){
            button.setSelected(false);
            button.setToggleGroup(group2);
        }
        val group3 = ToggleGroup();
        for(button in btList3){
            button.setSelected(false);
            button.setToggleGroup(group3);
        }

        //添加ToogleButton事件
        //页面1
        btList1[0].setOnAction { event ->
            if(!ftPatternP1.text.trim().isEmpty() && !taInputP1.text.trim().isEmpty()){
                val parser = StrParser(ftPatternP1.text.trim());
                parser.complie(taInputP1.text.trim());
                val result = parser.toXML(false);
                taOutputP1.text = result;
            }
        };
        btList1[1].setOnAction { event ->
            if(!ftPatternP1.text.trim().isEmpty() && !taInputP1.text.trim().isEmpty()){
                val parser = StrParser(ftPatternP1.text.trim());
                parser.complie(taInputP1.text.trim());
                val result = parser.toXML(true);
                taOutputP1.text = result;
            }
        };
        btList1[2].setOnAction { event ->
            if(!ftPatternP1.text.trim().isEmpty() && !taInputP1.text.trim().isEmpty()){
                val parser = StrParser(ftPatternP1.text.trim());
                parser.complie(taInputP1.text.trim());
                val result = parser.toGradle(false);
                taOutputP1.text = result;
            }
        };
        btList1[3].setOnAction { event ->
            if(!ftPatternP1.text.trim().isEmpty() && !taInputP1.text.trim().isEmpty()){
                val parser = StrParser(ftPatternP1.text.trim());
                parser.complie(taInputP1.text.trim());
                val result = parser.toGradle(true);
                taOutputP1.text = result;
            }
        };

        //页面2
        btList2[0].setOnAction { event ->
            if(!taInputP2.text.trim().isEmpty()){
                val parser = XMLParser();
                parser.compile(taInputP2.text.trim());
                val result = parser.toXML(false);
                taOutputP2.text = result;
            }
        };
        btList2[1].setOnAction { event ->
            if(!taInputP2.text.trim().isEmpty()){
                val parser = XMLParser();
                parser.compile(taInputP2.text.trim());
                val result = parser.toXML(true);
                taOutputP2.text = result;
            }
        };
        btList2[2].setOnAction { event ->
            if(!taInputP2.text.trim().isEmpty()){
                val parser = XMLParser();
                parser.compile(taInputP2.text.trim());
                val result = parser.toGradle(false);
                taOutputP2.text = result;
            }
        };
        btList2[3].setOnAction { event ->
            if(!taInputP2.text.trim().isEmpty()){
                val parser = XMLParser();
                parser.compile(taInputP2.text.trim());
                val result = parser.toGradle(true);
                taOutputP2.text = result;
            }
        };

        //页面3
        btList3[0].setOnAction { event ->
            if(!taInputP3.text.trim().isEmpty()){
                val parser = GradleParser();
                parser.compile(taInputP3.text.trim());
                val result = parser.toXML(false);
                taOutputP3.text = result;
            }
        };
        btList3[1].setOnAction { event ->
            if(!taInputP3.text.trim().isEmpty()){
                val parser = GradleParser();
                parser.compile(taInputP3.text.trim());
                val result = parser.toXML(true);
                taOutputP3.text = result;
            }
        };
        btList3[2].setOnAction { event ->
            if(!taInputP3.text.trim().isEmpty()){
                val parser = GradleParser();
                parser.compile(taInputP3.text.trim());
                val result = parser.toGradle(false);
                taOutputP3.text = result;
            }
        };
        btList3[3].setOnAction { event ->
            if(!taInputP3.text.trim().isEmpty()){
                val parser = GradleParser();
                parser.compile(taInputP3.text.trim());
                val result = parser.toGradle(true);
                taOutputP3.text = result;
            }
        };


    }

}
