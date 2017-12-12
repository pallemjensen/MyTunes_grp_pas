/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
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
public class MyTunesController implements Initializable {
    
    private final MyTunesModel myTunesModel = new MyTunesModel();
    private final BLLManager bllmanager = new BLLManager();
    private MediaPlayer player;
    private Song songSelected;
    
    @FXML
    private TableView<Song> TVSongs;  
    @FXML
    private TextField txtFilter;
    @FXML
    private TableView<Playlist> TVPlaylists;
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
    private final ObservableList<Song> filteredSongs
            = FXCollections.observableArrayList();
    
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

        PlaylistsNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        PlaylistsNrOfSongsColumn.setCellValueFactory(new PropertyValueFactory("id"));
        PlaylistsSongDurationColumn.setCellValueFactory(new PropertyValueFactory("duration"));
        TVPlaylists.setItems(myTunesModel.getPlaylists());
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
        EditSongController esc = fxmlLoader1.getController();
        esc.setId(songSelected.getId());
        esc.setArtist(songSelected.getArtist());
        esc.setTitle(songSelected.getTitle());
        esc.setGenre(songSelected.getGenre());
        esc.insertSongInfo();
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
        player.stop();
    }

    @FXML
    private void btnPause(ActionEvent event) {
        player.pause();
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
        
        player.play();
    }

    @FXML
    private void btnNextSong(ActionEvent event) {
    }

    @FXML
    private void btnPreviousSong(ActionEvent event) {
    }

    @FXML
    private void btnFilter(ActionEvent event) {
        TVSongs.getItems().clear();
        String filterString = txtFilter.getText().trim();
        List<Song> loadedSongs = bllmanager.getAllSongs();
        for (Song song : loadedSongs) {
            if (song.getArtist().trim().contains(filterString) || (song.getTitle().trim().contains(filterString)))
            {
                filteredSongs.add(song);
                TVSongs.setItems(filteredSongs);
            }
      
        }
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

    @FXML
    private void btnLoadPlaylists(ActionEvent event) {
        myTunesModel.loadPlaylists();
    }

    @FXML
    private void actionMouseClicked(MouseEvent event) {
        songSelected = TVSongs.getSelectionModel().getSelectedItem();
        File file = new File(songSelected.getSongPath());
        player = new MediaPlayer(new Media(file.toURI().toString()));
    }

    @FXML
    private void btnClearFilter(ActionEvent event) {
            txtFilter.clear();
            TVSongs.getItems().clear();
            TVSongs.setItems(myTunesModel.getSongs());
    }
    
         
}

