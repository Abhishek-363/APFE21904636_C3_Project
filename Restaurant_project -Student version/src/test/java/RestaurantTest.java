import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;


    @BeforeEach
    public void createSetup() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE



        Restaurant spyMock = Mockito.spy(restaurant);

        LocalTime presentTimeMocked = LocalTime.parse("18:00:00");

        Mockito.when(spyMock.getCurrentTime()).thenReturn(presentTimeMocked);

        assertTrue(spyMock.isRestaurantOpen());


    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        Restaurant spyMock = Mockito.spy(restaurant);

        LocalTime presentTimeMocked = LocalTime.parse("23:00:00");

        Mockito.when(spyMock.getCurrentTime()).thenReturn(presentTimeMocked);

        assertFalse(spyMock.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {


        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //<<<<<<<<<<<<<<<<<<TDD for new Feature>>>>>>>>>>>>>>>>>>>>>>>>
    //The module should take item names as parameter
    //The module should be able to return the total amount for the items
    //If no item is selected the value should be 0

    @Test
    public void adding_items_to_list_should_give_correct_total_amount() {

        List<String> itemAdded = new ArrayList<>();
        itemAdded.add("Sweet corn soup");
        itemAdded.add("Vegetable lasagne");

        int totalAmount = restaurant.getAmount(itemAdded);

        assertEquals(119+269,totalAmount);

    }

    @Test
    public void if_no_items_are_added_to_list_it_should_give_0_total_amount() {

        List<String> itemAdded = new ArrayList<>();

        int totalAmount = restaurant.getAmount(itemAdded);

        assertEquals(0,totalAmount);

    }


}