package views.screen;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

import controller.AuthenticationController;
import controller.BaseController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;
import views.screen.Notification;



public abstract class BaseScreenHandler extends FXMLScreenHandler {

	private static final Logger LOGGER = Utils.getLogger(BaseScreenHandler.class.getName());
	private Notification notification = new PopupNotification();

	private Scene scene;
	private BaseScreenHandler prev;
	protected final Stage stage;
	protected HomeScreenHandler homeScreenHandler;
	protected Hashtable<String, String> messages;
	private BaseController bController;

	protected BaseScreenHandler(Stage stage, String screenPath) throws IOException {
		super(screenPath);
		this.stage = stage;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public Notification getNotification(){return notification;}
	
	/**
	 * Design Pattern: Template Method
	 */
	public void setupScreen(Object object) throws IOException {
		try {
            setupData(object);
            setupFunctionality();
        } catch (IOException ex) {
            LOGGER.info(ex.getMessage());
//            PopupScreen.error("Error when loading resources.");
            notification.showError("Error when loading resources.");
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage());
//            PopupScreen.error(ex.getMessage());
			notification.showError("Error when loading resources.");
        }
	}
	
	abstract protected void setupData(Object object) throws Exception;
	abstract protected void setupFunctionality() throws Exception;
	/*
	 * 
	 */
	
	public void setPreviousScreen(BaseScreenHandler prev) {
		this.prev = prev;
	}

	public BaseScreenHandler getPreviousScreen() {
		return this.prev;
	}

	public void show() {
		if (this.scene == null) {
			this.scene = new Scene(this.content);
		}
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	public void setScreenTitle(String string) {
		this.stage.setTitle(string);
	}

	public void setBController(BaseController bController){
		this.bController = bController;
	}

	public BaseController getBController(){
		return this.bController;
	}

	public void forward(Hashtable messages) {
		this.messages = messages;
	}

	public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
		this.homeScreenHandler = HomeScreenHandler;
	}

}
