package com.theogapplepie;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent>{
Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bSubtract,bPlus,bMultiply,bDivide,bEquals,bDot;
TextField textField;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        initUI(stage);
    }

    private void initUI(Stage stage) {

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane, 300,300);
        textField = new TextField("0");
        b0 = new Button ("0");
        b1 = new Button ("1");
        b2 = new Button ("2");
        b3 = new Button ("3");
        b4 = new Button ("4");
        b5 = new Button ("5");
        b6 = new Button ("6");
        b7 = new Button ("7");
        b8 = new Button ("8");
        b9 = new Button ("9");
        bDivide = new Button("/");
        bMultiply = new Button("x");
        bSubtract = new Button("-");
        bPlus = new Button("+");
        bEquals = new Button("=");
        bDot = new Button(".");
        // set up the gap and padding between the grid cells
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(10));

        pane.add(b0, 1, 4, 1,2);
        pane.add(bDot,3,4);
        pane.add(b1, 1, 3);
        pane.add(b2, 2, 3);
        pane.add(b3, 3, 3);
        pane.add(b4, 1, 2);
        pane.add(b5, 2, 2);
        pane.add(b6, 3, 2);
        pane.add(b7, 1, 1);
        pane.add(b8, 2, 1);
        pane.add(b9, 3, 1);
        pane.add(textField, 0,0,4,1);
        stage.setTitle("Calculator 200,000xtreme");
        stage.setScene(scene);
        stage.show();

    }
    @Override
    public void handle(ActionEvent event) {
        System.out.println("Hello World!");
    }
}
