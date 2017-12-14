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
 * FXML Controller class
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

    @FXML
    private void btnSaveEditPlaylistName(ActionEvent event) throws SQLException {
    int i = id;
    String name2 = txtEditPlaylistName.getText();
    m_myTunesModel.editPlaylist(name2, i);
    m_myTunesModel.loadPlaylists();
    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    }
    
    /**
     *
     */
    public void insertPlaylistInfo(){
    txtEditPlaylistName.setText(name);
    } 

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    void setUp(MyTunesModel myTunesModel) {
        m_myTunesModel = myTunesModel;
    }
    
    
    
}   
