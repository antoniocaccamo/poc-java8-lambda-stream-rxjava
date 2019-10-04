package me.antoniocaccamo.java8sample.rxjava.rxjavafx;/**
 * @author antoniocaccamo on 12/09/2019
 */

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.extern.slf4j.Slf4j;
import org.consensusj.supernautfx.SupernautFxApp;
import org.consensusj.supernautfx.SupernautFxLauncher;
import org.controlsfx.control.Notifications;

import picocli.CommandLine;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.Scanner;

/**
 * A subsribeOn() instructs the source Observable what thread to emit items on. However, the observeOn() switches emissions to a different thread at that point in the chain.
 *  In JavaFX, the most common useage of observeOn() is to put items back on the JavaFX thread after a compution or IO operation finishes from another thread.
 *  Say you wanted to import some expensive data on Schedulers.io() and collect it in a List.
 *  Once it is ready, you want to move that List emission to the JavaFX thread to feed a ListView
 */
@CommandLine.Command(
        name = "player-rxjavafx",
        mixinStandardHelpOptions = true
) @javax.inject.Singleton @Slf4j
public class PlayerRxJavaFx implements SupernautFxApp, Runnable {


    @Override
    public void start(Stage stage) {

        VBox vBox = new VBox();
        vBox.setMinSize(300, 259);
        Label label = new Label("Input URL");
        TextField input = new TextField();
        input.setEditable(false);
        TextArea output = new TextArea();
        Button button = new Button("Open file..");
        MediaView mediaView = new MediaView();
        mediaView.setPreserveRatio(false);

//        Observable.just( new FileChooser().showOpenDialog(null))
//                .filter(f->f !=null && f.isFile())
//                .map(f -> f.getPath());



        output.setWrapText(true);



        JavaFxObservable.actionEventsOf(button)
                .doOnError(t -> log.error("error occurred : ", t))
                .flatMapMaybe( ea ->
                        Observable.just( new FileChooser().showOpenDialog(null))
                        .lastElement()
                )
                .observeOn(Schedulers.io())
                .map(f -> new Media(f.toURI().toASCIIString()))
                .doOnError(t -> log.error("error occurred :", t))
                .subscribeOn(JavaFxScheduler.platform())
                .subscribe(
                        m -> {
                            input.setText(m.getSource());
                            MediaPlayer mediaPlayer = new MediaPlayer(m);
                            mediaView.setMediaPlayer(mediaPlayer);
                        },
                        t -> log.error("error occurred : ", t)
                )

        ;

//        JavaFxObservable.actionEventsOf(button)
//                .map(ae -> input.getText())
//                .observeOn(Schedulers.io())
//                .map(PlayerRxJavaFx::getResponse)
//                .observeOn(JavaFxScheduler.platform())
//                .subscribe( s-> Notifications.create().text(s).owner(vBox).notify());

        input.setMinWidth(400);
        vBox.getChildren().setAll(
                new HBox(button, input),
                mediaView
        );
        stage.setScene(new Scene(vBox));


        JavaFxObservable.changesOf(stage.xProperty())
                .subscribe(ae -> log.info("stage.xProperty : {}", ae))
        ;

        JavaFxObservable.changesOf(stage.yProperty())
                .subscribe(ae -> log.info("stage.yProperty : {}", ae))
        ;

        JavaFxObservable.changesOf(stage.widthProperty())
                .subscribe(ae -> log.info("stage.widthProperty : {}", ae))
        ;

        JavaFxObservable.changesOf(stage.heightProperty())
                .subscribe(ae -> log.info("stage.heightProperty : {}", ae))
        ;


        stage.show();

    }
    private static String getResponse(String path) {
        try {
            return new Scanner(new URL(path).openStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public void run() {
        SupernautFxLauncher.superLaunch(PlayerRxJavaFx.class, null);
    }
}
