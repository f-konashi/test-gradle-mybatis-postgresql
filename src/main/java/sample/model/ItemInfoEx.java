package sample.model;

public class ItemInfoEx extends ItemInfo {
	private Integer cartId;
	
	private Integer itemCount;
	
	private Integer subtotal;

	public Integer getItemCount() {
        return itemCount;
    }
	
    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }
    
	public Integer getCartId() {
        return cartId;
    }
	
    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
    
	public Integer getSubtotal() {
        return subtotal;
    }
	
    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }
}
