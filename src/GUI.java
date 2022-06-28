import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class GUI implements ActionListener{
    Formula1ChampionshipManager FM = new Formula1ChampionshipManager();

    String[][] driverStatisticsTableRow      = new String[FM.detailsOfAllDrivers().length][8];                                                                                      //2d array to for table content
    String[] driverStatisticsTableColumn     = {"ID","NAME","TEAM","LOCATION","POINTS","NOS FIRST PLACE","CHAMPIONSHIPS","PARTICIPATED RACES"};    //table column names
    JTable table                             = new JTable(driverStatisticsTableRow, driverStatisticsTableColumn);                                                             //creating a table

    String[][] raceStatisticsTableRow        = new String[FM.detailsOfAllDrivers().length][6];                                                                                        //2d array to for table content
    String[] raceStatisticsTableColumn       = {"ID","NAME","TEAM","LOCATION","START POSITION","END POSITION"};      //table column names
    JTable raceTable                         = new JTable(raceStatisticsTableRow, raceStatisticsTableColumn);                                                             //creating a table

    String[][] raceDetailsTableRow           = new String[FM.detailsOfRace().length][2];                                                                                      //2d array to for table content
    String[] raceDetailsTableColumn          = {"RACE ID","RACE DATE"};
    JTable raceDetailsTable                  = new JTable(raceDetailsTableRow, raceDetailsTableColumn);

    String[][] availableDriversTableRow      = new String[FM.detailsOfAllDrivers().length+5][2];                                                                                      //2d array to for table content
    String[] availableDriversTableColumn     = {"DRIVER ID","DRIVER NAME"};
    JTable availableDriversTable             = new JTable(availableDriversTableRow,availableDriversTableColumn);

    String[][] displaySelectedDriverInfoRow  = new String[FM.detailsOfRace().length][4];                                                                                      //2d array to for table content
    String[] displaySelectedDriverInfoColumn = {"RACE ID","RACE DATE","START POSITION","END POSITION"};
    JTable displaySelectedDriverInfoTable    = new JTable(displaySelectedDriverInfoRow,displaySelectedDriverInfoColumn);

    Formula1Driver[] formula1Drivers         = FM.detailsOfAllDrivers();            //all the driver details
    createRace[] raceDetails                 = FM.detailsOfRace();                  //all the race details

    JFrame frame             = new JFrame();                                    //main frame
    JFrame viewRaceFrame     = new JFrame();                                    //new frame to view race details
    JFrame searchPlayerFrame = new JFrame();                                    //new frame to search a player

    JPanel sortPanel =  new JPanel();

    JScrollPane scrollPaneDriverTable                = new JScrollPane(table);              //creating a scroll pane for the table
    JScrollPane scrollPaneRaceTable                  = new JScrollPane(raceTable);          //creating a scroll pane for the race table
    JScrollPane scrollPaneRaceDetailsTable           = new JScrollPane(raceDetailsTable);
    JScrollPane scrollPaneAvailableDriverTable       = new JScrollPane(availableDriversTable);
    JScrollPane scrollPaneDisplaySelectedDriverTable = new JScrollPane(displaySelectedDriverInfoTable);

    JButton exitButton                 = new JButton("EXIT");
    JButton sortButton                 = new JButton("SORT");
    JButton randomRaceButtonOne        = new JButton("GENERATE RACE WITH RANDOM END POSITIONS");
    JButton randomRaceButtonTwo        = new JButton("GENERATE RACE WITH RANDOM START POSITIONS");
    JButton loadRaceFrame              = new JButton("VIEW ALL RACES");
    JButton backToMainFromRaceFrame    = new JButton("BACK");
    JButton exitAllFromRaceFrame       = new JButton("EXIT");
    JButton loadSearchFrame            = new JButton("SEARCH FOR A PLAYER");
    JButton searchDriver               = new JButton("SEARCH");
    JButton backToMainFromSearchFrame  = new JButton("BACK");
    JButton exitAllFromSearchFrame     = new JButton("EXIT");

    JRadioButton sortTypeAscend        = new JRadioButton("Ascending Order");
    JRadioButton sortTypeDescend       = new JRadioButton("Descending Order");
    JRadioButton sortByFirstPlace      = new JRadioButton("No.of First Place Finishes");
    JRadioButton sortByPoints          = new JRadioButton("Total Points");
    JRadioButton sortChampionships     = new JRadioButton("Championships");
    JRadioButton sortParticipatedRaces = new JRadioButton("Participated Races");

    JTextField userInputId = new JTextField();

    JLabel mainFrameLabel       = new JLabel("FORMULA ONE CHAMPIONSHIP");
    JLabel mainViewFrameLabel   = new JLabel("ALL COMPLETED RACES");
    JLabel mainSearchFrameLabel = new JLabel("SEARCH PLAYER");
    JLabel mainSortLabel        = new JLabel("SORT");
    JLabel sortTypeLabel        = new JLabel("SELECT SORT TYPE");
    JLabel sortByLabel          = new JLabel("SORT BY...");
    JLabel userPromptId         = new JLabel("ENTER THE DRIVER ID");
    JLabel idValidity           = new JLabel();

    public void loadProgram(){
        Event();
    }

    public void Event() {
        frame.setVisible(true);                        //frame visibility
        frame.setTitle("Driver Statistics");           //frame title
        frame.setLayout(null);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        if (screenWidth == 1366){
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }else {
            frame.setSize(1370, 730);
        }


        mainFrameLabel.setBounds(491, 10, 400, 55);               //main label
        frame.getContentPane().add(mainFrameLabel);
        mainFrameLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));

        exitButton.setBounds(1105, 660, 250, 30);               //exit button
        frame.getContentPane().add(exitButton);
        exitButton.addActionListener(this);

        randomRaceButtonOne.setBounds(100,380,350,30);
        frame.getContentPane().add(randomRaceButtonOne);                           //button generating random race
        randomRaceButtonOne.addActionListener(this);

        randomRaceButtonTwo.setBounds(550,380,350,30);
        frame.getContentPane().add(randomRaceButtonTwo);                           //button generating random race
        randomRaceButtonTwo.addActionListener(this);

        loadRaceFrame.setBounds(1105,455,250,30);
        frame.getContentPane().add(loadRaceFrame);                                 //button opening a view races
        loadRaceFrame.addActionListener(this);

        loadSearchFrame.setBounds(1105,557,250,30);
        frame.getContentPane().add(loadSearchFrame);                               //button opening a search player
        loadSearchFrame.addActionListener(this);

        displayDriverGuiTable();
        sortGUI();
        displayRaceTable();
    }
    /**
     * Method to load and execute
     **/
    public void sortGUI() {
        ButtonGroup sortType =new ButtonGroup();            //grouping sort types
        sortType.add(sortTypeAscend);
        sortType.add(sortTypeDescend);

        ButtonGroup sortBy =new ButtonGroup();              //grouping sort by
        sortBy.add(sortByFirstPlace);
        sortBy.add(sortChampionships);
        sortBy.add(sortByPoints);
        sortBy.add(sortParticipatedRaces);

        sortPanel.setLayout(new GridLayout(0,1,0,0));                        //panel layout
        sortPanel.setBounds(1105,75,250,290);                                   //panel position
        sortPanel.setBorder(BorderFactory.createLineBorder(Color.black));                         //adding border
        sortPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));       //border thickness

        mainSortLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));             //styling the labels
        sortTypeLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        sortByLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        mainSortLabel.setHorizontalAlignment(JLabel.CENTER);                                     //center align the label
        sortPanel.add(mainSortLabel);                                                            //adding to the JPane
        sortPanel.add(sortTypeLabel);
        sortPanel.add(sortTypeAscend);
        sortPanel.add(sortTypeDescend);
        sortPanel.add(sortByLabel);
        sortPanel.add(sortByFirstPlace);
        sortPanel.add(sortChampionships);
        sortPanel.add(sortByPoints);
        sortPanel.add(sortParticipatedRaces);

        sortPanel.add(sortButton);                                                               //button to sort
        sortButton.addActionListener(this);

        frame.getContentPane().add(sortPanel);                                                  //Adding the JPane to the frame
    }
    /**
     * Method to display the driver table
     **/
    public void displayDriverGuiTable(){
        Arrays.sort(formula1Drivers);

        int index = 0;
        while (FM.getTotalDriver()>index) {             //assigning values to the 2d array

            driverStatisticsTableRow[index][0] = String.valueOf(formula1Drivers[index].getDriverId());
            driverStatisticsTableRow[index][1] = formula1Drivers[index].getDriverName().toUpperCase();
            driverStatisticsTableRow[index][2] = formula1Drivers[index].getDriverTeam().toUpperCase();
            driverStatisticsTableRow[index][3] = formula1Drivers[index].getDriverLocation().toUpperCase();
            driverStatisticsTableRow[index][4] = String.valueOf(formula1Drivers[index].getDriverPoints());
            driverStatisticsTableRow[index][5] = String.valueOf(formula1Drivers[index].getDriverFirstPos());
            driverStatisticsTableRow[index][6] = String.valueOf(formula1Drivers[index].getDriverChampionship());
            driverStatisticsTableRow[index][7] = String.valueOf(formula1Drivers[index].getDriverParticipatedRaces());
            index++;
        }
        table.setRowHeight(25);
        frame.getContentPane().add(scrollPaneDriverTable);           //adding the table to the frame
        scrollPaneDriverTable.setBounds(10,70,1080,300);
        scrollPaneDriverTable.setVisible(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();       //center columns
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Georgia", Font.BOLD, 16));
        table.setFont(new Font("Times New Roman", Font.PLAIN, 14));

         //custom column width lengths
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(1).setPreferredWidth(35);
        table.getColumnModel().getColumn(2).setPreferredWidth(35);
        table.getColumnModel().getColumn(3).setPreferredWidth(35);
        table.getColumnModel().getColumn(4).setPreferredWidth(20);
        table.getColumnModel().getColumn(5).setPreferredWidth(85);
        table.getColumnModel().getColumn(6).setPreferredWidth(80);
        table.getColumnModel().getColumn(7).setPreferredWidth(130);
    }
    /**
     * Method to display the race table
     **/
    public void displayRaceTable(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        int index = 0;
        while (FM.getTotalDriver()>index) {             //assigning values to the 2d array
            raceStatisticsTableRow[index][0] = String.valueOf(formula1Drivers[index].getDriverId());
            raceStatisticsTableRow[index][1] = formula1Drivers[index].getDriverName().toUpperCase();
            raceStatisticsTableRow[index][2] = formula1Drivers[index].getDriverTeam().toUpperCase();
            raceStatisticsTableRow[index][3] = formula1Drivers[index].getDriverLocation().toUpperCase();
            index++;
        }

        raceTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        raceTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        raceTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        raceTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        raceTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        raceTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        JTableHeader tableHeader = raceTable.getTableHeader();
        tableHeader.setFont(new Font("Georgia", Font.BOLD, 16));
        raceTable.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        raceTable.setRowHeight(25);
        frame.getContentPane().add(scrollPaneRaceTable);           //adding the table to the frame
        scrollPaneRaceTable.setBounds(10,420,1080,275);
        scrollPaneRaceTable.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent Event) {
        if (Event.getSource() == exitButton) {
            exitProgram();
        }
        if (Event.getSource() == sortButton) {
            sortTable();
        }
        if (Event.getSource() == randomRaceButtonOne) {
            randomRaceOne();
        }
        if (Event.getSource() == randomRaceButtonTwo) {
            randomRaceTwo();
        }
        if (Event.getSource() == loadRaceFrame) {
            loadRaceGUI();
        }
        if (Event.getSource() == backToMainFromRaceFrame) {
            frame.setVisible(true);
            viewRaceFrame.setVisible(false);
        }
        if (Event.getSource() == exitAllFromRaceFrame) {
            exitProgram();
        }
        if (Event.getSource() == loadSearchFrame) {
            loadSearchGUI();
        }
        if (Event.getSource() == searchDriver) {
            validateUserInputValue();
        }
        if (Event.getSource() == backToMainFromSearchFrame) {
            frame.setVisible(true);
            searchPlayerFrame.setVisible(false);
        }
        if (Event.getSource() == exitAllFromSearchFrame) {
            exitProgram();
        }
    }
    /**
     * Method to exit the program
     **/
    public void exitProgram(){
        Object[] options = { "OK", "CANCEL" };
        int userSelect = JOptionPane.showOptionDialog(null, "CLICK OK TO EXIT", "ARE YOU SURE YOU WANT TO EXIT",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
        if(userSelect == JOptionPane.YES_OPTION){
            frame.dispose();                                            //exits the main frame not the program
            viewRaceFrame.dispose();                                    //exits the view race frame
            searchPlayerFrame.dispose();                                //exits the search player frame
            System.out.println("PRESS 9 TO TERMINATE THE WHOLE PROGRAM");
        }
    }
    /**
     * Method to sort the table
     **/
    public void sortTable(){
        if (sortTypeAscend.isSelected()){           //ascending order selected
            if (sortByPoints.isSelected()){         //ascending order by points
                ascendSort(4);
            }
            else if (sortByFirstPlace.isSelected()){        //ascending order by first place finishes
                ascendSort(5);
            }
            else if (sortParticipatedRaces.isSelected()){        //ascending order by first place finishes
                ascendSort(7);
            }
            else if (sortChampionships.isSelected()){          //ascending order by championships lap
                ascendSort(6);
            }
        }
        else if (sortTypeDescend.isSelected()){     //descending order is selected
            if (sortByPoints.isSelected()){         //descending order by points
                descendSort(4);
            }
            else if (sortByFirstPlace.isSelected()){    //descending order by first place finishes
                descendSort(5);
            }
            else if (sortParticipatedRaces.isSelected()){    //descending order by first place finishes
                descendSort(7);
            }
            else if (sortChampionships.isSelected()){      //descending order by championships lap
                descendSort(6);
            }
        }
    }
    /**
     * Method to Sort In Ascending order
     **/
    public void ascendSort(int val){
        String[] temp;
        boolean sorted = false;
        while(!sorted) {
            sorted = true;
            for (int i=0; i<FM.getTotalDriver()-1; i++) {
                if (Integer.parseInt(driverStatisticsTableRow[i][val]) > Integer.parseInt(driverStatisticsTableRow[i + 1][val])) {
                    temp = driverStatisticsTableRow[i];
                    driverStatisticsTableRow[i] = driverStatisticsTableRow[i+1];
                    driverStatisticsTableRow[i+1] = temp;
                    sorted = false;
                }
            }
        }
        table.repaint();
    }
    /**
     * Method to Sort In Descending order
     **/
    public void descendSort(int val){
        String[] temp;
        boolean sorted = false;
        while(!sorted) {
            sorted = true;
            for (int i=0; i<FM.getTotalDriver()-1; i++) {
                if (Integer.parseInt(driverStatisticsTableRow[i][val]) < Integer.parseInt(driverStatisticsTableRow[i + 1][val])) {
                    temp = driverStatisticsTableRow[i];
                    driverStatisticsTableRow[i] = driverStatisticsTableRow[i+1];
                    driverStatisticsTableRow[i+1] = temp;
                    sorted = false;
                }
            }
        }
        table.repaint();
    }
    /**
     * Method to create a race with random end positions
     **/
    public void randomRaceOne(){
        if (FM.getTotalRaces() == FM.detailsOfRace().length){
            System.out.println("MAXIMUM NUMBER OF RACES TAKEN PLACE");
        }
        else{
            ArrayList<Integer> array = new ArrayList<>();
            int[] value = new int[FM.getTotalDriver()];

            System.out.println(FM.getTotalDriver());
            for (int i=0; i<FM.getTotalDriver(); i++) {
                value[i] = randomInt(array);
            }

            ArrayList<Integer> posArray = new ArrayList<>();
            for (int i=0; i<FM.getTotalDriver(); i++) {
                posArray.add(formula1Drivers[findIndex(value,i+1)].getDriverId());
            }
            Object[] values2 = posArray.toArray();      //converting arraylist to an array

            int[] finalArray = new int[FM.getTotalDriver()+4];

            LocalDate localDate = LocalDate.now();                  //getting the local date
            String date = String.valueOf(localDate);
            finalArray[0] = 6;
            finalArray[1] = Integer.parseInt(date.substring(0,4));          //breaking the date    date-year
            finalArray[2] = Integer.parseInt(date.substring(5,7));          //date - month
            finalArray[3] = Integer.parseInt(date.substring(8,10));         //date - date


            for (int i=0; i<FM.getTotalDriver(); i++) {
                finalArray[i+4] = (int) values2[i];
            }

            for (int i=0; i<FM.getTotalDriver(); i++) {                 //displaying values in the start and end position columns
                raceStatisticsTableRow[i][4] = "N/A";
                raceStatisticsTableRow[i][5] = String.valueOf(value[i]);
            }
            raceTable.repaint();            //refresh the table

            FM.addRace(finalArray);
            displayDriverGuiTable();   //calling the driver table to refresh
            FM.saveRaceData();
            FM.saveData("driverData");
        }
    }
    /**
     * Method to create a race with random start positions
     **/
    public void randomRaceTwo(){
        if (FM.getTotalRaces() == FM.detailsOfRace().length){
            System.out.println("MAXIMUM NUMBER OF RACES TAKEN PLACE");
        }
        else {
            ArrayList<Integer> array = new ArrayList<>();
            int[] value = new int[FM.getTotalDriver()];

            for (int i = 0; i < FM.getTotalDriver(); i++) {
                value[i] = randomInt(array);
            }

            ArrayList<Integer> startPos = new ArrayList<>();
            ArrayList<Integer> storeStartPos = new ArrayList<>();
            for (int i = 0; i < FM.getTotalDriver(); i++) {
                storeStartPos.add(formula1Drivers[findIndex(value, i + 1)].getDriverId());
                startPos.add(formula1Drivers[findIndex(value, i + 1)].getDriverId());
            }

            int[] endPOS = new int[FM.getTotalDriver() + 4];

            LocalDate localDate = LocalDate.now();                  //getting the local date
            String date = String.valueOf(localDate);
            endPOS[0] = 9;
            endPOS[1] = Integer.parseInt(date.substring(0, 4));          //breaking the date    date-year
            endPOS[2] = Integer.parseInt(date.substring(5, 7));          //date - month
            endPOS[3] = Integer.parseInt(date.substring(8, 10));         //date - date
            boolean valid;
            do {
                valid = false;
                int probabilityValue = (int) (Math.random() * (100 - 1 + 1) + 1);        //getting a random value from 1 to 100
                System.out.println("Probability Value: " + probabilityValue);
                try {
                    if (probabilityValue >= 1 && probabilityValue <= 40) {           //first place wins the race
                        endPOS[4] = startPos.get(0);
                        startPos.remove(0);
                    } else if (probabilityValue >= 41 && probabilityValue <= 70) {     //second place wins the race
                        endPOS[4] = startPos.get(1);
                        startPos.remove(1);
                    } else if (probabilityValue >= 71 && probabilityValue <= 80) {     //third place wins the race
                        endPOS[4] = startPos.get(2);
                        startPos.remove(2);
                    } else if (probabilityValue >= 81 && probabilityValue <= 90) {     //fourth place wins the race
                        endPOS[4] = startPos.get(3);
                        startPos.remove(3);
                    } else if (probabilityValue >= 91 && probabilityValue <= 92) {     //fifth place wins the race
                        endPOS[4] = startPos.get(4);
                        startPos.remove(4);
                    } else if (probabilityValue >= 93 && probabilityValue <= 94) {     //sixth place wins the race
                        endPOS[4] = startPos.get(5);
                        startPos.remove(5);
                    } else if (probabilityValue >= 95 && probabilityValue <= 96) {     //seventh place wins the race
                        endPOS[4] = startPos.get(6);
                        startPos.remove(6);
                    } else if (probabilityValue >= 97 && probabilityValue <= 98) {     //eighth place wins the race
                        endPOS[4] = startPos.get(7);
                        startPos.remove(7);
                    } else if (probabilityValue >= 99 && probabilityValue <= 100) {    //ninth place wins the race
                        endPOS[4] = startPos.get(8);
                        startPos.remove(8);
                    }
                } catch (IndexOutOfBoundsException E) {
                    valid = true;
                    System.out.println("PROBABILITY VALUE DOES NOT MATCH WITH THE NUMBER OF DRIVERS AVAILABLE");
                }
            }while (valid);

            Collections.shuffle(startPos);                 //shuffling the array
            Object[] startOrder = startPos.toArray();      //converting arraylist to an array

            for (int i = 0; i < FM.getTotalDriver() - 1; i++) {
                endPOS[i + 5] = (int) startOrder[i];
            }

            ArrayList<Integer> endPosIndex = new ArrayList<>();
            for (int i = 0; i < FM.getTotalDriver(); i++) {                         //finding index position using driver id in the ending position array
                endPosIndex.add(findIndex(endPOS, formula1Drivers[i].getDriverId()) - 3);
            }
            Object[] endOrder = endPosIndex.toArray();                          //converting arraylist into an object array

            for (int i = 0; i < FM.getTotalDriver(); i++) {                         //displaying values in the start and end position columns
                raceStatisticsTableRow[i][4] = String.valueOf(value[i]);
                raceStatisticsTableRow[i][5] = String.valueOf(endOrder[i]);
            }
            raceTable.repaint();            //refresh the table

            for (int i = 0; i < formula1Drivers.length - FM.getTotalDriver(); i++) {  //adding default values for the rest of the driver slots
                storeStartPos.add(0);
            }
            FM.addRace(endPOS);                                              //adding the race with default start positions
            raceDetails[FM.getTotalRaces() - 1].setStartPos(storeStartPos);    //modifying the previous race with actual start positions
            displayDriverGuiTable();                                         //calling the driver table to refresh
            FM.saveRaceData();                                               //saving the race data
            FM.saveData("driverData");                               //saving the driver data
        }
    }
    /**
     * Method to load and view all the completed race
     **/
    public void loadRaceGUI(){
        viewRaceFrame.setVisible(true);              //frame visibility
        viewRaceFrame.setTitle("Race Details");      //frame title
        viewRaceFrame.setLayout(null);
        viewRaceFrame.setSize(500,440);  //frame  size
        frame.setVisible(false);                     //hiding the main frame

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        int index = 0;
        while (FM.getTotalRaces()>index) {
            int raceId = raceDetails[index].getRaceId();
            if (raceId == 9){
                raceDetailsTableRow[index][0] = "RANDOMLY GENERATED RACE";
            }
            else{
                raceDetailsTableRow[index][0] = String.valueOf(raceDetails[index].getRaceId());
            }
            raceDetailsTableRow[index][1] = raceDetails[index].getRaceDate();
            index++;
        }
        //sorting the race details table in ascending order of the date
        String[] temp;
        boolean sorted = false;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < FM.getTotalRaces() - 1; i++){
                String firstVal = raceDetailsTableRow[i][1];
                String secondVal = raceDetailsTableRow[i + 1][1];
                if (firstVal.compareTo(secondVal) > 0) {
                    temp = raceDetailsTableRow[i];
                    raceDetailsTableRow[i] = raceDetailsTableRow[i + 1];
                    raceDetailsTableRow[i + 1] = temp;
                    sorted = false;
                }
            }
        }

        raceDetailsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        raceDetailsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        JTableHeader tableHeader = raceDetailsTable.getTableHeader();
        tableHeader.setFont(new Font("Georgia", Font.BOLD, 16));
        raceDetailsTable.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        raceDetailsTable.setRowHeight(25);
        viewRaceFrame.getContentPane().add(scrollPaneRaceDetailsTable);           //adding the table to the frame
        scrollPaneRaceDetailsTable.setBounds(20,50,450,300);
        scrollPaneRaceDetailsTable.setVisible(true);

        backToMainFromRaceFrame.setBounds(20, 360, 125, 30);        //back button
        viewRaceFrame.getContentPane().add(backToMainFromRaceFrame);
        backToMainFromRaceFrame.addActionListener(this);

        exitAllFromRaceFrame.setBounds(345, 360, 125, 30);        //exit button
        viewRaceFrame.getContentPane().add(exitAllFromRaceFrame);
        exitAllFromRaceFrame.addActionListener(this);

        mainViewFrameLabel.setBounds(120, 10, 260, 30);        //back button
        viewRaceFrame.getContentPane().add(mainViewFrameLabel);
        mainViewFrameLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));

    }
    /**
     * Method to load a frame to display the search GUI
     **/
    public void loadSearchGUI(){
        searchPlayerFrame.setVisible(true);                  //search frame visibility
        searchPlayerFrame.setTitle("Search a Player");       //search frame title
        searchPlayerFrame.setLayout(null);                   //search frame layout
        searchPlayerFrame.setSize(960,560);     //search frame size
        frame.setVisible(false);                             //hiding the main frame

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        int index = 0;
        while (FM.getTotalDriver()>index) {
            availableDriversTableRow[index][0] = String.valueOf(formula1Drivers[index].getDriverId());
            availableDriversTableRow[index][1] = String.valueOf(formula1Drivers[index].getDriverName());
            index++;
        }
        availableDriversTable.setRowHeight(25);
        searchPlayerFrame.getContentPane().add(scrollPaneAvailableDriverTable);           //adding the table to the frame
        scrollPaneAvailableDriverTable.setBounds(20,60,250,398);
        scrollPaneAvailableDriverTable.setVisible(true);
        availableDriversTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        availableDriversTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        displaySelectedDriverInfoTable.setRowHeight(25);
        searchPlayerFrame.getContentPane().add(scrollPaneDisplaySelectedDriverTable);           //adding the table to the frame
        scrollPaneDisplaySelectedDriverTable.setBounds(325,290,600,169);
        scrollPaneDisplaySelectedDriverTable.setVisible(true);
        displaySelectedDriverInfoTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        displaySelectedDriverInfoTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        displaySelectedDriverInfoTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        displaySelectedDriverInfoTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        backToMainFromSearchFrame.setBounds(20, 480, 150, 30);        //back button
        searchPlayerFrame.getContentPane().add(backToMainFromSearchFrame);
        backToMainFromSearchFrame.addActionListener(this);

        exitAllFromSearchFrame.setBounds(770, 480, 150, 30);        //exit button
        searchPlayerFrame.getContentPane().add(exitAllFromSearchFrame);
        exitAllFromSearchFrame.addActionListener(this);

        mainSearchFrameLabel.setBounds(430, 10, 200, 30);        //exit button
        searchPlayerFrame.getContentPane().add(mainSearchFrameLabel);
        mainSearchFrameLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));

        userPromptId.setBounds(325,120,250,30);      //setting enter id label bounds
        searchPlayerFrame.getContentPane().add(userPromptId);          //adding the label to the search player frame
        userPromptId.setFont(new Font("Times New Roman", Font.BOLD, 15));

        userInputId.setBounds(325,160,200,30);       //setting text field to get user input bounds
        searchPlayerFrame.getContentPane().add(userInputId);           //adding the text field to the search player frame

        searchDriver.setBounds(550,160,100,30);     //setting the search button bounds
        searchPlayerFrame.getContentPane().add(searchDriver);         //adding the search button to the search player frame
        searchDriver.addActionListener(this);

        idValidity.setBounds(330,190,300,30);       //setting label for system output  bounds
        searchPlayerFrame.getContentPane().add(idValidity);           //adding the label to the search player frame

    }
    /**
     * Method to check if the user input value exists
     **/
    public void validateUserInputValue(){
        int userInputDriverId = Integer.parseInt(userInputId.getText());
        idValidity.setText("");

        for(int i =0;i<FM.getTotalDriver();i++){
            if (userInputDriverId == formula1Drivers[i].getDriverId()){
                searchPlayer(userInputDriverId);
                idValidity.setText("ALL PARTICIPATED RACES DISPLAYED");
                break;
            }
            else{
                idValidity.setText("THE INPUT DRIVER ID DOES NOT EXIST");
                for(int j =0;j<FM.getTotalRaces();j++){
                    displaySelectedDriverInfoRow[j][0] = "";
                    displaySelectedDriverInfoRow[j][1] = "";
                    displaySelectedDriverInfoRow[j][2] = "";
                    displaySelectedDriverInfoRow[j][3] = "";
                }
                displaySelectedDriverInfoTable.repaint();
            }
        }
    }
    /**
     * Method to search a player and display the participated races and positions
     **/
    public void searchPlayer(int userInputDriverId){
        for (int i=0; i<FM.getTotalRaces(); i++) {
            if (raceDetails[i].getEndPos().contains(userInputDriverId)){
                int id = raceDetails[i].getRaceId();
                if (id == 6 || id == 9){
                    displaySelectedDriverInfoRow[i][0] = "Random Race";
                }
                else{
                    displaySelectedDriverInfoRow[i][0] = String.valueOf(id);                               //race id
                }
                displaySelectedDriverInfoRow[i][1] = raceDetails[i].getRaceDate();                                              //race date
                int startPos = raceDetails[i].getStartPos().indexOf(userInputDriverId)+1;
                if (startPos == 0){
                    displaySelectedDriverInfoRow[i][2] = "N/A";
                }
                else{
                    displaySelectedDriverInfoRow[i][2] = String.valueOf(startPos);
                }
                displaySelectedDriverInfoRow[i][3] = String.valueOf(raceDetails[i].getEndPos().indexOf(userInputDriverId)+1);    //end position in a race
            }
        }
        displaySelectedDriverInfoTable.repaint();            //refresh the table
    }
    /**
     * Method to find index of a value in a given array
     *
     * @param arr the array
     * @param  value the value
     *
     **/
    public int findIndex(int[] arr, int value){
        boolean valid = true;
        int index = -1;
        do{
            for(int i = 0;i<arr.length;i++){
                if (arr[i] == value){
                    index = i;
                    valid = false;
                }
            }
        }while(valid);
        return index;
    }
    /**
     * Method to find a random int
     * @param array stores the chosen int to get unique values
     *
     **/
    public int randomInt(ArrayList<Integer> array){
        boolean valid = true;
        int num;
        do{
            num = (int)(Math.random()*(FM.getTotalDriver()-1+1)+1);
            if (!array.contains(num)){
                array.add(num);
                valid = false;
            }
        }while (valid);
        return num;
    }
}