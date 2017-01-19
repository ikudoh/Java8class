package ch05.ex10;

import java.time.DateTimeException;
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
		departAreaBox.setValue("America/Los_Angeles");
		Label inductionMarkLabel = new Label(" ---> ");
		Label arrivalAreaLabel = new Label("到着地：");
		ComboBox<String> arrivalAreaBox = new ComboBox<>();
		arrivalAreaBox.getItems().addAll(timezones);
		arrivalAreaBox.setValue("CET");

		Label frightTimeLabel = new Label("飛行時間：");
		TextField hourField = new TextField();
		Label hLabel = new Label(" h ");
		TextField minuteField = new TextField();
		Label mLabel = new Label(" m ");

		Label departTimeLabel = new Label("出発時刻（現地時刻）：");
		TextField departureHourField = new TextField();
		Label timeSeparatorLabel = new Label(" : ");
		TextField departureMinuteField = new TextField();

		Button arrivalTimeBtn = new Button("到着時刻（現地時刻）を表示");
		Label arrivalTimeLabel = new Label(" [ ここに到着時刻（現地時刻）が表示されます ] ");

		arrivalTimeBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// 現地出発時刻
				int departHour = 0;
				int departMinute = 0;
				String departAreaStr =  departAreaBox.getSelectionModel().getSelectedItem();
				ZonedDateTime departAreaTime = ZonedDateTime.now(ZoneId.of(departAreaStr));
				// 飛行時間
				int frightHour= 0;
				int frightMinute = 0;
				try {
					// 現地出発時刻
					departHour = Integer.parseInt(departureHourField.getText());
					departMinute = Integer.parseInt(departureMinuteField.getText());
					departAreaTime = departAreaTime.withHour(departHour);
					departAreaTime = departAreaTime.withMinute(departMinute);
					// 飛行時間
					frightHour = Integer.parseInt(hourField.getText());
					frightMinute = Integer.parseInt(minuteField.getText());
				} catch(NumberFormatException | DateTimeException ex) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle( "エラー" );
					alert.setHeaderText("入力エラー");
			        alert.getDialogPane().setContentText( "飛行時間には半角数値を、出発時刻には半角数値0-23(h), 0-59(m)を指定してください。" );
			        alert.show();
			        return;
				}
				String  arrivalAreaStr = arrivalAreaBox.getSelectionModel().getSelectedItem();
				ZonedDateTime arrivalAreaTime = departAreaTime.plusMinutes(frightHour*60 + frightMinute).withZoneSameInstant(ZoneId.of(arrivalAreaStr));
				arrivalTimeLabel.setText("到着時刻（現地時刻）：" +  arrivalAreaTime.getHour() + " : " + arrivalAreaTime.getMinute());
			}
		});

		VBox layout = new VBox();
		HBox areaBox = new HBox();
		areaBox.getChildren().addAll(departAreaLabel, departAreaBox, inductionMarkLabel, arrivalAreaLabel, arrivalAreaBox);
		HBox timeBox = new HBox();
		timeBox.getChildren().addAll(frightTimeLabel, hourField, hLabel, minuteField, mLabel);
		HBox departureTimeBox = new HBox();
		departureTimeBox.getChildren().addAll(departTimeLabel, departureHourField, timeSeparatorLabel, departureMinuteField);
		VBox arrivalTimeBtnBox = new VBox();
		arrivalTimeBtnBox.getChildren().addAll(arrivalTimeBtn, arrivalTimeLabel);

		layout.getChildren().addAll(areaBox, timeBox, departureTimeBox, arrivalTimeBtnBox);
		primaryStage.setScene(new Scene(layout));
		primaryStage.setTitle("到着時間");
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
