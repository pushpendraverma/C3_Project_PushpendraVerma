import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;

    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        addRestaurants();

        LocalTime openingTime = LocalTime.parse("10:00:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        Restaurant rotiDhabaRestaurant = service.addRestaurant("Roti Dhaba","New Delhi", openingTime,closingTime);
        Restaurant foundRestaurant = null;
        for (Restaurant restaurant : service.getRestaurants()) {
            if ("Roti Dhaba".equals(restaurant.getName())) {
                foundRestaurant = restaurant;
                break;
            }
        }
        assertEquals(rotiDhabaRestaurant, foundRestaurant);
    }

    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        addRestaurants();

        assertThrows(restaurantNotFoundException.class, () -> {
            if (null == service.findRestaurantByName("Rumali Roti Dhaba")) {
                throw new restaurantNotFoundException("Restaurant not found");
            }
        });
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        addMenuItems();

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        addMenuItems();

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>

    private void addRestaurants() {
        LocalTime openingTime = LocalTime.parse("10:00:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        Restaurant babaTheDhabaRestaurant = service.addRestaurant("Baba The Dhaba","New Delhi", openingTime,closingTime);
        Restaurant maaKiDaalRestaurant = service.addRestaurant("Maa Ki Daal","New Delhi", openingTime,closingTime);
        Restaurant rotiDhabaRestaurant = service.addRestaurant("Roti Dhaba","New Delhi", openingTime,closingTime);
        Restaurant rollsOnTheWheelsRestaurant = service.addRestaurant("Rolls On The Wheels","New Delhi", openingTime,closingTime);
        Restaurant littiWalaDhabaRestaurant = service.addRestaurant("Litti Wala Dhaba","New Delhi", openingTime,closingTime);
    }

    private void addMenuItems() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
}