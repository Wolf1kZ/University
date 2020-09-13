package OI;

import Validation.ValidationException;

import java.util.Scanner;

public class OutputInput {
    public static Scanner _in = new Scanner(System.in);

    public static void printMessage (String message ){
        System.out.println(message);
    }

    public static void checkIntVariable() throws ValidationException{
        if (!_in.hasNextInt()) {
            throw new ValidationException("Ошибка. Повторите ввод.");
        }
    }

    public static int readIntVariable() {
        int variable;
        while (!_in.hasNextInt()) {
            try {
                checkIntVariable();
            } catch (ValidationException e) {
                OutputInput.printMessage(e.getMessage());
                _in.next();
            }
        }
        variable = _in.nextInt();
        return variable;
    }

    public static void checkDoubleVariable() throws ValidationException{
        if (!_in.hasNextDouble()) {
            throw new ValidationException("Ошибка. Введено не число. Введите заново.");
        }
    }

    public static double readDoubleVariable() {
        double variable;
        while (!_in.hasNextDouble()) {
            try {
                checkDoubleVariable();
            } catch (ValidationException e) {
                OutputInput.printMessage(e.getMessage());
                _in.next();
            }
        }
        variable = _in.nextDouble();
        return variable;
    }

    public static String readString(){
        String string;
        string = _in.nextLine();
        return string;
    }
}
