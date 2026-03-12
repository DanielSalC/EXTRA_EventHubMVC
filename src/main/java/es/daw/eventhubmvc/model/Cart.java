package es.daw.eventhubmvc.model;

import es.daw.eventhubmvc.enums.TicketCategory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.*;

@Component
@SessionScope
public class Cart {

    private final Map<String, CartItem> items = new LinkedHashMap<>();

    public Collection<CartItem> getItems() {
        return items.values();
    }

    public int getItemCount() {
        return items.values().stream().mapToInt(CartItem::qty).sum();
    }

    public BigDecimal getTotal() {
        return items.values().stream()
                .map(CartItem::lineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public void addOrIncrement(CartItem incoming) {
        if (incoming == null || incoming.ticketTypeCode() == null) return;

        CartItem existing = items.get(incoming.ticketTypeCode());
        if (existing == null) {
            items.put(incoming.ticketTypeCode(), incoming);
        } else {
            items.put(incoming.ticketTypeCode(),
                    new CartItem(existing.ticketTypeCode(), existing.ticketName(), existing.unitPrice(), existing.qty() + incoming.qty()));
        }
    }

    public void updateQty(String ticketTypeCode, int qty) {
        CartItem existing = items.get(ticketTypeCode);
        if (existing == null) return;

        if (qty <= 0) {
            items.remove(ticketTypeCode);
        } else {
            items.put(ticketTypeCode, new CartItem(existing.ticketTypeCode(), existing.ticketName(), existing.unitPrice(), qty));
        }
    }

    public void remove(String ticketTypeCode) {
        items.remove(ticketTypeCode);
    }


    // ---- MÉTODOS NUEVOS DE COMPORTAMIENTO -----

    /**
     * Devuelve la cantidad de tickets comprados
     * Ya había X unidades del ticketTypeCode en el carrito, independientemente de la categoría
     * @param ticketTypeCode código del ticket (es único)
     * @return
     */
    public int getQty(String ticketTypeCode){
        CartItem existing = items.get(ticketTypeCode);
        return existing != null ? existing.qty() : 0;

    }

    /**
     * Cantidad de tickets de una categoría concreta ...
     * @param category
     * @return
     */
    public int getQtyByCategory(TicketCategory category) {
        return items.values().stream()
                .filter(i -> i.ticketName().equals(category.name()))
                .mapToInt(CartItem::qty)
                .sum();
    }
    public int getQtyByCategoryIterative(TicketCategory category) {
        int total = 0;
        if (category == null) return 0;
        for (CartItem item : items.values()) {
            if (item != null && category.name().equals(item.ticketName())) {
                total += item.qty();
            }
        }
        return total;
    }


}
