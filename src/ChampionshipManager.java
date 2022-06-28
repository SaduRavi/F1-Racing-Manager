public interface ChampionshipManager {
    void createDriver(int driverId,String driverName, String driverLocation, String driverTeam, int driverChampionship,
                      int driverFirstPosition, int driverSecondPosition, int driverThirdPosition, int driverPoints, int driverNoOfRacesParticipated);
    void deleteDriver(int driverId);
    void changeDriverTeam (int driverId, String driverNewTeam);
    void displayDriverStatistics(int driverId);
    void displayDriverTable();
    void addRace(int[] detailArray);
    void saveData(String fileName);
    void loadData();
    void loadGUI();
}
