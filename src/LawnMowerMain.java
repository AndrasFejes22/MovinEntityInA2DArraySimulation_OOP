import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LawnMowerMain {



    public static void main(String[] args) throws InterruptedException {

        Coordinates VcCoordinates = new Coordinates(1, 1);
        Coordinates VcCoordinates2 = new Coordinates(15, 9);

        Lawnmower vc = new Lawnmower("@ ", VcCoordinates, Direction.UP);


        //Room room = new Room(21, 21, vc, 3, 2); //komment

        //System.out.println("#######################");

        //Thread.sleep(3000);

        //System.out.println("spread asterisk presentation");

        //EZT VAGY IDE, HA GENERÁLT PÁLYA**
        //room.getShortestPath(Direction.UP, VcCoordinates, VcCoordinates2);//csak demohoz/from->to


        ////////////MAIN CONTROL////////////

        ///file-ból////

        int rows = 21; //it is better to scan how many rows and columns there are////////!!!!!!!!!!
        int columns = 21;
        int ctr = 0;
        String[][] myArray = new String[rows][columns];
        try {
            System.out.println("FIRST draw a room from a text: ");
            File myObj = new File("C:/Users/Andris/IdeaProjects/VacuumCleanerSimulation_OOP/src/garden.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                ctr++;
                for (int i = 0; i < myArray.length; i++) {
                    String[] line = myReader.nextLine().trim().split(""); //itt volt jó

                    for (int j = 0; j < line.length; j++) {
                        myArray[i][j] = line[j];
                        System.out.print(myArray[i][j]);
                    }
                    System.out.println();
                }
            }
            System.out.println("ctr :" + ctr);
            myReader.close();
        } catch (NoSuchElementException | FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        ///file-ból vége////

        Garden garden = new Garden(21, 21, myArray, vc); //komment

        //**vagy ide, ha előre megrajzol:
        //room.getShortestPath(Direction.UP, VcCoordinates, VcCoordinates2);//csak demohoz/from->to


        //Room room = new Room(21, 21, myArray, vc); //komment

        LawnMowerController.cleaning(garden, vc);//komment

    }
}
