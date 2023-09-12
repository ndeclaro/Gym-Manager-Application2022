package gym_manager;
import java.util.ArrayList;
/**
* This fitness class is used to add, remove, print, and sort in a certain class.
* @author Vinay Kumar, Noel Declaro
*/
public class FitnessClass{
    private String instructorName;
    private String fitnessClass;
    private Location classLocation;
    private Time classTime;
    private Member [] mlist;
    private int size;
    private static final int BASE_SIZE = 4;
   private ArrayList<Member> guestList = new ArrayList<>();
    /**
     * This constructor is used to create a fitness class.
     * @param instructorName the name of the instructor
     * @param fitnessClass the name of the class
     * @param classTime the time of the class
     * @param classLocation the location of the fitness class
    */
    public FitnessClass(String fitnessClass, String instructorName, Time classTime, Location classLocation) {
        this.instructorName = instructorName;
        this.fitnessClass = fitnessClass;
        this.classTime = classTime;
        this.classLocation = classLocation;
        mlist = new Member[BASE_SIZE];
        size = 0;
    }

    public FitnessClass(String fitnessClass, String instructorName, Location classLocation) {
        this.instructorName = instructorName;
        this.fitnessClass = fitnessClass;
        this.classLocation = classLocation;
        mlist = new Member[BASE_SIZE];
        size = 0;
    }

    /**
     * This method is used to find if a member exists in the class.
     * @param member you want to find in the class
     * @return Integer index of the member in the class
     */
    public int find(Member member){
        int found = -1;
        for(int i = 0; i < size; i++){
            Member m = new Member(member.getFirstName(), member.getLastName(), member.getDOB());
            Member onMlist = new Member(mlist[i].getFirstName(), mlist[i].getLastName(), mlist[i].getDOB());
            if(m.equals(onMlist))
                found = i;
        }
        return found;
    }

    /**
     * Method to remove inputted member
     * @param member Member object to pass
     * @return true if member was removed false either wise
     */
    public boolean removeParticipant(Member member){
        int index = find(member);
        if(index != -1){
            for (int i = index; i < size; i++)
                mlist[i] = mlist[i + 1];
            size--;
        System.out.println(member.getFirstName() + " " + member.getLastName() + " dropped " + fitnessClass);
            return true;
        }else{
            System.out.println(member.getFirstName() + " " + member.getLastName() + " is not in " + fitnessClass);
            return false;
        }
    
    }

    /**
     * This method is used to increase the size of the class.
     */
    private void grow(){
        Member [] temp = new Member[mlist.length + 1];
        for (int i = 0; i < mlist.length; i++)
            temp[i] = mlist[i];
        mlist = temp;
    }

    /**
     * This method is used to add a member to the class.
     * The method checks if the member already exists in the class.
     * If the member does exist, it increases the size of the class array
     * and adds the member is added to the class.
     * @param member member object to add in the class
     * @return Boolean true if the member is successfully added to the class,
     * false if the member is already in the class
     */
    public boolean checkIn(Member member){
         if (find(member) == -1) {
            if (size == mlist.length - 1)
                grow();
            mlist[size] = member;
            size++;
            System.out.println(member.getFirstName() + " " + member.getLastName() +
                    " has been added to " + fitnessClass + " class");
            return true;
        } else{
            System.out.println(member.getFirstName() + " " + member.getLastName() +
                    " has already checked in " + fitnessClass );
            return false;
        }
    }

    /**
     * This method adds members to the guest list
     * @param member object to add to guest list
     * @return true if guest is checked false eitherwise
     */
    public boolean checkInGuest(Member member){
        //guest members will be added to an arraylist instead of the regular member array
        System.out.println(member.getFirstName() + " " + member.getLastName() + " (GUEST) checked into: ");
        guestList.add(member);
        printClass();
        return true;
    }

    /**
     * removes guest from the guest list
     * @param member to remove from the guest list
     * @return true if guest is removed otherwise false
     */
    public boolean dropGuest(Member member){
        for(int i = 0; i < guestList.size(); i++){
            Member inList = (Member) guestList.get(i);
            Member toRemove = (Member) member;
            if(inList.getFirstName().equals(toRemove.getFirstName())){
                guestList.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used print participating members in each respective class
     *
     */
    public void printClass(){
        System.out.println(fitnessClass + " - " + instructorName + " - " + classTime.getDateTime() + ", " + classLocation);
        //prints regular members
        if(mlist[0] != null){
            System.out.println("\t** particpants **");
            for (int i = 0; i < size; i++)
                System.out.println("\t" + mlist[i].toString());
        }
        //prints guests
        if(!guestList.isEmpty()) {
            System.out.println("\t** guests **");
            for (Member guestList : guestList)
                System.out.println("\t" + guestList.toString());
        }
    }

    /**
     * method to check if there are participants in the class
     * @return true if there are participants otherwise false
     */
    public boolean participantsExists(){
        if(mlist[0] == null)
            return false;
        return true;
    }

    /**
     * method that returns memberlist of respective class
     * @return string that contains list of members
     */
    public String getClassMembers(){
        String memberText = "\t** particpants **\n";
        for(int i = 0; i < size; i++)
            memberText += "\t" + mlist[i].toString() + "\n";
        return memberText;
    }

    /**
     * method to check if there are guests in the guestlists
     * @return true if guests are present false otherwise
     */
    public boolean guestsExists(){return !guestList.isEmpty(); }

    /**
     * method that returns a list of guests in the class
     * @return string that contains the lists of guests
     */
    public String getGuests(){
        String memberText = "\t** guests **\n";
        for (Member guestList : guestList)
            memberText += "\t" + guestList.toString() + "\n";
        return memberText;
    }
    /**
     * method used to check if class is existent
     * @param other fitness class to compare
     * @return true if class is there false otherwise
     */
    public boolean equals(FitnessClass other)
    {
        return this.instructorName.equals(other.instructorName) && this.fitnessClass.equals(other.fitnessClass) &&
                this.classLocation.equals(other.classLocation);
    }

    /**
     * this method prints the format of the class
     * @return string that contains the class information
     */
    @Override
    public String toString(){ return fitnessClass + " - " + instructorName + " - " +
            classTime.getDateTime() + ", " + classLocation;
    }

    /**
     * method that returns class time
     * @return classTime Time object
     */
    public Time getClassTime() {
        return classTime;
    }

    /**
     * method that returns the class
     * @return string of class name
     */
    public String getFitnessClass(){ return fitnessClass; }

    /**
     * method that returns the instructor name
     * @return returns the instructor name
     */
    public String getInstructorName(){ return  instructorName;}

    /**
     * method that returns the classtime
     * @return string that contains the class time format
     */
    public String getTimeOfClass(){ return classTime.getDateTime();}
    /**
     * method that returns the class location
     * @return classLocation location object to return
     */
    public Location getClassLocation(){
        return classLocation;
    }
}