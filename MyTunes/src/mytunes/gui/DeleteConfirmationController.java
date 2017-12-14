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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mytunes.be.Playlist;
import mytunes.be.Song;

/**
 * FXML Controller class
 *
 * @author pmj
 */
public class DeleteConfirmationController implements Initializable {
   private MyTunesModel m_myTunesModel;
   private Song m_selectedSong;
   private Playlist m_selectedPlaylist;
    @FXML
    private Label lbl;
    
    /**
     * Initializes the controller class...
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnDelete(ActionEvent event) throws SQLException {
        if (m_selectedSong != null) 
        { 
            m_myTunesModel.remove(m_selectedSong);
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
        } 
        else if (m_selectedPlaylist != null) 
        { 
            m_myTunesModel.remove(m_selectedPlaylist);
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
        } 
            else 
        { 
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
        } 
        
        
    }

    /**
     *
     * @param myTunesModel
     * @param selectedSong
     */
    public void setUp(MyTunesModel myTunesModel, Song selectedSong) {
        m_myTunesModel = myTunesModel;
        m_selectedSong = selectedSong;
    }

    /**
     *
     * @param myTunesModel
     * @param selectedPlaylist
     */
    public void setUp2(MyTunesModel myTunesModel, Playlist selectedPlaylist) {
        m_myTunesModel = myTunesModel;
        m_selectedPlaylist = selectedPlaylist;

    }

    @FXML
    private void btnCancel(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
}