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

    /**
     * Default constructor for an airport object
     * */
    public Airport(){
        this.AirportName = "";
        this.AirportCity = "";
        this.Country = "";
        this.IATA_Code = "";
        this.ICAO_Code = "";
        this.AirportID = "";
    }

    /**
     * All getters and setters for the class
     * */

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


    /**
     * toString method for Airport objects
     * */
    @Override
    public String toString(){
        return "Airport-: " + "[" + AirportName + ", " + AirportCity + ", " + Country + ", " + IATA_Code + ", " + ICAO_Code + ", " + AirportID + "]";
    }
/**
 * HashMap that stores all airport objects. Keys are an ArrayList containing the IATA, name and country of an airport.
 */
    static HashMap<ArrayList<String>, Airport> airportmap = new HashMap<>();

    /**
     * @return  HashMap.
     * This method reads the airports file into a HashMap.
     * The keys of the hashmap are the IATA for an Airport and the values are airport objects
     */
    public static HashMap AirportFileReader(String Filename){

        try{
            BufferedReader inputStream = new BufferedReader(new FileReader(Filename));
            String line;
            Airport airport;
//            String airportKey;

            while((line = inputStream.readLine()) != null){
                String[]values = line.split(",");
                if(!(values[4].equals("\\N"))){
                    ArrayList<String> airportKey = new ArrayList<>(Arrays.asList(values[4], values[2], values[3]));
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
     * @return Airport.
     * This method returns the object in the airportMap HashMap associated with a given IATA code.
     */
    public static Airport objectInit(String key) {
        Airport airport = new Airport();
        Set<ArrayList<String>> keysList = airportmap.keySet();
        for (ArrayList<String> keySet : keysList) {
            if (keySet.get(0).equals(key)) {
                String name = airportmap.get(keySet).AirportName;
                String city = airportmap.get(keySet).AirportCity;
                String country = airportmap.get(keySet).Country;
                String iata = airportmap.get(keySet).IATA_Code;
                String icao = airportmap.get(keySet).ICAO_Code;
                String id = airportmap.get(keySet).ICAO_Code;
                airport = new Airport(name, city, country, iata, icao, id);
//                break;
            }
        }
        return airport;
    }

}
