package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Menus;
import christmas.domain.Order;
import christmas.domain.Planner;
import christmas.view.InputView;
import christmas.view.OutputView;

public class UtecoRestaurantController {

    public void startOperation(){
        Date date = inputDate();
        Menus menus = inputMenus();
        Order order = new Order(menus);
        Planner planner = new Planner(date, order);

        OutputView.printBasicPreview(date, menus, order);
        OutputView.printPlanner(order, planner);
    }

    private static Date inputDate() {
        OutputView.printRestaurantIntro();
        Date date = createDate();
        return date;
    }

    private static Date createDate() {
        try{
            return new Date(InputView.inputDate());
        }catch (IllegalArgumentException e){
            OutputView.printException(e);
            return createDate();
        }
    }

    private static Menus inputMenus() {
        OutputView.printRestaurantMenu();
        Menus menus = createMenus();
        return menus;
    }

    private static Menus createMenus() {
        try{
            return InputView.inputOrderMenu();
        }catch (IllegalArgumentException e){
            OutputView.printException(e);
            return createMenus();
        }
    }
}
