package gym_manager;
/**
This is a Member class that is used to create a member object. It contains the
 member's first name, last name, date of birth, and membership expiration date.
@author Vinay Kumar, Noel Declaro
*/
public class Member implements Comparable<Member> {
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    /**
    This is a constructor that is used to create a member object.
    @param fname string that contains the first name of the member
    @param lname string that contains the last name of the member
    @param dob Date object that contains the member's DOB
    @param expire Date object of the membership expiration
    @param location Location object for member's location
    */
    public Member(String fname, String lname, Date dob, Date expire, Location location) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
        this.location = location;
    }
    /**
    This is a constructor that is used to create a member object.
    @param fname string that contains the first name of the member
    @param lname string that contains the last name of the member
    @param dob Date object that contains the member's DOB
    */
    public Member(String fname, String lname, Date dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     *  returns the fee for the next billing date of a member
     * @return fee total of membership fee for next billing date
     */
    public double membershipFee(){
        double fee = 29.99 + 3 * (39.99);
        return fee;
    }

    /**
    This is method is used to return a String that represents the member.
    @return String Formatted string that represents the member.
    */
    @Override 
    public String toString(){
        String birthday = dob.DateToString();
        String expiration = expire.DateToString();
        //BreakDown Location into tokens 
        
        String town = location.toString();
        String county = location.getCounty();
        String zipCode = location.getZipCode();

       return " " + fname + " " + lname + ", DOB: " + birthday + ", Membership expires " +
               expiration + ", Location: " + town + ", " + zipCode + ", " + county;
    }

    /**
     * This method is used to see if two members are equal .
     * The comparison is based on the member's last name, first name, and date of birth.
     * @param obj Object to compare member
     * @return boolean True if the members are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Member){
            Member member = (Member) obj;
            if(member.fname.equals(this.fname) && member.lname.equals(this.lname) &&
                    member.dob.DateToString().equals(this.dob.DateToString())){
                return true;
            }
        }
        return false;
    }

    @Override
    /**
     * This method is used to compare two members using their first and last name.
     * @param member Member object to compare
     * @return int return the lexicographical difference between the two members.
     */
    public int compareTo(Member member){
        //Compare by last name 
        int compare = lname.compareTo(member.lname);
        if(compare == 0){
            //Compare by first name 
            compare = fname.compareTo(member.fname);
        }
        return compare;
        
    }
    /**
     * This method is used to compare two members by their county.
     * The comparison is based on the member's gym location.
     * @param member Member object to compare location of member
     * @return int return the lexicographical difference between the two members's county.
     */
    public int compareCounty(Member member){
        //int compare = location.toString().compareTo(member.location.toString());
        int compare = location.getCounty().compareTo(member.location.getCounty());
        if(compare == 0){
            compare = location.getCounty().compareTo(member.location.getCounty());
        }
        if(compare == 0){
            compare = location.getZipCode().compareTo(member.location.getZipCode());
        }
        return compare;
    }
    /**
     * This method is used to return the date of birth of the member.
     * @return Date date of birth of the member.
     */
    public Date getDOB(){
        return dob;
    }
    /**
     * This method is used to return the first name of the member.
     * @return String first name of the member
     */
    public String getFirstName(){
        return fname;
    }
     /**
     * This method is used to return the last name of the member.
     * @return String last name of the member
     */
    public String getLastName(){
        return lname;
    }
     /**
     * This method is used to return the membership expiration of the member.
     * @return Date expiration date of the membership
     */
    public Date getExpire(){
        return expire;
    }
        /**
        * This method is used to return the location of the member.
        * @return Location location of the member
        */
    public Location getLocation(){
        return location;
    }

    public static void main(String[] args) {

        System.out.println("Test Case #1:");
        Member member1a = new Member("Roy", "Brooks", new Date("9/2/2022"));
        Member member1b = new Member("Jane", "Doe", new Date("12/2/2022"));
        System.out.println("Expected output: 2 or -2");
        System.out.println("Output: " + member1a.compareTo(member1b));
        System.out.println();

        System.out.println("Test Case #2:");
        Member member2a = new Member("Paul", "Scanlan", new Date("9/2/2022"));
        Member member2b = new Member("Jack", "Scranton", new Date("12/2/2022"));
        System.out.println("Expected output: 17 or -17");
        System.out.println("Output: " + member2a.compareTo(member2b));
        System.out.println();

        System.out.println("Test Case #3:");
        Member member3a = new Member("Bary", "Brooks", new Date("9/2/2022"));
        Member member3b = new Member("Roy", "Brooks", new Date("12/2/2022"));
        System.out.println("Expected output: 16 or -16");
        System.out.println("Output: " + member3a.compareTo(member3b));
        System.out.println();

        System.out.println("Test Case #4:");
        Member member4a = new Member("Jack", "Doe", new Date("9/2/2022"));
        Member member4b = new Member("John", "Doe", new Date("12/2/2022"));
        System.out.println("Expected output: 14 or -14");
        System.out.println("Output: " + member4a.compareTo(member4b));
        System.out.println();
           
    }
}