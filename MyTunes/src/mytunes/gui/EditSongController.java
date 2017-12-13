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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }      

    @FXML
    public void btnEditSongSave(ActionEvent event) throws SQLException {
      int i = id;
      String artist = txtEditSongArtist.getText();
      String title = txtEditSongTitle.getText();
      String genre = txtEditSongGenre.getText();
      m_myTunesModel.editSong(i, artist, title, genre);
      m_myTunesModel.loadSongs();
      ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void btnEditSongCancel(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    public void setId(int id) {
        this.id = id;
    } 

    public void setArtist(String Artist) {
        this.Artist = Artist;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    
    public void insertSongInfo(){
    txtEditSongArtist.setText(Artist);
    txtEditSongTitle.setText(Title);
    txtEditSongGenre.setText(Genre);
    } 
    public void setUp(MyTunesModel myTunesModel) {
        m_myTunesModel = myTunesModel;
    }
}
