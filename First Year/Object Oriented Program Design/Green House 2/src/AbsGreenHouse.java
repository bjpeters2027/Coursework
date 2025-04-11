import java.util.ArrayList;
import java.util.List;
import java.util.GregorianCalendar;


/**
 * An abstract superclass to provide template methods for performance specific subclasses.
 */
public abstract class AbsGreenHouse implements QualityControlable, Sensible{

    private GregorianCalendar gc = new GregorianCalendar();
    private ParsedDataStrategy pds;

    private List<Double> stored = null;

    public AbsGreenHouse() {

    }

    public AbsGreenHouse(GregorianCalendar cal){
        this.gc = cal;
    }

    public void storeData(List<Double> listy){
        stored = listy;
    }

    // GIVEN CODE
    /**
     * Assume a sensor value is a date if it is greater jan 01, 1970
     * @param sensorDatum the datum which may be a date, datetime, temperature, or humidity
     * @return true if it is a formatted date number
     */
    public boolean isDate(double sensorDatum){
        return sensorDatum > 19700101.0;
    }

    /**
     * Assume a sensor value is a date if it is greater jan 01, 1970 00:00:00 represented as a double
     * @param sensorDatum the datum which may be a date, datetime, temperature, or humidity
     * @return true if it is a formatted date number
     */
    public boolean isDateTime(double sensorDatum){
        return sensorDatum > 19700101000000.0;
    }

    /**
     * Converts the double date time format to just the date part by dividing and rounding
     * @param dateTime YYYYMMDDhhmmss.0
     * @return YYYYMMDD.0
     */
    public double toDate(double dateTime){
        return Math.floor(dateTime / 1000000.0); // convert YYYYMMDDhhmmss -> YYYYMMDD
    }

    /**
     * compares two YYYYMMDD.0 for equality
     * @param date1 one YYYYMMDD.0
     * @param date2 another YYYYMMDD.0
     * @return true if they are within some error tolerance (0.001) of each other
     */
    public boolean sameDate(double date1, double date2){
        return Math.abs(date1 - date2) < 0.001;
    }

    public void setStrategy(ParsedDataStrategy otherStrategy) {

    }

    /**
     * Removes the times for processing by date
     * @param timeFull a list of data with dates and humidities
     * @return the same list but with the dateTimes only having the date portions by chopping
     * off the time bit
     */
    public  List<Double> NoTimes(List<Double> timeFull){
        List<Double> temp = new ArrayList<>();
        double bub;
        for(double dub : timeFull){
            if(dub > 10000){
                dub /= 1000000;
                bub = Math.floor(dub);
                temp.add(bub);
            }
            else{
                temp.add(dub);
            }
        }
        return temp;
    }


    /**
     * Cleans a list of dates for easier processing
     * @param dateFull a list with dates in it
     * @return the same list with no dates, ply humidities and temperatures
     */

    public  List<Double> removeDates(List<Double> dateFull) {
        List<Double> temp = new ArrayList<>();
        for(Double dub : dateFull){
            if(!(dub > 1000.0)){
                temp.add(dub);
            }
        }
        return temp;
    }


    /**
     * removes all -999.0s
     * @param toBeCleaned a list sith -999.0s
     * @return the same list without -999.0s
     */
    public  List<Double> clean(List<Double> toBeCleaned) {
        List<Double> ans = new ArrayList<>();
        for(Double dub : toBeCleaned){
            if(!(dub == -999.0)){
                ans.add(dub);
            }
        }
        return ans;
    }


    /**
     * assists in the calculation of middlepoint
     * @param listy a list of data
     * @param tORh an indicator for whether to calculate for humidity or temperature
     *             1 meaning temperature and any other int meaning humidity
     * @return the middle point in the givin list
     */
    public double midReadingHelper(List<Double> listy, int tORh){
        List<Double> dateless = removeDates(listy);
        List<Double> nListy = new ArrayList<Double>();

        //temps
        if(tORh == 1) {
            for (int i = 0; i < dateless.size(); i += 2) {
                nListy.add(dateless.get(i));
            }
        }
        else{ //Humids
            for (int i = 1; i <= dateless.size()-1; i += 2){
                nListy.add(dateless.get(i));
            }
        }

        List<Double> ListyClean = clean(nListy);


        if(ListyClean.isEmpty()){
            return -999.0;
        }

        ListyClean.sort(Double::compareTo);


        return ListyClean.get((ListyClean.size()/2));
    }

    /**
     * produces a pair of the middle temperature and humidity (respectively) from the stored readings ignoring error values (-999s)
     *
     * @param dateTime the date which to consider medianReadings for (inclusive) with the format YYYYMMDD.0
     * @return a new SensorReading object that has the middle temperature of all the sensor values (value at index (size() / 2) of the sorted temperatures)
     * and the middle humidity of the sorted humidities
     * If there are no valid temperature or humidity values, respectively, then the resulting sensor reading should have -999 for that data
     */
    public SuperTempHumidReading midOnDate(List<Double> listy, double dateTime){
        if(listy == null){
            return new SuperTempHumidReading(-999.0, -999.0);
        }
        int i = 0;
        List<Double> humids = new ArrayList<Double>();
        List<Double> temps = new ArrayList<Double>();
        if(!(listy.contains(dateTime))){
            return new SuperTempHumidReading(-999.0, -999.0);
        }
        for(int j = 0; j < listy.size(); j++){
            if (listy.get(j) == dateTime){
                i = j;
            }
        }
        //while (start with int k = i + 1) until next value is > 1000
        ArrayList<Double> dateList = new ArrayList<>();
        int k = i + 1;
        while((k < listy.size()) && (listy.get(k) < 1000)){
            dateList.add(listy.get(k));
            k++;
        }

        for (int l = 0; l < dateList.size(); l += 2){
            temps.add(dateList.get(l));
        }

        for (int h = 1; h <= dateList.size()-1; h += 2){
            humids.add(dateList.get(h));
        }

        List<Double> humidsClean = clean(humids);
        List<Double> tempsClean = clean(temps);

        humidsClean.sort(Double::compareTo);
        tempsClean.sort(Double::compareTo);


        double midTemp = tempsClean.get((tempsClean.size()/2));
        double midHumid = humidsClean.get((humidsClean.size()/2));
        return new SuperTempHumidReading(midTemp, midHumid);
    }

}