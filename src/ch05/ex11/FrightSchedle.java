package ch05.ex11;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FrightSchedle extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label departAreaLabel = new Label("出発地：");
		ComboBox<String> departAreaBox = new ComboBox<>();
		String[] timezones = TimeZone.getAvailableIDs();
		departAreaBox.getItems().addAll(timezones);
		departAreaBox.setValue("CET");
		TextField departureHourField = new TextField("14");
		Label timeSeparator1Label = new Label(" : ");
		TextField departureMinuteField = new TextField("5");

		Label arrivalAreaLabel = new Label("到着地：");
		ComboBox<String> arrivalAreaBox = new ComboBox<>();
		arrivalAreaBox.getItems().addAll(timezones);
		arrivalAreaBox.setValue("America/Los_Angeles");
		TextField arrivalHourField = new TextField("16");
		Label timeSeparator2Label = new Label(" : ");
		TextField arrivalMinuteField = new TextField("40");

		Button frightTimeBtn = new Button("飛行時間を表示");
		Label frightTimeLabel = new Label(" [ ここに飛行時間が表示されます ] ");

		frightTimeBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// 現地出発地時刻
				int departHour = 0;
				int departMinute = 0;
				int arrivalHour = 0;
				int arrivalMinute = 0;
				String departAreaStr =  departAreaBox.getSelectionModel().getSelectedItem();
				ZonedDateTime departAreaTime = ZonedDateTime.now(ZoneId.of(departAreaStr));
				String arrivalAreaStr =  arrivalAreaBox.getSelectionModel().getSelectedItem();
				ZonedDateTime arrivalAreaTime = ZonedDateTime.now(ZoneId.of(arrivalAreaStr));
				ZonedDateTime arrivalAreaTime2;
				try {
					// 現地出発時刻
					departHour = Integer.parseInt(departureHourField.getText());
					departMinute = Integer.parseInt(departureMinuteField.getText());
					departAreaTime = departAreaTime.withHour(departHour);
					departAreaTime = departAreaTime.withMinute(departMinute);
					// 現地到着時刻
					arrivalHour = Integer.parseInt(arrivalHourField.getText());
					arrivalMinute = Integer.parseInt(arrivalMinuteField.getText());
					arrivalAreaTime = departAreaTime.withHour(arrivalHour);
					arrivalAreaTime = departAreaTime.withMinute(arrivalMinute);
					arrivalAreaTime2 = arrivalAreaTime.withZoneSameInstant(ZoneId.of(departAreaStr));
				} catch(NumberFormatException | DateTimeException ex) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle( "エラー" );
					alert.setHeaderText("入力エラー");
					alert.getDialogPane().setContentText( "時刻には半角数値0-23(h), 0-59(m)を指定してください。" );
					alert.show();
					return;
				}

				// TODO 35h固定になってる、、、
				long frightSeconds = Duration.between(departAreaTime, arrivalAreaTime2).getSeconds();
				frightTimeLabel.setText("飛行時間：" + frightSeconds/60 + " h " + frightSeconds%60 + "m ");
			}
		});

		VBox layout = new VBox();
		HBox area1Box = new HBox();
		area1Box.getChildren().addAll(departAreaLabel, departAreaBox, departureHourField, timeSeparator1Label, departureMinuteField);
		HBox area2Box = new HBox();
		area2Box.getChildren().addAll(arrivalAreaLabel, arrivalAreaBox, arrivalHourField, timeSeparator2Label,  arrivalMinuteField);
		VBox arrivalTimeBtnBox = new VBox();
		arrivalTimeBtnBox.getChildren().addAll(frightTimeBtn, frightTimeLabel);

		layout.getChildren().addAll(area1Box, area2Box, arrivalTimeBtnBox);
		primaryStage.setScene(new Scene(layout));
		primaryStage.setTitle("飛行時間");
		primaryStage.show();

	}

	// すみません、不具合あります。(結果が35h固定…)
	public static void main(String[] args) {
		launch(args);
	}
}
