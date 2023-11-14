package christmas.controller;

import christmas.domain.Date;
import christmas.view.InputView;
import christmas.view.OutputView;

public class UtecoRestaurantController {

    public void startOperation(){
        OutputView.printRestaurantIntro();
        Date date = createDate();
        System.out.println(date.toString());
    }

    private static Date createDate() {
        try{
            return new Date(InputView.inputDate());
        }catch (IllegalArgumentException e){
            OutputView.printException(e);
            return createDate();
        }
    }
}
