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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author pmj
 */
public class MyTunesController implements Initializable {

    @FXML
    private TextField txtFilter;
    @FXML
    private TableView<?> TVPlaylists;
    @FXML
    private TableView<?> TVSongs;
    @FXML
    private Button btnNewSong;
    @FXML
    private Label lblNowPlaying;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnPlaySong(ActionEvent event) {
    }

    @FXML
    private void btnNextSong(ActionEvent event) {
    }

    @FXML
    private void btnPreviousSong(ActionEvent event) {
    }

    @FXML
    private void btnFilter(ActionEvent event) {
    }

    @FXML
    private void btnAddSongToPlaylist(ActionEvent event) {
    }

    @FXML
    private void btnNewPlaylist(ActionEvent event) {
    }

    @FXML
    private void btnEditPlaylist(ActionEvent event) {
    }

    @FXML
    private void btnDeletePlaylist(ActionEvent event) {
    }

    @FXML
    private void btnDeleteSongFromPlaylist(ActionEvent event) {
    }

    @FXML
    private void btnEditSong(ActionEvent event) {
    }

    @FXML
    private void btnDeleteSong(ActionEvent event) {
    }

    @FXML
    private void btnClose(ActionEvent event) {
    }
    
}
