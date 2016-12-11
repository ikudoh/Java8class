package ch03.ex11;
import java.io.File;
import java.util.function.UnaryOperator;

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

	/**
	 * ２つのColorTransformerを合成する関数を取得します
	 * @param ct 合成元ColorTransformer
	 * @return 合成後ColorTransformer生成関数
	 */
	public static ColorTransformer compound(ColorTransformer ct) {
		return (x, y, color) -> ct.apply(x, y, ct.apply(x, y, color) );
	}

	/**
	 * 座標を無視するColorTransformer生成関数を取得します
	 * @param ct ColorTransformer
	 * @return 座標を無視したColorTransformer関数
	 */
	public static ColorTransformer ignoreCoordinate(UnaryOperator<Color> ct) {
		return (x, y, color) -> ct.apply(color);
	}

	/**
	 * 画像に枠を描画するための関数を取得します
	 * @param frameWidth 枠線の幅
	 * @param frameColor 枠線の色
	 * @param imageWidth 画像の幅
	 * @param imageHeight 画像の高さ
	 * @return
	 */
	public static ColorTransformer colorTransformer(int frameWidth, Color frameColor,
			int imageWidth, int imageHeight) {
		return (x, y, color) ->
		x <= frameWidth || x >= imageWidth - frameWidth
		|| y <= frameWidth || y >= imageHeight - frameWidth
		? frameColor : color;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		File file = new File("src/ch03/ex08/queen-mary.png");
		Image image = new Image(file.toURI().toString());
		int width = (int)image.getWidth();
		int height = (int)image.getHeight();
		Image brighterImage = transform(image, MyColorTransformer.ignoreCoordinate(Color::brighter));
		Image afterImage = transform(brighterImage, MyColorTransformer.compound((x, y, color) ->
				x <= 10 || x >= width - 10 || y <= 10 || y >= height - 10 ? Color.GRAY : color));
		Scene scene = new Scene(new HBox(new ImageView(image), new ImageView(afterImage)));
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}