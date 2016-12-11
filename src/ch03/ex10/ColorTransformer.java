package ch03.ex10;

import javafx.scene.paint.Color;

interface ColorTransformer {
	Color apply(int x, int y, Color color);
}
