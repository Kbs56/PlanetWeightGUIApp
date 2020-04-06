package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.text.DecimalFormat;

public class Main extends Application {

    Stage window;
    Scene scene;
    Label titleLabel;
    Label planetLabel;
    Label resultLabel;
    Button clearButton;
    Button calculateButton;
    TextField weightInput;
    ChoiceBox<String> choiceBox;

    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    public void start(Stage primaryStage) {
       window = primaryStage;
       window.setTitle("Planet Weight Calculator");

       titleLabel = new Label("Enter your weight on Earth in lbs.");
       titleLabel.requestFocus();

        weightInput = new TextField();
        weightInput.setPromptText("Ex. 198.84");

        clearButton = new Button("Clear Text Field");
        clearButton.setOnAction(event -> {
            weightInput.clear();
            weightInput.requestFocus();
        });

        calculateButton = new Button("Calculate Weight");
        calculateButton.setOnAction(event -> calcWeight(weightInput, weightInput.getText()));

        planetLabel = new Label("Please select a planet...");
        planetLabel.setPadding(new Insets(0, 20, 5, 20));

        choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune");
        choiceBox.setValue("Mercury");

        resultLabel = new Label("Your result will be here...");
        resultLabel.setPadding(new Insets(5, 5, 0,5));

        HBox optionButtons = new HBox(10);
        optionButtons.setPadding(new Insets(10, 20, 10, 20));
        optionButtons.getChildren().addAll(createSpacer(), clearButton, createSpacer(), calculateButton, createSpacer());

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(titleLabel, weightInput, resultLabel, optionButtons, planetLabel, choiceBox);

        scene = new Scene(layout, 400, 250);
        window.setScene(scene);
        window.show();
    }

    public void calcWeight(TextField input, String message) {
        // weight on different planets is calculated by weight * the planets surface gravity (SG)
        final double MARS_MERCURY_SG = 0.38;
        final double VENUS_SG = 0.91;
        final double JUPITER_SG = 2.34;
        final double SATURN_SG = 0.93;
        final double URANUS_SG = 0.92;
        final double NEPTUNE_SG = 1.12;
        try {
            double weight = Double.parseDouble(input.getText());
            switch (choiceBox.getValue()) {
                case "Mercury":
                case "Mars":
                    resultLabel.setText("Your weight on " + choiceBox.getValue() + " is " + df.format(weight * MARS_MERCURY_SG) + " pounds");
                    break;
                case "Venus":
                    resultLabel.setText("Your weight on " + choiceBox.getValue() + " is " + df.format(weight * VENUS_SG) + " pounds");
                    break;
                case "Earth":
                    resultLabel.setText("Your weight on " + choiceBox.getValue() + " is " + df.format(weight) + " pounds");
                    break;
                case "Jupiter":
                    resultLabel.setText("Your weight on " + choiceBox.getValue() + " is " + df.format(weight * JUPITER_SG) + " pounds");
                    break;
                case "Saturn":
                    resultLabel.setText("Your weight on " + choiceBox.getValue() + " is " + df.format(weight * SATURN_SG) + " pounds");
                    break;
                case "Uranus":
                    resultLabel.setText("Your weight on " + choiceBox.getValue() + " is " + df.format(weight * URANUS_SG) + " pounds");
                    break;
                case "Neptune":
                    resultLabel.setText("Your weight on " + choiceBox.getValue() + " is " + df.format(weight * NEPTUNE_SG) + " pounds");
                    break;
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter a valid weight, " + "\"" + message.strip() + "\"" + " is not a valid weight");
        }
    }

    // Function to create even space in HBox I found on stackoverflow
    private Node createSpacer() {
        final Region spacer = new Region();
        // Make it always grow or shrink according to the available space
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
