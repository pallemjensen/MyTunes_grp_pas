/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.audio.mp3.MP3FileReader;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.ID3v1Tag;

/**
 * FXML Controller class
 *
 * @author pmj
 */
public class Mediaplayer implements Initializable {

    String songPath = "";
    File song = null;
    Media hit;
    MediaPlayer mediaPlayer;

    @FXML
    private TextField txtDuration;
    @FXML
    private TextField txtArtist;
    @FXML
    private TextField txtTitle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnfindSong(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 Files", "mp3");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            song = chooser.getSelectedFile();
            songPath = chooser.getSelectedFile().getPath();
            System.out.println(songPath);
        }
        
        hit = new Media(song.toURI().toString());
        mediaPlayer = new MediaPlayer(hit); 
    }

    @FXML
    public int getDuration(File song){
        int duration = 0;
                try {
            AudioFile audioFile = AudioFileIO.read(song);
            
            MP3File mp3file = new MP3File(song);
            Tag tag = mp3file.getTag();
            ID3v1Tag v1Tag = (ID3v1Tag) tag;

            duration = audioFile.getAudioHeader().getTrackLength();
                    } catch (Exception e) {
            e.printStackTrace();
        }
        
     return duration;   
    }
    
    
    @FXML
    private void btnPlay(ActionEvent event) throws IOException, TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException {
        int duration = 0;
        String songTitle = null;
        String songArtist = null;
        mediaPlayer.play();

//        try {
//            AudioFile audioFile = AudioFileIO.read(song);
//            
//            MP3File mp3file = new MP3File(song);
//            Tag tag = mp3file.getTag();
//            ID3v1Tag v1Tag = (ID3v1Tag) tag;
//
//            duration = audioFile.getAudioHeader().getTrackLength();
//            songTitle = v1Tag.getFirstTitle();
//            songArtist = v1Tag.getFirstArtist();
//
//            txtDuration.setText(Integer.toString(duration));
//            txtArtist.setText(songArtist);
//            txtTitle.setText(songTitle);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    private void btnPause(ActionEvent event) {
    mediaPlayer.pause();
    }
    
}
