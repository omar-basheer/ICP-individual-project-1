import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Airline {

    String IATA_Code;
    String ICAO_Code;
    String AirportID;
    String Country;

    public Airline(String IATA_Code, String ICAO_Code, String AirportID, String Country) {
        this.IATA_Code = IATA_Code;
        this.ICAO_Code = ICAO_Code;
        this. AirportID = AirportID;
        this.Country = Country;
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

    public String getCountry() {
        return Country;
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

    public void setCountry(String country) {
        Country = country;
    }

    public String toString(){
        return "Airline-: " + "[" + IATA_Code + ", " + ICAO_Code + ", " + AirportID + ", " + Country + "]";
    }


    static HashMap<String, Airline> airlinemap = new HashMap<>();


    public static Map AirlinesFileReader(String Filename){

        try{
            BufferedReader inputStream = new BufferedReader(new FileReader(Filename));
            String line;
            String Key;
            Airline airlines;

            while((line = inputStream.readLine()) != null){
                String[]values = line.split(",");
                Key = "[" + values[3] + "]";
                airlines = new Airline(values[3], values[4], values[0], values[5]);
                airlinemap.putIfAbsent(Key, airlines);
                // values[0] = airline id
                // values[3] = Iata
                // values[4] = Icao
                // values[5] = Country
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        for(Map.Entry<String, Airline> entry: map.entrySet()){
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//        }
        return airlinemap;


    }
}

