package Menu;

import OI.OutputInput;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private static List<MenuItem> _menuItems = null;

    public static List<MenuItem> getMenuItems() {
        if (_menuItems == null) {
            _menuItems = new ArrayList<MenuItem>();
            _menuItems.add(new MenuItemExit());
            _menuItems.add(new MenuItemHelloWorld());
            _menuItems.add(new MenuItemFormula());
            _menuItems.add(new MenuItemDates());
            _menuItems.add(new MenuItemStrings());
        }
        return _menuItems;
    }

    public static void print() {
        List<MenuItem> menuItems = getMenuItems();
        OutputInput.printMessage("======================================");
        OutputInput.printMessage("              MAIN MENU:              ");
        for (int iMenu = 0; iMenu < menuItems.size(); iMenu++) {
            OutputInput.printMessage("  "+iMenu+") "+menuItems.get(iMenu).getTitle());
        }
        OutputInput.printMessage("======================================");
    }

    public static MenuItem getSelectedMenuItem() {
        List<MenuItem> menuItems = getMenuItems();
        int iMenuItem = 0;
        while (true) {
            iMenuItem = OutputInput.readIntVariable();
            if ((iMenuItem < 0) || (iMenuItem >= menuItems.size())) {
                OutputInput.printMessage("ERROR: Number must be >=0 and <"+menuItems.size());
                continue;
            }

            return menuItems.get(iMenuItem);
        }
    }
}
