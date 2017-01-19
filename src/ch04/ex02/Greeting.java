package ch04.ex02;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Greeting {

//	private StringProperty text = new SimpleStringProperty("");
	private StringProperty textProperty = null;
	private String textString = "";


	public final StringProperty textProperty() {
		if(textProperty == null) {
			textProperty = new SimpleStringProperty(textString);
		}
		return textProperty;
	}

	public final void setText(String newValue) {
		if(textProperty != null) {
			textProperty.set(newValue);
		} else {
			textString = newValue;
		}
	}

	public final String getText() {
		return textProperty != null ? textProperty.get() : textString;
	}
}
