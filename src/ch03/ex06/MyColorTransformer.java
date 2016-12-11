package ch03.ex06;
import java.io.File;
import java.util.function.BiFunction;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MyColorTransformer extends Application {

	public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg) {
		int width = (int)in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				out.getPixelWriter().setColor(x, y,
						f.apply(in.getPixelReader().getColor(x, y), arg));
			}
		}
		return out;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		File file = new File("src/ch03/ex06/queen-mary.png");
		Image image = new Image(file.toURI().toString());
		int width = (int)image.getWidth();
		int height = (int)image.getHeight();
		Image imageAfter = transform(image,
				(c, factor) -> c.deriveColor(0.0, 1.0, factor, 1.0),
				2
				);

		Scene scene = new Scene(new HBox(new ImageView(image), new ImageView(imageAfter)));
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}