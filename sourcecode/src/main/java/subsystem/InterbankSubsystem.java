package subsystem;

import entity.payment.PaymentMethod;
import entity.payment.PaymentMethodFactory;
import entity.payment.PaymentTransaction;
import subsystem.interbank.InterbankSubsystemController;

/***
 * The {@code InterbankSubsystem} class is used to communicate with the
 * Interbank to make transaction.
 * 
 * @author hieud
 *
 */
public class InterbankSubsystem implements InterbankInterface {

	/**
	 * Represent the controller of the subsystem
	 */
	/*
	 * Clean code. sửa ctrl => interbankSubsystemController
	 */
	private final InterbankSubsystemController interbankSubsystemController;

	/**
	 * Initializes a newly created {@code InterbankSubsystem} object so that it
	 * represents an Interbank subsystem.
	 */
	public InterbankSubsystem() {
		this.interbankSubsystemController = new InterbankSubsystemController();
	}

	
	/**
	 * Clean code: remove dependancy from concrete class (CreditCard)
	 * and change dependancy to superclass (PaymentMethod)
	 * is a abstract class
	 */
	
	/**
	 * @see InterbankInterface#payOrder(CreditCard, int,
	 *      String)
	 */
	// Data Coupling
	// SOLID: DIP do phu thuoc vao Credit Card
	// SOLID: OCP do phai modify khi thay doi phuong thuc thuc thanh toan
	public PaymentTransaction payOrder(PaymentMethod card, int amount, String contents, PaymentMethodFactory paymentMethodFactory) {
		PaymentTransaction transaction = interbankSubsystemController.payOrder(card, amount, contents, paymentMethodFactory);
		return transaction;
	}

	/**
	 * @see InterbankInterface#refund(CreditCard, int,
	 *      String)
	 */
	// Data Coupling
	public PaymentTransaction refund(PaymentMethod card, int amount, String contents) {
		PaymentTransaction transaction = interbankSubsystemController.refund(card, amount, contents);
		return transaction;
	}
}
