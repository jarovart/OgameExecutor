package Data;

import java.sql.Timestamp;
import java.util.Map;

public class Fleet {

    private final String name;
    private final FleetAttackType fleetAttackType;
    private Timestamp startTime;
    private Timestamp destinationTime;
    private Timestamp returnTime;

    private Map<FleetType, Integer> fleetTypeCount;

    public Fleet(String name, FleetAttackType fleetAttackType, Map<FleetType, Integer> fleetTypeCount) {
        this.name = name;
        this.fleetAttackType = fleetAttackType;
        this.fleetTypeCount = fleetTypeCount;
    }

    public Fleet(String name, FleetAttackType fleetAttackType, Map<FleetType, Integer> fleetTypeCount, Timestamp startTime, Timestamp destinationTime, Timestamp returnTime) {
        this.name = name;
        this.fleetAttackType = fleetAttackType;
        this.fleetTypeCount = fleetTypeCount;
        this.startTime = startTime;
        this.destinationTime = destinationTime;
        this.returnTime = returnTime;
    }

    public int getAllFleetTypeCounts() {
        int count = 0;
        for (Map.Entry<FleetType, Integer> entry : fleetTypeCount.entrySet()) {
            count += entry.getValue();
        }
        return count;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(Timestamp destinationTime) {
        this.destinationTime = destinationTime;
    }

    public Timestamp getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Timestamp returnTime) {
        this.returnTime = returnTime;
    }
}
