package sample.model;

public class ItemInfoEx extends ItemInfo {
	private Integer cartId;
	
	private Integer itemCount;

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
}
