package gui;

import javax.crypto.BadPaddingException;

import aes.AES;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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

    String initVector = "EzhC276X3qrZ9LhJ"; // 16 bytes IV

    public boolean checkKey(String keyField) {
    	int len = keyField.length();
    	if (len != 16) {
    		return false;
		}
    	return true;
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		Label dataLabel = new Label("Data :");
		TextArea dataArea = new TextArea();
		Label keyLabel = new Label("Key :");
		TextField keyField = new TextField();
		Button encryptButton = new Button("Encrypt");
		Button decryptButton = new Button("Decrypt");
		decryptButton.setDisable(true);

		Label errorLabel = new Label();
		
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(encryptButton, decryptButton);

		VBox verticalBox = new VBox(10);
		verticalBox.setPrefSize(400, 400);
		verticalBox.setPadding(new Insets(10, 10, 10, 10));
		verticalBox.getChildren().addAll(dataLabel, dataArea, keyLabel, keyField, buttonBox, errorLabel);

		Scene scene = new Scene(verticalBox);
		
		scene.setFill(Color.GRAY);
		
		encryptButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				String data = dataArea.getText();
				String key = keyField.getText();
				if (data.isEmpty()) {
					errorLabel.setText("No input provided for data");
				} else {
					if (checkKey(key)) {
						String encryptedData = AES.encrypt(key, initVector, data);
						dataArea.setText(encryptedData);
						errorLabel.setText("");
						decryptButton.setDisable(false);
						encryptButton.setDisable(true);
						keyField.setDisable(true);
					} else {
						errorLabel.setText("Key needs to be 16 bits long");
					}
				}
			}
		});
		
		decryptButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e){
				String encrypted = dataArea.getText();
				String key = keyField.getText();
				if (checkKey(key)) {
					String decryptedData = AES.decrypt(key, initVector, encrypted);
					dataArea.setText(decryptedData);
					errorLabel.setText("");
					decryptButton.setDisable(true);
					encryptButton.setDisable(false);
					keyField.setDisable(false);
				} else {
					errorLabel.setText("Key needs to be 16 bits long");
				}
			}
		});

		primaryStage.setTitle("AES encryptor and decryptor");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
