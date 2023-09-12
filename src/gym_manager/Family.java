package gym_manager;

/**
 This is a Family class that extends the member class to implement the Family membership
 type
 @author Vinay Kumar, Noel Declaro
 */
public class Family extends Member {
    private int noOfGuests = 1;

    /**
     * constructor for Family class
     * @param fname first name
     * @param lname last name
     * @param dob date of birth object
     * @param expire expiry day
     * @param location location of member
     */
    public Family(String fname, String lname, Date dob, Date expire, Location location){
        super(fname, lname, dob, expire, location);

    }

    /**
     * constructor for testing
     * @param fname first name
     * @param lname last name
     * @param dob date of birth
     */
    public Family(String fname, String lname, Date dob){
        super(fname, lname, dob);
        noOfGuests = 1;
    }
        /**
         *  returns the fee for the next billing date of a member
         * @return fee total of membership fee for next billing date
         */
    @Override
    public double membershipFee(){
        double fee = 29.99 + 3 * (59.99);
        return fee;
    }

    /**
     This is method is used to return a String that represents the member.
     @return String Formatted string that represents the member.
     */
    @Override
    public String toString(){
        return super.toString() + ", (Family) guest-pass remaining: " + checkGuestPass();
    }

    /**
     * this method returns the amount of passes left
     * @return noOfGuests int number of passes on member's family account
     */
    public int checkGuestPass(){
        return noOfGuests;
    }

    /**
     * decreases the number of guest passes
     */
    public void decrementGuest(){
        noOfGuests--;
    }

    /**
     * increases the number of guest pass
     */
    public void incrementGuest(){
        noOfGuests++;
    }

}
