

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;


public class WaterHeater extends ServiceCall {

    private int waterHeaterType;

    public WaterHeater(String serviceAddress, String problemDescription, Date date, int waterHeaterType) {

        super(serviceAddress, problemDescription, date);

        //TODO Error checking - is this a valid furnace type?
        this.waterHeaterType = waterHeaterType;
    }
    @Override
    public String toString() {

        String typeString = WaterHeaterTypeManager.getTypeString(waterHeaterType);
        String resolvedDateString = ( resolvedDate == null) ? "Unresolved" : this.resolvedDate.toString();
        String resolutionString = ( this.resolution == null) ? "Unresolved" : this.resolution;
        String feeString = (fee == UNRESOLVED) ? "Unresolved" : "$" + Double.toString(fee);


        return "Water Heater Service Call " + "\n" +
                "Service Address= " + serviceAddress + "\n" +
                "Problem Description = " + problemDescription  + "\n" +
                "Water Heater Type = " + typeString + "\n" +
                "Reported Date = " + reportedDate + "\n" +
                "Resolved Date = " + resolvedDateString + "\n" +
                "Resolution = " + resolutionString + "\n" +
                "Fee = " + feeString ;

    }


    //Inner class to collect the different varieties of furnace
    //When we ask for the type, we'll use the constants from this class to display the choice of types.

    protected static class WaterHeaterTypeManager {

        protected static final int DIRECT = 1;
        protected static final int TANK = 2;

        static HashMap<Integer, String> waterHeaterTypes;

        //Static initializaion block
        static {
            waterHeaterTypes = new HashMap<Integer, String>();
            waterHeaterTypes.put(DIRECT,"On demand hot water");
            waterHeaterTypes.put(TANK, "Hot water tank");
        }

        public static String getTypeString(int typeInt) {

            if (waterHeaterTypes.containsKey(typeInt)) {
                return waterHeaterTypes.get(typeInt);
            }
            else {
                return "Unknown type";
            }

        }

        public static String waterHeaterTypeUserChoices() {

            //Get all of the keys from the Hashmap and sort them in order
            ArrayList<Integer> keys = new ArrayList<Integer>(waterHeaterTypes.keySet());
            Collections.sort(keys);


            //Build and return a string of all the keys and their values
            String userChoices = "";
            for (Integer k : keys) {

                userChoices = userChoices + k + " : " + waterHeaterTypes.get(k) + "\n";

            }

            return userChoices;
        }

    }



}
