import java.util.Random;

public class VacuumCleanerMain {

    static Random RANDOM = new Random();

    static int loopCounter = 0;

    public static void main(String[] args) throws InterruptedException {

        Coordinates VcCoordinates = new Coordinates(1, 1);

        VacuumCleaner vc = new VacuumCleaner("@", VcCoordinates, Direction.RIGHT);

        Room room = new Room(RANDOM, 15, 15, vc);

        //room.addRandomWalls(2,4);

        //room.draw2DArray();


        //room.drawARoomAndACleaner();


        vc.cleaningARoom(room);

        //room.drawARoomAndACleaner();
    }
}
