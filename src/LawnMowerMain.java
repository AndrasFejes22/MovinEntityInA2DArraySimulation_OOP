import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LawnMowerMain {

    //The task is actually to move an object in a 2D array that avoids ALL obstacles while traversing the entire array.

    //System.exit(0) van a Garden konstruktorában!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    //Further improvements needed:

    //1.: Exception handling in the main menu /d
    //2.: Moving entity in the garden /d
    //3.: Two, or more mower in the garden
    //4.: addRandomWalls --> addDiagonalWalls
    //5.: write more clean, structured and readable code
    //6.: implement stop() function

    public static void main(String[] args) throws InterruptedException {

        try(Scanner scanner = new Scanner(System.in)){
            new LawnMowerMain().run(scanner);
        }

    }

    private void run(Scanner scanner) throws InterruptedException {

        Coordinates lmCoordinates = new Coordinates(1, 19);
        Coordinates meCoordinates = new Coordinates(2, 19);
        Lawnmower lawnmower = new Lawnmower("@", lmCoordinates, Direction.RIGHT);
        MovingEntity movingentity = new MovingEntity("C", meCoordinates, Direction.LEFT);



        int menuItem;
        try (scanner) {
            ReadFile readFile = new ReadFile(scanner, 21, 21);
            do {
                printingMenu();
                System.out.println();
                menuItem = readInt("Please give a whole number!", scanner);
                switch (menuItem) {
                    case 1:
                        String [][] myArrayPr = readFile.arrayFromTxt("C:/Users/Andris/IdeaProjects/VacuumCleanerSimulation_OOP/src/gardens/gardenForPresentation.txt");
                        Garden garden1 = new Garden (21,21, myArrayPr, lawnmower);
                        Coordinates lmCoordinates0 = new Coordinates(8, 6);
                        Coordinates lmCoordinates1 = new Coordinates(10, 6);
                        Coordinates lmCoordinates2 = new Coordinates(15, 9); //demohoz!
                        garden1.getShortestPathForDemo(Direction.UP, lmCoordinates0, lmCoordinates2);
                        Thread.sleep(3500);
                        garden1.getShortestPathForDemo(Direction.UP, lmCoordinates1, lmCoordinates2);
                        System.out.println();
                        break;
                    case 2:
                        System.out.print("Number of vertical walls? ");
                        int verticalWall = scanner.nextInt();

                        System.out.print("Number of horizontal walls? ");
                        int horizontalWall = scanner.nextInt();

                        System.out.print("Number of diagonal walls? ");
                        int diagonalWall = scanner.nextInt();

                        Garden garden = new Garden(21, 21, lawnmower, verticalWall, horizontalWall, diagonalWall); //generated garden/grid
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
                        Garden garden3 = new Garden (21,21, myArray2, lawnmower);
                        lawnmower.mazeRunner(garden3);
                        System.out.println();
                        break;
                    case 5:
                        String [][] myEmptyArray = readFile.arrayFromTxt("C:/Users/Andris/IdeaProjects/VacuumCleanerSimulation_OOP/src/gardens/emptyGarden.txt");
                        //Garden garden4 = new Garden (21,21, myEmptyArray, lawnmower);
                        Garden garden4 = new Garden (21,21, myEmptyArray, lawnmower);
                        lawnmower.mowingTheLawnInAnEmptyGarden(garden4);
                        System.out.println();
                        break;
                    case 6:
                        String [][] myArray6 = readFile.arrayFromTxt("C:/Users/Andris/IdeaProjects/VacuumCleanerSimulation_OOP/src/gardens/gardenWithDiagonalWalls.txt");
                        Garden garden6 = new Garden (21,21, myArray6, lawnmower, movingentity);
                        lawnmower.mowingTwoObjects(garden6, movingentity);
                        //System.out.println("kezdeti string: "+garden6.getCell(meCoordinates));
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
        System.out.println("1. Spread asterisks presentation (for developers)");
        System.out.println("2. Generated garden with obstacles"); //for tests, can put walls on top of each other and next to each other--> TO DO
        System.out.println("3. Drawn garden with obstacles");
        System.out.println("4. Maze type garden");
        System.out.println("5. Mowing lawn in an empty garden (no obstacles)");
        System.out.println( "\u001b[1;32m" + "6. Mowing two objects in the same garden. NEW!" + "\u001b[0m");
        System.out.println("0. Exit");
    }

    static int readInt(String askMessage, Scanner scanner) {
        boolean inputCorrect;
        int number = 0;
        do {
            inputCorrect = true;
            System.out.println(askMessage);
            try {
                number = scanner.nextInt();
                if(number > 6 || number < 0){
                    System.out.println("You can only enter a number between 0 and 5!");
                    inputCorrect = false;
                }

            } catch (InputMismatchException e) {
                System.out.println("This is not a valid integer!");
                inputCorrect = false;
            } finally {
                scanner.nextLine();
            }
        } while (!inputCorrect);
        return number;
    }
}
