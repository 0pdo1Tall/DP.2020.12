package views.screen;

import views.screen.BaseScreenHandler;

import java.io.IOException;

public interface Notification {
    public abstract void showError(String message) throws IOException;
    public abstract void showSuccess(String message) throws IOException;
    public abstract BaseScreenHandler showLoading(String message) throws IOException;
    public abstract void close();
}
