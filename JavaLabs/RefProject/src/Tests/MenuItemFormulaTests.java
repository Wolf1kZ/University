package Tests;

import Menu.MenuItemFormula;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuItemFormulaTests {

    MenuItemFormula _menuItem = new MenuItemFormula();

    @Test
    public void MenuItemFormula_getTitle(){
        assertEquals("Formula", _menuItem.getTitle());
    }

    @Test
    public void MenuItemFormula_calcFormula(){
        assertEquals(0, _menuItem.calcFormula(9,2));
        assertEquals(1, _menuItem.calcFormula(9,3));
        assertEquals(-1, _menuItem.calcFormula(25,1));
    }
}
