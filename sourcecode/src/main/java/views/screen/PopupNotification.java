package views.screen;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import views.screen.Notification;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.util.Base64;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;

public class PopupNotification implements Notification{
    @FXML
    ImageView icon;

    @FXML
    Label message;

    private static PopupScreen popup(String message, String imagePath, Boolean undecorated) throws IOException{

        PopupScreen popup = new PopupScreen(new Stage());
        if (undecorated) popup.stage.initStyle(StageStyle.UNDECORATED);
        popup.message.setText(message);
        popup.setImage(imagePath);
        return popup;
    }

    @Override
    public void showError(String message) throws IOException {
        popup(message, ViewsConfig.IMAGE_PATH + "/" + "tickgreen.png", true)
                .show(true);
    }

    @Override
    public void showSuccess(String message) throws IOException {
        popup(message, ViewsConfig.IMAGE_PATH + "/" + "tickgreen.png", true)
                .show(true);
    }

    @Override
    public BaseScreenHandler showLoading(String message) throws IOException {
        return popup(message, ViewsConfig.IMAGE_PATH + "/" + "loading.gif", true);
    }

    @Override
    public void close() {

    }
}
