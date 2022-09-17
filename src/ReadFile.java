import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadFile {

    //Egy txt file-t 2D arry-á alakít

    private Scanner scanner;
    private int rows;
    private int columns;

    public ReadFile(Scanner scanner, int rows, int columns) {
        super();
        this.scanner = scanner;
        this.rows = rows;
        this.columns = columns;
    }



    public String[][] arrayFromTxt(String filename) {
        String[][] myArray = new String[rows][columns];
        try {

            File myObj = new File(filename);

            scanner = new Scanner(myObj);

            while (scanner.hasNextLine()) {

                for (int i = 0; i < myArray.length; i++) {
                    String[] line = scanner.nextLine().trim().split(""); // itt volt jó

                    for (int j = 0; j < line.length; j++) {
                        myArray[i][j] = line[j];
                        System.out.print(myArray[i][j]);
                    }
                    System.out.println();
                }
            }

            scanner.close();

        } catch (NoSuchElementException | FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return myArray;

    }
}
