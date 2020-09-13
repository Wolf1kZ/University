package Tests;

import Menu.MenuItemDates;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class MenuItemDatesTests {

    MenuItemDates _menuItem = new MenuItemDates();

    @Test
    public void MenuItemDates_getTitle(){
        assertEquals("Dates", _menuItem.getTitle());
    }

    @Test
    public void MenuItemDates_calcFactorial(){
        assertEquals(1, _menuItem.calcFactorial(0));
        assertEquals(1, _menuItem.calcFactorial(1));
        assertEquals(6, _menuItem.calcFactorial(3));
        assertEquals(3628800, _menuItem.calcFactorial(10));
    }

    @Test
    public void MenuItemDates_testSequence(){
        assertFalse(_menuItem.testSequence(new GregorianCalendar(2000, Calendar.FEBRUARY, 12),
                new GregorianCalendar(2000, Calendar.FEBRUARY, 11))
        );
        assertTrue(_menuItem.testSequence(new GregorianCalendar(2000, Calendar.FEBRUARY, 12),
                new GregorianCalendar(2000, Calendar.FEBRUARY, 12))
        );
    }

    @Test
    public void MenuItemDates_calcIntersection(){
        assertEquals(0, _menuItem.calcIntersection(
                new GregorianCalendar(2020, Calendar.MAY, 15),
                new GregorianCalendar(2020, Calendar.MAY, 20),
                new GregorianCalendar(2020, Calendar.MAY, 21),
                new GregorianCalendar(2020, Calendar.MAY, 25))
        );
        assertEquals(0, _menuItem.calcIntersection(
                new GregorianCalendar(2020, Calendar.MAY, 15),
                new GregorianCalendar(2020, Calendar.MAY, 20),
                new GregorianCalendar(2020, Calendar.MAY, 10),
                new GregorianCalendar(2020, Calendar.MAY, 14))
        );
        assertEquals(1, _menuItem.calcIntersection(
                new GregorianCalendar(2020, Calendar.MAY, 15),
                new GregorianCalendar(2020, Calendar.MAY, 20),
                new GregorianCalendar(2020, Calendar.MAY, 10),
                new GregorianCalendar(2020, Calendar.MAY, 15))
        );
        assertEquals(5, _menuItem.calcIntersection(
                new GregorianCalendar(2020, Calendar.MAY, 15),
                new GregorianCalendar(2020, Calendar.MAY, 19),
                new GregorianCalendar(2020, Calendar.MAY, 15),
                new GregorianCalendar(2020,Calendar.MAY,19))
        );
    }

    @Test
    public void MenuItemDates_validationExcess(){
        assertFalse(_menuItem.validationExcess(11));
        assertTrue(_menuItem.validationExcess(10));
    }
}
