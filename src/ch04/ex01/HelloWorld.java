package ch04.ex01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloWorld extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		Label message = new Label("Hello, JavaFX!");
		message.setFont(new Font(100));

		TextField field = new TextField(message.getText());
		field.textProperty().addListener(
				property -> message.setText(field.getText())
				);

		VBox layout = new VBox();	// VBox…垂直列に子をレイアウトする
		layout.getChildren().addAll(field, message);
		stage.setScene(new Scene(layout));
		stage.setTitle("Hello");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
