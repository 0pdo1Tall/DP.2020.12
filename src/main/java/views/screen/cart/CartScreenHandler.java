package views.screen.cart;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import common.exception.MediaNotAvailableException;
import common.exception.PlaceOrderException;
import controller.PlaceOrderController;
import controller.ViewCartController;
import entity.cart.CartItem;
import entity.order.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.popup.PopupScreen;
import views.screen.shipping.ShippingScreenHandler;


// LOGICAL COHESION
public class CartScreenHandler extends BaseScreenHandler {
	private static Logger LOGGER = Utils.getLogger(CartScreenHandler.class.getName());

	@FXML
	private ImageView aimsImage;

	@FXML
	private Label pageTitle;

	@FXML
	VBox vboxCart;

	@FXML
	private Label shippingFees;

	@FXML
	private Label labelAmount;

	@FXML
	private Label labelSubtotal;

	@FXML
	private Label labelVAT;

	@FXML
	private Button btnPlaceOrder;


	public CartScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		try {
			setupFunctionality();
		} catch (IOException ex) {
			LOGGER.info(ex.getMessage());
			PopupScreen.error("Error when loading resources.");
		} catch (Exception ex) {
			LOGGER.info(ex.getMessage());
			PopupScreen.error(ex.getMessage());
		}
	}

	protected void setupFunctionality() throws Exception {
		// fix relative image path caused by fxml
		File file = new File(ViewsConfig.IMAGE_PATH + "/Logo.png");
		Image im = new Image(file.toURI().toString());
		aimsImage.setImage(im);

		// on mouse clicked, we back to home
		aimsImage.setOnMouseClicked(e -> {
			homeScreenHandler.show();
		});

		// on mouse clicked, we start processing place order use case
		btnPlaceOrder.setOnMouseClicked(e -> {
			LOGGER.info("Place Order button clicked");
			try {
				requestToPlaceOrder();
			} catch (SQLException | IOException exp) {
				LOGGER.severe("Cannot place the order, see the logs");
				exp.printStackTrace();
				throw new PlaceOrderException(Arrays.toString(exp.getStackTrace()).replaceAll(", ", "\n"));
			}

		});
	}

	public ViewCartController getBController(){
		return (ViewCartController) super.getBController();
	}

	public void requestToViewCart(BaseScreenHandler prevScreen) throws SQLException {
		setPreviousScreen(prevScreen);
		setScreenTitle("Cart Screen");
		getBController().checkAvailabilityOfProduct();
		displayCartWithMediaAvailability();
		show();
	}

	public void requestToPlaceOrder() throws SQLException, IOException {
		try {
			// create placeOrderController and process the order
			PlaceOrderController placeOrderController = new PlaceOrderController();
			if (placeOrderController.getListCartMedia().size() == 0){
				PopupScreen.error("You don't have anything to place");
				return;
			}

			placeOrderController.placeOrder();
			
			// display available media
			displayCartWithMediaAvailability();

			// create order
			Order order = placeOrderController.createOrder();

			// display shipping form
			ShippingScreenHandler shippingScreenHandler = new ShippingScreenHandler(
					this.stage, ViewsConfig.SHIPPING_SCREEN_PATH, order);
			shippingScreenHandler.setPreviousScreen(this);
			shippingScreenHandler.setHomeScreenHandler(homeScreenHandler);
			shippingScreenHandler.setScreenTitle("Shipping Screen");
			shippingScreenHandler.setBController(placeOrderController);
			shippingScreenHandler.show();

		} catch (MediaNotAvailableException e) {
			// if some media are not available then display cart and break usecase Place Order
			displayCartWithMediaAvailability();
		}
	}

	public void updateCart() throws SQLException{
		getBController().checkAvailabilityOfProduct();
		displayCartWithMediaAvailability();
	}

	void updateCartAmount(){
		// calculate subtotal and amount
		int subtotal = getBController().getCartSubtotal();
		int vat = (int)((ViewsConfig.PERCENT_VAT/100)*subtotal);
		int amount = subtotal + vat;
		LOGGER.info("amount" + amount);

		// update subtotal and amount of Cart
		labelSubtotal.setText(ViewsConfig.getCurrencyFormat(subtotal));
		labelVAT.setText(ViewsConfig.getCurrencyFormat(vat));
		labelAmount.setText(ViewsConfig.getCurrencyFormat(amount));
	}
	
	private void displayCartWithMediaAvailability(){
		// clear all old cartMedia
		vboxCart.getChildren().clear();

		// get list media of cart after check availability
		List lstMedia = getBController().getListCartMedia();

		try {
			for (Object cm : lstMedia) {

				// display the attribute of vboxCart media
				CartItem cartItem = (CartItem) cm;
				MediaHandler mediaCartScreen = new MediaHandler(ViewsConfig.CART_MEDIA_PATH, this);
				mediaCartScreen.setCartItem(cartItem);

				// add spinner
				vboxCart.getChildren().add(mediaCartScreen.getContent());
			}
			// calculate subtotal and amount
			updateCartAmount();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
