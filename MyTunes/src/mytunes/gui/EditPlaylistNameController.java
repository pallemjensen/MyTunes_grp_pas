/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class for edit playlist
 *
 * @author pmj
 */
public class EditPlaylistNameController implements Initializable {
    
    private MyTunesModel m_myTunesModel;
    
     String name;
     int id;

    @FXML
    private TextField txtEditPlaylistName;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCancelEditPlaylistNAme(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    //Change the name of the playlist and load the playlist with new name
    @FXML
    private void btnSaveEditPlaylistName(ActionEvent event) throws SQLException {
    int i = id;
    String name2 = txtEditPlaylistName.getText();
    m_myTunesModel.editPlaylist(name2, i);
    m_myTunesModel.loadPlaylists();
    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    }
    
    /**
     *Set the playlist name in the name field
     */
    public void insertPlaylistInfo(){
    txtEditPlaylistName.setText(name);
    } 

    /**
     * @set name 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param id
     * @set id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    // Set myTunesModel to the instance with a new controller
    public void setUp(MyTunesModel myTunesModel) {
        m_myTunesModel = myTunesModel;
    }
    
    
    
}   
