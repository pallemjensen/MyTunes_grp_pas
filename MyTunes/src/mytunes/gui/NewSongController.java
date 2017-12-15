/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.ID3v1Tag;

/**
 * FXML Controller class for making a new song
 *
 * @author Anders
 */
public class NewSongController implements Initializable {
    private final MyTunesModel model = new MyTunesModel();
    
    private MyTunesModel m_myTunesModel;
    private Stage stage;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    //Closes the window
    @FXML
    private void btnCancel(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

        // Opens up the new file dialog. Filter on mp3 and wav files.
        //If the song file is MP3v1 version, the song info is automatically filled out.
        //If not, the user have to manually fill out the song info. Some info is already filled with the file name text,
        //but can be altered as pr the users wish.
    @FXML
    private void btnChoose(ActionEvent event) throws MalformedURLException, CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
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
            AudioFile audioFile = AudioFileIO.read(file);
            duration = audioFile.getAudioHeader().getTrackLength();
            txtNewSongDuration.setText(Integer.toString(duration));
            try {
            
            MP3File mp3file = new MP3File(file);
            Tag tag = mp3file.getTag();
            ID3v1Tag v1Tag = (ID3v1Tag) tag;

            songTitle = v1Tag.getFirstTitle();
            songArtist = v1Tag.getFirstArtist();
            songGenre = v1Tag.getFirstGenre();

            txtNewSongArtist.setText(songArtist);
            txtNewSongTitle.setText(songTitle);
            txtNewSongGenre.setText(songGenre);
            
        } catch (Exception e) {
            e.printStackTrace();
        }     
    }
    
    //Create a new song and store it in our DB.
    //It then loads all songs from the DB.
    @FXML
    private void btnSave(ActionEvent event) throws SQLException {
        String title = txtNewSongTitle.getText();
        String genre = txtNewSongGenre.getText();
        String duration = txtNewSongDuration.getText();
        String artist = txtNewSongArtist.getText();
        model.addNewSong(title, genre, duration, newSongPath, artist); 
        m_myTunesModel.loadSongs();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    }

    /**
     *@ Create a new instance of myTunesModel with a new controller.
     * @param myTunesModel
     */
    public void setUp(MyTunesModel myTunesModel) {
        m_myTunesModel = myTunesModel;
       
    }
}

