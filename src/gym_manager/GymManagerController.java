package gym_manager;

/**
 * This GymManagerController Class is to manage i/o of gym manager package
 * @author Vinay Kumar, Noel Declaro
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;

public class GymManagerController {
    //member adding data
    @FXML
    private TextField firstNameForAddingMember, lastNameForAddingMember , locationForAddingMember;
    @FXML
    private DatePicker dobForAddingMember;
    @FXML
    private RadioButton premiumMembership, familyMembership, standardMembership;
    @FXML
    private TextArea loadMemberTextArea;
    @FXML
    private TextArea addMemberTextArea;

    //member removing data
    @FXML
    private TextField firstNameForRemove, lastNameForRemove;
    @FXML
    private DatePicker dobForRemove;
    @FXML
    private TextArea removalText;
    @FXML
    private TextArea loadFitnessClassText;

    //adding fitness class fields
    @FXML
    private TextField classNameForAddingFitnessMember;
    @FXML
    private TextField instructorNameForAddingFitnessMember;
    @FXML
    private TextField LocationForAddingFitnessMember;
    @FXML
    private TextField firstNameForAddingFitnessMember;
    @FXML
    private TextField lastNameForAddingFitnessMember;
    @FXML
    private DatePicker dobForAddingFitnessMember;
    @FXML
    private TextArea addFitnessMemberText;

    //Dropping Class Tab
    @FXML
    private TextField classNameForDroppingFitnessMember;
    @FXML
    private TextField firstNameForDroppingFitnessMember;
    @FXML
    private TextField lastNameForDroppingFitnessMember;
    @FXML
    private TextField locationForDroppingFitnessMember;
    @FXML
    private TextField instructorNameForDroppingFitnessMember;
    @FXML
    private DatePicker dobForDroppingFitnessMember;
    @FXML
    private TextArea dropFitnessMemberText;

    //Dropping Class Guest Tab
    @FXML
    private TextField classNameForDroppingFitnessGuest;
    @FXML
    private TextField firstNameForDroppingFitnessGuest;
    @FXML
    private TextField lastNameForDroppingFitnessGuest;
    @FXML
    private TextField locationForDroppingFitnessGuest;
    @FXML
    private TextField instructorNameForDroppingFitnessGuest;
    @FXML
    private DatePicker dobForDroppingFitnessGuest;
    @FXML
    private TextArea dropFitnessGuestText;

    //guest checkin tab
    @FXML
    private TextField classNameForAddingFitnessGuest;
    @FXML
    private TextField instructorNameForAddingFitnessGuest;
    @FXML
    private TextField LocationForAddingFitnessGuest;
    @FXML
    private TextField firstNameForAddingFitnessGuest;
    @FXML
    private TextField lastNameForAddingFitnessGuest;
    @FXML
    private DatePicker dobForAddingFitnessGuest;
    @FXML
    private TextArea addFitnessGuestText;

    //information tab
    @FXML
    private TextArea informationPrint;

    private final MemberDatabase memberDatabase = new MemberDatabase();
    private final ClassSchedule classSchedule = new ClassSchedule();

    //sanitze textfields
    //check if textfields are empty

    /**
     * Method to check empty text fields
     * @param textField text field to input
     * @return true if field is empty false otherwise
     */
    public boolean checkEmpty(TextField textField) {
        if (textField.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * method to check the date field
     * @param datePicker field to input
     * @return true if the field is empty false otherwise
     */
    public boolean checkEmptyDate(DatePicker datePicker) {
        if (datePicker.getValue() == null) {
            return true;
        }
        return false;
    }

    /**
     * handler for the add member button
     */
    @FXML
    void addMember() {
        if (checkEmpty(lastNameForAddingMember) || checkEmpty(locationForAddingMember) ||
                checkEmptyDate(dobForAddingMember)) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("One or more fields are empty.");
            alert.setContentText("Please fill out all fields.");
            alert.showAndWait();
        }
        else {
            if(standardMembership.isSelected()) {
                addStandardMember();
            }
            else if (familyMembership.isSelected()) {
                addFamilyMember();
            }
            else if (premiumMembership.isSelected()) {
                addPremiumMember();
            }
            else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning!!");
                alert.setHeaderText("No membership type selected.");
                alert.setContentText("Please select a membership type.");
                alert.showAndWait();
            }
        }
    }

    /**
     * method to add standard member
     */
    @FXML
    void addStandardMember() {
        String firstName = firstNameForAddingMember.getText();
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        String lastName = lastNameForAddingMember.getText();
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
        //get string from datepicker in format MM/DD/YYYY
        LocalDate myDate = dobForAddingMember.getValue();
        String dob = myDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String expire = dateAdd(3);
        Date birthday = new Date(dob);
        Date expiration = new Date(expire);
        String location = locationForAddingMember.getText();
        Location location2 = getLocation(location);
        if(birthday.BirthdayIsValid() && location2 != null && expiration.isValid()
                && birthday.isValid() && birthday.IsOlderThan18()){
            Member member = new Member(firstName, lastName,  birthday, expiration, location2);
            boolean memberAdded = memberDatabase.add(member);
            if (memberAdded) {
                addMemberTextArea.setText("Member added successfully.");
            }
            else {
                addMemberTextArea.setText("Member already exists.");
            }
        }else{
            if(birthday.BirthdayIsValid() == false){
                addMemberTextArea.setText("\n " + "DOB " +
                        birthday.DateToString() + " cannot be today or a future date!");
            }
            else if(birthday.IsOlderThan18() == false){ addMemberTextArea.setText("\n " +"DOB " +
                    birthday.DateToString() + " Must be 18 or older to join"); }
            else if(expiration.isValid() == false){ addMemberTextArea.setText("\n " +"Expiration date " +
                    expiration.DateToString() + ": Invalid calendar date!"); }
            else if(birthday.isValid() == false){ addMemberTextArea.setText("\n " +"DOB " +
                    birthday.DateToString() + ": Invalid calendar date!"); }
            else if(location2 == null){ addMemberTextArea.setText("\n " + location + ": Invalid location!"); }
        }
    }

    /**
     * method to add Family Membership
     */
    @FXML
    void addFamilyMember() {
        String firstName = firstNameForAddingMember.getText();
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        String lastName = lastNameForAddingMember.getText();
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
        //get string from datepicker in format MM/DD/YYYY
        LocalDate myDate = dobForAddingMember.getValue();
        String dob = myDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String expire = dateAdd(3);
        Date birthday = new Date(dob);
        Date expiration = new Date(expire);
        String location = locationForAddingMember.getText();
        Location location2 = getLocation(location);
        if(birthday.BirthdayIsValid() && location2 != null && expiration.isValid()
                && birthday.isValid() && birthday.IsOlderThan18()){
            Family member = new Family(firstName, lastName,  birthday, expiration, location2);
            boolean memberAdded = memberDatabase.add(member);
            if (memberAdded) {
                addMemberTextArea.setText("Member added successfully.");
            }
            else {
                addMemberTextArea.setText("Member already exists.");
            }
        }else{
            if(birthday.BirthdayIsValid() == false){
                addMemberTextArea.setText("\n " + "DOB " +
                        birthday.DateToString() + " cannot be today or a future date!");
            }
            else if(birthday.IsOlderThan18() == false){ addMemberTextArea.setText("\n " +"DOB " +
                    birthday.DateToString() + " Must be 18 or older to join"); }
            else if(expiration.isValid() == false){ addMemberTextArea.setText("\n " +"Expiration date " +
                    expiration.DateToString() + ": Invalid calendar date!"); }
            else if(birthday.isValid() == false){ addMemberTextArea.setText("\n " +"DOB " +
                    birthday.DateToString() + ": Invalid calendar date!"); }
            else if(location2 == null){ addMemberTextArea.setText("\n " + location + ": Invalid location!"); }
        }
    }

    /**
     * method to add Premium Member
     */
    @FXML
    void addPremiumMember() {
        String firstName = firstNameForAddingMember.getText();
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        String lastName = lastNameForAddingMember.getText();
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
        //get string from datepicker in format MM/DD/YYYY
        LocalDate myDate = dobForAddingMember.getValue();
        String dob = myDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String expire = dateAdd(12);
        Date birthday = new Date(dob);
        Date expiration = new Date(expire);
        String location = locationForAddingMember.getText();
        Location location2 = getLocation(location);
        if(birthday.BirthdayIsValid() && location2 != null && expiration.isValid()
                && birthday.isValid() && birthday.IsOlderThan18()){
            Premium member = new Premium(firstName, lastName,  birthday, expiration, location2);
            boolean memberAdded = memberDatabase.add(member);
            if (memberAdded) {
                addMemberTextArea.setText("Member added successfully.");
            }
            else {
                addMemberTextArea.setText("Member already exists.");
            }
        }else{
            if(birthday.BirthdayIsValid() == false){
                addMemberTextArea.setText("\n " + "DOB " +
                        birthday.DateToString() + " cannot be today or a future date!");
            }
            else if(birthday.IsOlderThan18() == false){ addMemberTextArea.setText("\n " +"DOB " +
                    birthday.DateToString() + " Must be 18 or older to join"); }
            else if(expiration.isValid() == false){ addMemberTextArea.setText("\n " +"Expiration date " +
                    expiration.DateToString() + ": Invalid calendar date!"); }
            else if(birthday.isValid() == false){ addMemberTextArea.setText("\n " +"DOB " +
                    birthday.DateToString() + ": Invalid calendar date!"); }
            else if(location2 == null){ addMemberTextArea.setText("\n " + location + ": Invalid location!"); }
        }
    }

    /**
     * handler for remove member button
     */
    @FXML
    void removeMember(){
        //check if anything is empty
        if(checkEmpty(firstNameForRemove) || checkEmpty(lastNameForRemove) || checkEmptyDate(dobForRemove)){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("One or more fields are empty.");
            alert.setContentText("Please fill out all fields.");
            alert.showAndWait();
        }else{
            String firstName = firstNameForRemove.getText();
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
            String lastName = lastNameForRemove.getText();
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
            LocalDate myDate = dobForRemove.getValue();
            String dob = myDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            Date birthday = new Date(dob);
            Member member = new Member(firstName, lastName, birthday);
            if(memberDatabase.remove(member))
                removalText.setText("Member removed successfully.");
            else
                removalText.setText("Member does not exist.");
        }
    }

    /**
     * method/handler to import member file
     */
    @FXML
    void importMember() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Source File for the Import");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File sourceFile = chooser.showOpenDialog(stage);
        Scanner inputStream = null;
        String textForTextArea = "";
        try {
            inputStream = new Scanner(sourceFile);
            textForTextArea = "-list of members loaded-" + "\n";
            while(inputStream.hasNextLine()){
                String line = inputStream.nextLine();
                StringTokenizer st = new StringTokenizer(line);
                String fname = st.nextToken();
                fname = fname.substring(0,1).toUpperCase() + fname.substring(1).toLowerCase();
                String lname = st.nextToken();
                lname = lname.substring(0,1).toUpperCase() + lname.substring(1).toLowerCase();
                String dob = st.nextToken();
                String expire = st.nextToken();
                String location = st.nextToken();
                Date birthday = new Date(dob);
                Date expiration = new Date(expire);
                Location location2 = getLocation(location);
                Member member = new Member(fname, lname,  birthday, expiration, location2);
                boolean memberAdded = memberDatabase.add(member);
                if(memberAdded)
                    textForTextArea += " " + member.toString() + "\n";
            }
            textForTextArea += "-end of list-";
            loadMemberTextArea.setText(textForTextArea);
        } catch (FileNotFoundException e) {
            loadMemberTextArea.setText("File not found.");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *  method/handler to import class schedule
     */
    @FXML
    void importClasses() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Source File for the Import");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File sourceFile = chooser.showOpenDialog(stage);
        Scanner inputStream = null;
        String textForTextArea = "";
        try{
            inputStream = new Scanner(sourceFile);
            textForTextArea = "-Fitness Classes loaded-" + "\n";
            while(inputStream.hasNextLine()){
                String line = inputStream.nextLine();
                if(line.length() > 0){
                    StringTokenizer st = new StringTokenizer(line);
                    String className = st.nextToken();
                    String InstructorName = st.nextToken();
                    String time = st.nextToken();
                    String location = st.nextToken();
                    Location location2 = getLocation(location);
                    Time time2 = getTime(time);
                    FitnessClass fclass = new FitnessClass(className, InstructorName, time2, location2);
                    if(classSchedule.addClass(fclass)){
                        String classdisplay = className + " - " + InstructorName + " - " + time2.getDateTime() +
                                " - " + location2 + "\n";
                        textForTextArea += classdisplay;
                    }
                }
            }
            textForTextArea += "-end of list-";
            loadFitnessClassText.setText(textForTextArea);
        }catch (FileNotFoundException e) {
            loadFitnessClassText.setText("File not found.");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * handler for member check in
     */
    @FXML
    void checkIn(){
        if (checkEmpty(classNameForAddingFitnessMember) || checkEmpty(instructorNameForAddingFitnessMember) ||
                checkEmpty(LocationForAddingFitnessMember) || checkEmpty(firstNameForAddingFitnessMember) ||
                checkEmpty(lastNameForAddingFitnessMember) || checkEmptyDate(dobForAddingFitnessMember)) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("One or more fields are empty.");
            alert.setContentText("Please fill out all fields.");
            alert.showAndWait();
        } else {
            FitnessClass fclass = checkInHelperClass();
            Member member = checkInHelperMember();
            if(memberDatabase.memberExists(member) != null && classExists(fclass) &&
                    member.getDOB().isValid() && member.getDOB().IsOlderThan18() && member.getDOB().BirthdayIsValid() ){
                Member memberInDataBase = memberDatabase.memberExists(member);
                Date memberExpiration = memberInDataBase.getExpire();
                FitnessClass fclassconfirmed = classSchedule.findClass(fclass);
                if(memberExpiration.MembershipDateIsValid())
                    processCheckin(memberInDataBase, fclassconfirmed);
                else addFitnessMemberText.setText(member.getFirstName() + " " + member.getLastName() + " "
                        + member.getDOB().DateToString() + " membership expired");
            }
            else if(member.getDOB().isValid() == false) addFitnessMemberText.setText("DOB " +
                    member.getDOB().DateToString() + ": " + " invalid calendar date!");
            else if(memberDatabase.memberExists(member) == null)addFitnessMemberText.setText( member.getFirstName() +
                    " " + member.getLastName() + " " + member.getDOB().DateToString() + " is not in the database.");
            else if(!classExists(fclass))
                addFitnessMemberText.setText("class does not exists");
        }
    }

    /**
     * helper method to checkIn
     * @return FitnessClass object from inputs
     */
    FitnessClass checkInHelperClass(){
        String className = classNameForAddingFitnessMember.getText();
        className = className.substring(0,1).toUpperCase() + className.substring(1).toLowerCase();
        String instructorName = instructorNameForAddingFitnessMember.getText();
        instructorName = instructorName.substring(0,1).toUpperCase() + instructorName.substring(1).toLowerCase();
        String locationGet = LocationForAddingFitnessMember.getText();
        Location location = getLocation(locationGet);
        FitnessClass fclass = new FitnessClass(className, instructorName, location);
        return fclass;
    }

    /**
     * helper method to checkIn
     * @return Member object from inputs
     */
    Member checkInHelperMember(){
        String firstName = firstNameForAddingFitnessMember.getText();
        firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
        String lastName = lastNameForAddingFitnessMember.getText();
        lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
        LocalDate myDate = dobForAddingFitnessMember.getValue();
        String dob = myDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Date birthday = new Date(dob);
        Member member = new Member(firstName, lastName, birthday);
        return member;
    }

    /**
     *  helper method to help process checking in guest for the commandC method
     * @param member member object to access
     * @param fclass Fitness class object to access
     */
    void processCheckin(Member member,FitnessClass fclass){
        if(!classSchedule.clashingClasses(member, fclass)){
            if(!memberDatabase.typeOfMember(member)){
                if(member.getLocation().equals(fclass.getClassLocation())){
                    if(classSchedule.addMemberToClass(member, fclass))
                        addFitnessMemberText.setText("Member Checked In");
                }
                else
                    addFitnessMemberText.setText("Member is not allowed to attend class at this location");
            }else{
                if(classSchedule.addMemberToClass(member, fclass))
                    addFitnessMemberText.setText("Member Checked In");
                else
                    addFitnessMemberText.setText("Member is not allowed to attend class at this location");
            }
        }else{
            Member memberSearch = new Member(member.getFirstName(), member.getLastName(),  member.getDOB());
            if(fclass.find(memberSearch) == -1)
                addFitnessMemberText.setText("Time Conflict");
            else {
                classSchedule.addMemberToClass(member, fclass);
                addFitnessMemberText.setText("Member Already Checked In");
            }
        }
    }

    /**
     * handler to check in guest
     */
    @FXML
    void checkInGuest(){
        if (checkEmpty(classNameForAddingFitnessGuest) || checkEmpty(instructorNameForAddingFitnessGuest) ||
                checkEmpty(LocationForAddingFitnessGuest) || checkEmpty(firstNameForAddingFitnessGuest) ||
                checkEmpty(lastNameForAddingFitnessGuest) || checkEmptyDate(dobForAddingFitnessGuest)) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("One or more fields are empty.");
            alert.setContentText("Please fill out all fields.");
            alert.showAndWait();
        } else {
            FitnessClass fclass = guestCheckInClassHelper();
            Member member = guestCheckInMemberHelper();
            if(memberDatabase.memberExists(member) != null && classExists(fclass) &&
                    member.getDOB().isValid() && member.getDOB().IsOlderThan18() && member.getDOB().BirthdayIsValid()){
                Member memberInDataBase = memberDatabase.memberExists(member);
                Date memberExpiration = memberInDataBase.getExpire();
                Location memberLocation = memberInDataBase.getLocation();
                if(memberExpiration.MembershipDateIsValid()){
                    if(memberDatabase.typeOfMember(memberInDataBase)){
                        if(memberLocation.equals(fclass.getClassLocation())){
                            processGuest(memberInDataBase, fclass);
                        }else
                            addFitnessGuestText.setText("Cannot check-in at this location");
                    }else addFitnessGuestText.setText("Guest Check in-not allowed");
                }
                else addFitnessGuestText.setText(member.getFirstName() + " " + member.getLastName() + " "
                        + member.getDOB().DateToString() + " membership expired");
            }
            else if(member.getDOB().isValid() == false) addFitnessGuestText.setText("DOB " +
                    member.getDOB().DateToString() + ": " + " invalid calendar date!");
            else if(memberDatabase.memberExists(member) == null)addFitnessGuestText.setText( member.getFirstName() +
                    " " + member.getLastName() + " " + member.getDOB().DateToString() + " is not in the database.");
            else if(!classExists(fclass))
                addFitnessGuestText.setText("class does not exist");
        }
    }

    /**
     * Helper method for guestCheckin
     * @return fitness class from inputs
     */
    FitnessClass guestCheckInClassHelper(){
        String className = classNameForAddingFitnessGuest.getText();
        className = className.substring(0,1).toUpperCase() + className.substring(1).toLowerCase();
        String instructorName = instructorNameForAddingFitnessGuest.getText();
        instructorName = instructorName.substring(0,1).toUpperCase() + instructorName.substring(1).toLowerCase();
        String locationGet = LocationForAddingFitnessGuest.getText();
        Location location = getLocation(locationGet);
        FitnessClass fclass = new FitnessClass(className, instructorName, location);
        return fclass;
    }

    /**
     * Helper method for guestCheckin
     * @return Member from inputs
     */
    Member guestCheckInMemberHelper(){
        String firstName = firstNameForAddingFitnessGuest.getText();
        firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
        String lastName = lastNameForAddingFitnessGuest.getText();
        lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
        LocalDate myDate = dobForAddingFitnessGuest.getValue();
        String dob = myDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Date birthday = new Date(dob);
        Member member = new Member(firstName, lastName, birthday);
        return member;
    }

    /**
     * Helper method for checking in guests
     * @param guest Member object that contains the information related to the owner
     * @param fclass the class the guest is checking into
     */
    void processGuest(Member guest, FitnessClass fclass) {
        if(guest instanceof Premium){
            if(((Premium) guest).checkGuestPass() > 0){
                ((Premium) guest).decrementGuest();
                classSchedule.addGuestToClass(guest, fclass);
                addFitnessGuestText.setText("Guest Added To Class");
            } else
                addFitnessGuestText.setText("Member Ran out of guest pass");
        }else{
            if(((Family) guest).checkGuestPass() > 0){
                ((Family) guest).decrementGuest();
                classSchedule.addGuestToClass(guest, fclass);
                addFitnessGuestText.setText("Guest Added To Class");
            } else
                addFitnessGuestText.setText("Member Ran out of guest pass");
        }
    }

    /**
     * Handler for the drop class button
     */
    @FXML
    void dropClass(){
        if (checkEmpty(classNameForDroppingFitnessMember) || checkEmpty(instructorNameForDroppingFitnessMember) ||
                checkEmpty(locationForDroppingFitnessMember) || checkEmpty(firstNameForDroppingFitnessMember) ||
                checkEmpty(lastNameForDroppingFitnessMember) || checkEmptyDate(dobForDroppingFitnessMember)) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("One or more fields are empty.");
            alert.setContentText("Please fill out all fields.");
            alert.showAndWait();
        } else {
            String className = classNameForDroppingFitnessMember.getText();
            className = className.substring(0,1).toUpperCase() + className.substring(1).toLowerCase();
            String instructorName = instructorNameForDroppingFitnessMember.getText();
            instructorName = instructorName.substring(0,1).toUpperCase() + instructorName.substring(1).toLowerCase();
            String locationGet = locationForDroppingFitnessMember.getText();
            Location location = getLocation(locationGet);
            String firstName = firstNameForDroppingFitnessMember.getText();
            firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
            String lastName = lastNameForDroppingFitnessMember.getText();
            lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
            LocalDate myDate = dobForDroppingFitnessMember.getValue();
            String dob = myDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            Date birthday = new Date(dob);
            FitnessClass fclass = new FitnessClass(className, instructorName, location);
            Member member = new Member(firstName, lastName, birthday);
            if(!birthday.isValid())
                dropFitnessMemberText.setText("DOB " + birthday.DateToString() + ": invalid calendar date!");
            else if(classExists(fclass) == false)
                dropFitnessMemberText.setText(className + " Class does not exist.");
            else if(memberDatabase.memberExists(member) != null){
                if(classSchedule.removeMemberFromClass(member, fclass))
                    dropFitnessMemberText.setText("Member Dropped Class");
            }else
                dropFitnessMemberText.setText(firstName + " " + lastName + " is not a participant in " + className);
        }
    }

    /**
     * handler to dropGuest button
     */
    @FXML
    void dropGuest(){
        if (checkEmpty(classNameForDroppingFitnessGuest) || checkEmpty(instructorNameForDroppingFitnessGuest) ||
                checkEmpty(locationForDroppingFitnessGuest) || checkEmpty(firstNameForDroppingFitnessGuest) ||
                checkEmpty(lastNameForDroppingFitnessGuest) || checkEmptyDate(dobForDroppingFitnessGuest)) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("One or more fields are empty.");
            alert.setContentText("Please fill out all fields.");
            alert.showAndWait();
        } else{
            FitnessClass fclass = dropGuestClassHelper();
            Member member = dropGuestMemberHelper();
            if(memberDatabase.memberExists(member) != null && classExists(fclass) &&
                    member.getDOB().isValid() && member.getDOB().IsOlderThan18() && member.getDOB().BirthdayIsValid() ){
                Member memberInDataBase = memberDatabase.memberExists(member);
                Date memberExpiration = memberInDataBase.getExpire();
                Location memberLocation = memberInDataBase.getLocation();
                if(memberExpiration.MembershipDateIsValid()){
                    if(memberDatabase.typeOfMember(memberInDataBase)){
                        if(memberLocation.equals(fclass.getClassLocation()))
                            processRemovingGuest(memberInDataBase, fclass);
                        else dropFitnessGuestText.setText("Member is not allowed to attend class at this location");
                    }
                } else
                    dropFitnessGuestText.setText(member.getFirstName() + " " + member.getLastName()
                            + " " + member.getDOB().DateToString() + " membership expired");
            }
            else if(member.getDOB().isValid() == false) dropFitnessGuestText.setText("DOB " +
                    member.getDOB().DateToString() + ": " + " invalid calendar date!");
            else if(memberDatabase.memberExists(member) == null)dropFitnessGuestText.setText( member.getFirstName() +
                    " " + member.getLastName() + " " + member.getDOB().DateToString() + " is not in the database.");
            else if (!classExists(fclass))
                dropFitnessGuestText.setText("Class does not exists");
        }
    }

    /**
     * helper for dropGuest method
     * @return Fitness class from inputs
     */
    FitnessClass dropGuestClassHelper(){
        String className = classNameForDroppingFitnessGuest.getText();
        className = className.substring(0,1).toUpperCase() + className.substring(1).toLowerCase();
        String instructorName = instructorNameForDroppingFitnessGuest.getText();
        instructorName = instructorName.substring(0,1).toUpperCase() + instructorName.substring(1).toLowerCase();
        String locationGet = locationForDroppingFitnessGuest.getText();
        Location location = getLocation(locationGet);
        FitnessClass fclass = new FitnessClass(className, instructorName, location);
        return fclass;
    }

    /**
     * helper method for dropGuest
     * @return member from inputs
     */
    Member dropGuestMemberHelper(){
        String firstName = firstNameForDroppingFitnessGuest.getText();
        firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
        String lastName = lastNameForDroppingFitnessGuest.getText();
        lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
        LocalDate myDate = dobForDroppingFitnessGuest.getValue();
        String dob = myDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Date birthday = new Date(dob);
        Member member = new Member(firstName, lastName, birthday);
        return member;
    }

    /**
     * helper method for removing guests
     * @param guest member object to possibly remove
     * @param fclass fitness class to check if guest is existent
     */
    public void processRemovingGuest(Member guest, FitnessClass fclass) {
        if( guest instanceof Premium){
            if(classSchedule.removeGuestFromClass(guest, fclass) == true){
                ((Premium)guest).incrementGuest();
                dropFitnessGuestText.setText(guest.getFirstName() + " " +
                        guest.getLastName() + " Guest is done with class");
            } else
                dropFitnessGuestText.setText("guest not found in class");
        }else{
            Premium membershipHolder = new Premium(guest.getFirstName(), guest.getLastName(),
                    guest.getDOB(), guest.getExpire(), guest.getLocation());
            if(classSchedule.removeGuestFromClass(membershipHolder, fclass) == true){
                ((Family)guest).incrementGuest();
                dropFitnessGuestText.setText(guest.getFirstName() + " " + guest.getLastName() +
                        " Guest is done with class");
            } else
                dropFitnessGuestText.setText("guest not found in class");
        }
    }

    /**
     * handler to print members by county
     */
    @FXML
    public void printByCounty() {
        String textForTextArea = "-list of members by county-\n";
        if(memberDatabase.findMember(0) == null)
            informationPrint.appendText("Member list is empty\n");
        else {
            memberDatabase.printByCounty();
            int i = 0;
            while (memberDatabase.findMember(i) != null) {
                textForTextArea += memberDatabase.findMember(i).toString() + "\n";
                i++;
            }
            textForTextArea += "-end of list- \n";
            informationPrint.appendText(textForTextArea);
        }
    }

    /**
     * handler to print member by name
     */
    @FXML
    public void printByName() {
        String textForTextArea = "-list of members by name-\n";
        if(memberDatabase.findMember(0) == null)
            informationPrint.appendText("Member list is empty\n");
        else {
            memberDatabase.printByName();
            int i = 0;
            while (memberDatabase.findMember(i) != null) {
                textForTextArea += memberDatabase.findMember(i).toString() + "\n";
                i++;
            }
            textForTextArea += "-end of list- \n";
            informationPrint.appendText(textForTextArea);
        }
    }

    /**
     * handler to print members by expiration
     */
    @FXML
    public void printByExpiration() {
        String textForTextArea = "-list of members by expiration date-\n";
        if(memberDatabase.findMember(0) == null)
            informationPrint.appendText("Member list is empty\n");
        else {
            memberDatabase.printByExpirationDate();
            int i = 0;
            while (memberDatabase.findMember(i) != null) {
                textForTextArea += memberDatabase.findMember(i).toString() + "\n";
                i++;
            }
            textForTextArea += "-end of list- \n";
            informationPrint.appendText(textForTextArea);
        }
    }

    /**
     * handler to print members w/ membership fee
     */
    @FXML
    public void printByFee() {
        String textForTextArea = "-list of members w/ membership fees-\n";
        if(memberDatabase.findMember(0) == null)
            informationPrint.appendText("Member list is empty\n");
        else {
            int i = 0;
            while (memberDatabase.findMember(i) != null) {
                String member = memberDatabase.findMember(i).toString();
                Double fee = memberDatabase.findMember(i).membershipFee();
                textForTextArea += member + ", Membership Fee: $ " + fee + "\n";
                i++;
            }
            textForTextArea += "-end of list- \n";
            informationPrint.appendText(textForTextArea);
        }
    }

    /**
     * handler to print classes
     */
    @FXML
    public void printByClass(){
        String textForTextArea = "-list of classes-\n";
        if(classSchedule.getClasses(0) == null)
            informationPrint.appendText("Class list is empty\n");
        else{
            int i = 0;
            while(classSchedule.getClasses(i) != null){
                String classInfo = classSchedule.getClasses(i).toString() + "\n";
                textForTextArea += classInfo;
                if(classSchedule.getClasses(i).participantsExists())
                    textForTextArea += classSchedule.getClasses(i).getClassMembers();
                if(classSchedule.getClasses(i).guestsExists())
                    textForTextArea += classSchedule.getClasses(i).getGuests();
                i++;

            }
            textForTextArea += "-end of list- \n";
            informationPrint.appendText(textForTextArea);
        }
    }


    /**
     * This method is called to check if the location is valid.
     * It searches through the Location enum to see if the location is valid.
     * @param location a string with the location the user has entered
     * @return  a location object if the parameter is equal to a Location enum.
     * If it is not equal to a Location enum, it returns null.
     */
    public Location getLocation(String location){
        String locationToUppercase = location.toUpperCase();
        for(int i = 0; i < Location.values().length; i++){
            if(Location.values()[i].toString().equals(locationToUppercase))
                return Location.values()[i];
        }
        return null;
    }

    /**
     * method to create time object
     * @param timeString string of time
     * @return time value of the string or null
     */
    public Time getTime(String timeString){
        String timeToUpperCase = timeString.toUpperCase();
        for(int i = 0; i < Time.values().length; i++){
            if(Time.values()[i].toString().equals(timeToUpperCase))
                return Time.values()[i];
        }
        return null;
    }

    /**
     * gets current date instance and adds inputed months
     * @param months number of months to add
     * @return date string of new date.
     */
    public String dateAdd(int months){
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.MONTH, months);
        //creating expiry date
        int monthNum = calendar.get(Calendar.MONTH);
        int dayNum = calendar.get(Calendar.DATE);
        int yearNum = calendar.get(Calendar.YEAR);
        String date = monthNum + 1 + "/" + dayNum + "/" + yearNum;
        return date;
    }

    /**
     * This method is called to check if the member is in the cardio class.
     * It calls the find method from the FitnessClass class to see if the member is in the class.
     * @param class1 a Member object with the member the user has entered
     * @return true if the member is not in the class. If the member is in the class, it returns false.
     */
    public boolean classExists(FitnessClass class1){
        if(classSchedule.findClass(class1) != null)
            return true;
        else{
            System.out.println("\tClass does not exist");
            return false;
        }
    }


}


