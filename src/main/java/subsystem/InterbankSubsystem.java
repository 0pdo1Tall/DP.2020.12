package subsystem;

import entity.payment.CreditCard;
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
	private InterbankSubsystemController interbankSubsystemController;

	/**
	 * Initializes a newly created {@code InterbankSubsystem} object so that it
	 * represents an Interbank subsystem.
	 */
	public InterbankSubsystem() {
		this.interbankSubsystemController = new InterbankSubsystemController();
	}

	/**
	 * @see InterbankInterface#payOrder(CreditCard, int,
	 *      String)
	 */
	// Data Coupling
	// SOLID: DIP do phu thuoc vao Credit Card
	// SOLID: OCP do phai modify khi thay doi phuong thuc thuc thanh toan
	public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
		PaymentTransaction transaction = interbankSubsystemController.payOrder(card, amount, contents);
		return transaction;
	}

	/**
	 * @see InterbankInterface#refund(CreditCard, int,
	 *      String)
	 */
	// Data Coupling
	public PaymentTransaction refund(CreditCard card, int amount, String contents) {
		PaymentTransaction transaction = interbankSubsystemController.refund(card, amount, contents);
		return transaction;
	}
}
