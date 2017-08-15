package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private MediaView media;
    private MediaPlayer mp;
    private Media me;
    @FXML
    ListView<String> listView;
    @FXML
    private TextField goalWeight;
    @FXML
    private TextField currentWeight;
    @FXML
    private TextField difference;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        File f = new File("src/media");
        ObservableList<String> names = FXCollections.observableArrayList(Arrays.asList(f.list()));
        Collections.sort(names);
        listView.getItems().setAll(names);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    @FXML
    public void handleOnSelection() {

        String fileName = listView.getSelectionModel().getSelectedItem();
        if(mp != null) {
            mp.stop();
        }
        String path = new File("src/media/"+ fileName).getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        media.setMediaPlayer(mp);
        mp.setAutoPlay(true);
    }

    @FXML
    public void handleOnClick() {
        MediaPlayer.Status status = mp.getStatus();

        if (status == MediaPlayer.Status.PAUSED) {
            mp.play();
        } else {
            mp.pause();
        }
    }

    @FXML
    public void handleEnterPress(KeyEvent key) {
        if(key.getCode() == KeyCode.ENTER) {
            goalWeight.setText(goalWeight.getText());
            currentWeight.setText(currentWeight.getText());
            difference.setText(Integer.toString(Integer.parseInt(currentWeight.getText()) - Integer.parseInt(goalWeight.getText())));
            //TODO save to file or something to persist when program is closed
        }
    }
}
