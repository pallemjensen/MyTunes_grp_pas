/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mytunes.bll.BLLManager;
import mytunes.dal.SongDAO;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.id3.ID3v1Tag;

/**
 * FXML Controller class
 *
 * @author Anders
 */
public class NewSongController implements Initializable {
    private final SongDAO songdao = new SongDAO();
    private final BLLManager bllmanager = new BLLManager();
    private final MyTunesModel model = new MyTunesModel();

    private Stage stage;
    private String audioFile;
    @FXML
    private TextField txtNewSongTitle;
    @FXML
    private TextField txtNewSongArtist;
    @FXML
    private TextField txtNewSongDuration;
    @FXML
    private TextField txtNewSongPath;
    @FXML
    private TextField txtNewSongGenre;
    String newSongPath = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCancel(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void btnChoose(ActionEvent event) throws MalformedURLException {
        String songTitle = null;
        String songArtist = null;
        String songGenre = null;
        int duration;
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().clear();
            FileChooser.ExtensionFilter filterMp3 = new FileChooser.ExtensionFilter("select your media(*.mp3)", "*.mp3");
            FileChooser.ExtensionFilter filterWav = new FileChooser.ExtensionFilter("select your media(*.wav)", "*.wav");
            chooser.getExtensionFilters().add(filterMp3);
            chooser.getExtensionFilters().add(filterWav);
            File file = chooser.showOpenDialog(this.stage);
            newSongPath = file.getAbsolutePath();
            txtNewSongPath.setText(newSongPath);
            txtNewSongArtist.setText(file.getName());
            txtNewSongTitle.setText(file.getName());
            
            try {
            AudioFile audioFile = AudioFileIO.read(file);
            
            MP3File mp3file = new MP3File(file);
            Tag tag = mp3file.getTag();
            ID3v1Tag v1Tag = (ID3v1Tag) tag;

            duration = audioFile.getAudioHeader().getTrackLength();
            songTitle = v1Tag.getFirstTitle();
            songArtist = v1Tag.getFirstArtist();
            songGenre = v1Tag.getFirstGenre();

            txtNewSongDuration.setText(Integer.toString(duration));
            txtNewSongArtist.setText(songArtist);
            txtNewSongTitle.setText(songTitle);
            txtNewSongGenre.setText(songGenre);
            
        } catch (Exception e) {
            e.printStackTrace();
        }     
    }
    
    
    @FXML
    private void btnSave(ActionEvent event) throws SQLException {
        String title = txtNewSongTitle.getText();
        String genre = txtNewSongGenre.getText();
        String duration = txtNewSongDuration.getText();
        String artist = txtNewSongArtist.getText();
        model.addNewSong(title, genre, duration, newSongPath, artist); 
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    }  
}

