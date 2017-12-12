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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pmj
 */
public class EditPlaylistNameController implements Initializable {
     String name;
     int id;

    @FXML
    private TextField txtEditPlaylistName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCancelEditPlaylistNAme(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void btnSaveEditPlaylistName(ActionEvent event) {
        //        todo
    //    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    }
    
    public void insertPlaylistInfo(){
    txtEditPlaylistName.setText(name);
    } 

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}   
