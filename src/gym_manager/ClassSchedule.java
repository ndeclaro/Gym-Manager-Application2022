package gym_manager;

/**
 * This ClassSchedule Class is to manage the list of fitness class objects.
 * @author Vinay Kumar, Noel Declaro
 */
public class ClassSchedule {
    private FitnessClass [] classes;
    private int numClasses;
    private static final int BASE_numClasses = 4;

    /**
     * constructor used to initialize the class database.
     */
    public ClassSchedule(){
        numClasses = 0;
        classes = new FitnessClass[BASE_numClasses];
    }

    /**
     * increases the FitnessClass list
     */
    private void grow(){
        FitnessClass [] temp = new FitnessClass[classes.length + 1];
        for (int i = 0; i < classes.length; i++)
            temp[i] = classes[i];
            classes = temp;
    }

    /**
     * The addClass method takes a fitclass object and adds it to the list
     * @param fclass fitness class object created by user to make
     * @return true if the class is added otherwise false
     */
    public boolean addClass(FitnessClass fclass){
        if (findClass(fclass) == null) {
           if (numClasses == classes.length - 1)
               grow();
           classes[numClasses] = fclass;
           numClasses++;
           return true;
       } else{
           return false;
       }
   }

    /**
     * Method used to access the a class in the list
     * @param i integer rank of class in list
     * @return returns the class
     */
    public FitnessClass getClasses(int i) {
        if(i >= numClasses)
            return null;
        return classes[i];
    }

    /**
     * This method finds and returns the class in the class list
     * @param fitnessClass object to compare into the class list
     * @return returns the matching class object in the class list
     */
    public FitnessClass findClass(FitnessClass fitnessClass) {
        int i;
        for( i=0; i<numClasses; i++) {
            if(classes[i].equals(fitnessClass))
                return classes[i];
        }
        return null;
    }

    /**
     * method to print the list of of classes
     * @return true if there are classes to print. false otherwise
     */
    public boolean printClasses(){
        if(classes[0] != null) {
            System.out.println("-Fitness Classes-");
            for (int i = 0; i < numClasses; i++)
                classes[i].printClass();
        }
        else
            return false;
        return true;
    }

    /**
     * This method adds finds the inputted class and trys to add the member
     * @param member object to add to class
     * @param fitnessClass object to compare class search
     * @return false if member could not be added
     */
    public boolean addMemberToClass(Member member, FitnessClass fitnessClass)
    {
        FitnessClass fclass = findClass(fitnessClass);
        if(fclass != null)
        {
            return fclass.checkIn(member);
        }
        return false;
    }

    /**
     * This method adds a guest to a class
     * @param member object to add into class
     * @param fitnessClass object compare to in the class list
     */
    public void addGuestToClass(Member member, FitnessClass fitnessClass)
    {
        FitnessClass fclass = findClass(fitnessClass);
        if(fclass != null)
        {
            fclass.checkInGuest(member);
        }
    }

    /**
     * This method removes a member in a fitness class
     * @param member object to remove in possible class
     * @param fitnessClass object to compare in class search
     * @return true if member removed otherwise false
     */
    public boolean removeMemberFromClass(Member member, FitnessClass fitnessClass)
    {
        FitnessClass fclass = findClass(fitnessClass);
        if(fclass != null)
        {
            return fclass.removeParticipant(member);
        }
        return false;
    }

    /**
     * This method removes a guest from a class
     * @param member object to check class for
     * @param fitnessClass object to look for class
     * @return true if the guest was removed false otherwise
     */
    public boolean removeGuestFromClass(Member member, FitnessClass fitnessClass)
    {
        FitnessClass fclass = findClass(fitnessClass);
        if(fclass != null)
        {
           if(fclass.dropGuest(member) == true)
            {
                 return true;
            }
        }
        return false;
    }

    /**
     * this method checks for time constraits with other checked in classes
     * @param member object to see if member is in class
     * @param fitnessClass object to search for in class list
     * @return true if there is a time constraint with the given member, false otherwise.
     */
    public boolean clashingClasses(Member member, FitnessClass fitnessClass)
    {
        boolean clash = false;
        FitnessClass fclass = findClass(fitnessClass);

        for(int i=0; i<numClasses; i++)
        {
            if(classes[i].find(member) != -1)
            {
                if(classes[i].getClassTime().equals(fclass.getClassTime()))
                {
                    clash = true;
                }
            }
        }
        return clash;
    }
    
    




 

}

