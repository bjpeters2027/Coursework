import java.util.ArrayList;
import java.util.List;

public interface ParsedDataStrategy{

    /**
     * Removes the times for processing by date
     * @param timeFull a list of data with dates and humidities
     * @return the same list but with the dateTimes only having the date portions by chopping
     * off the time bit
     */
    public  List<Double> NoTimes(List<Double> timeFull);

    /**
     * Cleans a list of dates for easier processing
     * @param dateFull a list with dates in it
     * @return the same list with no dates, ply humidities and temperatures
     */
    public  List<Double> removeDates(List<Double> dateFull);


    /**
     * removes all -999.0s
     * @param toBeCleaned a list sith -999.0s
     * @return the same list without -999.0s
     */
    public  List<Double> clean(List<Double> toBeCleaned);


    /**
     * assists in the calculation of middlepoint
     * @param listy a list of data
     * @param tORh an indicator for whether to calculate for humidity or temperature
     *             1 meaning temperature and any other int meaning humidity
     * @return the middle point in the givin list
     */
    public double midReadingHelper(List<Double> listy, int tORh);

    /**
     * produces a pair of the middle temperature and humidity (respectively) from the stored readings ignoring error values (-999s)
     *
     * @param dateTime the date which to consider medianReadings for (inclusive) with the format YYYYMMDD.0
     * @return a new SensorReading object that has the middle temperature of all the sensor values (value at index (size() / 2) of the sorted temperatures)
     * and the middle humidity of the sorted humidities
     * If there are no valid temperature or humidity values, respectively, then the resulting sensor reading should have -999 for that data
     */

    public SuperTempHumidReading midOnDate(List<Double> listy, double dateTime);

    public void switchStrategy(ParsedDataStrategy otherStrategy);
}
