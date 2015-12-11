package sample.form;

import org.hibernate.validator.constraints.NotEmpty;

public class OrderInfoForm {
	@NotEmpty(message = Message.ERROR_EMPTY)
    private Integer postalCode1;
    
	@NotEmpty(message = Message.ERROR_EMPTY)
    private Integer postalCode2;
	
	@NotEmpty(message = Message.ERROR_EMPTY)
    private String address;

	@NotEmpty(message = Message.ERROR_SELECT)
    private String payment;

	@NotEmpty(message = Message.ERROR_SELECT)
    private String delivery;

	// 以下、全てアクセッサーメソッド
	
    public Integer getPostalCode1() {
        return postalCode1;
    }

    public void setPostalCode1(Integer postalCode1) {
        this.postalCode1 = postalCode1;
    }

    public Integer getPostalCode2() {
        return postalCode2;
    }

    public void setPostalCode2(Integer postalCode2) {
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
