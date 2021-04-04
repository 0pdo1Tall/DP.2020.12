import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

	@Override
	public void start(Stage primaryStage) {
		try {
			
			/**
			 * 	Temporal cohesion, do c�c th�nh ph?n fadeIn, fadeOut kh�ng li�n quan, th?c hi?n theo tr�nh t? th?i gian
			 *  n�n ???c t�ch ra ph??ng th?c ri�ng ?? g?i ??n
			 */
			

			// initialize the scene
			BaseScreenHandler introScreen = new IntroScreenHandler(primaryStage, ViewsConfig.INTRO_SCREEN_PATH);
			introScreen.show();

			// Load splash screen with fade in effect
			FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), introScreen.getContent());
			fadeIn.setFromValue(0);
			fadeIn.setToValue(1);
			fadeIn.setCycleCount(1);		
			
			// Data coupling, do ch? truy?n ?�ng tham s? c?n thi?t ?? ho?t ??ng

			// Finish splash with fade out effect
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), introScreen.getContent());
			fadeOut.setFromValue(1);
			fadeOut.setToValue(0);
			fadeOut.setCycleCount(1);		
			
			// Data coupling, do truy?n tham s? c?n thi?t ?? ho?t ??ng

			// After fade in, start fade out
			fadeIn.play();
			fadeIn.setOnFinished((e) -> {
				fadeOut.play();
			});

			// After fade out, load actual content
			fadeOut.setOnFinished((e) -> {
				try {
					HomeScreenHandler homeHandler = new HomeScreenHandler(primaryStage, ViewsConfig.HOME_PATH);
					homeHandler.setScreenTitle("Home Screen");
					homeHandler.setImage();
					homeHandler.show();
					
					// Data coupling, do ch? g?i c�c ph??ng th?c v� truy?n tham s? v?a ??
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
