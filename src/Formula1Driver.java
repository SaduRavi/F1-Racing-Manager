public class Formula1Driver extends Driver implements Comparable<Formula1Driver>{
    private int driverFirstPos;
    private int driverSecondPos;
    private int driverThirdPos;
    private int driverPoints;
    private int driverParticipatedRaces;


    protected Formula1Driver(int driverId, String driverName, String driverLocation, String driverTeam , int driverChampionship,
                          int driverFirstPos, int driverSecondPos, int driverThirdPos, int driverPoints, int driverParticipatedRaces){
        super(driverId,driverName,driverLocation,driverTeam,driverChampionship);
        this.driverFirstPos = driverFirstPos;
        this.driverSecondPos = driverSecondPos;
        this.driverThirdPos = driverThirdPos;
        this.driverPoints = driverPoints;
        this.driverParticipatedRaces = driverParticipatedRaces;
    }

    public int getDriverFirstPos() {
        return driverFirstPos;
    }

    public void setDriverFirstPos(int driverFirstPos) {
        this.driverFirstPos = driverFirstPos;
    }

    public int getDriverSecondPos() {
        return driverSecondPos;
    }

    public void setDriverSecondPos(int driverSecondPos) {
        this.driverSecondPos = driverSecondPos;
    }

    public int getDriverThirdPos() {
        return driverThirdPos;
    }

    public void setDriverThirdPos(int driverThirdPos) {
        this.driverThirdPos = driverThirdPos;
    }

    public int getDriverPoints() {
        return (driverPoints);
    }

    public void setDriverPoints(int driverPoints) {
        this.driverPoints = driverPoints;
    }

    public int getDriverParticipatedRaces() {
        return driverParticipatedRaces;
    }

    public void setDriverParticipatedRaces(int driverParticipatedRaces) {
        this.driverParticipatedRaces = driverParticipatedRaces;
    }

    @Override
    public int compareTo(Formula1Driver object) {
        if (object.driverPoints == this.driverPoints){
            return object.driverFirstPos - this.driverFirstPos;
        }
        else {
            return object.driverPoints - this.driverPoints;
        }

    }
}
