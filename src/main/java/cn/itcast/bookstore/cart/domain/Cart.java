package cn.itcast.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private Map<String, CartItem> items = new LinkedHashMap<>();

    public double getTotal() {
        BigDecimal total = new BigDecimal("0");
        for (CartItem item : items.values()) {
            total = total.add(new BigDecimal(item.getSubTotal() + ""));
        }
        return total.doubleValue();
    }

    public void add(CartItem item) {
        String bid = item.getBook().getBid();
        if (items.containsKey(bid)) {
            CartItem oldItem = items.get(bid);
            oldItem.setCount(oldItem.getCount() + item.getCount());
            items.put(bid, oldItem);
        } else {
            items.put(bid, item);
        }
    }

    public void clear() {
        items.clear();
    }

    public void delete(String bid) {
        items.remove(bid);
    }

    public Collection<CartItem> getCartItems() {
        return items.values();
    }
}
