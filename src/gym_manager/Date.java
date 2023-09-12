package gym_manager;
import java.util.Calendar;
/**
 * This class Date is used to create a date object. It contains the day, month, and year of the date.
 * @author Vinay Kumar, Noel Declaro
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    // Create and object with today's date (See Calendar Class)
    public Date(){
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DATE);
    }
    /**
     * This is a constructor that is used to create a date object from the user's input.
     * @param date string that contains the inputted date
     */
    public Date(String date){
        String[] tokens = date.split("/");
        year = Integer.parseInt(tokens[2]);
        month = Integer.parseInt(tokens[0]);
        day = Integer.parseInt(tokens[1]);
    }


    /**
     * This is method is used to represent the date object by a string .
     * @return String Formatted string that represents the date.
     */
    public String DateToString(){
        return month + "/" + day + "/" + year;
    }

    /**
     * This method is used to see organize dates by comparing them.
     * @param date date object
     * @return int 0 if the dates are equal, 1 if the date is greater than the date passed in,
     * -1 if the date is less than the date passed in.
     */
    @Override
    public int compareTo(Date date){
        if (year > date.year)
            return 1;
        else if (year < date.year)
            return -1;
        else{
            if (month > date.month)
                return 1;
            else if (month < date.month)
                return -1;
            else{
                if (day > date.day)
                    return 1;
                else if (day < date.day)
                    return -1;
                else
                    return 0;
            }
        }
    }

    /**
     * This method is used to see if the date is valid.
     * This method checks if the dates are valid including leap years.
     * @return boolean True if the dates is valid, false if the dates is not valid.
     */
    public boolean isValid(){
        boolean isLeapYear = false;
        //check for leap year
        if(year % 4 == 0){
            if(year % 100 == 0){
                if(year % 400 == 0)
                    isLeapYear = true;
            }
            else
                isLeapYear = true;
        }
        //check for valid month
        if(month < 1 || month > 12)
            return false;
        //check for valid day for Feburary
        if(month == 2){
            if(isLeapYear){
                if(day < 1 || day > 29)
                    return false;
            } else{
                if(day < 1 || day > 28)
                    return false;
            }
        } else{
            //check for valid day for months with 31 days
            if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                if(day < 1 || day > 31)
                    return false;
            } else{
                //check for valid day for months with 30 days
                if(day < 1 || day > 30)
                    return false;
            }
        }
        //if all tests are passed return true
        return true;
    }
    /**
     * This method is used to see if the birthday is valid.
     * @return boolean if the date is less than today's date. False if the birthday is today or a future date.
     */
    public boolean BirthdayIsValid(){
        //if Birthday is greater than today's date return false
        if(year > Calendar.getInstance().get(Calendar.YEAR))
            return false;
        if(year == Calendar.getInstance().get(Calendar.YEAR)){
            if(month > Calendar.getInstance().get(Calendar.MONTH))
                return false;
            if(month == Calendar.getInstance().get(Calendar.MONTH)){
                if(day > Calendar.getInstance().get(Calendar.DATE))
                    return false;
            }
        }
        return true;
    }

    /**
     * This method is used to see if the member is 18 years or older.
     * @return boolean True if the date is greater than or equal to 18 , false if the date is less than 18.
     */
    public boolean IsOlderThan18(){
        //Checks to see if the member is older than 18
        if(year > Calendar.getInstance().get(Calendar.YEAR) - 18)
            return false;
        if(year == Calendar.getInstance().get(Calendar.YEAR) - 18){
            if(month > Calendar.getInstance().get(Calendar.MONTH))
                return false;
            if(month == Calendar.getInstance().get(Calendar.MONTH)){
                if(day >= Calendar.getInstance().get(Calendar.DATE))
                    return false;
            }
        }
        return true;
    }

    /**
     * This method is used to see if the member's membership is expired.
     * @return boolean true if the expiration date is greater than today's date,
     * false if the expiration date is less than today's date.
     */
    public boolean MembershipDateIsValid(){
        //Checks to see if the membership date is valid
        if(year > Calendar.getInstance().get(Calendar.YEAR))
            return true;
        if(year == Calendar.getInstance().get(Calendar.YEAR)){
            if(month > Calendar.getInstance().get(Calendar.MONTH))
                return true;
            if(month == Calendar.getInstance().get(Calendar.MONTH)){
                if(day >= Calendar.getInstance().get(Calendar.DATE))
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        System.out.println("**Test Case #1**");
        System.out.println("Input: '13/20/2023'");
        System.out.println("Expected Output: false");
        Date input1 = new Date("13/20/2023");
        System.out.println("Output: " + input1.isValid());
        System.out.println();

        System.out.println("**Test Case #2**");
        System.out.println("Input: '13/40/2023'");
        System.out.println("Expected Output: false");
        Date input2 = new Date("13/40/2023");
        System.out.println("Output: " + input2.isValid());
        System.out.println();

        System.out.println("**Test Case #3**");
        System.out.println("Input: '-1/20/2023'");
        System.out.println("Expected Output: false");
        Date input3 = new Date("-1/20/2023");
        System.out.println("Output: " + input3.isValid());
        System.out.println();

        System.out.println("**Test Case #4**");
        System.out.println("Input: '1/40/2023'");
        System.out.println("Expected Output: false");
        Date input4 = new Date("1/40/2023");
        System.out.println("Output: " + input4.isValid());
        System.out.println();

        System.out.println("**Test Case #5**");
        System.out.println("Input: '1/-20/2023'");
        System.out.println("Expected Output: false");
        Date input5 = new Date("1/-20/2023");
        System.out.println("Output: " + input5.isValid());
        System.out.println();

        System.out.println("**Test Case #6**");
        System.out.println("Input: '2/29/2020'");
        System.out.println("Expected Output: true");
        Date input6 = new Date("2/29/2020");
        System.out.println("Output: " + input6.isValid());
        System.out.println();

        System.out.println("**Test Case #7**");
        System.out.println("Input: '2/29/2021'");
        System.out.println("Expected Output: false");
        Date input7 = new Date("2/29/2021");
        System.out.println("Output: " + input7.isValid());
        System.out.println();

        System.out.println("**Test Case #8**");
        System.out.println("Input: '1/31/2001'");
        System.out.println("Expected Output: true");
        Date input8 = new Date("1/31/2001");
        System.out.println("Output: " + input8.isValid());
        System.out.println();

        System.out.println("**Test Case #9**");
        System.out.println("Input: '2/31/2001'");
        System.out.println("Expected Output: false");
        Date input9 = new Date("2/31/2001");
        System.out.println("Output: " + input9.isValid());
        System.out.println();

        System.out.println("**Test Case #10**");
        System.out.println("Input: '3/4/2001'");
        System.out.println("Expected Output: true");
        Date input10 = new Date("3/4/2001");
        System.out.println("Output: " + input10.isValid());
        System.out.println();




    }
}

