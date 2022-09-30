import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class Node {

    /**
     * Instance variables
     */

    static String Airport_state;   // current airport IATA code
    static String Airport_parent;  // parent airport IATA code
    Airport tempState = Airport.objectInit(Airport_state);  // airport object associated with Airport_state.
    Airport tempParent = Airport.objectInit(Airport_parent); // airport object associated with Airport_parent.


    /**
     * Constructor:
     * Build and initialise objects of this class
     * @param Airport_state the IATA of an airport
     * @param Airport_parent the IATA of an airport's parent

     */
    public Node(String Airport_state, String Airport_parent) {
        this.Airport_state = Airport_state;
        this.Airport_parent = Airport_parent;
//        this.successor = successor;
    }

    public static String getAirport_state() {
        return Airport_state;
    }

    public static String getAirport_parent() {
        return Airport_parent;
    }

    @Override
    public String toString(){
        String string = "nodeState: " + this.tempState.AirportName;
        if(this.tempParent != null){
            string = string + ", parent: " + this.tempParent.AirportName;
        }
        return string;
    }

    /**
     * @return ArrayList
     * This method returns a list of all nodes in solution path
     */
    public static ArrayList<String> solutionPath(){
        ArrayList<String> solution_path = new ArrayList<>();

        Node currentState = new Node(Airport_state, Airport_parent);
        Airport temp = (Airport.objectInit(currentState.Airport_state));
        System.out.println(temp);

        solution_path.add(temp.getICAO_Code());

        while(!(Airport.objectInit(currentState.Airport_parent) == null)){
            solution_path.add(temp.getIATA_Code());
            temp = Airport.objectInit(currentState.Airport_parent);
        }
        Collections.reverse(solution_path);

        return solution_path;

    }

    public static void main(String[]args){

        Map RoutesMap = Route.Router.getRoutes("routes.csv");
        System.out.println(RoutesMap.get("SCN"));
        Map AirportsMap = Airport.AirportFileReader("airports.csv");
        System.out.println(AirportsMap.get("LUX"));



        System.out.println(Route.Router.findRoute("SCN", "LUX"));
//            System.out.println(findRoute("BAH", "BAY"));
//            System.out.println(findRoute("SCN", "JFK"));

    }
}
