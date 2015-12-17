package sample.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import sample.util.Message;

public class OrderInfoForm {
	@Pattern(regexp="\\d{3}", message="3桁の数字を入力して下さい。")
	@NotEmpty(message = Message.ERROR_EMPTY)
    private String postalCode1;
 
	@Pattern(regexp="\\d{4}", message="4桁の数字を入力して下さい。")
	@NotEmpty(message = Message.ERROR_EMPTY)
    private String postalCode2;
	
	@NotEmpty(message = Message.ERROR_EMPTY)
    private String address;

	@NotEmpty(message = Message.ERROR_SELECT)
    private String payment;

	@NotEmpty(message = Message.ERROR_SELECT)
    private String delivery;

	// 以下、全てアクセッサーメソッド
	
    public String getPostalCode1() {
        return postalCode1;
    }

    public void setPostalCode1(String postalCode1) {
        this.postalCode1 = postalCode1;
    }

    public String getPostalCode2() {
        return postalCode2;
    }

    public void setPostalCode2(String postalCode2) {
        this.postalCode2 = postalCode2;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery == null ? null : delivery.trim();
    }

}
