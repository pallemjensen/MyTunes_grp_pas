/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *@Main class
 * @author pmj
 */
public class MyTunes extends Application {
    @Override
    public void start(Stage stage) throws Exception {
    FXMLLoader fxloader = new FXMLLoader(getClass().getResource("gui/MyTunes.fxml"));
    Parent root = fxloader.load();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}