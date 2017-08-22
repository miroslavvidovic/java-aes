package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GUI extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Label dataLabel = new Label("Data :");
		TextArea dataArea = new TextArea();
		Label keyLabel = new Label("Key :");
		TextField keyField = new TextField();
		Button encryptButton = new Button("Encrypt");
		Button decryptButton = new Button("Decrypt");
		
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(encryptButton, decryptButton);

		VBox verticalBox = new VBox(10);
		verticalBox.setPrefSize(400, 400);
		verticalBox.getChildren().addAll(dataLabel, dataArea, keyLabel, keyField, buttonBox);

		Scene scene = new Scene(verticalBox);
		
		scene.setFill(Color.GRAY);
		
		primaryStage.setTitle("AES encryptor and decryptor");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
