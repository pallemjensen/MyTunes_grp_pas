/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Anders
 */
public class NewAndEditController implements Initializable {

    @FXML
    private TextField EditSongTitel;
    @FXML
    private TextField EditArtistName;
    @FXML
    private TextField EditSongDuration;
    @FXML
    private TextField EditFileLoaction;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnMoreEdit(ActionEvent event) {
    }

    @FXML
    private void btnCancelEdit(ActionEvent event) {
    }

    @FXML
    private void btnChooseEdit(ActionEvent event) {
    }

    @FXML
    private void btnSaveEdit(ActionEvent event) {
    }
    
}
