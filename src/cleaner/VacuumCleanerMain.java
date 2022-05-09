package cleaner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class VacuumCleanerMain {

	public static void main(String[] args) throws InterruptedException {
		
		Coordinates VcCoordinates = new Coordinates(1, 1);
		Coordinates VcCoordinates2 = new Coordinates(12, 4);
		Coordinates VcCoordinates3 = new Coordinates(3, 5);
        
        VacuumCleaner vc = new VacuumCleaner("@", VcCoordinates, Direction.RIGHT);
       
        
        //Room room = new Room(15, 15, vc, 3, 3);
        
        HashMap<Coordinates, Integer> sm = new HashMap<Coordinates, Integer>();
        
        //sm.put(VcCoordinates, 2);
        //sm.put(VcCoordinates2, 4);
        //sm.put(VcCoordinates3, 7);
        
        
        
        
        
        ////////////MAIN CONTROLL////////////
        //VacuumCleanerController.cleaning(room, vc);
        
        int rows = 15; //it is better to scan how many rows and columns there are
        int columns = 15;
        String [][] myArray = new String[rows][columns];
        try {
            System.out.println("FIRST draw a room from a text: ");
            //File myObj = new File("C:/Users/Andris/eclipse-workspaceJAVA/CleanerSimulation_OOP/src/cleaner/emptyRoom.txt");
            //File myObj = new File("C:/Users/Andris/eclipse-workspaceJAVA/CleanerSimulation_OOP/src/cleaner/rooms");
            File myObj = new File("C:/Users/Andris/eclipse-workspaceJAVA/CleanerSimulation_OOP/src/cleaner/rooms2.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                for (int i = 0; i < myArray.length; i++) {
                    String[] line = myReader.nextLine().trim().split("");
                    //String[] line = myReader.nextLine().split(" ");
                    for (int j = 0; j < line.length; j++) {
                        myArray[i][j] = line[j];
                        System.out.print(myArray[i][j]);
                    }
                    System.out.println();
                }
            }
            myReader.close();
        } catch (NoSuchElementException | FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        Room room = new Room (15,15, myArray, vc);
        
        VacuumCleanerController.cleaning(room, vc);
        /*
        List<Map.Entry<Coordinates, Integer>> singleList = sm.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        //comparingByValue(): termésszetes sorrend
        singleList.get(0);
        
        //.forEach(System.out::println);
        
        System.out.println("sm_ "+sm);
        
        Collection<Integer> values = sm.values();
        List<Integer> val = new ArrayList<Integer>(values);
        System.out.println("values: "+val);  
        
        //Stream<Entry<Coordinates, Integer>> values2 = ((HashMap<Coordinates, Integer>) sm.keySet()).entrySet().stream().sorted(Map.Entry.comparingByValue());
                
        List<Coordinates> val2 = new ArrayList<Coordinates>();
        System.out.println("legkisebb key_row: "+singleList.get(0).getKey().getRow()); 
        System.out.println("legkisebb key_column: "+singleList.get(0).getKey().getColumn()); 
        System.out.println("legkisebb value: "+singleList.get(0).getValue()); 
        */

	}

}
