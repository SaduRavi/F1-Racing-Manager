abstract class Driver {
    private int driverId;
    private String driverName;
    private String driverLocation;
    private String driverTeam;
    private int driverChampionship;

    protected Driver(int driverId, String driverName, String driverLocation, String driverTeam, int driverChampionship) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverLocation = driverLocation;
        this.driverTeam = driverTeam;
        this.driverChampionship = driverChampionship;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverLocation() {
        return driverLocation;
    }

    public void setDriverLocation(String driverLocation) {
        this.driverLocation = driverLocation;
    }

    public String getDriverTeam() {
        return driverTeam;
    }

    public void setDriverTeam(String driverTeam) {
        this.driverTeam = driverTeam;
    }

    public int getDriverChampionship() {
        return driverChampionship;
    }

    public void setDriverChampionship(int driverChampionship) {
        this.driverChampionship = driverChampionship;
    }
}
