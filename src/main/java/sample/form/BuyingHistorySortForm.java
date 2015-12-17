package sample.form;

public class BuyingHistorySortForm {
    private String buyingDate;

    private String payment;

    private String delivery;

    private String itemName;

    private String ascOrDesc;

    /**
     * @return buyingDate
     */
    public String getBuyingDate() {
        return buyingDate;
    }

    /**
     * @param buyingDate
     *            セットする buyingDate
     */
    public void setBuyingDate(String buyingDate) {
        this.buyingDate = buyingDate;
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
     * @return itemName
     */
    public String getItem_name() {
        return itemName;
    }

    /**
     * @param itemName
     *            セットする itemName
     */
    public void setItem_name(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return AscOrDesc
     */
    public String getAscOrDesc() {
        return ascOrDesc;
    }

    /**
     * @param ascOrDesc
     *            セットする ascOrDesc
     */
    public void setAsc(String ascOrDesc) {
        this.ascOrDesc = ascOrDesc;
    }

}
