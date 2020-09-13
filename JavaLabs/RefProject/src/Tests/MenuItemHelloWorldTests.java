package Tests;

import Menu.MenuItemHelloWorld;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuItemHelloWorldTests {

    MenuItemHelloWorld _menuItem = new MenuItemHelloWorld();

    @Test
    public void MenuItemHelloWorld_getTitle(){
        assertEquals("Hello world", _menuItem.getTitle());
    }
}