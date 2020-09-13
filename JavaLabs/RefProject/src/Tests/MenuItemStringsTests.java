package Tests;

import Menu.MenuItemStrings;
import Validation.ValidationException;
import org.junit.jupiter.api.Test;

import static Menu.MenuItemStrings.EMAIL_PATTERN;
import static Menu.MenuItemStrings.IP_PATTERN;
import static Menu.MenuItemStrings.PHONE_PATTERN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MenuItemStringsTests {

    MenuItemStrings _menuItem = new MenuItemStrings();

    @Test
    public void MenuItemStrings_getTitle(){
        assertEquals("Strings", _menuItem.getTitle());
    }

    @Test
    public void MenuItemStrings_isMatchNoFormat(){
        String str1 = "a    b C";
        String str2 = "a b c";
        Throwable thrown = assertThrows(ValidationException.class, () ->
                _menuItem.isMatch(str1, str2,
                        "Строки без форматирования не совпадают",
                        "Строки без форматирования совпадают"));
        assertEquals("Строки без форматирования не совпадают", thrown.getMessage());

        String str3 = "a b c";
        String str4 = "a b c";
        thrown = assertThrows(ValidationException.class, () ->
                _menuItem.isMatch(str3, str4,
                        "Строки без форматирования не совпадают",
                        "Строки без форматирования совпадают"));
        assertEquals("Строки без форматирования совпадают", thrown.getMessage());
    }

    @Test
    public void MenuItemStrings_isMatchFormat(){
        String str1 = "a    b C";
        String str2 = "a b c";
        Throwable thrown = assertThrows(ValidationException.class, () ->
                _menuItem.isMatch(_menuItem.formatString(str1), _menuItem.formatString(str2),
                        "Отформатированные строки не совпадают",
                        "Отформатированные строки совпадают"));
        assertEquals("Отформатированные строки совпадают", thrown.getMessage());

        String str3 = "HeLLo";
        String str4 = "olleh";
        thrown = assertThrows(ValidationException.class, () ->
                _menuItem.isMatch(_menuItem.formatString(str3), _menuItem.formatString(str4),
                        "Отформатированные строки не совпадают",
                        "Отформатированные строки совпадают"));
        assertEquals("Отформатированные строки не совпадают", thrown.getMessage());
    }

    @Test
    public void MenuItemStrings_isMatchReverse(){
        String str1 = "HeLLo";
        String str2 = "olleh";
        Throwable thrown = assertThrows(ValidationException.class, () ->
                _menuItem.isMatch(_menuItem.reverseString(str1), str2,
                        "Строки не являются обратными друг к другу",
                        "Строки являются обратными друг к другу"));
        assertEquals("Строки не являются обратными друг к другу", thrown.getMessage());

        String str3 = "hello";
        String str4 = "olleh";
        thrown = assertThrows(ValidationException.class, () ->
                _menuItem.isMatch(_menuItem.reverseString(str1), str2,
                        "Строки не являются обратными друг к другу",
                        "Строки являются обратными друг к другу"));
        assertEquals("Строки не являются обратными друг к другу", thrown.getMessage());
    }

    @Test
    public void MenuItemStrings_isMatchPatternEmail(){
        String str1 = "a@com";
        Throwable thrown = assertThrows(ValidationException.class, () ->
                _menuItem.isMatchPattern(str1, EMAIL_PATTERN,
                        ", строка не является email'ом",
                ", строка является email'ом"));
        assertEquals(str1 + ", строка не является email'ом", thrown.getMessage());

        String str2 = "a@g.com";
        thrown = assertThrows(ValidationException.class, () ->
                _menuItem.isMatchPattern(str2, EMAIL_PATTERN,
                        ", строка не является email'ом",
                        ", строка является email'ом"));
        assertEquals(str2 + ", строка является email'ом", thrown.getMessage());
    }

    @Test
    public void MenuItemStrings_isMatchPatternIP(){
        String str1 = "1.1.1.1";
        Throwable thrown = assertThrows(ValidationException.class, () ->
                _menuItem.isMatchPattern(str1, IP_PATTERN,
                        ", строка не является ip-адресом",
                        ", строка является ip-адресом"));
        assertEquals(str1 + ", строка является ip-адресом", thrown.getMessage());

        String str2 = "255.255.255.255";
        thrown = assertThrows(ValidationException.class, () ->
                _menuItem.isMatchPattern(str2, IP_PATTERN,
                        ", строка не является ip-адресом",
                        ", строка является ip-адресом"));
        assertEquals(str2 + ", строка является ip-адресом", thrown.getMessage());

        String str3 = "280.255.255.255";
        thrown = assertThrows(ValidationException.class, () ->
                _menuItem.isMatchPattern(str3, IP_PATTERN,
                        ", строка не является ip-адресом",
                        ", строка является ip-адресом"));
        assertEquals(str3 + ", строка не является ip-адресом", thrown.getMessage());

        String str4 = "255.255.255.255.255";
        thrown = assertThrows(ValidationException.class, () ->
                _menuItem.isMatchPattern(str4, IP_PATTERN,
                        ", строка не является ip-адресом",
                        ", строка является ip-адресом"));
        assertEquals(str4 + ", строка не является ip-адресом", thrown.getMessage());
    }

    @Test
    public void MenuItemStrings_isMatchPatternPHONE(){
        String str1 = "+79992341234";
        Throwable thrown = assertThrows(ValidationException.class, () ->
                _menuItem.isMatchPattern(str1, PHONE_PATTERN,
                        ", строка не является тел. номером",
                        ", строка является тел. номером"));
        assertEquals(str1 + ", строка является тел. номером", thrown.getMessage());

        String str2 = "89992341234";
        thrown = assertThrows(ValidationException.class, () ->
                _menuItem.isMatchPattern(str2, PHONE_PATTERN,
                        ", строка не является тел. номером",
                        ", строка является тел. номером"));
        assertEquals(str2 + ", строка является тел. номером", thrown.getMessage());

        String str3 = "+79992341234456";
        thrown = assertThrows(ValidationException.class, () ->
                _menuItem.isMatchPattern(str3, PHONE_PATTERN,
                        ", строка не является тел. номером",
                        ", строка является тел. номером"));
        assertEquals(str3 + ", строка не является тел. номером", thrown.getMessage());

        String str4 = "+790O2341234";
        thrown = assertThrows(ValidationException.class, () ->
                _menuItem.isMatchPattern(str4, PHONE_PATTERN,
                        ", строка не является тел. номером",
                        ", строка является тел. номером"));
        assertEquals(str4 + ", строка не является тел. номером", thrown.getMessage());
    }

    @Test
    public void MenuItemString_reverseString(){
        assertEquals("dlrow", _menuItem.reverseString("world"));
    }

    @Test
    public void MenuItemStrings_formatString(){
        assertEquals("a b c",_menuItem.formatString("A      B        C"));
    }

}
