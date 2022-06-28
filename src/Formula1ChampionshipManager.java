import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Formula1ChampionshipManager implements ChampionshipManager {
    private static int totalDriver = 0;                                           //total drivers
    private static int totalRaces = 0;                                            //total races
    private static final Formula1Driver[] f1Drivers = new Formula1Driver[10];           //storing 10 participating driver details
    private static final createRace[] race = new createRace[25];                        //storing 25 completed races
    private static final GUI gui = new GUI();
    /**
     * Method to return the total number of races
     **/
    public int getTotalRaces(){
        return totalRaces;
    }
    /**
     * Method to return all the driver details
     **/
    public createRace[] detailsOfRace(){
        return race;
    }
    /**
     * Method to initalise the race array
     **/
    public void initEndPos(){
        ArrayList<Integer> startPos = new ArrayList<>();
        ArrayList<Integer> endPos = new ArrayList<>();
        for (int i=0; i< race.length;i++){
            race[i] = new createRace(0,"",startPos,endPos);
        }
    }
    /**
     * Method to return all the driver details
     **/
    public Formula1Driver[] detailsOfAllDrivers(){
        return f1Drivers;
    }
    /**
     * Method to return total drivers
     **/
    public int getTotalDriver(){
        return totalDriver;
    }
    /**
     * Method to initalise the array with default values
     **/
    public void initalise()
    {
        for (int i = 0; i < f1Drivers.length; i++) {
            f1Drivers[i] = new Formula1Driver(0, " "," "," ",0,
                    0,0,0,0,0);
        }
    }
    /**
     * Method to create a driver
     */
    @Override
    public void createDriver(int driverId,String driverName, String driverLocation, String driverTeam,
                             int driverChampionship, int driverFirstPosition, int driverSecondPosition,
                             int driverThirdPosition, int driverPoints, int driverNoOfRacesParticipated)
    {
        if (totalDriver<f1Drivers.length){          //check if slots available
            if (checkValidId(driverId)){
                System.out.println("ENTER A UNIQUE DRIVER ID (ID ALREADY EXISTS)");                     //checking if the driver ID is unique
            }
            else if (checkValidTeam(driverTeam)) {                                                      //checking if the team already has a driver
                System.out.println("THE TEAM "+  driverTeam +" ALREADY HAS A DRIVER");
            }
            else{
                f1Drivers[totalDriver].setDriverId(driverId);
                f1Drivers[totalDriver].setDriverName(driverName);
                f1Drivers[totalDriver].setDriverLocation(driverLocation);
                f1Drivers[totalDriver].setDriverTeam(driverTeam);
                f1Drivers[totalDriver].setDriverChampionship(driverChampionship);
                f1Drivers[totalDriver].setDriverFirstPos(driverFirstPosition);
                f1Drivers[totalDriver].setDriverSecondPos(driverSecondPosition);
                f1Drivers[totalDriver].setDriverThirdPos(driverThirdPosition);
                f1Drivers[totalDriver].setDriverPoints(driverPoints);
                f1Drivers[totalDriver].setDriverParticipatedRaces(driverNoOfRacesParticipated);
                System.out.println("DRIVER ADDED SUCCESSFULLY");

                totalDriver++;                  //adding one driver
            }
        }
        else{
            System.out.println("THERE ARE NO AVAILABLE SLOTS FOR ANY NEW DRIVERS");             //Max number of drivers
        }
    }
    /**
     * Method to delete driver\
     * @param driverId To delete this specific driver
     */
    @Override
    public void deleteDriver(int driverId) {
        if (totalDriver != 0){
            boolean validity = checkValidId(driverId);          //checking if input driver id exists
            if (validity){
                for (Formula1Driver f1Driver : f1Drivers) {
                    if (f1Driver.getDriverId() == driverId) {       //resetting the driver details to default
                        f1Driver.setDriverId(0);
                        f1Driver.setDriverName("");
                        f1Driver.setDriverLocation("");
                        f1Driver.setDriverTeam("");
                        f1Driver.setDriverChampionship(0);
                        f1Driver.setDriverFirstPos(0);
                        f1Driver.setDriverSecondPos(0);
                        f1Driver.setDriverThirdPos(0);
                        f1Driver.setDriverPoints(0);
                        f1Driver.setDriverParticipatedRaces(0);

                        System.out.println("DRIVER REMOVED SUCCESSFULLY");
                        totalDriver--;
                    }
                }
            }
            else {
                System.out.println("ENTERED DRIVER ID DOES NOT EXISTS");
            }
        }
        else{
            System.out.println("ALL THE SLOTS ARE EMPTY NO DRIVERS TO REMOVE");
        }

    }
    /**
     * Method to change the driver team
     */
    @Override
    public void changeDriverTeam(int driverId, String driverNewTeam) {
        for (int i = 0; i < f1Drivers.length; i++) {
            if (f1Drivers[i].getDriverId() == driverId){
                f1Drivers[i].setDriverTeam(driverNewTeam);
            }
        }
    }
    /**
     * Method to display a driver statistics
     *
     * @param driverId whose statistics to be displayed
     *
     */
    @Override
    public void displayDriverStatistics(int driverId) {
        for (int i = 0; i < f1Drivers.length; i++) {
            if (f1Drivers[i].getDriverId() == driverId){
                System.out.println("Driver's Championships         :" + f1Drivers[i].getDriverChampionship());
                System.out.println("Driver's First Place Positions :" + f1Drivers[i].getDriverFirstPos());
                System.out.println("Driver's Points                :" + f1Drivers[i].getDriverPoints());
            }
        }
    }
    /**
     * Method to display the driver table
     */
    @Override
    public void displayDriverTable() {
        System.out.printf("%90s", "DRIVER TABLE");
        System.out.println();
        System.out.println("******************************************************************************************************************************************************************************");
        System.out.print("|");
        System.out.printf("%9s %7s %14s %11s %11s %8s %15s %8s %14s %9s %25s %4s %18s %7s",
                "ID", "|",
                "NAME", "|",
                "TEAM", "|",
                "LOCATION", "|",
                "POINTS", "|",
                "FIRST PLACE FINISHES", "|",
                "CHAMPIONSHIPS", "|");
        System.out.println();
        System.out.println("|----------------|--------------------------|--------------------|------------------------|------------------------|------------------------------|--------------------------|");
        Arrays.sort(f1Drivers);         //sorting the array according to points

        for (int i = 0; i < f1Drivers.length; i++) {
            if (f1Drivers[i].getDriverId() != 0)
            {
                System.out.print("|");
                System.out.format("%10s " + "%6s " + "%16s " + "%9s " + "%10s " + "%9s " + "%12s " + "%11s" + "%13s " + "%11s " + "%14s " + "%15s " + "%14s" + "%12s ",
                        f1Drivers[i].getDriverId(), "|", f1Drivers[i].getDriverName().toUpperCase(), "|", f1Drivers[i].getDriverTeam().toUpperCase(), "|", f1Drivers[i].getDriverLocation().toUpperCase(), "|",
                        f1Drivers[i].getDriverPoints(), "|", f1Drivers[i].getDriverFirstPos(), "|", f1Drivers[i].getDriverChampionship(), "|");
                System.out.println();
                System.out.println("|----------------|--------------------------|--------------------|------------------------|------------------------|------------------------------|--------------------------|");
            }

        }
        System.out.println("******************************************************************************************************************************************************************************");
    }
    /**
     * Method to add a race
     *
     * @param detailArray array with race id, date start position and  end position
     *
     **/
    @Override
    public void addRace(int[] detailArray) {
        int raceId = detailArray[0];
        String raceDate = detailArray[1] + "-" + detailArray[2] + "-" + detailArray[3];
        System.out.println(raceDate);                                                                           //race date
        ArrayList<Integer> startPosition = new ArrayList<>();
        ArrayList<Integer> endPosition = new ArrayList<>();
        for(int i =0;i<totalDriver;i++){
            if (f1Drivers[i].getDriverId() == detailArray[4]){                                                  //first position
                f1Drivers[i].setDriverFirstPos(f1Drivers[i].getDriverFirstPos()+1);
                f1Drivers[i].setDriverParticipatedRaces(f1Drivers[i].getDriverParticipatedRaces()+1);
                f1Drivers[i].setDriverPoints(f1Drivers[i].getDriverPoints()+25);
            }
            else if (f1Drivers[i].getDriverId() == detailArray[5]){                                             //second position
                f1Drivers[i].setDriverSecondPos(f1Drivers[i].getDriverSecondPos()+1);
                f1Drivers[i].setDriverParticipatedRaces(f1Drivers[i].getDriverParticipatedRaces()+1);
                f1Drivers[i].setDriverPoints(f1Drivers[i].getDriverPoints()+18);
            }
            else if (f1Drivers[i].getDriverId() == detailArray[6]){                                             //third position
                f1Drivers[i].setDriverThirdPos(f1Drivers[i].getDriverThirdPos()+1);
                f1Drivers[i].setDriverParticipatedRaces(f1Drivers[i].getDriverParticipatedRaces()+1);
                f1Drivers[i].setDriverPoints(f1Drivers[i].getDriverPoints()+15);
            }
            else if (f1Drivers[i].getDriverId() == detailArray[7]){                                             //fourth position
                f1Drivers[i].setDriverParticipatedRaces(f1Drivers[i].getDriverParticipatedRaces()+1);
                f1Drivers[i].setDriverPoints(f1Drivers[i].getDriverPoints()+12);
            }
            else if (f1Drivers[i].getDriverId() == detailArray[8]){                                             //fifth position
                f1Drivers[i].setDriverParticipatedRaces(f1Drivers[i].getDriverParticipatedRaces()+1);
                f1Drivers[i].setDriverPoints(f1Drivers[i].getDriverPoints()+10);
            }
            else if (f1Drivers[i].getDriverId() == detailArray[9]){                                             //sixth position
                f1Drivers[i].setDriverParticipatedRaces(f1Drivers[i].getDriverParticipatedRaces()+1);
                f1Drivers[i].setDriverPoints(f1Drivers[i].getDriverPoints()+8);
            }
            else if (f1Drivers[i].getDriverId() == detailArray[10]){                                             //seventh position
                f1Drivers[i].setDriverParticipatedRaces(f1Drivers[i].getDriverParticipatedRaces()+1);
                f1Drivers[i].setDriverPoints(f1Drivers[i].getDriverPoints()+6);
            }
            else if (f1Drivers[i].getDriverId() == detailArray[11]){                                             //eighth position
                f1Drivers[i].setDriverParticipatedRaces(f1Drivers[i].getDriverParticipatedRaces()+1);
                f1Drivers[i].setDriverPoints(f1Drivers[i].getDriverPoints()+4);
            }
            else if (f1Drivers[i].getDriverId() == detailArray[12]){                                             //ninth position
                f1Drivers[i].setDriverParticipatedRaces(f1Drivers[i].getDriverParticipatedRaces()+1);
                f1Drivers[i].setDriverPoints(f1Drivers[i].getDriverPoints()+2);
            }
            else if (f1Drivers[i].getDriverId() == detailArray[13]){                                            //tenth position
                f1Drivers[i].setDriverParticipatedRaces(f1Drivers[i].getDriverParticipatedRaces()+1);
                f1Drivers[i].setDriverPoints(f1Drivers[i].getDriverPoints()+1);
            }
            else{
                f1Drivers[i].setDriverParticipatedRaces(f1Drivers[i].getDriverParticipatedRaces()+1);           //above tenth position
            }
        }
        for(int j=0;j<f1Drivers.length;j++){        //giving default starting positions
            startPosition.add(0);
        }
        for(int j=0;j<detailArray.length-4;j++){            //adding the ending positions to the array list
            endPosition.add(j,detailArray[j+4]);
        }
        if (detailArray.length-4< f1Drivers.length){
            for(int k=0;k<(f1Drivers.length-(detailArray.length-4));k++){   //adding default ending positions to the remaining slots
                endPosition.add(0);
            }
        }

        race[totalRaces].setRaceId(raceId);             //setting the race id of the particular race
        race[totalRaces].setRaceDate(raceDate);         //setting the race date of the  particular race
        race[totalRaces].setStartPos(startPosition);    //setting the starting positions of the particular race
        race[totalRaces].setEndPos(endPosition);        //setting the ending positions of the particular race
        totalRaces = totalRaces + 1;                    //adding a race to the total race count
        System.out.println("RACE COMPLETED SUCCESSFULLY");
    }
    /**
     * Method to save the data to a file
     *
     * @param fileName File to store the data
     *
     **/
    @Override
    public void saveData(String fileName){
        try {
            File dataFile = new File(fileName+".txt");
            FileWriter myWriter = new FileWriter(dataFile);
            for (int i = 0; i < f1Drivers.length; i++) {
                myWriter.write(f1Drivers[i].getDriverId()+"\n");
                myWriter.write(f1Drivers[i].getDriverName()+"\n");
                myWriter.write(f1Drivers[i].getDriverLocation()+"\n");
                myWriter.write(f1Drivers[i].getDriverTeam()+"\n");
                myWriter.write(f1Drivers[i].getDriverChampionship()+"\n");
                myWriter.write(f1Drivers[i].getDriverFirstPos()+"\n");
                myWriter.write(f1Drivers[i].getDriverSecondPos()+"\n");
                myWriter.write(f1Drivers[i].getDriverThirdPos()+"\n");
                myWriter.write(f1Drivers[i].getDriverPoints()+"\n");
                myWriter.write(f1Drivers[i].getDriverParticipatedRaces()+"\n");
            }
            myWriter.close();
            System.out.println("DATA SAVED SUCCESSFULLY");
        }
        catch (IOException E){
            System.out.println("FILE NOT FOUND");
        }
    }
    /**
     * Method to save the data on completed races to file
     **/
    public void saveRaceData(){
        try {
            File dataFile = new File("raceData.txt");
            FileWriter myWriter = new FileWriter(dataFile);
            ArrayList<Integer> startPos;
            ArrayList<Integer> endPos;
            for (int i = 0; i < totalRaces; i++) {
                myWriter.write(race[i].getRaceId() + "\n");
                myWriter.write(race[i].getRaceDate() + "\n");
                startPos = race[i].getStartPos();
                for (int k = 0; k < startPos.size(); k++) {
                    myWriter.write(startPos.get(k) + "\n");
                }
                endPos = race[i].getEndPos();
                for (int k = 0; k < endPos.size(); k++) {
                    myWriter.write(endPos.get(k) + "\n");
                }
            }
            myWriter.close();
        }
        catch (IOException E){
            System.out.println("FILE NOT FOUND");
        }
    }
    /**
     * Method to load the data on previous races from a file
     **/
    public void loadRaceData(){
        try{
            File data = new File("raceData.txt");
            Scanner read = new Scanner(data);
            int index = 0;
            while (read.hasNextLine()) {
                ArrayList<Integer> loadRaceStartPos = new ArrayList<>();
                ArrayList<Integer> loadRaceEndPos = new ArrayList<>();
                race[index].setRaceId(Integer.parseInt(read.nextLine()));
                race[index].setRaceDate(read.nextLine());
                for(int i =0;i< f1Drivers.length;i++){
                    loadRaceStartPos.add(Integer.parseInt(read.nextLine()));
                }
                race[index].setStartPos(loadRaceStartPos);
                for(int i =0;i< f1Drivers.length;i++){
                    loadRaceEndPos.add(Integer.parseInt(read.nextLine()));
                }
                race[index].setEndPos(loadRaceEndPos);
                index++;
            }
            read.close();
            for (int i = 0; i < race.length; i++) {
                if (!race[i].getRaceDate().equals("")) {
                    totalRaces++;
                }
            }
        }
        catch (FileNotFoundException E){
            System.out.println("File Not Found");
        }
    }
    /**
     * Method to load details of the drivers
     **/
    @Override
    public void loadData(){
        try{
            File data = new File("driverData.txt");
            Scanner read = new Scanner(data);
            int index = 0;
            while (read.hasNextLine()) {
                f1Drivers[index].setDriverId(Integer.parseInt(read.nextLine()));
                f1Drivers[index].setDriverName(read.nextLine());
                f1Drivers[index].setDriverLocation(read.nextLine());
                f1Drivers[index].setDriverTeam(read.nextLine());
                f1Drivers[index].setDriverChampionship(Integer.parseInt(read.nextLine()));
                f1Drivers[index].setDriverFirstPos(Integer.parseInt(read.nextLine()));
                f1Drivers[index].setDriverSecondPos(Integer.parseInt(read.nextLine()));
                f1Drivers[index].setDriverThirdPos(Integer.parseInt(read.nextLine()));
                f1Drivers[index].setDriverPoints(Integer.parseInt(read.nextLine()));
                f1Drivers[index].setDriverParticipatedRaces(Integer.parseInt(read.nextLine()));

                index++;
            }
            read.close();
            for (int i = 0; i < f1Drivers.length; i++) {
                if (f1Drivers[i].getDriverId() != 0) {
                    totalDriver++;
                }
            }
            loadRaceData();
        }
        catch (FileNotFoundException E){
            System.out.println("File Not Found");
        }

    }
    /**
     * Method to check if the entered id is valid
     *
     * @param driverId To check if driver exists
     *
     **/
    public boolean checkValidId(int driverId){
        boolean idExists = false;
        for (int i = 0; i < f1Drivers.length; i++) {
            if (f1Drivers[i].getDriverId() == driverId) {
                idExists = true;
                break;
            }
        }
        return idExists;
    }
    /**
     * Method to check if the entered team is unique
     *
     * @param driverTeam To check if team exists
     *
     **/
    public boolean checkValidTeam(String driverTeam){
        boolean teamExists = false;
        for (int i = 0; i < f1Drivers.length; i++) {
            if (f1Drivers[i].getDriverTeam().equals(driverTeam)) {
                teamExists = true;
                break;
            }
        }
        return teamExists;
    }
    /**
     * Method to display all the available drivers
     **/
    public void currentDrivers(){
        for (int i = 0; i < f1Drivers.length; i++) {
            if (f1Drivers[i].getDriverId() != 0){           //To check if driver id is the initialised value
                System.out.println(
                        "Driver ID    : " + f1Drivers[i].getDriverId()  + "\n" +
                        "Driver Name  : " + f1Drivers[i].getDriverName()+ "\n" +
                        "Driver Team  : " + f1Drivers[i].getDriverTeam()+ "\n");
            }
        }
    }
    /**
     * Method to load the GUI
     **/
    @Override
    public void loadGUI() {
        gui.loadProgram();
    }
    /**
     * Method to console view of all the driver details
     **/
    public void data(){
        for (int i = 0; i < f1Drivers.length; i++) {
            if (f1Drivers[i].getDriverId() != 0){
                System.out.println(
                                "Driver ID                 : " + f1Drivers[i].getDriverId()                    + "\n" +
                                "Driver Name               : " + f1Drivers[i].getDriverName()                  + "\n" +
                                "Driver Location           : " + f1Drivers[i].getDriverLocation()              + "\n" +
                                "Driver Team               : " + f1Drivers[i].getDriverTeam()                  + "\n" +
                                "Driver Championships      : " + f1Drivers[i].getDriverChampionship()          + "\n" +
                                "No. of First Position     : " + f1Drivers[i].getDriverFirstPos()              + "\n" +
                                "No. of Second Position    : " + f1Drivers[i].getDriverSecondPos()             + "\n" +
                                "No. of Third Position     : " + f1Drivers[i].getDriverThirdPos()              + "\n" +
                                "Total Points              : " + f1Drivers[i].getDriverPoints()                + "\n" +
                                "No. of Races Participated : " + f1Drivers[i].getDriverParticipatedRaces()     + "\n" );
            }
        }
    }
}
