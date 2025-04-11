public class SuperTempHumidReading extends TempHumidReading{
    /**
     * A standard data constructor
     *
     * @param temperature in Fahrenheit
     * @param humidity    in percentage
     */
    public SuperTempHumidReading(double temperature, double humidity) {
        super(temperature, humidity);
    }


    //Copy constructor - takes in TempHumidReading -> copies its value
    public SuperTempHumidReading(TempHumidReading reading){
        super(reading.temperature, reading.humidity);
    }


    //No argument constructor - takes in nothing -> initializes -999.0
    public SuperTempHumidReading(){
        super(-999.0, -999.0);
    }


    @Override
    public String toString(){
        String first = String.format("%.1f",temperature);
        String second = String.format("%.1f", humidity);

        return ("{" + first + "F;" + second + "%}");
    }

}


