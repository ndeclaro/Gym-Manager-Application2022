package gym_manager;
/**
This enum class is used to specify the classes and their instructors and times.
This enum class includes the class name, instructor name, and the time of the class.
It also includes a method to get the, instructor name, and time of the class.
@author Vinay Kumar, Noel Declaro
*/
public enum Time{
    MORNING("09:30" ),
    AFTERNOON("14:00"),
    EVENING("18:30");
    private final String dateTime;

    Time(String dateTime){
        this.dateTime = dateTime;
       
    }
     /**
    This method returns the time of the class.
    @return the time of the class
    */
    public String getDateTime(){
        return dateTime;
    }
     /**
    This method returns the instructor of the class.
    @return the name of the instructor
   
    */
   

}




