package Menu;

import OI.OutputInput;

public class MenuItemFormula extends MenuItem {

    public MenuItemFormula(){
        _title = "Formula";
    }

    public void execute(){
        double x = readingX();
        double z = readingZ();
        OutputInput.printMessage(calcFormula(x, z) + "\n");
    }

    public double calcFormula(double x, double z){
        return Math.sqrt(x) - 6 / z;
    }

    public double readingX(){
        OutputInput.printMessage("sqrt(x) - 6/z\n" + "Введите x: ");
        double x = OutputInput.readDoubleVariable();
        while (x < 0) {
            OutputInput.printMessage("X должен быть неотрицательным числом. Повторите ввод!");
            x = OutputInput.readDoubleVariable();
        }
        return x;
    }

    public double readingZ(){
        OutputInput.printMessage("Введите z: ");
        double z = OutputInput.readDoubleVariable();
        while (z == 0) {
            OutputInput.printMessage("Делить на 0 нельзя! Повторите ввод!");
            z = OutputInput.readDoubleVariable();
        }
        return z;
    }
}
