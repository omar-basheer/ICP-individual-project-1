import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Omar Basheer
 *
 */
public class Airport {
    /**
     * Instance Variables/Fields
     */

    String AirportName;    // name of an airport
    String AirportCity;    // city a given airport is in
    String Country;       //  country a given airport is in
    String IATA_Code;     // IATA code of given airport
    String ICAO_Code;     // ICAO code of given airport
    String AirportID;     // ID of given airport


    /**
     * Constructor:
     * Build and initialise objects of this class
     * @param airportName the name of airport.
     * @param airportID the unique OpenFlights identifier for the airport.
     * @param airportCity the main city served by airport.
     * @param country the country where airport is located.
     * @param IATA_Code the 3-letter IATA code
     * @param ICAO_Code the 4-letter ICAO code
     */
    public Airport(String airportName, String airportCity, String country, String IATA_Code, String ICAO_Code, String airportID) {
        this.AirportName = airportName;
        this.AirportCity = airportCity;
        this.Country = country;
        this.IATA_Code = IATA_Code;
        this.ICAO_Code = ICAO_Code;
        this.AirportID = airportID;
    }

    public Airport(){
        this.AirportName = getAirportName();
        this.AirportCity = getAirportCity();
        this.Country = getCountry();
        this.IATA_Code = getIATA_Code();
        this.ICAO_Code = getICAO_Code();
        this.AirportID = getAirportID();
    }
    // All getters for an airport

    public String getAirportName() {
        return AirportName;
    }

    public String getAirportCity() {
        return AirportCity;
    }

    public String getCountry() {

        return Country;
    }

    public String getIATA_Code() {
        return IATA_Code;
    }

    public String getICAO_Code() {
        return ICAO_Code;
    }

    public String getAirportID() {
        return AirportID;
    }

    public void setAirportName(String airportName) {
        AirportName = airportName;
    }

    public void setAirportCity(String airportCity) {
        AirportCity = airportCity;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setIATA_Code(String IATA_Code) {
        this.IATA_Code = IATA_Code;
    }

    public void setICAO_Code(String ICAO_Code) {
        this.ICAO_Code = ICAO_Code;
    }

    public void setAirportID(String airportID) {
        AirportID = airportID;
    }

    @Override
    // to string for an airport object
    public String toString(){
        return "Airport-: " + "[" + AirportName + ", " + AirportCity + ", " + Country + ", " + IATA_Code + ", " + ICAO_Code + ", " + AirportID + "]";
    }
/**
 *
 */
    //static HashMap<ArrayList<String>, Airport> airportmap = new HashMap<>();
    static HashMap<String, Airport> airportmap = new HashMap<>();

    /**
     * @return  HashMap
     * This method reads the airports file into a HashMap.
     * The keys of the hashmap are the IATA for an Airport and the values are airport objects
     */
    public static HashMap AirportFileReader(String Filename){

        try{
            BufferedReader inputStream = new BufferedReader(new FileReader(Filename));
            String line;
            Airport airport;
            String airportKey;

            while((line = inputStream.readLine()) != null){
                String[]values = line.split(",");
                if(!(values[4].equals("\\N"))){
//                    ArrayList<String> airportKey = new ArrayList<>(Arrays.asList(values[4],values[1], values[2], values[3]));
                    airportKey = values[4];
                    airport = new Airport(values[1], values[2], values[3], values[4], values[5], values[0]);
                    airportmap.putIfAbsent(airportKey, airport);
                }
//                values[1] name
//                values[2] city
//                values[3] country
//                values[0] airport id
//                values[4] IATA
//                values[5] ICAO
            }
            inputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        for(Map.Entry<String, Airport> entry: map.entrySet()){
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//        }
        return airportmap;
    }

    /**
     * @return Airport
     * This method gets the object in the HashMap associated with a given IATA code.
     */
    public static Airport objectInit(String key) {

        Set<String> keysList = airportmap.keySet();
        Airport temp = new Airport();
        for (String keySet : keysList) {
            if (keySet.equals(key)) {
                temp = airportmap.get(key);
                break;
            }
        }
        return temp;
    }

    public static void main(String[]args){

        Map AirportsMap = AirportFileReader("airports.csv");
        System.out.println(objectInit("SCL"));

    }

}
