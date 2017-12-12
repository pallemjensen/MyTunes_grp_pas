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
import mytunes.dal.PlaylistDAO;

/**
 * FXML Controller class
 *
 * @author pmj
 */
public class NewPlaylistController implements Initializable {
    
    private final PlaylistDAO playlistDAO = new PlaylistDAO();
    int song1, song2, song3, song4, song5, song6, song7, song8, song9, song10;

    @FXML
    private TextField txtNewPlaylistName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCancelNewPlaylist(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void btnCreateNewPlaylist(ActionEvent event) throws SQLException {
        String name = null;
        name = txtNewPlaylistName.getText();
        playlistDAO.createPlaylist(name, song1, song2, song3, song4, song5, song6, song7, song8, song9, song10);
//        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();  
    }
}
