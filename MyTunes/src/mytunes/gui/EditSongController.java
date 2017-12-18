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
 * Controller class for edit song
 *
 * @author Anders
 */
public class EditSongController implements Initializable {
    
    int id;
    String Artist;
    String Genre;
    String Title;
    
    private MyTunesModel m_myTunesModel;
    
    @FXML
    private TextField txtEditSongGenre;
    @FXML
    private TextField txtEditSongTitle;
    @FXML
    private TextField txtEditSongArtist;
    
    /**
     * Initializes the controller class for edit song.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }      

    /**
     *@ Edit artist, title and genre on a song with id i and loads the new songlist.
     * @param event
     */
    @FXML
    public void btnEditSongSave(ActionEvent event){
      int i = id;
      String artist = txtEditSongArtist.getText();
      String title = txtEditSongTitle.getText();
      String genre = txtEditSongGenre.getText();
      m_myTunesModel.editSong(i, title, artist, genre);
      m_myTunesModel.loadSongs();
      ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void btnEditSongCancel(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    /**
     *@set id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    } 

    /**
     *@set Artist
     * @param Artist
     */
    public void setArtist(String Artist) {
        this.Artist = Artist;
    }

    /**
     *@ set genre
     * @param Genre
     */
    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    /**
     *@ set title
     * @param Title
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }
    
    /**
     *@ Fill out the song info in the edit song window with current parameters before editing.
     */
    public void insertSongInfo(){
    txtEditSongArtist.setText(Artist);
    txtEditSongTitle.setText(Title);
    txtEditSongGenre.setText(Genre);
    } 

    /**
     * @ Receive myTunesModel and set to new instance with new controller.
     * @param myTunesModel
     */
    public void setUp(MyTunesModel myTunesModel) {
        m_myTunesModel = myTunesModel;
    }
}   
    

