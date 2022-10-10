import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Garden {

    private  int height;

    private  int width;

    private String[][] garden;

    //private  Random RANDOM;

    private int numberOfHorizontalWalls;
    private int numberOfVerticalWalls;

    private Lawnmower lawnmower;
    private MovingEntity movingEntity;
    static Random RANDOM = new Random();

    /**
     * @param height
     * @param width
     *
     */
    public Garden(int height, int width, Lawnmower lawnmower, int numberOfHorizontalWalls, int numberOfVerticalWalls, int numberOfDiagonalWalls) throws InterruptedException {
        super();
        //this.RANDOM = random;
        this.height = height;
        this.width = width;
        this.lawnmower = lawnmower;
        this.garden = new String[height][width];
        int lastRowIndex = height - 1;
        int lastColumnIndex = width - 1;

        int isPassableCounter = 0;

        do {
            initLevelWithSurroundingWalls(height, width, lastRowIndex, lastColumnIndex);
            addRandomWalls(numberOfHorizontalWalls, numberOfVerticalWalls, numberOfDiagonalWalls);
            isPassableCounter++;
        }while(!isPassable());

        draw();

        isPassable(false);

        System.out.println("The No " + isPassableCounter + " board is passable");

        System.exit(0);//////////////////////////////////////////////////////////////////////////////////////////

    }

    public Garden(int height, int width, String[][] arr, Lawnmower cleaner, MovingEntity movingEntity){
        this.height = height;
        this.width = width;
        this.movingEntity = movingEntity;//új
        this.lawnmower = cleaner;
        this.garden =  new String[height][width];

        for (int row = 0; row < height; row++) {//initLevel();
            for (int column = 0; column < width; column++) {

                garden[row][column] = arr[row][column];

            }
        }
    }

    public Garden(int height, int width, Lawnmower lawnmower, MovingEntity movingEntity, int numberOfHorizontalWalls, int numberOfVerticalWalls) throws InterruptedException {
        super();
        this.movingEntity = movingEntity;//új
        this.height = height;
        this.width = width;
        this.lawnmower = lawnmower;
        this.garden = new String[height][width];
        int lastRowIndex = height - 1;
        int lastColumnIndex = width - 1;

        int isPassableCounter = 0;
        do {
            initLevelWithSurroundingWalls(height, width, lastRowIndex, lastColumnIndex);
            addRandomWalls2(numberOfHorizontalWalls, numberOfVerticalWalls);
            isPassableCounter++;
        }while(!isPassable());
        draw2();//új
        isPassable(true);

        System.out.println("The No " + isPassableCounter + " board is passable");

    }

    public Garden(int height, int width, String[][] arr, Lawnmower lawnmower){
        this.height = height;
        this.width = width;
        //RANDOM = random;
        this.lawnmower = lawnmower;
        this.garden =  new String[height][width];

        for (int row = 0; row < height; row++) {//initLevel();
            for (int column = 0; column < width; column++) {

                garden[row][column] = arr[row][column];

            }
        }
    }

    public String[][] getGarden() {
        return garden;
    }

    public void setGarden(String[][] garden) {
        this.garden = garden;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private void initLevelWithSurroundingWalls(int height, int width, int lastRowIndex, int lastColumnIndex) {
        for (int row = 0; row < height; row++) {//initLevel();
            for (int column = 0; column < width; column++) {

                if(row == 0 ||  row == lastRowIndex || column == 0 || column == lastColumnIndex) {//walls
                    garden[row][column] = "X"; //walls
                }else{
                    garden[row][column] = "."; //dirt
                }
            }
        }
    }


    public Garden(int height, int width, String[][] arr, Random random, Lawnmower lawnmower){
        this.height = height;
        this.width = width;
        RANDOM = random;
        this.lawnmower = lawnmower;
        this.garden =  new String[height][width];

        for (int row = 0; row < height; row++) {//initLevel();
            for (int column = 0; column < width; column++) {

                garden[row][column] = arr[row][column];

            }
        }
    }

    public void addRandomWalls(int numberOfVerticalWalls, int numberOfHorizontalWalls, int numberOfDiagonalWalls) {
        //TODO fal ne kerüljön a játékosokra
        //for(int i = 0; i < numberOfHorizontalWalls; i++) {
            addHorizontalWall(numberOfHorizontalWalls);
        //}
        for(int i = 0; i < numberOfVerticalWalls; i++) {
            addVerticalWall();
        }
        for(int i = 0; i < numberOfDiagonalWalls; i++) {
            addDiagonalWall();
        }

    }

    public void addRandomWalls2(int numberOfVerticalWalls, int numberOfHorizontalWalls) {
        //TODO fal ne kerüljön a játékosokra
        //for(int i = 0; i < numberOfHorizontalWalls; i++) {
        addHorizontalWall(numberOfHorizontalWalls);
        //}
        for(int i = 0; i < numberOfVerticalWalls; i++) {
            addVerticalWall();
        }


    }



    private void addHorizontalWall(int numberOfHorizontalWalls) {

        //Generate of N non-identical random numbers (N = numberOfHorizontalWalls)
        Set<Integer> setWallWidth = new LinkedHashSet<Integer>();
        while (setWallWidth.size() < numberOfHorizontalWalls) {
            setWallWidth.add(RANDOM.nextInt(width-4) + 1);//ne legyen 0

        }
        // numbers to an ArrayList
        ArrayList<Integer> listWallWidth = new ArrayList<Integer>(setWallWidth);
        System.out.println("listWallWidth : "+listWallWidth); //milyen szélesek a vízszintes falak

        //generate column index:
        int wallColumn = 0;
        ArrayList<Integer> columns = new ArrayList<Integer>();
        for(int i = 0; i < listWallWidth.size(); i++){
            wallColumn = RANDOM.nextInt(width-2-listWallWidth.get(i));
            columns.add(wallColumn);

        }
        System.out.println("columns : "+columns);//hányadik oszloptól indulnak a vízszintes falak

        //generate row index
        Set<Integer> setWallRow = new LinkedHashSet<Integer>();
        while (setWallRow.size() < numberOfHorizontalWalls) {
            setWallRow.add(RANDOM.nextInt(height-2)+1);
        }
        // numbers to an ArrayList
        ArrayList<Integer> listWallRow = new ArrayList<Integer>(setWallRow);//hányadik sorban vannak a generált falak



        System.out.println("setWallRow.size: "+setWallRow.size());
        System.out.println("setWallWidth.size: "+setWallWidth.size());
        System.out.println("listWallRow: "+listWallRow);
        System.out.println("listWallWidth: "+listWallWidth);


        //int wallWidth = RANDOM.nextInt(width-3);
        //int wallRow = RANDOM.nextInt(height-2)+1;
        //int wallColumn = RANDOM.nextInt(width-2-listWallWidth.get(j));
        //generate  horizontal walls:
        for(int j = 0; j < numberOfHorizontalWalls; j++ ) {
            for (int i = 0; i < listWallWidth.get(j); i++) {
                wallColumn = columns.get(j);
                garden[listWallRow.get(j)][wallColumn + i] = "X"; //generate walls
                //System.out.println("wallColumn : "+wallColumn);
                //System.out.println("listWallRow.get(i)): " + i + " : " + listWallRow.get(j));
                //System.out.println("listWallWidth.get(i): "+ i + " : " + listWallWidth.get(j));
            }
        }
    }

    private void addVerticalWall() {

        int wallHeight = RANDOM.nextInt(height - 3) + 1;
        int wallRow = RANDOM.nextInt(height - 2 - wallHeight);
        int wallColumn = RANDOM.nextInt(width - 2) + 1;
        for (int i = 0; i < wallHeight; i++) {
            garden[wallRow + i][wallColumn] = "X";
        }

    }

    public void draw(){
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {//room[i].length

                Coordinates coordinatesToDraw = new Coordinates(row, column);
                if (coordinatesToDraw.isSame(lawnmower.getCoordinates())) {
                    System.out.print(lawnmower.getMark());
                }else{
                    System.out.print(getCell(coordinatesToDraw));
                }
            }
            System.out.println();
        }
    }

    public void drawTwoObjects(){
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {//room[i].length

                Coordinates coordinatesToDraw = new Coordinates(row, column);
                if (coordinatesToDraw.isSame(lawnmower.getCoordinates())) {
                    System.out.print(lawnmower.getMark());
                } else if (coordinatesToDraw.isSame(movingEntity.getCoordinates())) {

                    System.out.print(movingEntity.getMark());
                }else{
                    System.out.print(getCell(coordinatesToDraw));
                }
            }
            System.out.println();
        }
    }

    //draw with moving entity in the garden:

    public void draw2() {//draw the mower, and the movingEntity, and the me is shadowing tge grid

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                Coordinates coordinatesToDraw = new Coordinates(row, column);

                if (coordinatesToDraw.isSame(lawnmower.getCoordinates())) {
                    //setCell2(mower);//új
                    System.out.print(lawnmower.getMark());
                    //System.out.print(getCellLm(coordinatesToDraw, mower));//új
                } else if (coordinatesToDraw.isSame(movingEntity.getCoordinates())) {
                    //setCell2(movingEntity);//új
                    //System.out.print(movingEntity.getMark());
                    System.out.print(getCellme(coordinatesToDraw, movingEntity));//új
                } else {
                    //System.out.print(getCell(coordinatesToDraw));
                    System.out.print(getCellGrid(coordinatesToDraw));//új
                }
            }
            System.out.println();


        }
    }

    private void addDiagonalWall() {
        int lastRowIndex = height -1;
        int lastColumnIndex = width -1;
        int wallWidth = RANDOM.nextInt(width-3);
        int wallRow = RANDOM.nextInt(height-2)+1;
        int wallColumn = RANDOM.nextInt(width-2-wallWidth);
        int x = RANDOM.nextInt(10) +1;
        int y = RANDOM.nextInt(5) +1;
        System.out.println("x: " + x); // ha az x = 0 akkor eggyel kevesebb fal is lehet?
        System.out.println("y: " + y); // ha az x = 0 akkor eggyel kevesebb fal is lehet?

        for(int row = x; row < 19; row++ ) {
            for(int column = x; column < 19; column++ ) {
                if ((row+y == column)){

                    garden[row][column] = "X"; //walls
                }
            }
        }
    }





    public void searchAndMove(Coordinates other){
        int rowDifference = 0;
        int columnDifference = 0;
        outer: for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if(garden[row][column].equals(".")) {
                    rowDifference = Math.abs(row - other.getRow());
                    columnDifference = Math.abs(column - other.getColumn());
                    break outer;
                }
            }

        }
        System.out.println(rowDifference);
        System.out.println(columnDifference);

    }

    public void draw2DArray(){

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                System.out.print(garden[row][column] );
            }
            System.out.println();
        }

    }

    public boolean checkRoom(String toCheck){

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if(garden[row][column].equals(toCheck)){
                    return true;
                }
            }
        }
        return false;
    }


    public String getCell(Coordinates coordinates) {

        return garden[coordinates.getRow()][coordinates.getColumn()];
    }

    public boolean isMark(Coordinates coordinates, String mark) {//isDot
        return mark.equals(garden[coordinates.getRow()][coordinates.getColumn()]);
    }

    public String setCleaned(Coordinates coordinates) {

        return garden[coordinates.getRow()][coordinates.getColumn()] = " ";
    }

    public String setCleaned2(Coordinates coordinates, String text) {

        //return room[coordinates.getRow()][coordinates.getColumn()] = "\u001b[1;31m" + text + "\u001b[0m";//red
        return garden[coordinates.getRow()][coordinates.getColumn()] = text ;
    }

    public String getCellme(Coordinates coordinates, MovingEntity movingEntity) {
        return garden[coordinates.getRow()][coordinates.getColumn()] = movingEntity.getMark();
    }

    public String getCellLm(Coordinates coordinates, Lawnmower mower) {
        return garden[coordinates.getRow()][coordinates.getColumn()] = mower.getMark();
    }

    public String getCellGrid(Coordinates coordinates) {
        return garden[coordinates.getRow()][coordinates.getColumn()] = garden[coordinates.getRow()][coordinates.getColumn()];
    }


    public String setCleaned3(Coordinates coordinates, String text) {

        return garden[coordinates.getRow()][coordinates.getColumn()] = "\u001b[1;32m" + text + "\u001b[0m";//green
    }

    //2D array lemásolása
    public String[][] copy(String[][] level){
        String[][] copy = new String[height][width];
        for (int row = 0; row < height; row++) {
            System.arraycopy(level[row], 0, copy[row], 0, width);
        }
        return copy;
    }

    //////////ITT VAN A LÉNYEG/////////////

    private boolean spreadAsterisks(String[][] levelCopy) {
        boolean changed = false;

        for (int row = 0; row < height; row++) {

            for (int column = 0; column < width; column++) {

                // a körülötte lévő helyekre *-ot rak (amíg tud)
                if ("*".equals(levelCopy[row][column])) {// * van valahol
                    if (".".equals(levelCopy[row - 1][column])) {
                        levelCopy[row - 1][column] = "*";
                        changed = true;//csak akkor rajzolom ki a pályát, ha változott valami
                    }
                    if (".".equals(levelCopy[row + 1][column])) {
                        levelCopy[row + 1][column] = "*";
                        changed = true;
                    }
                    if (".".equals(levelCopy[row][column - 1])) {
                        levelCopy[row][column - 1] = "*";
                        changed = true;
                    }
                    if (".".equals(levelCopy[row][column + 1])) {// levelCopy[1][4]:" "
                        levelCopy[row][column + 1] = "*";
                        changed = true;
                    }

                }
            }
        }
        return changed;
    }

    private boolean spreadAsterisksWithCheck(String[][] levelCopy) {
        //létrehozunk egy 2D boolean array-t, ahol megjelöljük, hogy melyik mezőn már volt eredetileg csillag
        //rögzitem azt,hogy amikor belépünk ebbe a metódusba, akkor hol vannak *-ok
        boolean[][] mask = new boolean [height][width]; //alapértelmezetten csupa false-val van tele
        //végigmegyek a levelCopy-n, és ha találok valahol csillagot, ott a mask-ot true-ra állítom:
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if ("*".equals(levelCopy[row][column])) {
                    mask[row][column] = true;

                }
            }
        }
        //csak demo
        /*
        for (int i = 0; i < mask.length; i++) {
            for (int j = 0; j < mask[i].length; j++) {
                if(!mask[i][j]) {
                    System.out.print("F");
                }else{
                    System.out.print("*");
                }

            }
            System.out.println();

        }
        */
        //csak demo vége
        boolean changed = false;
        for (int row = 0; row < height; row++) {

            for (int column = 0; column < width; column++) {

                // a körülötte lévő helyekre *-ot rak (amíg tud)
                if ("*".equals(levelCopy[row][column]) && mask[row][column]) {// * van valahol ÉS a mask az true

                    if (".".equals(levelCopy[row - 1][column]) || " ".equals(levelCopy[row - 1][column])) {
                        // "." vagy " " lehet a pályán, oda tehet csillagot, ez lehetséges útvonal
                        levelCopy[row - 1][column] = "*";
                        changed = true;
                    }
                    if (".".equals(levelCopy[row + 1][column]) || " ".equals(levelCopy[row + 1][column])) {
                        levelCopy[row + 1][column] = "*";
                        changed = true;
                    }
                    if (".".equals(levelCopy[row][column - 1])|| " ".equals(levelCopy[row][column - 1])) {
                        levelCopy[row][column - 1] = "*";
                        changed = true;
                    }
                    if (".".equals(levelCopy[row][column + 1])|| " ".equals(levelCopy[row][column + 1])) {// levelCopy[1][4]:" "
                        levelCopy[row][column + 1] = "*";
                        changed = true;
                    }

                }
            }
        }
        return changed;
    }

    public Direction getShortestPath(Direction defaultDirection, Coordinates from, Coordinates to) throws InterruptedException {
        // pálya lemásolása:
        String[][] levelCopy = copy(garden);// 2D array lemásolása
        // első csillag lehelyezése a célpontra
        levelCopy[to.getRow()][to.getColumn()] = "*";
        while(spreadAsterisksWithCheck(levelCopy)) {
            //draw2DArray2(levelCopy);//csak demohoz//////////////////////////////////////////////////////////////////////
            //Thread.sleep(350);//csak demohoz//////////////////////////////////////////////////////////////////////
            if("*".equals(levelCopy[from.getRow() -1][from.getColumn()])) {//ha a visszafelé terjedő csillagok közül az első felülről
                //jelent meg akkor felfele megyünk*
                System.out.println("up");
                return Direction.UP;
            }
            if("*".equals(levelCopy[from.getRow() +1][from.getColumn()])) {//ha a visszafelé terjedő csillagok közül az első alulról
                //jelent meg akkor lefele megyünk*
                System.out.println("down");
                return Direction.DOWN;
            }
            if("*".equals(levelCopy[from.getRow()][from.getColumn() -1])) {//ha a visszafelé terjedő csillagok közül az első balról
                //jelent meg akkor balra megyünk*
                System.out.println("left");
                return Direction.LEFT;
            }
            if("*".equals(levelCopy[from.getRow()][from.getColumn()+1])) {//ha a visszafelé terjedő csillagok közül az első jobbra
                //jelent meg akkor jobbra megyünk*
                System.out.println("right");
                return Direction.RIGHT;
            }

        }
        return defaultDirection;

    }

    public Direction getShortestPathForDemo(Direction defaultDirection, Coordinates from, Coordinates to) throws InterruptedException {
        // pálya lemásolása:
        String[][] levelCopy = copy(garden);// 2D array lemásolása
        // első csillag lehelyezése a célpontra
        levelCopy[to.getRow()][to.getColumn()] = "*";
        while(spreadAsterisksWithCheck(levelCopy)) {
            draw2DArray2(levelCopy);//csak demohoz//////////////////////////////////////////////////////////////////////
            Thread.sleep(350);//csak demohoz//////////////////////////////////////////////////////////////////////
            if("*".equals(levelCopy[from.getRow() -1][from.getColumn()])) {//ha a visszafelé terjedő csillagok közül az első felülről
                //jelent meg akkor felfele megyünk*
                System.out.println("up");
                return Direction.UP;
            }
            if("*".equals(levelCopy[from.getRow() +1][from.getColumn()])) {//ha a visszafelé terjedő csillagok közül az első alulról
                //jelent meg akkor lefele megyünk*
                System.out.println("down");
                return Direction.DOWN;
            }
            if("*".equals(levelCopy[from.getRow()][from.getColumn() -1])) {//ha a visszafelé terjedő csillagok közül az első balról
                //jelent meg akkor balra megyünk*
                System.out.println("left");
                return Direction.LEFT;
            }
            if("*".equals(levelCopy[from.getRow()][from.getColumn()+1])) {//ha a visszafelé terjedő csillagok közül az első jobbra
                //jelent meg akkor jobbra megyünk*
                System.out.println("right");
                return Direction.RIGHT;
            }
        }
        return defaultDirection;

    }

    public boolean isPassable() throws InterruptedException {
        return isPassable(true);
    }


    //megválaszthatjuk, hogy a magyarázathoz ki akarjuk e rajzoltatni a csillagokat (igen)
    public boolean isPassable(boolean drawAsterisks) throws InterruptedException {
        // pálya lemásolása
        String[][] levelCopy = copy(garden);// 2D array lemásolása, hogy az eredeti pályát ne módosítsam
        // Az első . megkeresése és *-al történő helyettesítése
        outer: for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (levelCopy[row][column].equals(".")) {
                    levelCopy[row][column] = "*"; //tehát eddig az első lehetséges helyre leraktam a *-ot

                    break outer;
                    // break;
                }
            }
        }

        // stars spreading
        // nem tud alul/felülindexelődni mert szélsőséges esetben 1,1-ről vagy
        // max-1,max-1 ről indul
        //ez csak a kirajzoláshoz kell, nem a logikához
        while(spreadAsterisks(levelCopy)) {
            if(drawAsterisks) {
                //Thread.sleep(700); //csillagok terjesztése-demo ki-be-kapcsolás /////////////////////////////////////
                draw2DArray2(levelCopy);

            }
        }

        draw2DArray2(levelCopy);

        //pályamásolat vizsgálata: maradt-e pont valahol
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (levelCopy[row][column].equals(".")) {
                    System.out.println("The board is not passable");
                    //Thread.sleep(1000);
                    return false;
                }
            }
        }

        //System.exit(0);

        return true;

    }

    public static void draw2DArray2(String[][] arr){

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }

    }
}
