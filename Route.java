import java.io.*;
import java.util.*;

public class Route{

    /**
     * Instance Variables/Fields
     */

    String SA_Code;
    String SA_ID;
    String DA_Code;
    String DA_ID;
    String AirlineCode;
    String AirlineID;
    String Stops;


    /**
     * Constructor:
     * Build and initialise objects of this class
     * @param SA_Code the Unique OpenFlights identifier for a source airport
     * @param SA_ID the Unique OpenFlights identifier for a source airport
     * @param DA_ID the Unique OpenFlights identifier for a destination airport
     * @param  DA_Code the Unique OpenFlights identifier for a destination airport
     * @param AirlineID the unique OpenFlights identifier for airline
     * @param Stops the number of stops on this flight ("0" for direct)
     */
    public Route(String SA_Code, String SA_ID, String DA_Code, String DA_ID, String AirlineCode, String AirlineID, String Stops) {
        this.SA_Code = SA_Code;
        this.SA_ID = SA_ID;
        this.DA_ID = DA_ID;
        this.DA_Code = DA_Code;
        this.AirlineCode = AirlineCode;
        this.AirlineID = AirlineID;
        this.Stops = Stops;
    }

    @Override
    public String toString(){
        return "[Route: " + "(" + SA_Code + ", " + SA_ID + ") to " + "(" + DA_Code + ", " + DA_ID+ ");  " + "AID: " + AirlineID + ";  AC: " + AirlineCode + "; Stops:" + Stops +"]";
    }


    public String getSA_Code() {
        return SA_Code;
    }

    public String getSA_ID() {
        return SA_ID;
    }

    public String getDA_Code() {
        return DA_Code;
    }

    public String getDA_ID() {
        return DA_ID;
    }

    public String getAirlineCode() {
        return AirlineCode;
    }

    public String getAirlineID() {
        return AirlineID;
    }

    public String getStops() {
        return Stops;
    }

    public void setSA_Code(String SA_Code) {
        this.SA_Code = SA_Code;
    }

    public void setSA_ID(String SA_ID) {
        this.SA_ID = SA_ID;
    }

    public void setDA_Code(String DA_Code) {
        this.DA_Code = DA_Code;
    }

    public void setDA_ID(String DA_ID) {
        this.DA_ID = DA_ID;
    }

    public void setAirlineCode(String airlineCode) {
        AirlineCode = airlineCode;
    }

    public void setAirlineID(String airlineID) {
        AirlineID = airlineID;
    }

    public void setStops(String stops) {
        Stops = stops;
    }

    /**
     * This map contians the list of airports IATA that can be reached from a given IATA
     * The key is a source airport IATA and the value is an ArrayList of destination IATA
     */
    static HashMap<String, ArrayList<String>> routeMap = new HashMap<>();
    public static class Router{

        //static HashMap<String, ArrayList<String>> routeEdges = new HashMap<>();

        /**
         * @return HashMap
         * This method reads the routes file into a HashMap
         */
        public static HashMap getRoutes(String Filename){
            try{
                BufferedReader inputStream = new BufferedReader(new FileReader(Filename));
                String line;

                while((line = inputStream.readLine()) != null){
                    String[]values = line.split(",");
                    String Key = values[2];

                    if(routeMap.containsKey(values[2])){
                        ArrayList<String> routeList = routeMap.get(values[2]);
                        String current = values[4];
                        routeList.add(current);
                        routeMap.put(Key, routeList);
                    }
                    else {
                        ArrayList<String> list = new ArrayList<>();
                        String current = values[4];
                        //routeEdges.get(values[2]);
                        list.add(current);
                        routeMap.put(Key, list);
                    }
                }
                System.out.println(routeMap.get("PMI").size());

            } catch (IOException e){
                throw new RuntimeException(e);
            }
            return routeMap;
        }

        /**
         * @return ArrayList
         * This method implements a breadth first search given a source airport IATA and destination airport IATA
         */
        public static ArrayList<String> findRoute(String startState, String goalState) {

            Deque<String> frontier = new ArrayDeque<>();

            // add root node
            Set<String> keys = routeMap.keySet();
            for(String key : keys ){
                if(key.equals(startState))
                    startState = key;
            }
            frontier.addLast(startState);

            // create explored set
            ArrayList<String> exploredSet = new ArrayList<>();

            while(!frontier.isEmpty()){

                String nodeString = frontier.removeFirst();
                Airport.objectInit(nodeString);
//                System.out.println(nodeString);

                if(nodeString.equals(goalState)){
                    System.out.println("found goal: " + nodeString);
                    // return solution path
                    return Node.solutionPath();
                }
                else{
                    exploredSet.add(nodeString);
                    System.out.println("expanding node..." + nodeString);
                    System.out.println("exploredSet: " + exploredSet);
                    System.out.println("frontier: " + frontier);

                    ArrayList<String> successors = routeMap.get(nodeString);
                    for(int i = 0; i < successors.size(); i++){
//                        frontier.add(successors.get(i));
                        for(int j = 0; j < exploredSet.size(); j++){
                            if(!(successors.get(i).equals(exploredSet.get(j)))){
                                frontier.add(successors.get(i));
                            }
                        }
                    }
//                    System.out.println(frontier);
                }
            }

            return exploredSet;
        }


        public static void writeToFile(String file){
            try {
                PrintWriter outPutstream = new PrintWriter(new FileOutputStream(file));
                outPutstream.write("Testing");
                outPutstream.close();
            } catch (FileNotFoundException f) {
                System.out.println("PROBLEM OPENING FILE");
                System.exit(0);
            }
        }


        public static void main(String[]args){

            Map RoutesMap = getRoutes("routes.csv");
            System.out.println(RoutesMap.get("SCN"));
//            Map AirportsMap = Airport.AirportFileReader("airports.csv");


            System.out.println(findRoute("SCN", "LUX"));
//            System.out.println(findRoute("BAH", "BAY"));
//            System.out.println(findRoute("SCN", "JFK"));

        }
    }
}
