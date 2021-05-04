package com.theogapplepie;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        Scene scene = new Scene(pane, 300,300);
        textArea = new TextArea("0");
        textArea.setPrefSize(250,25);
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
        pane.setHgap(5);
        pane.setVgap(5);

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

        pane.add(textArea, 1,0);
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
            if (textArea.getText().isEmpty()){
                textArea.appendText("0");
            }
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
//        if(calculation.startsWith("-")){
//            calculation = "0" + calculation;
//            calculation = calculateHelp("-", calculation);
//        }
        boolean bedmasComplete = false;
        /*else*/if(calculation.startsWith("+")){
            calculation = calculation.substring(1);
        }else if(calculation.startsWith("/")|| calculation.startsWith("x")){
            textArea.setText("Your calculation cannot start with \""+ calculation.charAt(0)+"\"");
            bedmasComplete = true;
        }

        while (!bedmasComplete){
            while (calculation.contains("x")){
            calculation = calculateHelp("x", calculation);
            }
            while (calculation.contains("/")){
                calculation = calculateHelp("/", calculation);
            }
            while (calculation.contains("+")){
                calculation = calculateHelp("+", calculation);
            }
            if(calculation.length()>2 && calculation.substring(1).contains("-")){
            while (calculation.contains("-") ){
                calculation = calculateHelp("-", calculation);
            }}
        bedmasComplete = true;
            textArea.setText(calculation);

        }
    }

    private int findSurroundingOp(String substring, int i) {
        if (i == 0){
            for (int a = substring.length()-1; a>=0; a--){
                if (containsOperations(substring.substring(a,a+1)))
                    return a;
            }
            return 0;
        }
        else{
            for (int a = 0; a< substring.length(); a++){
                if (containsOperations(substring.substring(a,a+1)))
                    return a;
            }
        }
        return substring.length();
    }
    private String calculateHelp(String operation, String calculation){
        int locationOfOperation = calculation.indexOf(operation);
        int previousOp = findSurroundingOp(calculation.substring(0,locationOfOperation),0);
        if (calculation.charAt(previousOp) != '-' && previousOp != 0){
            previousOp++;
        }
        int nextOp = findSurroundingOp(calculation.substring(locationOfOperation+1),1)+1+locationOfOperation;
        if((nextOp - 1-locationOfOperation) == -1){
            nextOp = calculation.length();
        }

        calculation = calculation.substring(0, previousOp)+ calculateHelp2(calculation.substring(previousOp, nextOp)) + calculation.substring(nextOp);
        return calculation;
    }
    private double calculateHelp2(String partition){
        System.out.println(partition);
        double a;
        double b;
        if (partition.contains("x")){
            a = Double.parseDouble(partition.split("x")[0]);
            b = Double.parseDouble(partition.split("x")[1]);
            return a*b;
        }else if(partition.contains("/")){
            a = Double.parseDouble(partition.split("/")[0]);
            b = Double.parseDouble(partition.split("/")[1]);
            return a/b;
        }else if(partition.contains("+")){
            a = Double.parseDouble(partition.split("\\+")[0]);
            b = Double.parseDouble(partition.split("\\+")[1]);
            return a+b;
        }else {
            a = Double.parseDouble(partition.split("-")[0]);
            b = Double.parseDouble(partition.split("-")[1]);
            return a-b;
        }
    }
    private boolean containsOperations(String calculations){
        for (String a : arrayOfOperations){
            if (calculations.contains(a)){
            return true;
        }
        }return false;
    }
}
