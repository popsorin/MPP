package Tema.Client;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import  javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.Event;
import javafx.scene.control.Label;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.awt.*;

public class AlertBox {
    public static void display(String title,String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(150);

        Label labe1 = new Label();
        labe1.setText(message);
        Button tryAgainButton = new Button("Try again");
        tryAgainButton.setOnAction(e ->window.close());

        VBox layout = new VBox(30);
        layout.getChildren().addAll(labe1, tryAgainButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);

        window.setScene(scene);

        window.showAndWait();


    }
}
