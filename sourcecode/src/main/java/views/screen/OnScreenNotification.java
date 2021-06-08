package views.screen;

import java.io.IOException;

public class OnScreenNotification implements Notification{
    @Override
    public void showError(String message) throws IOException {

    }

    @Override
    public void showSuccess(String message) throws IOException {

    }

    @Override
    public BaseScreenHandler showLoading(String message) throws IOException {
        return null;
    }

    @Override
    public void close() {

    }
}
