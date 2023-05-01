package com.example.media_player;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class HelloController implements Initializable {

    public VBox parentVBox;
    public HBox mainScreen;
    public VBox controls;
    public VBox mediaOptions;
    public VBox mediaPlaySpace;
    public ImageView home;
    public ImageView music;
    public ImageView video;
    public HBox header;
    public ImageView headerIcon;
    public Label headerTitle;
    public MediaView mediaView;
    public HBox firstControls;
    public Label currentTime;
    public Slider durationSlider;
    public Label totalTime;
    public HBox secondControls;
    public Slider volumeSlider;
    public Button stop;
    public ImageView pause;
    public ImageView play;
    public ImageView restart;
    public Label speed;
    public HBox leftControls;
    public HBox midControls;
    public HBox rightControls;
    public Label labelVolume;
    public Button butt_playpause;
    public Label labelSpeed;
    public Button back;
    public Button forward;

    public ImageView fullScreen;
    public ImageView mute;
    public ImageView volume;
    public ImageView minimize;
    public Button buttVideo;
    public Button buttVideo1;
    public Button buttVideo11;
    public VBox mediaViewVBox;
    private Media media;
    private MediaPlayer mediaPlayer;
    private VBox homeBoard;

    private boolean atEndOfVideo = false;
    private boolean isPlaying = true;
    private boolean isMuted = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mainScreen.prefHeightProperty().bind(parentVBox.heightProperty().multiply(0.87));
        controls.prefHeightProperty().bind(parentVBox.heightProperty().multiply(0.13));

        mediaOptions.prefWidthProperty().bind(parentVBox.widthProperty().multiply(0.04));
        mediaOptions.prefHeightProperty().bind(mainScreen.heightProperty().multiply(1.0));

        mediaPlaySpace.prefWidthProperty().bind(parentVBox.widthProperty().multiply(0.96));
        mediaPlaySpace.prefHeightProperty().bind(mainScreen.heightProperty().multiply(1.0));

        headerIcon.setFitWidth(50);
        headerIcon.setFitHeight(50);

        durationSlider.setPrefWidth(1155);
        volumeSlider.setPrefWidth(200);
        mediaView.setFitHeight(520);
        mediaView.setFitWidth(1150);

        Image videoI = new Image(Objects.requireNonNull(getClass().getResource("/images/videoT.png")).toString());
        video = new ImageView(videoI);
        video.setFitHeight(30);
        video.setFitWidth(30);

        Image homeI = new Image(Objects.requireNonNull(getClass().getResource("/images/home.png")).toString());
        home = new ImageView(homeI);
        home.setFitHeight(33);
        home.setFitWidth(33);

        Image musicI = new Image(Objects.requireNonNull(getClass().getResource("/images/music.png")).toString());
        music = new ImageView(musicI);
        music.setFitHeight(30);
        music.setFitWidth(30);

        Image playI = new Image(Objects.requireNonNull(getClass().getResource("/images/playT.png")).toString());
        play = new ImageView(playI);
        play.setFitHeight(30);
        play.setFitWidth(30);

        Image fullScreenI = new Image(Objects.requireNonNull(getClass().getResource("/images/m.png")).toString());
        fullScreen = new ImageView(fullScreenI);
        fullScreen.setFitHeight(25);
        fullScreen.setFitWidth(25);

        Image pauseI = new Image(Objects.requireNonNull(getClass().getResource("/images/pauseT.png")).toString());
        pause = new ImageView(pauseI);
        pause.setFitHeight(30);
        pause.setFitWidth(30);

        Image muteI = new Image(Objects.requireNonNull(getClass().getResource("/images/mute.png")).toString());
        mute = new ImageView(muteI);
        mute.setFitHeight(25);
        mute.setFitWidth(25);

        Image volumeI = new Image(Objects.requireNonNull(getClass().getResource("/images/vol.png")).toString());
        volume = new ImageView(volumeI);
        volume.setFitHeight(25);
        volume.setFitWidth(25);

        Image minI = new Image(Objects.requireNonNull(getClass().getResource("/images/min (2).png")).toString());
        minimize = new ImageView(minI);
        minimize.setFitHeight(25);
        minimize.setFitWidth(25);

        Image restartI = new Image(Objects.requireNonNull(getClass().getResource("/images/restart1.png")).toString());
        restart = new ImageView(restartI);
        restart.setFitHeight(30);
        restart.setFitWidth(30);

        Image backI = new Image(Objects.requireNonNull(getClass().getResource("/images/back.png")).toString());
        ImageView backRW = new ImageView(backI);
        backRW.setFitHeight(25);
        backRW.setFitWidth(25);

        Image forwardI = new Image(Objects.requireNonNull(getClass().getResource("/images/forward.png")).toString());
        ImageView forwardRW = new ImageView(forwardI);
        forwardRW.setFitHeight(25);
        forwardRW.setFitWidth(25);

        Image stopI = new Image(Objects.requireNonNull(getClass().getResource("/images/fullS.png")).toString());
        ImageView stopIV = new ImageView(stopI);
        stopIV.setFitHeight(25);
        stopIV.setFitWidth(25);

        butt_playpause.setGraphic(pause);
        labelVolume.setGraphic(mute);
        leftControls.getChildren().remove(volumeSlider);
        labelSpeed.setGraphic(fullScreen);
        stop.setGraphic(stopIV);
        back.setGraphic(backRW);
        forward.setGraphic(forwardRW);
        buttVideo.setGraphic(video);
        buttVideo1.setGraphic(music);
        buttVideo11.setGraphic(home);

    }

    public  void homeScreen (ActionEvent event) throws FileNotFoundException {
        mediaViewVBox.getChildren().remove(mediaView);

        Label homeL = new Label("Home");
        homeL.setId("homeL");

        Image displayI = new Image(Objects.requireNonNull(getClass().getResource("/images/display.png")).toString());
        ImageView display = new ImageView(displayI);
        display.setFitHeight(350);
        display.setFitWidth(350);

        Label displayTitle = new Label("Welcome to the new Tess Media Player");
        displayTitle.setId("displayTitle");
        Label displayDescription = new Label("Choose our best Tess Media Player to play your audio and video files at any time of the day");
        displayDescription.setId("displayDescription");
        displayDescription.setMaxWidth(330);
        displayDescription.setWrapText(true);

        MenuItem musicItem = new MenuItem("Music");
        MenuItem voiceItem = new MenuItem("Video");

        musicItem.setOnAction(this::functionality);
        voiceItem.setOnAction(this::functionality);

        MenuButton menuButton = new MenuButton();
        menuButton.setText("Open File(s)");
        menuButton.setGraphic(getIcon("folder.png"));
        menuButton.getItems().addAll(musicItem, voiceItem);
        menuButton.setPrefWidth(150);
        menuButton.setId("menuButton");

        VBox displayText = new VBox(10,displayTitle, displayDescription, menuButton);
        displayText.setId("displayText");

        HBox homeHBox = new HBox(display, displayText);
        homeBoard = new VBox(15,homeL, homeHBox);

        mediaViewVBox.getChildren().add(homeBoard);

    }

    ImageView getIcon (String imageName) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:/Users/E4310/IdeaProjectsm/src/main/resources/Images/" + imageName);
        Image image = new Image(fileInputStream, 30 , 30 ,false, false);
        return new ImageView(image);
    }

    public void functionality (ActionEvent event){
        mediaViewVBox.getChildren().remove(homeBoard);

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        String path = file.toURI().toString();

        if (path != null){
            media = new Media(path);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.setAutoPlay(true);
        }

        butt_playpause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bindCurrentTime();
                Button buttonP = (Button) actionEvent.getSource();
                bindCurrentTime();
                if(atEndOfVideo) {
                    durationSlider.setValue(0);
                    atEndOfVideo = false;
                    isPlaying = false;
                }
                if(isPlaying) {
                    buttonP.setGraphic(play);
                    mediaPlayer.pause();
                    isPlaying = false;
                }
                else {
                    buttonP.setGraphic(pause);
                    mediaPlayer.play();
                }
            }
        });

        mediaPlayer.volumeProperty().bindBidirectional(volumeSlider.valueProperty());

        bindCurrentTime();

        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volumeSlider.getValue());
                if(mediaPlayer.getVolume() != 0.0) {
                    labelVolume.setGraphic(volume);
                    isMuted = false;
                }
                else {
                    labelVolume.setGraphic(mute);
                    isMuted = true;
                }
            }
        });

        speed.setOnMouseClicked(mouseEvent -> {
            if(speed.getText().equals("1X")) {
                speed.setText("2X");
                mediaPlayer.setRate(2.0);
            }
            else {
                speed.setText("1X");
                mediaPlayer.setRate(1.0);
            }
        });

        labelVolume.setOnMouseClicked(mouseEvent -> {
            if(isMuted) {
                labelVolume.setGraphic(volume);
                volumeSlider.setValue(0.2);
                isMuted = false;
            }
            else {
                labelVolume.setGraphic(mute);
                volumeSlider.setValue(0);
                isMuted = true;
            }
        });

        labelVolume.setOnMouseEntered(mouseEvent -> {
            if(leftControls.lookup("#volumeSlider") == null) {
                leftControls.getChildren().add(volumeSlider);
                volumeSlider.setValue(mediaPlayer.getVolume());
            }
        });
        leftControls.setOnMouseExited(mouseEvent -> {
            leftControls.getChildren().remove(volumeSlider);
        });

        parentVBox.sceneProperty().addListener(new ChangeListener<Scene>() {
            @Override
            public void changed(ObservableValue<? extends Scene> observableValue, Scene oldScene, Scene newScene) {
                if(oldScene == null && newScene != null){
                    mediaView.fitHeightProperty().bind(newScene.heightProperty().subtract(controls.heightProperty().add(20)));
                }

            }
        });

        labelSpeed.setOnMouseEntered(mouseEvent -> {
            Label label = (Label) mouseEvent.getSource();
            Stage stage = (Stage) label.getScene().getWindow();
            if(stage.isFullScreen()) {
                stage.setFullScreen(false);
                labelSpeed.setGraphic(fullScreen);
            }
            else {
                stage.setFullScreen(true);
                labelSpeed.setGraphic(minimize);
            }
            stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if(keyEvent.getCode() == KeyCode.ESCAPE) {
                        labelSpeed.setGraphic(fullScreen);
                    }
                }
            });
        });

        mediaPlayer.totalDurationProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration oldDuration, Duration newDuration) {
                bindCurrentTime();
                durationSlider.setMax(newDuration.toSeconds());
                totalTime.setText(getTime(newDuration));
            }
        });

        durationSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean isChanging) {
                bindCurrentTime();
                if(!isChanging) {
                    mediaPlayer.seek(Duration.seconds(durationSlider.getValue()));
                }
            }
        });

        durationSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                bindCurrentTime();
                double currentTime1 = mediaPlayer.getCurrentTime().toSeconds();
                if(Math.abs(currentTime1 - newValue.doubleValue()) > 0.5) {
                    mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
                }
                labelMatchEndVideo(currentTime.getText(), totalTime.getText());
            }
        });

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration oldTime, Duration newTime) {
                bindCurrentTime();
                if(!durationSlider.isValueChanging()) {
                    durationSlider.setValue(newTime.toSeconds());
                }
                labelMatchEndVideo(currentTime.getText(), totalTime.getText());
            }
        });

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                butt_playpause.setGraphic(restart);
                atEndOfVideo = true;
                if(!currentTime.textProperty().equals(totalTime.textProperty())) {
                    currentTime.textProperty().unbind();
                    currentTime.setText(getTime(mediaPlayer.getTotalDuration()));
                }
            }
        });

        mediaViewVBox.getChildren().add(mediaView);

    }

    public void bindCurrentTime() {
        currentTime.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getTime(mediaPlayer.getCurrentTime());
            }
        },mediaPlayer.currentTimeProperty()));
    }

    public String getTime (Duration time) {
        int hrs = (int) time.toHours();
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds();

        if(seconds > 59) seconds = seconds % 60;
        if(minutes > 59) minutes = minutes % 60;
        if(hrs > 59) hrs = hrs % 60;

        if(hrs > 0) return String.format("%d:%02d:%02d",
                hrs,
                minutes,
                seconds);
        else return String.format("%02d:%02d",
                minutes,
                seconds);
    }

    public void labelMatchEndVideo (String labelTime, String totalTime) {
        for(int i =0; i < totalTime.length(); i++) {
            if(labelTime.charAt(i) != totalTime.charAt(i)){
            atEndOfVideo = false;
            if (isPlaying) butt_playpause.setGraphic(pause);
            else butt_playpause.setGraphic(play);
            break;}
            else {
                atEndOfVideo = true;
                butt_playpause.setGraphic(restart);
            }
        }

    }

    public void stop(ActionEvent event){
        mediaPlayer.stop();
    }

    public void skipForward(ActionEvent event){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(10)));
    }

    public void skipback(ActionEvent event){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(-10)));
    }
}