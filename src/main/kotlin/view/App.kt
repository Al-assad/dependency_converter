package view

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/9/26 16:23
 * Description:
 */
class App: Application() {
    override fun start(primaryStage: Stage?) {
        val root: Parent = FXMLLoader.load<Parent>(javaClass.classLoader.getResource("main.fxml"));  //加载fxml视图资源

        primaryStage!!.title = "Coord Converter";
        primaryStage.scene = Scene(root,800.0,600.0);
        primaryStage.show();
    }
    companion object {
        @JvmStatic
        fun main(args:Array<String>){
            launch(App::class.java);
        }


    }

}