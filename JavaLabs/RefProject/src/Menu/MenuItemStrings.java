package Menu;

import OI.OutputInput;
import Validation.ValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuItemStrings extends MenuItem {

    public static final String IP_PATTERN = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"+
            "(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}";
    public static final String PHONE_PATTERN = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";
    public static final String EMAIL_PATTERN = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+";

    public MenuItemStrings(){
        _title = "Strings";
    }

    public void execute(){
        OutputInput.readString();
        OutputInput.printMessage("Введите первую строку: ");
        String str1 = OutputInput.readString();
        OutputInput.printMessage("Введите вторую строку: ");
        String str2 = OutputInput.readString();
        OutputInput.printMessage("Результат анализа строк:");
        OutputInput.printMessage("===================================================");
        checking(str1, str2);
        OutputInput.printMessage("===================================================");
    }

    public void checkMatch(String one, String two, String ErrorMessage, String TrueMessage){
        try {
            isMatch(one, two, ErrorMessage, TrueMessage);
        } catch (ValidationException e) {
            OutputInput.printMessage(e.getMessage());
        }
    }

    public void isMatch(String one, String two, String ErrorMessage, String TrueMessage) throws ValidationException{
        if (!one.equals(two)) {
            throw new ValidationException(ErrorMessage);
        }
        else throw new ValidationException(TrueMessage);
    }

    public void isMatchPattern(String str, String PATTERN, String ErrorMessage, String TrueMessage) throws ValidationException{
        Pattern Pat = Pattern.compile(PATTERN);
        Matcher Mat = Pat.matcher(str);
        boolean isValid = Mat.matches();
        if (!isValid) {
            throw new ValidationException(str + ErrorMessage);
        }
        else throw new ValidationException(str + TrueMessage);
    }

    public String reverseString(String str){
        return new StringBuffer(str).reverse().toString();
    }

    public String formatString(String str){
        String FormatStr = str.trim().replaceAll("\\s+"," ");
        FormatStr = FormatStr.toLowerCase();
        return FormatStr;
    }

    public void checkMatchPattern(String str, String PATTERN, String ErrorMessage, String TrueMessage){
        try {
            isMatchPattern(str, PATTERN, ErrorMessage, TrueMessage);
        } catch (ValidationException e) {
            OutputInput.printMessage(e.getMessage());
        }
    }

    public void checking(String str1, String str2) {
        checkMatch(str1, str2, "Строки без форматирования не совпадают.",
                "Строки без форматирования совпадают.");
        checkMatch(formatString(str1), formatString(str2), "Отформатированные строки не совпадают.",
                "Отформатированные строки совпадают.");
        checkMatch(reverseString(str1), str2, "Строки не являются обратными друг к другу.",
                "Строки являются обратными друг к другу.");
        OutputInput.printMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        checkMatchPattern(str1, EMAIL_PATTERN, ", строка не является email'ом",
                ", строка является email'ом");
        checkMatchPattern(str1, PHONE_PATTERN, ", строка не является тел. номером.",
                ", строка является тел. номером.");
        checkMatchPattern(str1, IP_PATTERN, ", строка не является ip-адресом",
                ", строка является ip-адресом");
        checkMatchPattern(str2, EMAIL_PATTERN, ", строка не является email'ом",
                ", строка является email'ом");
        checkMatchPattern(str2, PHONE_PATTERN, ", строка не является тел. номером.",
                ", строка является тел. номером.");
        checkMatchPattern(str2, IP_PATTERN, ", строка не является ip-адресом",
                ", строка является ip-адресом");
    }
}
