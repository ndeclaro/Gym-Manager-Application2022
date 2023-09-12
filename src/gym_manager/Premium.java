package gym_manager;

/**
 This is a Premium class that extends the family class to implement the premium membership
 type
 @author Vinay Kumar, Noel Declaro
 */
public class Premium extends Family {
    protected int noOfGuests;

    /**
     * constructor for premium class
     * @param fname first name
     * @param lname last name
     * @param dob date of birth
     * @param expire expiry date
     * @param location location of member
     */
    public Premium(String fname, String lname, Date dob, Date expire, Location location){
        super(fname, lname, dob, expire, location);
        noOfGuests = 3;
    }

    /**
     * constructor for testing
     * @param fname first name
     * @param lname last name
     * @param dob date of birth
     */
    public Premium(String fname, String lname, Date dob){
        super(fname, lname, dob);
        noOfGuests = 3;
    }


    /**
     * this method returns the membership fees associated with the premium class
     * @return fee contains rounded amount of dues owed for the next billing statement
     */
    @Override
    public double membershipFee(){
        double fee = 11 * (59.99);
        return fee;
    }

    /**
     This is method is used to return a String that represents the member.
     @return String Formatted string that represents the member.
     */
    @Override
    public String toString(){
        String call = super.toString();
        String add = call.replaceAll("Family", "Premium");
        return add;
    }

    /**
     * this method returns the amount of passes left
     * @return noOfGuests int number of passes on member's permium account
     */
    @Override
    public int checkGuestPass(){
        return noOfGuests;
    }

    /**
     * decreases the number of guest passes
     */
    @Override
    public void decrementGuest(){
        noOfGuests--;
    }

    /**
     * increases the number of guest pass
     */
    @Override
    public void incrementGuest(){
        noOfGuests++;
    }

}
