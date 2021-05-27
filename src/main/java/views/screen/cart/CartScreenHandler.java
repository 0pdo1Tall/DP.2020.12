package views.screen.cart;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import common.exception.MediaNotAvailableException;
import common.exception.PlaceOrderException;
import common.interfaces.Observable;
import common.interfaces.Observer;
import controller.BaseController;
import controller.PlaceOrderController;
import controller.ViewCartController;
import entity.cart.Cart;
import entity.cart.CartItem;
import entity.media.Media;
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
import views.screen.cart.MediaHandler;
import views.screen.popup.PopupScreen;
import views.screen.shipping.ShippingScreenHandler;


// SRP class hien thi, update san pham trong cart

public class CartScreenHandler extends BaseScreenHandler implements Observer {
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
		setupScreen(null);
	}

	protected void setupData(Object object) throws Exception{}
	
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
	
	public void displayShippingForm(Order order, BaseController placeOrderController) throws IOException {
	
		ShippingScreenHandler shippingScreenHandler = new ShippingScreenHandler(
					this.stage, ViewsConfig.SHIPPING_SCREEN_PATH, order);
		shippingScreenHandler.setPreviousScreen(this);
		shippingScreenHandler.setHomeScreenHandler(homeScreenHandler);
		shippingScreenHandler.setScreenTitle("Shipping Screen");
		shippingScreenHandler.setBController(placeOrderController);
		shippingScreenHandler.show();
	}

	// clean method: should split several method.
	public void requestToPlaceOrder() throws SQLException, IOException {
		try {
			// create placeOrderController and process the order
			PlaceOrderController placeOrderController = PlaceOrderController.getInstance();
			if (placeOrderController.getListCartMedia().size() == 0){
//				PopupScreen.error("You don't have anything to place");
				getNotification().showError("You don't have anything to place");
				return;
			}
			
			// Clean code: Update method names after refactor
			placeOrderController.checkAvailabilityOfProduct();
			
			// display available media
			displayCartWithMediaAvailability();

			// create order
			Order order = placeOrderController.createOrder();

			// display shipping form
			/*ShippingScreenHandler shippingScreenHandler = new ShippingScreenHandler(
					this.stage, ViewsConfig.SHIPPING_SCREEN_PATH, order);
			shippingScreenHandler.setPreviousScreen(this);
			shippingScreenHandler.setHomeScreenHandler(homeScreenHandler);
			shippingScreenHandler.setScreenTitle("Shipping Screen");
			shippingScreenHandler.setBController(placeOrderController);
			shippingScreenHandler.show();*/
			
			displayShippingForm(order, placeOrderController);

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
	
	/*
	 * Clean Code: rename Object cm --> Object object
	 */
	//Sequential Cohesion vi su dung du lieu dau ra cua getBController();
	private void displayCartWithMediaAvailability(){
		// clear all old cartMedia
		vboxCart.getChildren().clear();

		// get list media of cart after check availability
		List lstMedia = getBController().getListCartMedia();

		try {
			for (Object object : lstMedia) {

				// display the attribute of vboxCart media
				CartItem cartItem = (CartItem) object;
				MediaHandler mediaCartScreen = new MediaHandler(ViewsConfig.CART_MEDIA_PATH, this);
				mediaCartScreen.attach(this);
				mediaCartScreen.setCartItem(cartItem);
				mediaCartScreen.attach(this);

				// add spinner
				vboxCart.getChildren().add(mediaCartScreen.getContent());
			}
			// calculate subtotal and amount
			updateCartAmount();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable observable) {
		if (observable instanceof MediaHandler) update((MediaHandler) observable);
	}
	
	private void update(MediaHandler mediaHandler) {
		int requestQuantity = mediaHandler.getRequestQuantity();
        Media media = mediaHandler.getCartItem().getMedia();

        try {
        	int remainQuantity = media.getQuantity();
        	LOGGER.info("NumOfProd: " + requestQuantity + " -- remainOfProd: " + remainQuantity);
            if (requestQuantity > remainQuantity) {
            	LOGGER.info("product " + media.getTitle() + " only remains " + remainQuantity + " (required " + requestQuantity + ")");
				mediaHandler.labelOutOfStock.setText("Sorry, Only " + remainQuantity + " remain in stock");
				mediaHandler.setSpinnerQuantity(remainQuantity);
				requestQuantity = remainQuantity;
            	throw new MediaNotAvailableException();
            } else if (requestQuantity == 0) {
            	LOGGER.info("Deleted " + mediaHandler.getCartItem().getMedia().getTitle() + " from the cart");
            	Cart.getCart().removeCartMedia(mediaHandler.getCartItem());
            	this.updateCart();
            	getNotification().showSuccess("The media " + media.getTitle() + " had been remove from cart");
            	return;
            }
            Cart cart = Cart.getCart();
            // if media already in cart then we will increase the quantity by 1 instead of create the new cartMedia
            CartItem mediaInCart = getBController().checkMediaInCart(media);
            if (mediaInCart != null) {
                mediaInCart.setQuantity(requestQuantity);
            } else {
                CartItem cartItem = new CartItem(media, cart, requestQuantity, media.getPrice());
                cart.addCartMedia(cartItem);
                LOGGER.info("Added " + cartItem.getQuantity() + " " + media.getTitle() + " to cart");
            }

            // subtract the quantity and redisplay
            media.setQuantity(media.getQuantity() - requestQuantity);
            
            updateCartAmount();
            //PopupScreen.success("The media " + media.getTitle() + " added to Cart");
            getNotification().showSuccess("The media " + media.getTitle() + " had been changed quantity");
        } catch (MediaNotAvailableException exp) {
            try {
                String message = "Not enough media:\nRequired: " + requestQuantity + "\nAvail: " + media.getQuantity();
                LOGGER.severe(message);
                getNotification().showError("The media " + media.getTitle() + " not available");
            } catch (Exception e) {
                LOGGER.severe("Cannot add media to cart: ");
            }

        } catch (Exception exp) {
            LOGGER.severe("Cannot add media to cart: ");
            exp.printStackTrace();
        }
	}
}
//Sequential Cohesion vi 1 so method su dung du lieu dau ra cua getBController();
