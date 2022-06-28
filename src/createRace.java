import java.util.ArrayList;

public class createRace {
    private int raceId;
    private String raceDate;
    private ArrayList<Integer> startPos;
    private ArrayList<Integer> endPos;

    public createRace(int raceId, String raceDate,ArrayList<Integer> startPos, ArrayList<Integer> endPos) {
        this.raceId = raceId;
        this.raceDate = raceDate;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public String getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(String raceDate) {
        this.raceDate = raceDate;
    }


    public ArrayList<Integer> getStartPos() {
        return startPos;
    }

    public void setStartPos(ArrayList<Integer> startPos) {
        this.startPos = startPos;
    }

    public ArrayList<Integer> getEndPos() {
        return endPos;
    }

    public void setEndPos(ArrayList<Integer> endPos) {
        this.endPos = endPos;
    }
}
