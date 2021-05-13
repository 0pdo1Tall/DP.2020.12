import java.io.File;
import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.home.*;
import views.screen.intro.IntroScreenHandler;

public class App extends Application {

	@FXML
	ImageView logo;
	
	/*
	 * Clean code: add variable for constant
	 */
	
	private static final int DURATION_TIME_FADEIN = 2;
	private static final int DURATION_TIME_FADEOUT = 1;
	private static final int CYCLE_COUNT = 1;
	private static final int START_VALUE_FADEIN = 0;
	private static final int STOP_VALUE_FADEIN = 1;
	private static final int START_VALUE_FADEOUT = 1;
	private static final int STOP_VALUE_FADEOUT = 0;
	
	/**
	 * Clean code: method start do many thing: initialize the scene, setup fade in effect, setup fade out effect
	 * load actual content
	 * --> separate to some methods: initialize(), setupFadeInEffect(), setupFateOutEffect(), loadContent()
	 */
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			/**
			 * 	Temporal cohesion, do c�c th�nh ph?n fadeIn, fadeOut kh�ng li�n quan, th?c hi?n theo tr�nh t? th?i gian
			 *  n�n ???c t�ch ra ph??ng th?c ri�ng ?? g?i ??n
			 */
			

			// initialize the scene
			BaseScreenHandler introScreen = initialize(primaryStage);

			// Load splash screen with fade in effect
			FadeTransition fadeIn = setupFadeInEffect(introScreen);
			
			// Data coupling, do ch? truy?n ?�ng tham s? c?n thi?t ?? ho?t ??ng

			// Finish splash with fade out effect
			FadeTransition fadeOut = setupFadeOutEffect(introScreen);	
			
			// Data coupling, do truy?n tham s? c?n thi?t ?? ho?t ??ng

			// After fade in, start fade out
			fadeIn.play();
			fadeIn.setOnFinished((e) -> {
				fadeOut.play();
			});

			// After fade out, load actual content
			fadeOut.setOnFinished((e) -> {
				try {
					loadContent(primaryStage);
					
					// Data coupling, do ch? g?i c�c ph??ng th?c v� truy?n tham s? v?a ??
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private BaseScreenHandler initialize(Stage primaryStage) throws IOException {
		BaseScreenHandler introScreen = new IntroScreenHandler(primaryStage, ViewsConfig.INTRO_SCREEN_PATH);
		introScreen.show();
		return introScreen;
	}
	
	private FadeTransition setupFadeInEffect(BaseScreenHandler introScreen) {
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(DURATION_TIME_FADEIN), introScreen.getContent());
		fadeIn.setFromValue(START_VALUE_FADEIN);
		fadeIn.setToValue(STOP_VALUE_FADEIN);
		fadeIn.setCycleCount(CYCLE_COUNT);
		return fadeIn;
	}
	
	private FadeTransition setupFadeOutEffect(BaseScreenHandler introScreen) {
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(DURATION_TIME_FADEOUT), introScreen.getContent());
		fadeOut.setFromValue(START_VALUE_FADEOUT);
		fadeOut.setToValue(STOP_VALUE_FADEOUT);
		fadeOut.setCycleCount(CYCLE_COUNT);	
		return fadeOut;
	}
	
	private void loadContent(Stage primaryStage) throws IOException {
		HomeScreenHandler homeHandler = new HomeScreenHandler(primaryStage, ViewsConfig.HOME_PATH);
		homeHandler.setScreenTitle("Home Screen");
		homeHandler.setImage();
		homeHandler.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
