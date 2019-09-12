package me.antoniocaccamo.java8sample.rxjava.rxjavafx;/**
 * @author antoniocaccamo on 12/09/2019
 */

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.consensusj.supernautfx.SupernautFxApp;
import org.consensusj.supernautfx.SupernautFxLauncher;
import picocli.CommandLine;


@CommandLine.Command(
        name = "rxjavafx-b",
        mixinStandardHelpOptions = true
) @javax.inject.Singleton
public class RxJavaFxB implements SupernautFxApp, Runnable {


    @Override
    public void start(Stage stage) {

        VBox vBox = new VBox();

        ListView<String> listView = new ListView<>();

        Observable.range(0,9)
                .map(String::valueOf)
                .toList()
                .subscribe(strings -> listView.getItems().addAll(strings))
        ;


        JavaFxObservable.eventsOf(listView, KeyEvent.KEY_TYPED)
                .map(KeyEvent::getCharacter)
                .filter(s -> s.matches("[0-9]"))
                .subscribe(s -> listView.getSelectionModel().select(s));

        VBox childBox = new VBox();

        Label positionLabel = new Label();
        Rectangle rectangle = new Rectangle(200,200);
        rectangle.setFill(Color.RED);

        childBox.getChildren().addAll(positionLabel, rectangle);
        vBox.getChildren().addAll(listView, childBox);


        JavaFxObservable.eventsOf(rectangle, MouseEvent.MOUSE_MOVED)
                .map(mouseEvent -> String.format("(%f, %f)", mouseEvent.getX(), mouseEvent.getY()))
                .subscribe(positionLabel::setText )
        ;

        stage.setScene(new Scene(vBox));
        stage.show();

    }

    @Override
    public void run() {
        SupernautFxLauncher.superLaunch(RxJavaFxB.class, null);
    }
}
