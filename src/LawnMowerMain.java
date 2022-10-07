import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LawnMowerMain {



    public static void main(String[] args) throws InterruptedException {

        try(Scanner scanner = new Scanner(System.in)){
            new LawnMowerMain().run(scanner);
        }



        /*
        Coordinates VcCoordinates = new Coordinates(8, 6);
        Coordinates VcCoordinates1 = new Coordinates(10, 6);
        Coordinates VcCoordinates2 = new Coordinates(15, 9); //demohoz!

        Lawnmower lawnmower = new Lawnmower("@ ", VcCoordinates, Direction.UP);
        int width = 16; //it is better to scan how many rows and columns there are////////!!!!!!!!!!
        int height = 16;

        Scanner scanner = new Scanner(System.in);

        ReadFile readFile = new ReadFile(scanner, 21, 21);//ez most csak azért van beégetve nert 21x21 méretű txt-k vannak (gardens/maze)

        //String [][] myArray = readFile.arrayFromTxt("C:/Users/Andris/IdeaProjects/VacuumCleanerSimulation_OOP/src/gardens/gardenWithDiagonalWalls.txt");
        //String [][] myArray = readFile.arrayFromTxt("C:/Users/Andris/IdeaProjects/VacuumCleanerSimulation_OOP/src/gardens/maze.txt");
        String [][] myArray = readFile.arrayFromTxt("C:/Users/Andris/IdeaProjects/VacuumCleanerSimulation_OOP/src/gardens/gardenForPresentation.txt");
        Garden garden = new Garden(21, 21, myArray, lawnmower); //komment
        //Room room = new Room(21, 21, lawnmower, 3, 2); //komment

        //System.out.println("#######################");

        //Thread.sleep(3000);

        //System.out.println("spread asterisk presentation");

        //EZT VAGY IDE, HA GENERÁLT PÁLYA**
        garden.getShortestPath(Direction.UP, VcCoordinates, VcCoordinates2);//csak demohoz/from->to
        Thread.sleep(3500);
        garden.getShortestPath(Direction.UP, VcCoordinates1, VcCoordinates2);//csak demohoz/from->to


        ////////////MAIN CONTROL////////////





        //**vagy ide, ha előre megrajzol:
        //garden.getShortestPath(Direction.UP, VcCoordinates, VcCoordinates2);//csak demohoz/from->to




        //lawnmower.mowing(garden);   ////////// MOVING ENTITY IN A RANDOM GARDEN /////////
        //lawnmower.mazeRunner(garden);  ////////// MOVING ENTITY IN A MAZE /////////
        //lawnmower.mowingTheLawnInAnEmptyGarden(garden); ////////// MOVING ENTITY IN AN EMPTY GARDEN /////////
        */

    }

    private void run(Scanner scanner) throws InterruptedException {

        Coordinates lmCoordinates = new Coordinates(1, 1);
        Coordinates meCoordinates = new Coordinates(11, 14);
        Lawnmower lawnmower = new Lawnmower("@", lmCoordinates, Direction.RIGHT);
        //MovingEntity movingentity = new MovingEntity("C", meCoordinates, Direction.LEFT);

        //unhandled InputMismatchException-->TODO
        int menuItem;
        try (scanner) {
            ReadFile readFile = new ReadFile(scanner, 21, 21);
            do {
                printingMenu();
                System.out.print("\n? ");
                menuItem = scanner.nextInt();
                switch (menuItem) {
                    case 1:
                        String [][] myArrayPr = readFile.arrayFromTxt("C:/Users/Andris/IdeaProjects/VacuumCleanerSimulation_OOP/src/gardens/gardenForPresentation.txt");
                        Garden garden1 = new Garden (21,21, myArrayPr, lawnmower);
                        Coordinates lmCoordinates0 = new Coordinates(8, 6);
                        Coordinates lmCoordinates1 = new Coordinates(10, 6);
                        Coordinates lmCoordinates2 = new Coordinates(15, 9); //demohoz!
                        //garden1.getShortestPathForDemo(Direction.UP, lmCoordinates0, lmCoordinates2);
                        Thread.sleep(3500);
                        //garden1.getShortestPathForDemo(Direction.UP, lmCoordinates1, lmCoordinates2);
                        System.out.println();
                        break;
                    case 2:
                        System.out.print("Number of vertical walls? ");

                        int verticalWall = scanner.nextInt();
                        System.out.print("Number of horizontal walls? ");

                        int horizontalWall = scanner.nextInt();
                        Garden garden = new Garden(21, 21, lawnmower, verticalWall, horizontalWall); //generated garden/grid
                        lawnmower.mowing(garden);
                        System.out.println();
                        break;
                    case 3:
                        String [][] myArray = readFile.arrayFromTxt("C:/Users/Andris/IdeaProjects/VacuumCleanerSimulation_OOP/src/gardens/gardenWithDiagonalWalls.txt");
                        Garden garden2 = new Garden (21,21, myArray, lawnmower);
                        lawnmower.mowing(garden2);
                        System.out.println("kezdeti string: "+garden2.getCell(meCoordinates));
                        System.out.println();
                        break;
                    case 4:
                        String [][] myArray2 = readFile.arrayFromTxt("C:/Users/Andris/IdeaProjects/VacuumCleanerSimulation_OOP/src/gardens/maze.txt");
                        //Garden garden3 = new Garden (21,21, myArray2, lawnmower);
                        //lawnmower.mazeRunner(garden3);
                        System.out.println();
                        break;
                    case 5:
                        String [][] myEmptyArray = readFile.arrayFromTxt("C:/Users/Andris/IdeaProjects/VacuumCleanerSimulation_OOP/src/gardens/emptyGarden.txt");
                        //Garden garden4 = new Garden (21,21, myEmptyArray, lawnmower);
                        Garden garden4 = new Garden (21,21, myEmptyArray, lawnmower);
                        lawnmower.mowingTheLawnInAnEmptyGarden(garden4);
                        System.out.println();
                        break;
                    //default:
                    //System.out.println("There is no such menu item");
                    //break;
                }

            } while (menuItem != 0);
        }

    }




    public static void printingMenu() {
        System.out.println("Please choose from the following menu items:");
        System.out.println("--------------------------------------------");
        System.out.println("1. Spread asterisks presentation");
        System.out.println("2. Generated garden with obstacles"); //for tests, can put walls on top of each other and next to each other--> TO DO
        System.out.println("3. Drawn garden with obstacles");
        System.out.println("4. Maze type garden");
        System.out.println("5. Mowing lawn in an empty garden (no obstacles)");
        System.out.println("0. Exit");
    }
}
