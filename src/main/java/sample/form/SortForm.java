package sample.form;

public class SortForm {
    private String buying_date;

    private String payment;

    private String delivery;

    private String item_name;
    
    private boolean isAsc;

    /**
     * @return isAsc
     */
    public boolean isAsc() {
        return isAsc;
    }

    /**
     * @param isAsc セットする isAsc
     */
    public void setAsc(boolean isAsc) {
        this.isAsc = isAsc;
    }

    /**
     * @return buying_date
     */
    public String getBuying_date() {
        return buying_date;
    }

    /**
     * @param buying_date
     *            セットする buying_date
     */
    public void setBuying_date(String buying_date) {
        this.buying_date = buying_date;
    }

    /**
     * @return payment
     */
    public String getPayment() {
        return payment;
    }

    /**
     * @param payment
     *            セットする payment
     */
    public void setPayment(String payment) {
        this.payment = payment;
    }

    /**
     * @return delivery
     */
    public String getDelivery() {
        return delivery;
    }

    /**
     * @param delivery
     *            セットする delivery
     */
    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    /**
     * @return item_name
     */
    public String getItem_name() {
        return item_name;
    }

    /**
     * @param item_name
     *            セットする item_name
     */
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

}
