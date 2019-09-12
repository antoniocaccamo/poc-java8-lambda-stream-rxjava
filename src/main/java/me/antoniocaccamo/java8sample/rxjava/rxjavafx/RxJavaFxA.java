package me.antoniocaccamo.java8sample.rxjava.rxjavafx;/**
 * @author antoniocaccamo on 12/09/2019
 */

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.consensusj.supernautfx.SupernautFxApp;
import org.consensusj.supernautfx.SupernautFxLauncher;
import picocli.CommandLine;

@CommandLine.Command(
        name = "rxjavafx-a",
        mixinStandardHelpOptions = true
) @javax.inject.Singleton
public class RxJavaFxA implements SupernautFxApp, Runnable {



    @Override
    public void start(Stage stage) {

//        VBox vBox = new VBox();
//        Button button = new Button("Press Me");
//        Label countLabel = new Label("0");
//
//        JavaFxObservable.actionEventsOf(button)
//                .map(ae -> 1)
//                .scan(0,(x,y) -> x + y)
//                .subscribe(clickCount -> countLabel.setText(clickCount.toString()));
//
//        vBox.getChildren().add(countLabel);
//        vBox.getChildren().add(button);
//
//        stage.setScene(new Scene(vBox));
//        stage.show();

        VBox vBox = new VBox();
        Button button = new Button("Press Me");
        Button secondSubButton = new Button("Subscribe Observer 2");

        Observable<ActionEvent> clicks =JavaFxObservable
                .actionEventsOf(button);

        //Observer 1
        clicks.subscribe(ae ->
                System.out.println("Observer 1 Received Click!"));

        //Subscribe Observer 2 when secondSubButton is clicked
        secondSubButton.setOnAction(event -> {
            System.out.println("Observer 2 subscribing!");
            secondSubButton.disableProperty().set(true);
            //Observer 2
            clicks.subscribe(ae ->
                    System.out.println("Observer 2 Received Click!")
            );
        });

        vBox.getChildren().addAll(button,secondSubButton);

        stage.setScene(new Scene(vBox));
        stage.show();

    }

    @Override
    public void run() {
        SupernautFxLauncher.superLaunch(RxJavaFxA.class, null);
    }
}
