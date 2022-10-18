import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadFile {

    //Egy txt file-t 2D arry-á alakít

    private Scanner scanner;
    private int rows;
    private int columns;

    public ReadFile(Scanner scanner) {
        super();
        this.scanner = scanner;
        //this.rows = rows;
        //this.columns = columns;
    }



    public String[][] arrayFromTxt(String filename) {
        bufferedReader(filename);
        String[][] myArray = new String[bufferedReader(filename)[0]][bufferedReader(filename)[1]];
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

    private static int [] bufferedReader(String filename) {
        int[] sizes = new int[2];
        int row = 0;
        int column = 0 ;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            StringBuilder content = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append('\n');
                column = line.length();
                row++;
            }
            System.out.println("A content of file: \n" + content);
            System.out.println("row :"+ row);
            System.out.println("line.length() :"+ column);
        } catch (IOException e) {
            System.out.println("Hiba történt: " + e.getMessage());
        }
        sizes[0] = row;
        sizes[1] = column;
        return sizes;
    }
}
