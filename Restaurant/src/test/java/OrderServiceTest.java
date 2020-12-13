import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrderServiceTest {

    OrderService orderService = new OrderService();

    @Test
    public void calculate_final_order_value_fail() {
        addFoodItemsToCart();

        assertNotEquals(100, orderService.totalOrderValue());
    }

    private void addFoodItemsToCart() {
        Item tandoorRoti = new Item("Tandoor Roti", 25);
        orderService.addFoodItemsToCart(tandoorRoti, 10);

        Item zeeraRice = new Item("Zeera Rice", 110);
        orderService.addFoodItemsToCart(zeeraRice, 1);

        Item paneerButterMasala = new Item("Paneer Butter Masala", 250);
        orderService.addFoodItemsToCart(paneerButterMasala, 2);

        Item dalMakhni = new Item("Dal Makhni", 180);
        orderService.addFoodItemsToCart(dalMakhni, 1);

        Item gulabJamun = new Item("Gulab Jamun", 35);
        orderService.addFoodItemsToCart(gulabJamun, 5);
    }
}
