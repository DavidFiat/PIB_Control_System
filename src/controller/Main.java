package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	private PIBController pib;

	public static void main(String[] args) {
		launch(args);
	}
	
	public Main() throws FileNotFoundException, ClassNotFoundException, IOException {
		pib = new PIBController();
//		ThreadCircleOne tc = new ThreadCircleOne(pib);
//		tc.start();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WindowMain.fxml"));

		fxmlLoader.setController(pib);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("PIB APP");
		primaryStage.show();
	}

}
