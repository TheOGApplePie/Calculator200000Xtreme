package com.theogapplepie;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent>{
Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bSubtract,bPlus,bMultiply,bDivide,bEquals,bDot, bClear;
TextArea textArea;
Label equationLabel;
String [] arrayOfOperations = {"+","-","x","/"};
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        initUI(stage);
    }

    private void initUI(Stage stage) {
        //Setting the layout
        GridPane pane = new GridPane();
        VBox verticalBox = new VBox();
        Scene scene = new Scene(verticalBox, 300,360);

        textArea = new TextArea("0");
        textArea.setFont(Font.font(16));
        textArea.setPrefSize(250,25);
        textArea.setEditable(false);

        b0 = new Button ("0");
        b0.prefHeightProperty().bind(verticalBox.heightProperty());
        b0.prefWidthProperty().bind(verticalBox.widthProperty());
        b0.setOnAction(this);

        b1 = new Button ("1");
        b1.prefHeightProperty().bind(verticalBox.heightProperty());
        b1.prefWidthProperty().bind(verticalBox.widthProperty());
        b1.setOnAction(this);

        b2 = new Button ("2");
        b2.prefHeightProperty().bind(verticalBox.heightProperty());
        b2.prefWidthProperty().bind(verticalBox.widthProperty());
        b2.setOnAction(this);

        b3 = new Button ("3");
        b3.prefHeightProperty().bind(verticalBox.heightProperty());
        b3.prefWidthProperty().bind(verticalBox.widthProperty());
        b3.setOnAction(this);

        b4 = new Button ("4");
        b4.prefHeightProperty().bind(verticalBox.heightProperty());
        b4.prefWidthProperty().bind(verticalBox.widthProperty());
        b4.setOnAction(this);

        b5 = new Button ("5");
        b5.prefHeightProperty().bind(verticalBox.heightProperty());
        b5.prefWidthProperty().bind(verticalBox.widthProperty());
        b5.setOnAction(this);

        b6 = new Button ("6");
        b6.prefHeightProperty().bind(verticalBox.heightProperty());
        b6.prefWidthProperty().bind(verticalBox.widthProperty());
        b6.setOnAction(this);

        b7 = new Button ("7");
        b7.prefHeightProperty().bind(verticalBox.heightProperty());
        b7.prefWidthProperty().bind(verticalBox.widthProperty());
        b7.setOnAction(this);

        b8 = new Button ("8");
        b8.prefHeightProperty().bind(verticalBox.heightProperty());
        b8.prefWidthProperty().bind(verticalBox.widthProperty());
        b8.setOnAction(this);

        b9 = new Button ("9");
        b9.prefHeightProperty().bind(verticalBox.heightProperty());
        b9.prefWidthProperty().bind(verticalBox.widthProperty());
        b9.setOnAction(this);

        bDivide = new Button("/");
        bDivide.prefHeightProperty().bind(verticalBox.heightProperty());
        bDivide.prefWidthProperty().bind(verticalBox.widthProperty());
        bDivide.setOnAction(this);

        bMultiply = new Button("x");
        bMultiply.prefHeightProperty().bind(verticalBox.heightProperty());
        bMultiply.prefWidthProperty().bind(verticalBox.widthProperty());
        bMultiply.setOnAction(this);

        bSubtract = new Button("-");
        bSubtract.prefHeightProperty().bind(verticalBox.heightProperty());
        bSubtract.prefWidthProperty().bind(verticalBox.widthProperty());
        bSubtract.setOnAction(this);

        bPlus = new Button("+");
        bPlus.prefHeightProperty().bind(verticalBox.heightProperty());
        bPlus.prefWidthProperty().bind(verticalBox.widthProperty());
        bPlus.setOnAction(this);

        bEquals = new Button("=");
        bEquals.prefHeightProperty().bind(verticalBox.heightProperty());
        bEquals.prefWidthProperty().bind(verticalBox.widthProperty());
        bEquals.setOnAction(this);

        bDot = new Button(".");
        bDot.prefHeightProperty().bind(verticalBox.heightProperty());
        bDot.prefWidthProperty().bind(verticalBox.widthProperty());
        bDot.setOnAction(this);

        bClear = new Button("C");
        bClear.prefHeightProperty().bind(verticalBox.heightProperty());
        bClear.prefWidthProperty().bind(verticalBox.widthProperty());
        bClear.setOnAction(this);

        // set up the gap and padding between the grid cells
        pane.setHgap(5);
        pane.setVgap(5);

        verticalBox.getChildren().addAll(textArea, pane);
        verticalBox.setPadding(new Insets(10,10,10,10));
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

        pane.add(bClear, 1,1, 2,1);
        pane.add(bDivide,4,1);

        stage.setTitle("Calculator 200,000xtreme");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void handle(ActionEvent event) {
    Button button = (Button) event.getSource();

    switch (button.getText()) {
        case "C":
            textArea.setText("0");
            break;
        case ".":
            if (textArea.getText().isEmpty()){
                textArea.appendText("0");
            }
            int index = findSurroundingOp(textArea.getText(),0);
            if (!textArea.getText().substring(index).contains(".")){
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
                if(containsOperations(textArea.getText().substring(textArea.getText().length()-1)) && containsOperations(button.getText()) && !button.getText().equals("-")){
                    textArea.setText(textArea.getText().substring(0,textArea.getText().length()-1) + button.getText());
                }else{
                    if(button.getText().equals("-") && textArea.getText().endsWith("--")){
                }else{
                        textArea.appendText(button.getText());
                    }}
            }
        }
    }
    /* Main function for calculating the calculation with some error checking.*/
    public void calculate(String calculation){

        boolean bedmasComplete = false;
        if(calculation.startsWith("+")){
            calculation = calculation.substring(1);
        }else if(calculation.startsWith("/")|| calculation.startsWith("x")){
            textArea.setText("Your calculation cannot start with \""+ calculation.charAt(0)+"\"");
            bedmasComplete = true;
        }else if(calculation.endsWith("+") ||calculation.endsWith("-") ||calculation.endsWith("x") ||calculation.endsWith("/") ){
            textArea.setText("Your calculation cannot end with \""+ calculation.charAt(calculation.length()-1)+"\"");
            bedmasComplete = true;
        }

        while (!bedmasComplete){
            calculation = clearDoubleNegatives(calculation);
            while (calculation.contains("x")){
                calculation = clearDoubleNegatives(calculation);
            calculation = calculateHelp("x", calculation);
                calculation = clearDoubleNegatives(calculation);

            }
            while (calculation.contains("/")){
                calculation = clearDoubleNegatives(calculation);
                calculation = calculateHelp("/", calculation);
                calculation = clearDoubleNegatives(calculation);

            }
            while (calculation.contains("+")){
                calculation = clearDoubleNegatives(calculation);
                calculation = calculateHelp("+", calculation);
            }
            if(calculation.length()>2 && calculation.substring(1).contains("-")){
            do{
                calculation = calculateHelp("-", calculation);
            }
                while (calculation.substring(1).contains("-") );
            }
        bedmasComplete = true;
            textArea.setText(calculation);

        }
    }
    public static String fmt(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }
    //Remove double negatives
    private String clearDoubleNegatives(String calculation) {
        for (int i = 0; i< calculation.length()-1; i++){
            if(calculation.charAt(i) == '-' && calculation.charAt(i+1) == '-'){
                if(i != 0){
                calculation = calculation.substring(0, i) + "+" + calculation.substring(i+2);
                }else{
                    calculation = calculation.substring(0, i) + calculation.substring(i+2);
                }
            }
        }
        return calculation;
    }

    /*Finds and returns index of surrounding operator based on what value for i was passed in.
     * If i is 0, the function will loop backwards through the partition to find the operator that preceded the current
     * operator in bedmas, otherwise it will loop forwards to find the next surrounding operator.*/
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
    /*
     * Divides the calculation string into three parts based on what the operator is (from bedmas): The first part is
     * the portion from the beginning of the string to the first operation char before the first number in the second
     * part (Non inclusive). The second part is from the beginning of the number to the left of the operation to the end
     * of the number to the right of the operation. The third part is from the end of the second part till the end.
     * EX: calculation string: 3x4+5; operator: x
     * 1st part: ""
     * 2nd part: "3x4"
     * 3rd part: "+5"
     */
    private String calculateHelp(String operation, String calculation){
        int locationOfOperation;
        if (operation.equals("-") && calculation.startsWith("-")){
            locationOfOperation = calculation.substring(1).indexOf(operation) + 1;
        }else{
            locationOfOperation = calculation.indexOf(operation);
        }
        int previousOp = findSurroundingOp(calculation.substring(0,locationOfOperation),0);
        if ((calculation.charAt(previousOp) == '-' || calculation.charAt(previousOp) == '+') && previousOp != 0){
            previousOp++;
        }
        System.out.println("Previous Op: "+ calculation.charAt(previousOp));
        int nextOp = findSurroundingOp(calculation.substring(locationOfOperation+1),1)+1+locationOfOperation;
        if(nextOp - locationOfOperation == 1){
            nextOp = findSurroundingOp(calculation.substring(locationOfOperation+2),1)+2+locationOfOperation;
        }

        calculation = calculation.substring(0, previousOp)+ fmt(calculateHelp2(calculation.substring(previousOp, nextOp))) + calculation.substring(nextOp);
        return calculation;
    }
    /* Partitions the string about the operation char. The partitions are then converted into a
     * number format and the math is conducted. The numerical answer is returned
     */
    private double calculateHelp2(String partition) throws ArrayIndexOutOfBoundsException{
        System.out.println("Partition: " + partition);
        double a = 0;
        double b = 0;
        if (partition.contains("x")){
            try{
            a = Double.parseDouble(partition.split("x")[0]);
            b = Double.parseDouble(partition.split("x")[1]);
            }catch (Exception e){
                System.err.println(e.toString());
            }
            return a*b;
        }else if(partition.contains("/")){
            try {
                a = Double.parseDouble(partition.split("/")[0]);
                b = Double.parseDouble(partition.split("/")[1]);
            }catch(Exception e){
                System.err.println(e.toString());
                return 0;
            }
            return a/b;
        }else if(partition.contains("+")){
            try{
            a = Double.parseDouble(partition.split("\\+")[0]);
            b = Double.parseDouble(partition.split("\\+")[1]);
            }catch (Exception e){
                System.err.println(e.toString());
            }
            return a+b;
        }else {
            try{
                if(partition.startsWith("-")){
            a = Double.parseDouble(partition.substring(1).split("-")[0]);
            a = 0-a;
            b = Double.parseDouble(partition.substring(1).split("-")[1]);
                }else{
                    a = Double.parseDouble(partition.split("-")[0]);
                    b = Double.parseDouble(partition.split("-")[1]);
                }
        }catch (Exception e){
            System.err.println(e.toString());
        }
            return a-b;
        }
    }
    /* Loops through string array of calculation checking each character to see if it's an operation character
     * Returns True if found anywhere, false otherwise
     */
    private boolean containsOperations(String calculations){
        for (String a : arrayOfOperations){
            if (calculations.contains(a)){
            return true;
        }
        }return false;
    }
}