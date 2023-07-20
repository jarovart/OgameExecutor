package Controller;

import Data.Fleet;
import Data.FleetAttackType;
import Data.FleetType;
import Data.Pair;
import Presentation.FleetPresentation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

public class FleetController {

    private final FleetPresentation fleetPresentation;
    private final List<Fleet> executedFleet;
    private Integer fleetCount;
    private Integer maxExpedition = -1;

    private final Integer planetNumber;
    private final boolean onlyCivilShips;

    private final Integer maxKTransporter;
    private final Integer maxGTransporter;

    public FleetController(WebDriver webDriver, String planetNumber, String onlyCivilShips, String maxKTransporter, String maxGTransporter){
        fleetPresentation = new FleetPresentation(webDriver);
        executedFleet = new ArrayList<>();
        fleetCount = 0;
        this.planetNumber = Integer.parseInt(planetNumber);
        this.onlyCivilShips = onlyCivilShips != null && !onlyCivilShips.equalsIgnoreCase("yes");
        this.maxKTransporter =  Integer.parseInt(maxKTransporter);
        this.maxGTransporter =  Integer.parseInt(maxGTransporter);
    }

    public long fleetHasToBeExecuted() {
        final long expoDuration = 300000;
        long destinationTime = Long.MAX_VALUE;
        if (isFleetOnTheWay() && maxExpedition <= executedFleet.size()) {
            destinationTime = executedFleet.get(0).getDestinationTime().getTime()-System.currentTimeMillis();
            System.out.println("Expo size: " + executedFleet.size() + " fastest destination: " +
                    ((!executedFleet.isEmpty()) ? executedFleet.get(0).getDestinationTime() : ""));
            return Math.min(destinationTime, expoDuration);
        }

        Pair<Integer, Integer> pairExpo = getAvailableExpos();
        int maxExpo = pairExpo.getKey();
        int openExpos = pairExpo.getValue();

        if (executedFleet.isEmpty() || executedFleet.size() != maxExpo + openExpos) {
            System.out.println("Expo and fleet are incorrect, fleet will be reloaded");
            executedFleet.clear();
            destinationTime = loadExpoFleetFromWebsite();
        }
        List<Map<FleetType, Integer>> fleetMap = calculateAvailableFleet(openExpos);
        while (fleetMap.size() > 0 && openExpos > 0) {
            //startExpedition(fleetMap.remove(0));
            openExpos--;
        }
        if(executedFleet.isEmpty()){
            return Math.min(destinationTime, expoDuration);
        }
        destinationTime = executedFleet.get(0).getDestinationTime().getTime()-System.currentTimeMillis();
        System.out.println("Expo size: " + executedFleet.size() + " fastest destination: " +
                executedFleet.get(0).getDestinationTime());
        return Math.min(destinationTime, expoDuration);
    }

    public long checkAndExecuteFleet() {
        long offSetWaiting = 5000;
        long standardWaiting = 30000;
        fleetPresentation.clickOnPlanet(planetNumber);
        fleetPresentation.clickOnCurrentFleetList();
        loadExpoFleetFromWebsite();
        Pair<Integer, Integer> pairExpo = getAvailableExpos();
        int maxExpo = pairExpo.getKey();
        int openExpos = pairExpo.getValue();

        if (executedFleet.size() != maxExpo) {
            List<Map<FleetType, Integer>> fleetMap = calculateAvailableFleet(openExpos);
            while (fleetMap.size() > 0 && openExpos > 0) {
                startExpedition(fleetMap.remove(0));
                openExpos--;
            }
        }
        if (executedFleet.isEmpty()) {
            System.out.println("["+new Timestamp(System.currentTimeMillis()) +"] "
                    +"Expo fleet is empty. Fleet will be executed in " + standardWaiting / 1000 + " seconds.");
            return standardWaiting;
        }
        executedFleet.sort(Comparator.comparing(Fleet::getDestinationTime));
        Timestamp destinationTime = executedFleet.get(0).getDestinationTime();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        System.out.println("["+new Timestamp(System.currentTimeMillis()) +"] "
                +"Expo size: " + executedFleet.size() + " fastest destination: " +
                executedFleet.stream().map(Fleet::getDestinationTime).collect(Collectors.toList()));
        if(currentTime.after(destinationTime) || maxExpo != executedFleet.size()){
            return standardWaiting;
        }else{
            return offSetWaiting+(destinationTime.getTime()-currentTime.getTime());
        }
    }

    private long loadExpoFleetFromWebsite() {
        executedFleet.clear();
        List<Integer> currentFleetList = fleetPresentation.showCurrentExpoFleetList();
        for (int i = 0; i < currentFleetList.size(); i++) {
            Fleet fleet = new Fleet("addedFleet"+i+1, FleetAttackType.EXPEDITION, new HashMap<>(),
                    new Timestamp(0), fleetPresentation.getDestinationTimeOfFleet(currentFleetList.get(i)), new Timestamp(0));
            executedFleet.add(fleet);
        }
        executedFleet.sort(Comparator.comparing(Fleet::getDestinationTime));
        if(!executedFleet.isEmpty()){
            return executedFleet.get(0).getDestinationTime().getTime()-System.currentTimeMillis();
        }
        return Long.MAX_VALUE;
    }

    private boolean isFleetOnTheWay() {
        boolean isFleetOnTheWay = true;
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        if(!executedFleet.isEmpty()){
            List<Fleet> arrivedFleets = new ArrayList<>();
            for(Fleet fleet : executedFleet){
                if(currentTime.after(fleet.getDestinationTime())){
                    arrivedFleets.add(fleet);
                    isFleetOnTheWay = false;
                }
            }
            executedFleet.removeAll(arrivedFleets);
            return isFleetOnTheWay;
        }
        return false;
    }

    private List<Map<FleetType, Integer>> calculateAvailableFleet(int openExpos) {
        List<Map<FleetType, Integer>> fleetMapList = new ArrayList<>();
        fleetPresentation.clickOnFleetMenu();
        if(!fleetPresentation.isFleetAvailable() || openExpos <= 0){
            return Collections.emptyList();
        }

        Pair<Integer, Integer> pairTransporter =
                new Pair<>(Integer.parseInt(fleetPresentation.showFleetTypeCount(FleetType.KLEINER_TRANSPORTER)),
                        Integer.parseInt(fleetPresentation.showFleetTypeCount(FleetType.GROSSER_TRANSPORTER)));
        int pathfinderCount = Integer.parseInt(fleetPresentation.showFleetTypeCount(FleetType.PATHFINDER));
        int spioCount = Integer.parseInt(fleetPresentation.showFleetTypeCount(FleetType.SPIONAGESONDE));
        List<FleetType> fleetTypeList = (onlyCivilShips) ? Collections.emptyList() : Arrays.asList(FleetType.REAPER,
                FleetType.ZERSTOERER, FleetType.BOMBER, FleetType.SCHLACHTKREUZER,
                FleetType.SCHLACHTSCHIFF, FleetType.KREUZER, FleetType.SCHWERER_JAEGER, FleetType.LEICHTER_JAEGER);
        List<Pair<FleetType,Integer>> generatedFleetTypeList = generateCurrentFleetTypeList(fleetTypeList);
        while(openExpos > 0){
            Map<FleetType, Integer> fleetMap = new HashMap<>();
            putOneAttackShipIntoMap(fleetMap, generatedFleetTypeList);
            putPathFinderIntoMap(fleetMap, pathfinderCount);
            putSpionagesondeIntoMap(fleetMap, spioCount);
            putGreatTransporterIntoMap(fleetMap, pairTransporter, openExpos);
            fleetMapList.add(fleetMap);
            openExpos--;
        }
        return fleetMapList;
    }

    private void putPathFinderIntoMap(Map<FleetType, Integer> fleetMap, int pathfinderCount) {
        if(pathfinderCount > 0){
            fleetMap.put(FleetType.PATHFINDER, 1);
            pathfinderCount--;
        }
    }

    private void putSpionagesondeIntoMap(Map<FleetType, Integer> fleetMap, int spiosondeCount) {
        if(spiosondeCount > 0){
            fleetMap.put(FleetType.SPIONAGESONDE, 1);
            spiosondeCount--;
        }
    }

    private void putGreatTransporterIntoMap(Map<FleetType, Integer> fleetMap, Pair<Integer, Integer> pairTransporter, int openExpos) {
        int kleinerTransporterCount = pairTransporter.getKey();
        int grosserTransporterCount = pairTransporter.getValue();
        int ktExpoCount = (int)Math.ceil((double) kleinerTransporterCount /openExpos);
        int gtExpoCount = (int)Math.ceil((double) grosserTransporterCount /openExpos);

        if(gtExpoCount > 0 ){
            int count = (gtExpoCount < maxGTransporter) ? gtExpoCount : maxGTransporter;
            fleetMap.put(FleetType.GROSSER_TRANSPORTER, count);
            pairTransporter.setValue(grosserTransporterCount-count);
        }

        if(ktExpoCount > 0 && gtExpoCount < maxGTransporter) {
            int count = (ktExpoCount < maxKTransporter) ? ktExpoCount : maxKTransporter;
            fleetMap.put(FleetType.KLEINER_TRANSPORTER, count);
            pairTransporter.setKey(kleinerTransporterCount - count);
        }
    }

    private void putTransporterIntoMap(Map<FleetType, Integer> fleetMap, Pair<Integer, Integer> pairTransporter, int openExpos) {
        int kleinerTransporterCount = pairTransporter.getKey();
        int grosserTransporterCount = pairTransporter.getValue();
        int totalPower = kleinerTransporterCount + grosserTransporterCount*5;
        int expoPower = totalPower / openExpos;
        int currentLowTransporter = 0;
        int currentHighTransporter = 0;

        if(expoPower <= kleinerTransporterCount){
            currentLowTransporter = expoPower;
            expoPower = 0;
            kleinerTransporterCount -= currentLowTransporter;
        }else{
            expoPower -= kleinerTransporterCount;
            currentLowTransporter = kleinerTransporterCount;
            kleinerTransporterCount = 0;
        }

        if(expoPower > 0){
            if(expoPower < grosserTransporterCount*5){
                currentHighTransporter = (expoPower)/5;
                expoPower = 0;
                grosserTransporterCount -= currentHighTransporter;
            }else{
                expoPower -= grosserTransporterCount*5;
                currentHighTransporter = grosserTransporterCount;
                grosserTransporterCount = 0;
            }
        }

        if(currentLowTransporter > 0){
            fleetMap.put(FleetType.KLEINER_TRANSPORTER, currentLowTransporter);
            pairTransporter.setKey(kleinerTransporterCount);
        }

        if(currentHighTransporter > 0){
            fleetMap.put(FleetType.GROSSER_TRANSPORTER, currentHighTransporter);
            pairTransporter.setValue(grosserTransporterCount);
        }
    }

    private List<Pair<FleetType, Integer>> generateCurrentFleetTypeList(List<FleetType> fleetTypeList) {
        List<Pair<FleetType,Integer>> generatedFleetTypeList = new ArrayList<>();
        for (FleetType fleetType : fleetTypeList) {
            int fleetTypeCount = Integer.parseInt(fleetPresentation.showFleetTypeCount(fleetType));
            if(fleetTypeCount != 0){
                generatedFleetTypeList.add(new Pair<>(fleetType, fleetTypeCount));
            }
        }
        return generatedFleetTypeList;
    }

    private void putOneAttackShipIntoMap(Map<FleetType, Integer> fleetMap, List<Pair<FleetType, Integer>> fleetTypeList){
        int fleetTypeCount = 0;
        while(fleetTypeList.size() != 0) {
            Pair<FleetType, Integer> fleetType = fleetTypeList.get(0);
            fleetTypeCount = fleetType.getValue();

            if(fleetTypeCount > 0){
                fleetMap.put(fleetType.getKey(), 1);
                fleetType.setValue(fleetTypeCount-1);
                break;
            }else{
                fleetTypeList.remove(fleetType);
            }
        }
    }

    private int getMaxExpo(){
        fleetPresentation.clickOnCurrentFleetList();
        if(fleetPresentation.isMaxExpoAvailable()){
            Integer.parseInt(fleetPresentation.showMaxExpo());
        }
        String expoSlots = fleetPresentation.showExpoSlots();
        expoSlots = expoSlots.substring(expoSlots.indexOf(":")+1);
        String[] split = expoSlots.split("/");
        return Integer.parseInt(split[1].trim());
    }
    private Pair<Integer, Integer> getAvailableExpos(){
        if(fleetPresentation.isMaxExpoAvailable()){
            return new Pair<>(Integer.parseInt(fleetPresentation.showMaxExpo()),Integer.parseInt(fleetPresentation.showMaxExpo())-Integer.parseInt(fleetPresentation.showCurrentExpo()));
        }
        String expoSlots = fleetPresentation.showExpoSlots();
        expoSlots = expoSlots.substring(expoSlots.indexOf(":")+1);
        String[] split = expoSlots.split("/");
        return new Pair<>(Integer.parseInt(split[1].trim()),Integer.parseInt(split[1].trim())-Integer.parseInt(split[0].trim()));
    }

    private void startExpedition(Map<FleetType, Integer> fleetMap){
        fleetPresentation.clickOnFleetMenu();
        FleetAttackType fleetAttackType = FleetAttackType.EXPEDITION;
        setFleetInPage1(fleetMap);
        setDestination(fleetAttackType);
        System.out.println("["+new Timestamp(System.currentTimeMillis()) +"] "
                +"StartExpedition with Arrival Time: "+fleetPresentation.getArrivalTime());
        addAndStartExecutedFleet(("Expo"+fleetCount++), fleetAttackType, fleetMap, new Timestamp(System.currentTimeMillis()),
                fleetPresentation.getArrivalTime(), fleetPresentation.getReturnTime());
    }

    private void setDestination(FleetAttackType fleetAttackType) {
        switch(fleetAttackType){
            case EXPEDITION:
                setExpedition();
                break;
            default:
                System.out.println("No Attack in Set Destination");
        }
    }

    private void setExpedition() {
        fleetPresentation.setPositionInput(16);
        fleetPresentation.setFleetAttackType(FleetAttackType.EXPEDITION);
    }

    private Map<FleetType, Integer> setFleetInPage1(Map<FleetType, Integer> fleetMap) {
        for (Map.Entry<FleetType, Integer> entry : fleetMap.entrySet()) {
            fleetPresentation.setFleetTypeCount(entry.getKey(), entry.getValue());
        }
        fleetPresentation.executeWeiter();
        return fleetMap;
    }

    private void setResourcesAndTarget_Page3(long metal, long crystal, long deuterium, FleetAttackType fleetAttackType){
        fleetPresentation.setFleetAttackType(fleetAttackType);
        //fleetPresentation.setMetal(0);
        //fleetPresentation.setCrystal(0);
        //fleetPresentation.setDeuterium(0);

        addAndStartExecutedFleet(null, FleetAttackType.EXPEDITION, null, null,
                fleetPresentation.getArrivalTime(), fleetPresentation.getReturnTime());
    }


    private void addAndStartExecutedFleet(String name, FleetAttackType fleetAttackType,
          Map<FleetType, Integer> fleetTypeCount, Timestamp startTime, Timestamp destinationTime, Timestamp returnTime){
        Fleet fleet = new Fleet(name, fleetAttackType, fleetTypeCount, startTime, destinationTime, returnTime);
        executedFleet.add(fleet);
        executedFleet.sort(Comparator.comparing(Fleet::getDestinationTime));
        fleetPresentation.executeWeiterPage2();
    }
}
