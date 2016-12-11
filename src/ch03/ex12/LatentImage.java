package ch03.ex12;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LatentImage extends Application{

	/**
	 * 変換元画像
	 */
	private Image in;

	/**
	 * 適用する色変換
	 */
	private List<ColorTransformer> pendingOperations;

	/**
	 * コンストラクタ
	 * @param in 変換元画像
	 */
	private LatentImage(Image in) {
		this.in = in;
		pendingOperations = new ArrayList<>();
	}

	/**
	 * コンストラクタ
	 */
	public LatentImage() {
		super();
	}

	/**
	 * 色変換を適用します（UnaryOperator<Color>）
	 * @param f 変換条件
	 * @return
	 */
	LatentImage transform(UnaryOperator<Color> f) {
		pendingOperations.add((x, y, color) -> f.apply(color));
		return this;
	}

	/**
	 * 色変換を適用します（ColorTransformer）
	 * @param f 変換条件
	 * @return
	 */
	LatentImage transform(ColorTransformer f) {
		pendingOperations.add(f);
		return this;
	}

	/**
	 * 現在の設定条件でのオブジェクトを生成します
	 * @param in 編集画像
	 * @return
	 */
	public static LatentImage from(Image in) {
		return new LatentImage(in);
	}

	/**
	 * 全ての操作を適用したイメージを取得します
	 * @return
	 */
	public Image toImage() {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				Color c = in.getPixelReader().getColor(x, y);
				for(ColorTransformer f : pendingOperations) {
					c = f.apply(x, y, c);
				}
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		return out;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		File file = new File("src/ch03/ex12/queen-mary.png");
		Image image = new Image(file.toURI().toString());
		int width = (int)image.getWidth();
		int height = (int)image.getHeight();

		Image afterImage = LatentImage
				.from(image)
				.transform(Color::brighter)
				.transform((x, y, color) -> x <= 10 || x >= width - 10 || y <= 10 || y >= height - 10 ? Color.GRAY : color)
				.toImage();
				Scene scene = new Scene(new HBox(new ImageView(image), new ImageView(afterImage)));
				primaryStage.setScene(scene);
				primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
