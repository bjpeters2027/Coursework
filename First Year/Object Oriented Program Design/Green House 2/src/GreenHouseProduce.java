import java.util.*;
import java.util.GregorianCalendar;


public class GreenHouseProduce extends AbsGreenHouse implements Sensible, QualityControlable{

    private List<Double> storedFull = null;
    private double mainTemp;
    private double mainHumid;
    private List<Double> storedComplete = null;


    private GregorianCalendar gc = new GregorianCalendar();

    private double usableDate = 0;

    SuperTempHumidReading mid = new SuperTempHumidReading();
    public GreenHouseProduce(GregorianCalendar gc){
        super((GregorianCalendar) gc.clone());
        usableDate = clockAsDatetime();
    }

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
        List<Double> temp = new ArrayList<>();
        for (int i = 0; i < values.size(); i++){
            if ((values.get(i) > 1000) && (values.get(i) >= clockAsDatetime())){
                temp.add(values.get(i));
                setClockTo(values.get(i));
                i++;
                while((i < values.size()) && (values.get(i) < 1000)){
                    temp.add(values.get(i));
                    i++;
                }
                i--;
            }

        }
        storedComplete = temp;
        List<Double> timeless = new ArrayList<>();
        timeless = NoTimes(storedComplete);
        storedFull = timeless;
        mainHumid = midReadingHelper(storedComplete, 2);
        mainTemp = midReadingHelper(storedComplete, 1);
    super.storeData(storedFull);

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
        return super.midOnDate(storedFull, onDate);
    }

    /**
     * computes the current percentage of non-datetime sensor values that are -999.0s
     *
     * @return a percent value between 0.0 and 100.0 inclusive
     */
    @Override
    public double percentError() {
        List<Double> dateless = removeDates(storedComplete);
        int amount = 0;
        for(int i = 0; i<dateless.size(); i++){
            if(dateless.get(i) == -999.0){
                amount++;
            }
        }
        return ( 100 * ((double) amount / dateless.size()));
    }


    /**
     * A helper method to convert a gregroian calendar to a HW3 style datetime double
     * @return a HW3 style datetime double
     */
    private double clockAsDatetime(){
        double year = gc.get(Calendar.YEAR);
        double month = gc.get(Calendar.MONTH) + 1;
        double day = gc.get(Calendar.DAY_OF_MONTH);
        double hour = gc.get(Calendar.HOUR_OF_DAY);
        double minute = gc.get(Calendar.MINUTE);
        double second = gc.get(Calendar.SECOND);
        return second +
                (minute * 100.0) + //shifted 2 decimal places
                (hour * 100.0 * 100.0) + //shifted 4 decimal places
                (day * 100.0 * 100.0 * 100.0) + //shifted 6 decimal places
                (month * 100.0 * 100.0 * 100.0 * 100.0) + //shifted 8 decimal places
                (year * 100.0 * 100.0 * 100.0 * 100.0 * 100.0); //shifted 10 decimal places
    }

    /**
     * Given a datetime as a double, make a java.util.GregorianCalendar object with the
     * appropriate year, month, day of the month, hour of the day, minute, and second.
     *
     * @param datetime a double in the format YYYYMMDDhhmmss.0
     * for example 20231112133045 for the date time Nov 12th 2023 at 1:30:45pm
     */
    public void setClockTo(double datetime) {
        String datetimeStr = String.format("%.0f", datetime);

        int year = Integer.parseInt(datetimeStr.substring(0, 4));
        // Subtract 1 from month because GregorianCalendar months are 0-based
        int month = Integer.parseInt(datetimeStr.substring(4, 6)) - 1;
        int day = Integer.parseInt(datetimeStr.substring(6, 8));
        int hour = Integer.parseInt(datetimeStr.substring(8, 10));
        int minute = Integer.parseInt(datetimeStr.substring(10, 12));
        int second = Integer.parseInt(datetimeStr.substring(12, 14));
        this.gc = new GregorianCalendar(year, month, day, hour, minute, second);
    }



}
