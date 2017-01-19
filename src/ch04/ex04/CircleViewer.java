package ch04.ex04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleViewer extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Circle circle = new Circle(100);
		Pane layout = new Pane();
		layout.getChildren().add(circle);
		Scene scene = new Scene(layout);
		circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2));
		circle.centerYProperty().bind(Bindings.divide(scene.heightProperty(), 2));
		primaryStage.setScene(scene);
		primaryStage.setTitle("Circle");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
