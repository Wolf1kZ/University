package Menu;

import OI.OutputInput;

public class MenuItemHelloWorld  extends MenuItem {

    public MenuItemHelloWorld() {
        _title = "Hello world";
    }

    public void execute() {
        OutputInput.printMessage("Hello world!");
    }
}
