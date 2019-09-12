package me.antoniocaccamo.java8sample.rxjava.rxjavafx;/**
 * @author antoniocaccamo on 12/09/2019
 */

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.consensusj.supernautfx.SupernautFxApp;
import org.consensusj.supernautfx.SupernautFxLauncher;
import picocli.CommandLine;

import java.net.URL;
import java.util.Scanner;

/**
 * A subsribeOn() instructs the source Observable what thread to emit items on. However, the observeOn() switches emissions to a different thread at that point in the chain.
 *  In JavaFX, the most common useage of observeOn() is to put items back on the JavaFX thread after a compution or IO operation finishes from another thread.
 *  Say you wanted to import some expensive data on Schedulers.io() and collect it in a List.
 *  Once it is ready, you want to move that List emission to the JavaFX thread to feed a ListView
 */
@CommandLine.Command(
        name = "rxjavafx-c",
        mixinStandardHelpOptions = true
) @javax.inject.Singleton
public class RxJavaFxC implements SupernautFxApp, Runnable {


    @Override
    public void start(Stage stage) {

        VBox vBox = new VBox();
        Label label = new Label("Input URL");
        TextField input = new TextField();
        TextArea output = new TextArea();
        Button button = new Button("Submit");

        output.setWrapText(true);

        JavaFxObservable.actionEventsOf(button)
                .map(ae -> input.getText())
                .observeOn(Schedulers.io())
                .map(RxJavaFxC::getResponse)
                .observeOn(JavaFxScheduler.platform())
                .subscribe(output::setText);

        vBox.getChildren().setAll(label,input,output,button);
        stage.setScene(new Scene(vBox));
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
        SupernautFxLauncher.superLaunch(RxJavaFxC.class, null);
    }
}
