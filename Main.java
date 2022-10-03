import java.util.Map;
/**
 * @author Omar Basheer
 *
 */
public class Main {

    public static void main(String[]args) {

        Map AirportsMap = Airport.AirportFileReader("airports.csv");
        Map RoutesMap = Route.Router.getRoutes("routes.csv");

//        System.out.println(Airport.objectInit("ACC"));
//        System.out.println(RoutesMap.get("ACC"));
//        System.out.println(Airport.objectInit("NBO"));
//        System.out.println(RoutesMap.get("NBO"));
//        System.out.println(Airport.objectInit("MGQ"));
//        System.out.println(RoutesMap.get("MGQ"));

        /**
         * pass string name of an input file as argument
         * */

//        readWrite.fileReader("accra_to_newyork.txt");
//        System.out.println(Route.Router.findRoute("ACC", "YWG"));
//        readWrite.fileReader("sochi_to_kazan.txt");
        readWrite.fileReader("accra_to_winnipeg.txt");

    }
}
