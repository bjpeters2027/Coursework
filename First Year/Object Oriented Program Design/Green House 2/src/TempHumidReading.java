/**
 * A simple DTO for temperature and humidity sensor data
 */
public abstract class TempHumidReading {

    /**
     * Temperature in Fahrenheit
     */
    public double temperature;
    /**
     * Humidity as a % from 0.0% to 100.0%
     */

    public double humidity;

    /**
     * A standard data constructor
     * @param temperature in Fahrenheit
     * @param humidity in percentage
     */
    public TempHumidReading(double temperature, double humidity){
        this.temperature = temperature;
        this.humidity = humidity;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof TempHumidReading)){
            return false;
        }

        TempHumidReading tester = (TempHumidReading) o;

        boolean temp = (this.temperature == tester.temperature);
        boolean humid = (this.humidity == tester.humidity);
        return (temp && humid);
    }
}
