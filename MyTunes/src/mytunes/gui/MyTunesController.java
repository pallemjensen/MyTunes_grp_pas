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
/**
 * FXML Controller class for our main window
 *
 * @author pmj
 */
public class MyTunesController implements Initializable {
    
    private final MyTunesModel myTunesModel = new MyTunesModel();
    private MediaPlayer player;
    private Song songSelected;
    private Playlist playlistSelected;
    
    
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
    private ObservableList<Song> filteredSongs
            = FXCollections.observableArrayList();
    @FXML
    private TableView<Song> TVSongsOnPlaylist;
    @FXML
    private TableColumn<Song, String> songsOnPlaylistTitleColumn;
    
    //set the property value of the parameters on our song objects
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

    //Get our songs and insert them into our song tableview.
        TVSongs.setItems(myTunesModel.getSongs());
        PlaylistsNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        PlaylistsNrOfSongsColumn.setCellValueFactory(new PropertyValueFactory("id"));
        PlaylistsSongDurationColumn.setCellValueFactory(new PropertyValueFactory("duration"));
        TVPlaylists.setItems(myTunesModel.getPlaylists());
        
        songsOnPlaylistTitleColumn.setCellValueFactory(new PropertyValueFactory("title"));
        TVSongsOnPlaylist.setItems(myTunesModel.getSongsOnPlaylist());
    }  
    
    //Open the new playlist window
    @FXML
    private void btnNewPlaylist(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("NewPlaylist.fxml"));
        Parent root = (Parent) fxmlLoader1.load();
        NewPlaylistController npc = fxmlLoader1.getController();
        npc.setUp(myTunesModel);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    //Open the edit playlist window. Add the playlist parameter values to a 
    //new myTunesModel instance with the new controller.
    @FXML
    private void btnEditPlaylist(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("EditPlaylistName.fxml"));
        Parent root = (Parent) fxmlLoader1.load();
        EditPlaylistNameController epnc = fxmlLoader1.getController();
        epnc.setId(TVPlaylists.getSelectionModel().getSelectedItem().getId());
        epnc.setName(TVPlaylists.getSelectionModel().getSelectedItem().getName());
        epnc.insertPlaylistInfo();
        epnc.setUp(myTunesModel);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Open the edit Song window. Add the song parameter values to a 
    //new myTunesModel instance with the new controller.
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
        esc.setUp(myTunesModel);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    //Close the main application
    @FXML
    private void btnClose(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();  
    }
    
    //Delete the selected song in our TVSongs tableview from our object list and DB through
    // our DeleteConfirmationController.
    @FXML
    private void btnDeleteSong(ActionEvent event) throws SQLException, IOException {
        Song selectedSong = TVSongs.getSelectionModel().getSelectedItem();
        if(selectedSong != null){
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("DeleteConfirmation.fxml"));
        Parent root = (Parent) fxmlLoader1.load();
        DeleteConfirmationController dcc = fxmlLoader1.getController();
        dcc.setUp(myTunesModel,selectedSong);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        }
    }
    //Stop playing the current media playing.
    @FXML
    private void btnStop(ActionEvent event) {
        player.stop();
    }
    //Pause the current media.
    @FXML
    private void btnPause(ActionEvent event) {
        player.pause();
    }
    //Load our songs from the DB
    @FXML
    private void btnLoadSongs(ActionEvent event) {
        myTunesModel.loadSongs();
    } 
    
    //Delete the selected playlist in our TVPlaylists tableview from our object list and DB through
    // our btnDelete method in DeleteConfirmationController.
     @FXML
    private void btnDeletePlaylist(ActionEvent event) throws IOException, SQLException {
        Playlist selectedPlaylist = 
        TVPlaylists.getSelectionModel().getSelectedItem();
        if(selectedPlaylist != null){
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("DeleteConfirmation.fxml"));
        Parent root = (Parent) fxmlLoader1.load();
        DeleteConfirmationController dcc = fxmlLoader1.getController();
        dcc.setUp2(myTunesModel,selectedPlaylist);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        }
    }
    
    //Shows the current media playing.
    @FXML
    private void btnPlaySong(ActionEvent event) {
        lblNowPlaying.setText(songSelected.getArtist()+": "+ songSelected.getTitle());
        player.play();
    }

    @FXML
    private void btnNextSong(ActionEvent event) {
    }

    @FXML
    private void btnPreviousSong(ActionEvent event) {
    }

    //Filter the songs in tableview on artist and title on the text input given and shows the new list. 
    @FXML
    private void btnFilter(ActionEvent event) {
        TVSongs.getItems().clear();
        String filterString = txtFilter.getText().toLowerCase().trim();
        List<Song> loadedSongs = myTunesModel.getSongs();
        for (Song song : loadedSongs) {
            if (song.getArtist().toLowerCase().trim().contains(filterString) || (song.getTitle().toLowerCase().trim().contains(filterString)))
            {
                filteredSongs.add(song);
                TVSongs.setItems(filteredSongs);
            }
        }
    }

    
    //Opens the New song window
    @FXML
    private void btnNewSong(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("NewSong.fxml"));
        Parent root = (Parent) fxmlLoader1.load();
        NewSongController nsc = fxmlLoader1.getController();
        nsc.setUp(myTunesModel);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    //load our playlists
    @FXML
    private void btnLoadPlaylists(ActionEvent event) {
        myTunesModel.loadPlaylists();
    }
    
    //define a new mediaplayer with the selected song from TVSongs table as media.
    @FXML
    private void mouseClickedSongs(MouseEvent event) {
        songSelected = TVSongs.getSelectionModel().getSelectedItem();
        File file = new File(songSelected.getSongPath());
        player = new MediaPlayer(new Media(file.toURI().toString()));
    }

    // We clear our search filter, songs tableview and load our songs
    @FXML
    private void btnClearFilter(ActionEvent event) {
            txtFilter.clear();
            TVSongs.getItems().clear();
            TVSongs.setItems(myTunesModel.getSongs());
            myTunesModel.loadSongs();
    }

    //Show the songs in the selected playlist
    @FXML
    private void mouseClickedPlaylists(MouseEvent event) {
        playlistSelected = TVPlaylists.getSelectionModel().getSelectedItem();
        myTunesModel.showSongsOnPlaylist(playlistSelected);
    }    
    
    //Add the selected song to the selected playlist using both their id's.
    @FXML
    private void btnAddSongToPlaylist(ActionEvent event) throws SQLException {
        playlistSelected.addSongToPlaylist(songSelected);
    int selectedSongId =  songSelected.getId();
    int selectedPlaylistId = playlistSelected.getId();
    myTunesModel.addSongToPlaylist(selectedPlaylistId, selectedSongId);
    }
    
    
}
