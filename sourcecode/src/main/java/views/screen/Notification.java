package views.screen;

import views.screen.BaseScreenHandler;

import java.io.IOException;

public interface Notification {
    void showError(String message) throws IOException;
    void showSuccess(String message) throws IOException;
    BaseScreenHandler showLoading(String message) throws IOException;
    void close();
}
