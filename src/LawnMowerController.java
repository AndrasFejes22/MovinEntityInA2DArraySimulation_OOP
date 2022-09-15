public class LawnMowerController {

    static Coordinates c = new Coordinates();

    static int loopCounter = 0;
    static int mainLoopCounter = 0;

    static int height = 15;
    static int width = 15;
    private static Garden garden;
    private static Lawnmower vc;

    public static void cleaning(Garden garden, Lawnmower vc) throws InterruptedException {

        while (garden.checkRoom(".")) {
            vc.cleaningARoom(garden);
            //vc.runMaze(room);
            Thread.sleep(120);
            garden.draw();
        }
        cleanerGoHome(garden, vc);

    }




    private static void cleanerGoHome(Garden garden, Lawnmower vc) throws InterruptedException {

        Coordinates c2 = new Coordinates();
        int ctr = 0;
        c2.setRow(1);
        c2.setColumn(1);


        while (!vc.getCoordinates().isSame(c2)) {

            vc.setDirection(garden.getShortestPath(vc.getDirection(), vc.getCoordinates(), c2));
            vc.moveToTheNextDirt(garden);
            garden.draw();
            System.out.println("-------" + ctr + "c-----");
            Thread.sleep(60);
            ctr++;

        }

    }


}



