package ch04.ex03;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Greeting {

//	private StringProperty text = new SimpleStringProperty("");
	private StringProperty textProperty = null;
	private String textString = "";


	// xxxPropertyメソッドが最初に呼び出されたときにプロパティを構築
	public final StringProperty textProperty() {
		if(textProperty == null) {
			textProperty = new SimpleStringProperty(textString);
		}
		return textProperty;
	}

	// デフォルトではない値に設定されたときにプロパティを構築
	public final void setText(String newValue) {
		if(!textString.equals(newValue)) {
			textProperty = new SimpleStringProperty(newValue);
		}
	}

	public final String getText() {
		return textProperty != null ? textProperty.get() : textString;
	}
}
