package gym_manager;
/**
This enum Location class is used to specify the location of the gym.
This enum class includes the county, zip code, and the city of the gym.
@author Vinay Kumar, Noel Declaro
*/
public enum Location {
    BRIDGEWATER ("08807","SOMERSETCOUNTY"),
     EDISON ("08837","MIDDLESEXCOUNTY"), 
     FRANKLIN("08873", "SOMERSETCOUNTY"), 
      PISCATAWAY ("08854", "MIDDLESEXCOUNTY"),
      SOMERVILLE ("08876", "SOMERSETCOUNTY");
      private final String zipCode;
      private final String county;

    /**
     * Location constructor that creates object to hold zipcode
     * and city of the gym
     * @param zipCode string that contains the zip code
     * @param county string that contains the county
     */
      Location (String zipCode, String county){
          this.zipCode = zipCode;
          this.county = county;
      }

      /**
       * This method returns the zip code of the gym.
       * @return String the zip code of the gym
       */
      public String getZipCode(){
        return zipCode;
     }
     /**
       * This method returns the county of the gym.
       * @return String the county of the gym
       */
      public String getCounty(){
            return county;
     }
     
}
