package Menu;

import OI.OutputInput;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class MenuItemDates extends MenuItem {

    private static Calendar _date1 = Calendar.getInstance();
    private static Calendar _date2 = Calendar.getInstance();
    private static Calendar _date3 = Calendar.getInstance();
    private static Calendar _date4 = Calendar.getInstance();

    public MenuItemDates(){
        _title = "Dates";
    }

    public void execute() {
        boolean isExcess = true;
        long days = 0;
        while (isExcess) {
            OutputInput.readString();
            readingDate(_date1, _date2, 1);
            readingDate(_date3, _date4, 2);
            days = calcIntersection(_date1, _date2, _date3, _date4);
            OutputInput.printMessage("Количество дней: " + days);
            isExcess = !validationExcess(days);
        }
        OutputInput.printMessage("Результат вычисления факториала: " + calcFactorial(days));
    }

    public long calcFactorial(long days){
        if (days == 0 || days == 1) {
            return 1;
        }
        else
            return days * (calcFactorial(days - 1));
    }

    public long calcIntersection(Calendar date1, Calendar date2, Calendar date3, Calendar date4){
        if((date3.compareTo(date2) > 0) || (date1.compareTo(date4) > 0)){
            return 0;
        }
        else{
            long[] numbersDate = new long[4];
            sortDate(numbersDate, date1, date2, date3, date4);
            return ((numbersDate[2] - numbersDate[1]) / (1000 * 60 * 60 * 24) + 1);
        }
    }

    public void sortDate(long[] arr, Calendar date1, Calendar date2, Calendar date3, Calendar date4){
        long numberDate1 = date1.getTimeInMillis();
        long numberDate2 = date2.getTimeInMillis();
        long numberDate3 = date3.getTimeInMillis();
        long numberDate4 = date4.getTimeInMillis();
        for(int i = 0; i<4; i++){
            if(i == 0) arr[i] = numberDate1;
            else if(i == 1) arr[i] = numberDate2;
            else if(i == 2) arr[i] = numberDate3;
            else arr[i] = numberDate4;
        }
        Arrays.sort(arr);
    }

    public boolean validationExcess(long days){
        if(days>10){
            OutputInput.printMessage("Слишком большой промежуток! Нажмите \"Enter\" и повторите ввод.");
            return false;
        }
        return true;
    }

    public void readingDate(Calendar d1, Calendar d2, int number){
        while (true) {
            OutputInput.printMessage("Введите дату начала " + number + "-ого временного отрезка (дд.мм.гггг):");
            String sd1 = OutputInput.readString();
            sd1 = sd1.trim().replaceAll(" +","");
            testDate(d1, sd1);
            OutputInput.printMessage("Введите дату конца " + number + "-ого временного отрезка (дд.мм.гггг):");
            String sd2 = OutputInput.readString();
            sd2 = sd2.trim().replaceAll(" +","");
            testDate(d2, sd2);
            if (!testSequence(d1, d2)) continue;
            break;
        }
    }

    public void testDate(Calendar Date, String sDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        while (true){
            try {
                if (!dateFormat.format(dateFormat.parse(sDate)).equals(sDate)){
                    OutputInput.printMessage("Такой даты не существует, попробуйте снова");
                    sDate = OutputInput.readString();
                    sDate = sDate.trim().replaceAll(" +","");
                    continue;
                }
                Date.setTime(dateFormat.parse(sDate));
                break;
            } catch (Exception q) {
                OutputInput.printMessage("Дата введена не верно, поробуйте снова");
                sDate = OutputInput.readString();
                sDate = sDate.trim().replaceAll(" +","");
            }
        }
    }

    public boolean testSequence(Calendar begDate, Calendar endDate){
        if (begDate.compareTo(endDate) > 0) {
            OutputInput.printMessage("Ошибка: дата окончания временного отрезка меньше чем дата начала временного отрезка!");
            OutputInput.printMessage("Попробуйте заново:");
            return false;
        } else {
            return true;
        }
    }
}
