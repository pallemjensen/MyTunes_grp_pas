/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.BLLManager;
import mytunes.dal.SongDAO;

/**
 * FXML Controller class
 *
 * @author pmj
 */
public class MyTunesController implements Initializable {
    
    private final MyTunesModel myTunesModel = new MyTunesModel();
    private final SongDAO songdao = new SongDAO();
    private final BLLManager bllmanager = new BLLManager();
    
    
    @FXML
    private TableView<Song> TVSongs;  
    @FXML
    private TextField txtFilter;
    @FXML
    private TableView<?> TVPlaylists;
    @FXML
    private Label lblNowPlaying;
    @FXML
    private TableColumn<Playlist, String> PlaylistsNameColumn;
    @FXML
    private TableColumn<Playlist, Integer> PlaylistsNrOfSongsColumn;
    @FXML
    private TableColumn<Playlist, Float> PlaylistsSongDurationColumn;
    @FXML
    private TableColumn<Song, String> SongsTitleColumn;
    @FXML
    private TableColumn<Song, String> SongsArtistColumn;
    @FXML
    private TableColumn<Song, String> SongsGenreColumn;
    @FXML
    private TableColumn<Song, Float> SongsDurationColumn;
    private Song song;
//    private int id;
//    private int idCreateSong;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SongsTitleColumn.setCellValueFactory(
            new PropertyValueFactory("title"));
        SongsArtistColumn.setCellValueFactory(
            new PropertyValueFactory("artist"));
        SongsDurationColumn.setCellValueFactory(
            new PropertyValueFactory("duration"));
        SongsGenreColumn.setCellValueFactory(
            new PropertyValueFactory("genre"));

        TVSongs.setItems(myTunesModel.getSongs());
        
    }  
    
    

    @FXML
    private void btnNewPlaylist(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("NewPlaylist.fxml"));
        Parent root = (Parent) fxmlLoader1.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void btnEditPlaylist(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("EditPlaylistName.fxml"));
        Parent root = (Parent) fxmlLoader1.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void btnEditSong(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("EditSong.fxml"));
        Parent root = (Parent) fxmlLoader1.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void btnClose(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();  
    }
    
  
    @FXML
    private void btnDeleteSong(ActionEvent event) throws SQLException {
        Song selectedSong = 
        TVSongs.getSelectionModel().getSelectedItem();
        myTunesModel.remove(selectedSong);

    }

    @FXML
    private void btnStop(ActionEvent event) {
    }

    @FXML
    private void btnPause(ActionEvent event) throws SQLException {
//    int idCreateSong = 0;
//        String title = "lilleper i brand";
//        String genre = "rock";
//        String duration = "300 seconds";
//        String songPath = "www.whatelse.com";
//        String artist = "sorte sarah";
//        songdao.createSong(id, title, genre, duration, songPath, artist);   
    }

    @FXML
    private void btnLoadSongs(ActionEvent event) {
        myTunesModel.loadSongs();
    } 
    
     @FXML
    private void btnDeletePlaylist(ActionEvent event) {
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
    private void btnNewSong(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("NewSong.fxml"));
        Parent root = (Parent) fxmlLoader1.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

