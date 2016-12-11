package ch03.ex10;
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

	@Override
	public void start(Stage primaryStage) throws Exception {
		File file = new File("src/ch03/ex10/queen-mary.png");
		Image image = new Image(file.toURI().toString());
		int width = (int)image.getWidth();
		int height = (int)image.getHeight();

//		Q. 次の呼び出しができないのはなぜ？
//		UnaryOperator<Color> op = Color::brighter;
//		Image finalImage = transform(image, op.compose(Color::grayscale));

//		A. 型が異なるから。
//			compose()は引数にFunction<V, R>を取る。
//			transform()は第２引数にColorTransformerを取る。

//		Q. ストラクチャル型とノミナル型のユーティリティに関して何が言える？
//		A.	ストラクチャル型…構造が一致していれば使えるので柔軟性がある。
//			ノミナル型…構造、名前が一致していないと同じとみなされないため柔軟性が無い。
//						同じ型を使っているということが分かりやすい。






		Image imageAfter = transform(image,
				MyColorTransformer.colorTransformer(30, Color.BROWN, width, height)
				);
		Scene scene = new Scene(new HBox(new ImageView(image), new ImageView(imageAfter)));
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

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

}