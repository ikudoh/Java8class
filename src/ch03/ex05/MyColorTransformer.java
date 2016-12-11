package ch03.ex05;
import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MyColorTransformer extends Application {

	public static  Image transform(Image in, ColorTransformer f) {
		int width = (int)in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				out.getPixelWriter().setColor(x, y,
						f.apply(x, y, in.getPixelReader().getColor(x, y)));
			}
		}
		return out;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		File file = new File("src/ch03/ex05/queen-mary.png");
		Image image = new Image(file.toURI().toString());
		int width = (int)image.getWidth();
		int height = (int)image.getHeight();
		Image imageAfter = transform(image,
				(x, y, color) ->
					x <= 10 || x >= width - 10 || y <= 10 || y >= height - 10 ? Color.GRAY : color
					);
		Scene scene = new Scene(new HBox(new ImageView(image), new ImageView(imageAfter)));
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}