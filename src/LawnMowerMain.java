import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LawnMowerMain {



    public static void main(String[] args) throws InterruptedException {

        Coordinates VcCoordinates = new Coordinates(1, 1);
        Coordinates VcCoordinates2 = new Coordinates(15, 9); //demo

        Lawnmower lawnmower = new Lawnmower("@ ", VcCoordinates, Direction.UP);
        int width = 16; //it is better to scan how many rows and columns there are////////!!!!!!!!!!
        int height = 16;

        Scanner scanner = new Scanner(System.in);

        ReadFile readFile = new ReadFile(scanner, 21, 21);//ez most csak azért van beégetve nert 21x21 méretű txt-k vannak (rooms)

        //String [][] myArray = readFile.arrayFromTxt("C:/Users/Andris/IdeaProjects/VacuumCleanerSimulation_OOP/src/gardens/gardenWithDiagonalWalls.txt");
        String [][] myArray = readFile.arrayFromTxt("C:/Users/Andris/IdeaProjects/VacuumCleanerSimulation_OOP/src/gardens/maze.txt");

        //Room room = new Room(21, 21, lawnmower, 3, 2); //komment

        //System.out.println("#######################");

        //Thread.sleep(3000);

        //System.out.println("spread asterisk presentation");

        //EZT VAGY IDE, HA GENERÁLT PÁLYA**
        //room.getShortestPath(Direction.UP, VcCoordinates, VcCoordinates2);//csak demohoz/from->to


        ////////////MAIN CONTROL////////////



        Garden garden = new Garden(21, 21, myArray, lawnmower); //komment

        //**vagy ide, ha előre megrajzol:
        //garden.getShortestPath(Direction.UP, VcCoordinates, VcCoordinates2);//csak demohoz/from->to




        lawnmower.mowing(garden);   ////////// MOVING ENTITY IN A RANDOM 2D ARRAY /////////
        lawnmower.runMaze(garden);  ////////// MOVING ENTITY IN A MAZE /////////

    }
}
