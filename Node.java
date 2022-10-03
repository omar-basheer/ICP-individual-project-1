import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * @author Omar Basheer
 *
 */
public class Node {

    /**
     * Instance variables
     */
    private String Airport_state;   // current airport IATA code
    private Node Airport_parent;  // parent airport IATA code


    /**
     * Constructor:
     * Build and initialise objects of this class
     * @param Airport_state the IATA of an airport
     * @param Airport_parent the IATA of an airport's parent

     */
    public Node(String Airport_state, Node Airport_parent) {
        this.Airport_state = Airport_state;
        this.Airport_parent = Airport_parent;
    }


    /**
     * All getters and setters in the class
     */
    public  String getAirport_state() {
        return Airport_state;
    }
    public  Node getAirport_parent() {
        return Airport_parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node node)) return false;
        return getAirport_state().equals(node.getAirport_state());
    }


//    @Override
//    public String toString(){
//        String string = "nodeState: " + getAirport_state();
////        string = string + ", parent: " + Airport_parent;
//        if(Airport_parent != null){
//            string = string + ", parent: " + Airport_parent.getAirport_state();
//        }
//        return string;
//    }


    @Override
    public String toString() {
        return "Node {" +
                "Airport_state='" + Airport_state + '\'' +
                ", Airport_parent=" + Airport_parent +
                '}';
    }

    /**
     * @return ArrayList
     * This method returns a list of all nodes in solution path
     */
    public ArrayList<String> solutionPath(){

        ArrayList<String> solution_path = new ArrayList<>();
        solution_path.add(this.Airport_state);

        Node currentState = this.getAirport_parent();
        while(!(currentState == null)){
            solution_path.add(currentState.Airport_state);
            currentState = currentState.Airport_parent;
        }
        Collections.reverse(solution_path);

        return solution_path;

    }

    public static void main(String[]args) {

        Map AirportsMap = Airport.AirportFileReader("airports.csv");
        Map RoutesMap = Route.Router.getRoutes("routes.csv");

//        System.out.println(Airport.objectInit("ACC"));
//        System.out.println(RoutesMap.get("ACC"));
//        System.out.println(Airport.objectInit("NBO"));
//        System.out.println(RoutesMap.get("NBO"));
//        System.out.println(Airport.objectInit("MGQ"));
//        System.out.println(RoutesMap.get("MGQ"));

//        readWrite.fileReader("accra_to_newyork.txt");
//        System.out.println(Route.Router.findRoute("ACC", "YWG"));
//        readWrite.fileReader("sochi_to_kazan.txt");
        readWrite.fileReader("accra_to_winnipeg.txt");

//        Node node1 = new Node("node1", null);
//        Node node2 = new Node("node2", node1);
//        Node node3 = new Node("node3", node2);
//        Node node4 = new Node("node4", node3);
//
//        System.out.println(node4);
    }
}
