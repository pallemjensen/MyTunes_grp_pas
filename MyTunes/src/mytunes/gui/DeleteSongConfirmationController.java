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
import javafx.scene.control.TableView;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.BLLManager;

/**
 * FXML Controller class
 *
 * @author pmj
 */
public class DeleteSongConfirmationController implements Initializable {
   private MyTunesModel m_myTunesModel;
   private Song m_selectedSong;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnDeleteSong(ActionEvent event) throws SQLException {
        m_myTunesModel.remove(m_selectedSong);
       ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void btnCancelDeleteSong(ActionEvent event) {
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    void setUp(MyTunesModel myTunesModel, Song selectedSong) {
        m_myTunesModel = myTunesModel;
        m_selectedSong = selectedSong;
    }
    
    
}
