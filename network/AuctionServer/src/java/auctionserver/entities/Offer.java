package auctionserver.entities;

/**
 *
 * @author vongenae
 */
public class Offer {
    private Item item;
    private boolean isSold;
    private double currentOffer;
    
    public Offer(Item item) {
        this.item = item;
        this.isSold = false;
    }

    public Item getItem() {
        return item;
    }
    
    public boolean isSold() {
        return isSold;
    }

    public double getCurrentOffer() {
        return currentOffer;
    }
    
    public boolean offerAccepted(double offer) {
        boolean accepted = false;
        if (sufficient(offer) && offer > currentOffer) {
            this.currentOffer = offer;
            this.isSold = true;
            accepted = true;
        }
        return accepted;
    }
     
    public boolean sufficient(double offer) {
        return item.getMinPrice() <= offer;
    }
}
