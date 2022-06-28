import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Formula1ChampionshipManager FM = new Formula1ChampionshipManager();

    public static void main(String[] args){
        FM.initalise();     //initialising the array with default values
        FM.initEndPos();
        FM.loadData();      //loading saved data from default file
        menuChoice();       //displaying available options
        int userInput = enterInt();
        while (userInput != 9){
            if (userInput == 1 ){
                System.out.println("__________CREATING A NEW DRIVER__________");
                creatingNewDriver();
            }
            else if (userInput == 2){
                System.out.println("__________DELETING AN EXISTING DRIVER__________");
                deleteExistingDriver();
            }
            else if (userInput == 3){
                System.out.println("__________CHANGING THE DRIVER TEAM__________");
                changeDriverTeam();
            }
            else if (userInput == 4){
                System.out.println("__________DISPLAYING STATISTICS FOR A DRIVER__________");
                displayDriverStatistics();
            }
            else if (userInput == 5){
                System.out.println("__________DISPLAYING DRIVER TABLE__________");
                FM.displayDriverTable();
            }
            else if (userInput == 6){
                System.out.println("__________CREATE A RACE__________");
                createRace();
            }
            else if (userInput == 7){
                System.out.println("__________SAVING DETAILS TO A FILE__________");
                saveDataToFile();
            }
            else if (userInput == 8){
                System.out.println("__________LOADING GUI__________");
                FM.loadGUI();
            }
            else if (userInput == 10){
                System.out.println("__________VIEWING ALL THE DRIVERS__________");
                FM.data();
            }
            else{
                System.out.println("Entered option does not exist");
                menuChoice();
            }
            System.out.println("Enter your choice");
            userInput = enterInt();
            if(userInput == 9){
                saveDataBeforeExit();
                System.exit(0);
            }
        }
    }
    /**
     * Method to print all the options available
     **/
    public static void menuChoice(){
        System.out.print("""
                ..........Choose a Option..........
                Option 1: Create a new driver
                Option 2: Delete a driver
                Option 3: Change the driver team
                Option 4: Display statics of driver
                Option 5: Display driver table
                option 6: Add a race
                Option 7: Save a file
                Option 8: Load GUI
                Option 9: Exit out the program
                Select Option :""");
    }
    /**
     * Method to get user inputs on creating a new driver
     **/
    public static void creatingNewDriver(){
        System.out.println("Enter Driver's Id");
        int driverId = enterInt();
        System.out.println("Enter Driver's Name");
        String driverName = input.next();
        System.out.println("Enter Driver's Location");
        String driverLocation = input.next();
        System.out.println("Enter Driver's Team");
        String driverTeam = input.next();
        System.out.println("Enter Driver's World Championships");
        int driverChampionship = enterInt();
        System.out.println("Enter Driver's No.of First place Position");
        int driverFirstPos = enterInt();
        System.out.println("Enter the Driver's No.of Second Place Position");
        int driverSecondPos = enterInt();
        System.out.println("Enter the Driver's No.of Third Place Position ");
        int driverThirdPos = enterInt();
        System.out.println("Enter the Driver's Total points");
        int driverPoints = enterInt();
        System.out.println("Enter the Driver's No.of Participated Races");
        int driverRacesParticipated = enterInt();

        FM.createDriver(driverId,driverName,driverLocation,driverTeam,driverChampionship,
                driverFirstPos,driverSecondPos,driverThirdPos,driverPoints,driverRacesParticipated);
        System.out.println("......................................");
    }
    /**
     * Method to delete a driver
     **/
    public static void deleteExistingDriver(){
        System.out.println("Enter the Driver Id");
        int userInput = enterInt();
        FM.deleteDriver(userInput);
    }
    /**
     * Method to change the driver toa new team
     **/
    public static void changeDriverTeam(){
        System.out.println("Enter the driver Id");
        int userInput = enterInt();
        System.out.println("Enter the new team");
        String userInput2 = input.next();
        if (!FM.checkValidId(userInput)){
            System.out.println("ENTERED DRIVER ID DOES NOT EXISTS");
        }
        else if (FM.checkValidTeam(userInput2)){
            System.out.println("THE TEAM "+  userInput2.toUpperCase() +" ALREADY HAS A DRIVER");
        }
        else{
            FM.changeDriverTeam(userInput,userInput2);
            System.out.println("DRIVER UPDATED SUCCESSFULLY TO A NEW TEAM");
        }
    }
    /**
     * Method to display any driver's statistics
     **/
    public static void displayDriverStatistics(){
        System.out.println("Do you want to view the participating Driver names and Id(Y/N)");
        String userInput = input.next().toLowerCase();
        if (userInput.equals("y")){
            FM.currentDrivers();
        }
        System.out.println("Enter the driver Id");
        int userInput2 = enterInt();
        if(!FM.checkValidId(userInput2)){
            System.out.println("ENTERED DRIVER ID DOES NOT EXISTS");
        }
        else{
            FM.displayDriverStatistics(userInput2);
            System.out.println("DISPLAYED ALL THE DRIVER STATISTICS");
        }
    }
    /**
     * Method to save the data to a file
     **/
    public static void saveDataToFile(){
        System.out.println("Do you want to store data in a new file(Y/N)");
        String userInput = input.next().toLowerCase();
        if (userInput.equals("y")){
            System.out.println("Enter the file name (without the extension)");
            String userInput2 = input.next();
            FM.saveData(userInput2);
        }
        else{
            FM.saveData("driverData");
        }
    }
    /**
     * Method to create a race
     */
    public static void createRace(){
        int totalDrivers =FM.getTotalDriver();
        int[] userInputArray = new int[totalDrivers+4];
        System.out.println("Enter race Id");
        int raceId = enterInt();
        System.out.println("Do you want continue with today's date(Y/N)");
        String userInput = input.next().toLowerCase();
        String date;
        if (userInput.equals("y")){
            LocalDate localDate = LocalDate.now();
            date = String.valueOf(localDate);
        }
        else{
            System.out.println("Enter the date(YYYY/MM/DD)");
            date = (input.next());
        }
        System.out.println(date);
        userInputArray[0] = raceId;
        userInputArray[1] = Integer.parseInt(date.substring(0,4));
        userInputArray[2] = Integer.parseInt(date.substring(5,7));
        userInputArray[3] = Integer.parseInt(date.substring(8,10));
        String[] num = {"FIRST","SECOND","THIRD","FOURTH","FIFTH","SIXTH","SEVENTH","EIGHTH","NINTH","TENTH"};      //array with position title strings
        FM.currentDrivers();                                                                                        //displaying all the participating drivers
        for (int i =0;i<totalDrivers;i++){
            System.out.println("ENTER THE DRIVER ID WHO CAME " + "\""+num[i]+"\"");
            userInputArray[i+4] = userInputValidId();
        }
        FM.addRace(userInputArray);
        System.out.println("Do you want to save the race (Y/N)");
        String user = input.next().toLowerCase();
        if (user.equals("y")){
            FM.saveRaceData();
            System.out.println("Data Saved");
        }
    }
    /**
     * Method to save data when exit out of the program
     **/
    public static void saveDataBeforeExit(){
        System.out.println("Do you want to save the data before exiting(Y/N)");
        String userInput = input.next().toLowerCase();
        if (userInput.equals("y")){
            saveDataToFile();                                                            //calling method to store data
        }
        else {
            System.out.println("**********THANK YOU **********");
        }
    }
    /**
     * Method to get a valid driver id from the user
     **/
    public static int userInputValidId(){
        int userInputId;
        boolean valid;
        do{
            userInputId = enterInt();
            if (!FM.checkValidId(userInputId)){
                System.out.println("ENTERED ID DOES NOT EXIST");
                System.out.println("ENTER THE ID AGAIN");
                valid = true;
            }
            else {
                valid = false;
            }
        }while (valid);
        return userInputId;
    }
    /**
     * Method to get an integer as an input
     **/
    public static int enterInt(){
        int value =-1;

        while (value <0){
            try{
                value = input.nextInt();
            }
            catch (InputMismatchException Exception){
                System.out.println("ENTER AN INTEGER");
                input.next();
            }
        }
        return value;
    }
}
