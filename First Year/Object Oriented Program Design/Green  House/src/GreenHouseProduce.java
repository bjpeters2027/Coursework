import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;



public class GreenHouseProduce extends AbsGreenHouse implements Sensible{

    List<Double> storedFull = null;
    double mainTemp;
    double mainHumid;

    SuperTempHumidReading mid = new SuperTempHumidReading();
    /**
     * Reads an ordered sequence of data from the weather sensors to store in the greenhouse
     * When called multiple times, appends the new readings after the current sensor readings
     *
     * @param values An ordered sequence of [datetime, temperature, humidity, temperature, humidity, ..., datetime, temperature, humidity,....]
     *               - a date and time in yyyymmddHHMMSS format. E.g. 20231106183930 for Nov 11, 2023, 6:39:30pm
     *               - temperature is either degrees Fahrenheit or -999 for an error case
     *               - humidity is either % from 0.0 to 100.0 or -999 for an error case
     *               Assume the sensor data always starts with a valid date
     *               The multiple temperature humidity pairs for a single datetime come from different plant sensors
     *               The values may skip dates and times when the sensors are off (you cannot assume that the date/time intervals will be regular)
     *               You *may* assume that the datetimes will be in ascending order
     */
    @Override
    public void pollSensorData(List<Double> values) {
        List<Double> timeless = new ArrayList<>();
        List<Double> dateless = new ArrayList<>();
        timeless = NoTimes(values);
        storedFull = timeless;
        dateless = removeDates(values);

        List<Double> humids = new ArrayList<Double>();
        List<Double> temps = new ArrayList<Double>();

        for (int i = 0; i < dateless.size(); i += 2){
            temps.add(dateless.get(i));
        }

        for (int i = 1; i < dateless.size()-1; i += 2){
            humids.add(dateless.get(i));
        }

        List<Double> humidsClean = clean(humids);
        List<Double> tempsClean = clean(temps);

        if((humidsClean.isEmpty() || (tempsClean.isEmpty()))){
            mainHumid = -999.0;
            mainTemp = -999.0;
        }
        else {

            humidsClean.sort(Double::compareTo);
            tempsClean.sort(Double::compareTo);

            double midTemp = tempsClean.get((tempsClean.size() / 2));
            double midHumid = humidsClean.get((humidsClean.size() / 2));

            mainHumid = midHumid;
            mainTemp = midTemp;
        }
    }

    /**
     * produces a pair of the middle temperature and humidity (respectively) from the stored readings ignoring error values (-999s)
     *
     * @return a new SensorReading object that has the middle temperature of all the sensor values (value at index (size() / 2) of the sorted temperatures)
     * and the middle humidity of the sorted humidities
     * If there are no valid temperature or humidity values, respectively, then the resulting sensor reading should have -999 for that data
     */
    @Override
    public TempHumidReading middleReading() {
        if(storedFull == null){
            return new SuperTempHumidReading(-999.0, -999.0);
        }
        return new SuperTempHumidReading(mainTemp, mainHumid);
    }

    /**
     * produces a pair of the middle temperature and humidity (respectively) from the stored readings ignoring error values (-999s)
     *
     * @param onDate the date which to consider medianReadings for (inclusive) with the format YYYYMMDD.0
     * @return a new SensorReading object that has the middle temperature of all the sensor values (value at index (size() / 2) of the sorted temperatures)
     * and the middle humidity of the sorted humidities
     * If there are no valid temperature or humidity values, respectively, then the resulting sensor reading should have -999 for that data
     */
    @Override
    public TempHumidReading middleReading(double onDate) {
        if(storedFull == null){
            return new SuperTempHumidReading(-999.0, -999.0);
        }
        int i = 0;
        List<Double> humids = new ArrayList<Double>();
        List<Double> temps = new ArrayList<Double>();
        if(!(storedFull.contains(onDate))){
            return new SuperTempHumidReading(-999.0, -999.0);
        }
        for(int j = 0; j < storedFull.size(); j++){
            if (storedFull.get(j) == onDate){
                i = j;
            }
        }
        //while (start with int k = i + 1) until next value is > 1000
        ArrayList<Double> dateList = new ArrayList<>();
        int k = i + 1;
        while((k < storedFull.size()) && (storedFull.get(k) < 1000)){
            dateList.add(storedFull.get(k));
            k++;
        }

        for (int l = 0; l < dateList.size(); l += 2){
            temps.add(dateList.get(l));
        }

        for (int h = 1; h < dateList.size()-1; h += 2){
            humids.add(dateList.get(h));
        }

        List<Double> humidsClean = clean(humids);
        List<Double> tempsClean = clean(temps);

        humidsClean.sort(Double::compareTo);
        tempsClean.sort(Double::compareTo);


        double midTemp = tempsClean.get((tempsClean.size()/2));
        double midHumid = humidsClean.get((humidsClean.size()/2));
        return new SuperTempHumidReading(midTemp, midHumid);    }

    /**
     * Removes the times form date values for easier processing
     * @param timeFull a list with full date values
     * @return the same list with the times cut off
     */
    public List<Double> NoTimes(List<Double> timeFull){
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
    @Override
    public List<Double> removeDates(List<Double> dateFull) {
        ArrayList<Double> temp = new ArrayList<>();
        for(Double dub : dateFull){
            if(!(dub > 1000.0)){
                temp.add(dub);
            }
        }
        return temp;
    }

}
