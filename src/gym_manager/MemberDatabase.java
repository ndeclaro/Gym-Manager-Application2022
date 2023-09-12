package gym_manager;
/**
This  class stores all the members that are a part of the gym.
This class is used to add, remove, print, and sort members.
 It is also used to search for a member for the purpose of adding them to a class.
@author Vinay Kumar, Noel Declaro
*/
public class MemberDatabase {
    private Member [] mlist;
    private int size;
    private static final int BASE_SIZE = 4;
    
    public MemberDatabase() {
        mlist = new Member[BASE_SIZE];
        size = 0;

      }
     /**
    This  method is used to find if a member exists in the gym database.
    @param member you want to find in the database
    @return Integer index of the member in the database
    */
    private int find(Member member){
        int found = -1;
        for(int i = 0; i < size; i++){
            Member m =new Member(member.getFirstName(), member.getLastName(), member.getDOB());
            Member onMlist = new Member(mlist[i].getFirstName(), mlist[i].getLastName(), mlist[i].getDOB());
            if(m.equals(onMlist)){ found = i; }
        }
        return found;
    }

    /**
     * this method is used to return a member in the database
     * @param i rank of member in database
     * @return member of the respective rank
     */
    public Member findMember(int i){
        if(i >= size)
            return null;
        return mlist[i];
    }

     /**
    This  method is used to increase the size of the database.
    */
    private void grow(){
        Member [] temp = new Member[mlist.length + 1];
        for (int i = 0; i < mlist.length; i++) {
            
            temp[i] = mlist[i];
        }
        mlist = temp;
    }
     /**
    This  method is used to add a member to the gym database.
    The method checks if the member already exists in the database. If the member does not exist,
      it increases the size of the database array
    and adds the member is added to the database.
    @param member you want to add in the database
    @return Boolean true if the member is successfully added to the database,
    false if the member is already in the database
    */
    public boolean add(Member member){
        //Add member to array
        if (find(member) == -1) {
            if (size == mlist.length - 1)
                grow();
            mlist[size] = member;
            size++;
            return true;
        }
        return false;
    }
     /**
    This  method is used to remove a member in the gym database.
    The method checks if the member exists in the database. If the member does exist,
      it removes the member from the database.
    @param member you want to find in the database
    @return Boolean true if the member is successfully removed from the database,
    false if the member is not in the database
    */
    public boolean remove(Member member){
        //Remove member from the array
        int index = find(member);
        if (index != -1) {
            for (int i = index; i < size; i++) {
                mlist[i] = mlist[i + 1];
            }
            size--;
            return true;
        }
        return false;
    }
     /**
    This  method is called to print the members in the gym database.
    */
    public void print(){
        if(mlist[0] != null){
            System.out.println("-list of members-");
            for (int i = 0; i < size; i++){
                System.out.println(mlist[i].toString());
        }
            System.out.println("- end of list -");
        }
        else
            System.out.println("Member database is empty!");
    }
    
    /**
    This  method is called to print the members in the gym database sorted by their gym county.
    */
    public void printByCounty(){
        if(mlist[0] != null) {
            System.out.println("-list of members sorted by county and zipcode-");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (mlist[i].compareCounty(mlist[j]) < 0) {
                        Member temp = mlist[i];
                        mlist[i] = mlist[j];
                        mlist[j] = temp;
                    }
                }
            }
            //printing
            for (int i = 0; i < size; i++)
                System.out.println(mlist[i].toString());
            System.out.println("- end of list -");
        }
        else
            System.out.println("Member database is empty!");

    }
    /**
    This  method is called to print the members in the gym database sorted by their gym membership expiration.
    */
    public void printByExpirationDate(){
        if (mlist[0] != null) {
            System.out.println("-list of members sorted by membership expiration date-");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (mlist[i].getExpire().compareTo(mlist[j].getExpire()) < 0) {
                        Member temp = mlist[i];
                        mlist[i] = mlist[j];
                        mlist[j] = temp;
                    }
                }
            }
            //printing
            for (int i = 0; i < size; i++)
                System.out.println(mlist[i].toString());
            System.out.println("- end of list -");
        }
        else
            System.out.println("Member database is empty!");
    }

    /**
    This  method is called to print the members in the gym database sorted by their last name.
    */
    public void printByName(){
        if(mlist[0] != null) {
            System.out.println("list of members sorted by last name, and first name-");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (mlist[i].compareTo(mlist[j]) < 0) {
                        Member temp = mlist[i];
                        mlist[i] = mlist[j];
                        mlist[j] = temp;
                    }
                }
            }
            //printing
            for (int i = 0; i < size; i++)
                System.out.println(mlist[i].toString());
            System.out.println("- end of list -");
        }
        else
            System.out.println("Member database is empty!");
    }

    public void printByMembershipFee(){
        if(mlist[0] != null) {
            System.out.println("-list of members with membership fee-");
            //printing
            for (int i = 0; i < size; i++)
                System.out.println(mlist[i].toString() + ", Membership Fee: $" + mlist[i].membershipFee());
                System.out.println("- end of list -");
             }
             else
                System.out.println("Member database is empty!");
    }



    /**
    This  method is used to see if a member exists in the database
    The method checks if the member exists in the database. If the member does exist,
     it returns the member. If the member does not exist, it returns null.
    @param member you want to find in the database
    @return Member if the member is in the database, null if the member is not in the database
    */
    public Member memberExists(Member member){
        //Check if member exists in the array
        if (find(member) != -1) {
        Member temp = mlist[find(member)];
        return temp;
        }
        return null;
    }

    /**
     * returns if member inputed is a instance of family or premium
     * @param member object to check member type
     * @return true if member is an instance of premium or family
     */
    public boolean typeOfMember(Member member){
        if(member instanceof Family  || member instanceof Premium){
            return true;
        }
            return false;
    }
}