package com.theogapplepie;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent>{
Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bSubtract,bPlus,bMultiply,bDivide,bEquals,bDot, bClear;
TextArea textArea;
String [] arrayOfOperations = {"+","-","x","/"};
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        initUI(stage);
    }

    private void initUI(Stage stage) {

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane, 400,400);
        textArea = new TextArea("0");
        textArea.setEditable(false);
        b0 = new Button ("0");
        b0.setOnAction(this);
        b1 = new Button ("1");
        b1.setOnAction(this);
        b2 = new Button ("2");
        b2.setOnAction(this);
        b3 = new Button ("3");
        b3.setOnAction(this);
        b4 = new Button ("4");
        b4.setOnAction(this);
        b5 = new Button ("5");
        b5.setOnAction(this);
        b6 = new Button ("6");
        b6.setOnAction(this);
        b7 = new Button ("7");
        b7.setOnAction(this);
        b8 = new Button ("8");
        b8.setOnAction(this);
        b9 = new Button ("9");
        b9.setOnAction(this);
        bDivide = new Button("/");
        bDivide.setOnAction(this);
        bMultiply = new Button("x");
        bMultiply.setOnAction(this);
        bSubtract = new Button("-");
        bSubtract.setOnAction(this);
        bPlus = new Button("+");
        bPlus.setOnAction(this);
        bEquals = new Button("=");
        bEquals.setOnAction(this);
        bDot = new Button(".");
        bDot.setOnAction(this);
        bClear = new Button("C");
        bClear.setOnAction(this);
        // set up the gap and padding between the grid cells
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(10));

        pane.add(b0, 1, 5, 2,1);
        pane.add(bDot,3,5);
        pane.add(bEquals,4,5);

        pane.add(b1, 1, 4);
        pane.add(b2, 2, 4);
        pane.add(b3, 3, 4);
        pane.add(bSubtract,4,4);

        pane.add(b4, 1, 3);
        pane.add(b5, 2, 3);
        pane.add(b6, 3, 3);
        pane.add(bPlus,4,3);

        pane.add(b7, 1, 2);
        pane.add(b8, 2, 2);
        pane.add(b9, 3, 2);
        pane.add(bMultiply,4,2);

        pane.add(bClear, 3,1);
        pane.add(bDivide,4,1);

        pane.add(textArea, 0,0);
        stage.setTitle("Calculator 200,000xtreme");
        stage.setScene(scene);
        stage.show();

    }
    @Override
    public void handle(ActionEvent event) {
    Button button = (Button) event.getSource();

    switch (button.getText()) {
        case "C":
            textArea.setText("");
            break;
        case ".":
            if (!textArea.getText().endsWith(".")){
                textArea.appendText(button.getText());
            }
            break;
        case "=":
            if (textArea.getText().contains("+") || textArea.getText().contains("-") ||
                    textArea.getText().contains("x") || textArea.getText().contains("/")){
                calculate(textArea.getText());
            }
            break;
        default:
            if(textArea.getText().equals("0") && !button.getText().equals(".")){
                textArea.setText(button.getText());

    }else{
            textArea.appendText(button.getText());}
    }
    }
    public void calculate(String calculation){
        while (containsOperations(calculation)&& calculation.length()>2){
            System.out.println(calculation);
            while(calculation.contains("x")){
                int locationOfx = calculation.indexOf("x");
                int previousOp = findSurroundingOp(calculation.substring(0,locationOfx),0);
                if(previousOp == -1){
                    previousOp = 0;
                }
                int nextOp = findSurroundingOp(calculation.substring(locationOfx+1),1);
                if(nextOp == -1){
                    nextOp = calculation.length()-1;
                }
                calculation = calculation.substring(0, previousOp)+ calculateHelp(calculation.substring(previousOp+1, nextOp)) + calculation.substring(nextOp);
            }
            while(calculation.contains("/")){
                int locationOfDivide = calculation.indexOf("/");
                int previousOp = findSurroundingOp(calculation.substring(0,locationOfDivide),0);
                if(previousOp == -1){
                    previousOp = 0;
                }
                int nextOp = findSurroundingOp(calculation.substring(locationOfDivide+1),1);
                if(nextOp == -1){
                    nextOp = calculation.length()-1;
                }
                calculation = calculation.substring(0, previousOp) + calculateHelp(calculation.substring(previousOp+1, nextOp)) + calculation.substring(nextOp);
            }
            while (calculation.contains("+")){
                int locationOfPlus = calculation.indexOf("+");
                int previousOp = findSurroundingOp(calculation.substring(0,locationOfPlus),0);
                if(previousOp == -1){
                    previousOp = 0;
                }
                int nextOp = findSurroundingOp(calculation.substring(locationOfPlus+1),1);
                if(nextOp == -1){
                    nextOp = calculation.length()-1;
                }
                calculation = calculation.substring(0, previousOp) + calculateHelp(calculation.substring(previousOp+1, nextOp)) + calculation.substring(nextOp);
            }
            while(calculation.contains("-")){
                int locationOfMinus = calculation.indexOf("-");
                int previousOp = findSurroundingOp(calculation.substring(0,locationOfMinus),0);
                if(previousOp == -1){
                    previousOp = 0;
                }
                int nextOp = findSurroundingOp(calculation.substring(locationOfMinus+1),1);
                if(nextOp == -1){
                    nextOp = calculation.length()-1;
                }
                calculation = calculation.substring(0, previousOp) + calculateHelp(calculation.substring(previousOp+1, nextOp)) + calculation.substring(nextOp);
            }
        }
    textArea.setText(calculation);
    }

    private int findSurroundingOp(String substring, int i) {
        if (i == 0){
            for (int a = substring.length()-1; a>=0; a--){
                if (containsOperations(substring.substring(a,a+1))){
                    return a;
                }
            }
        }
        else{
            for (int a = 0; a< substring.length(); a++){
                if (containsOperations(substring.substring(a,a+1))){
                    return a;
                }
            }
        }
        return -1;
    }

    public double calculateHelp(String partition){
        double a;
        double b;
        System.out.println(partition);
        if (partition.contains("+")){
            a = Double.parseDouble(partition.split("\\+")[0]);
            b = Double.parseDouble(partition.split("\\+")[1]);
            return a +b;
        }else if(partition.contains("-")){
            a = Double.parseDouble(partition.split("-")[0]);
            b = Double.parseDouble(partition.split("-")[1]);
            return a-b;
        }else if(partition.contains("x")){
            a = Double.parseDouble(partition.split("x")[0]);
            b = Double.parseDouble(partition.split("x")[1]);
            return a*b;
        }else{
            a = Double.parseDouble(partition.split("/")[0]);
            b = Double.parseDouble(partition.split("/")[1]);
            return a/b;}
    }
    private boolean containsOperations(String calculations){
        for (String a : arrayOfOperations){
            if (calculations.contains(a)){
                return true;
            }
        }
        return false;
    }
}
