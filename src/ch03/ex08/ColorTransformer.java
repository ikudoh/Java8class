package ch03.ex08;

import javafx.scene.paint.Color;

interface ColorTransformer {
	Color apply(int x, int y, Color color);
}
