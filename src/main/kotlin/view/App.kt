package view

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/9/25 20:00
 * Description:
 */
class App : Application() {
    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {

        val fxmlloader = FXMLLoader(javaClass.getResource("sample.fxml"));
        val root:Parent = fxmlloader.load();

        primaryStage.title = "Coord Converter"
        primaryStage.scene = Scene(root, 500.0, 500.0)
        primaryStage.show()
    }

}
fun main(args: Array<String>) {
    Application.launch(App::class.java,*args);
}