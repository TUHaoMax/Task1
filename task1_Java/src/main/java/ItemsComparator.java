import java.util.Comparator;

public  class ItemsComparator implements Comparator <PurchasedItems>{
    @Override
    public int compare(PurchasedItems o1, PurchasedItems o2) {
        return o1.version - o2.version;
    }
}
