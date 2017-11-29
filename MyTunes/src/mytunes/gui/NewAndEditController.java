/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anders
 */
public class NewAndEditController implements Initializable {

    private Stage stage;
    private String audioFile = "No file selected!";
    
    @FXML
    private TextField EditSongTitel;
    @FXML
    private TextField EditArtistName;
    @FXML
    private TextField EditSongDuration;
    @FXML
    private TextField EditFileLoaction;
    @FXML
    private ComboBox<?> comboNewEdit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void btnCancelEdit(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void btnChooseEdit(ActionEvent event) throws MalformedURLException {
        FileChooser chooser = new FileChooser();
            FileChooser.ExtensionFilter filterMp3 = new FileChooser.ExtensionFilter("select your media(*.mp3)", "*.mp3");
            FileChooser.ExtensionFilter filterWav = new FileChooser.ExtensionFilter("select your media(*.wav)", "*.wav");
            chooser.getExtensionFilters().add(filterMp3);
            chooser.getExtensionFilters().add(filterWav);
            File file = chooser.showOpenDialog(this.stage);
            if ( file !=null){
                this.audioFile = file.toURI().toURL().toString();
                EditFileLoaction.setText(audioFile);
            }else{
                EditFileLoaction.setText("Invalid Filename");
                this.audioFile = null;
            }
    }

    @FXML
    private void btnSaveEdit(ActionEvent event) {
    }

    @FXML
    private void comboChooseGenre(ActionEvent event) {
    }
    
}
