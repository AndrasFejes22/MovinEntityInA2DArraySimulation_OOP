package cleaner;

import java.util.Random;

public class Room {
	private  int height;

    private  int width;

    private String[][] room;

    //private  Random RANDOM;

    private int numberOfHorizontalWalls;
    private int numberOfVerticalWalls;

    private VacuumCleaner cleaner;

    static Random RANDOM = new Random();

    /**
     * @param height
     * @param width
     *
     */
    public Room(int height, int width, VacuumCleaner cleaner, int numberOfHorizontalWalls, int numberOfVerticalWalls) {
        super();
        //this.RANDOM = random;
        this.height = height;
        this.width = width;
        this.cleaner = cleaner;
        this.room = new String[height][width];
        int lastRowIndex = height - 1;
        int lastColumnIndex = width - 1;

        int isPassableCounter = 0;
        do {
            initLevelWithSurroundingWalls(height, width, lastRowIndex, lastColumnIndex);
            addRandomWalls(numberOfHorizontalWalls, numberOfVerticalWalls);
            isPassableCounter++;
        }while(!isPassable());

        isPassable(true);

        System.out.println("The No " + isPassableCounter + " board is passable");

    }
    
    public Room(int height, int width, String[][] arr, VacuumCleaner cleaner){
        this.height = height;
        this.width = width;
        //RANDOM = random;
        this.cleaner = cleaner;
        this.room =  new String[height][width];

        for (int row = 0; row < height; row++) {//initLevel();
            for (int column = 0; column < width; column++) {

                room[row][column] = arr[row][column];

            }
        }
    }

    private void initLevelWithSurroundingWalls(int height, int width, int lastRowIndex, int lastColumnIndex) {
        for (int row = 0; row < height; row++) {//initLevel();
            for (int column = 0; column < width; column++) {

                if(row == 0 ||  row == lastRowIndex || column == 0 || column == lastColumnIndex) {//walls
                    room[row][column] = "X"; //walls
                }else{
                    room[row][column] = "."; //dirt
                }
            }
        }
    }


    public Room(int height, int width, String[][] arr, Random random, VacuumCleaner cleaner){
        this.height = height;
        this.width = width;
        RANDOM = random;
        this.cleaner = cleaner;
        this.room =  new String[height][width];

        for (int row = 0; row < height; row++) {//initLevel();
            for (int column = 0; column < width; column++) {

                room[row][column] = arr[row][column];

            }
        }
    }

    public void addRandomWalls(int numberOfHorizontalWalls, int numberOfVerticalWalls) {
        //TODO fal ne ker�lj�n a j�t�kosokra
        for(int i = 0; i < numberOfHorizontalWalls; i++) {
            addHorizontalWall();
        }
        for(int i = 0; i < numberOfVerticalWalls; i++) {
            addVerticalWall();
        }
        /*
        for(int i = 0; i < numberOfDiagonalWalls; i++) {
            addDiagonalWall();
        }
        */

    }

    public void draw(){
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {//room[i].length

                Coordinates coordinatesToDraw = new Coordinates(row, column);
                if (coordinatesToDraw.isSame(cleaner.getCoordinates())) {
                    System.out.print(cleaner.getMark());
                }else{
                    System.out.print(getCell(coordinatesToDraw));
                }
            }
            System.out.println();
        }
    }


    private void addHorizontalWall() {

        int wallWidth = RANDOM.nextInt(width-3);
        int wallRow = RANDOM.nextInt(height-2)+1;
        int wallColumn = RANDOM.nextInt(width-2-wallWidth);
        for(int i = 0; i < wallWidth; i++ ) {
            room[wallRow][wallColumn + i] = "X";
        }
    }

    private void addVerticalWall() {//diagonal wall

        int wallHeight = RANDOM.nextInt(height - 3) + 1;
        int wallRow = RANDOM.nextInt(height - 2 - wallHeight);
        int wallColumn = RANDOM.nextInt(width - 2) + 1;
        for (int i = 0; i < wallHeight; i++) {
            room[wallRow + i][wallColumn] = "X";
        }

    }

    public void draw2DArray(){

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                System.out.print(room[row][column] );
            }
            System.out.println();
        }

    }

    public boolean checkRoom(String toCheck){

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if(room[row][column].equals(toCheck)){
                    return true;
                }
            }
        }
        return false;
    }

    
    public String getCell(Coordinates coordinates) {

        return room[coordinates.getRow()][coordinates.getColumn()];
    }

    public boolean isMark(Coordinates coordinates, String mark) {//isDot
        return mark.equals(room[coordinates.getRow()][coordinates.getColumn()]);
    }

    public String setCleaned(Coordinates coordinates) {

        return room[coordinates.getRow()][coordinates.getColumn()] = " ";
    }
    
    public String setCleaned2(Coordinates coordinates) {

        return room[coordinates.getRow()][coordinates.getColumn()] = "c";
    }

    //2D array lem�sol�sa
    private String[][] copy(String[][] level){
        String[][] copy = new String[height][width];
        for (int row = 0; row < height; row++) {
            System.arraycopy(level[row], 0, copy[row], 0, width);
        }
        return copy;
    }



    private boolean spreadAsterisks(String[][] levelCopy) {
        boolean changed = false;

        for (int row = 0; row < height; row++) {

            for (int column = 0; column < width; column++) {

                // a k�r�l�tte l�v� helyekre *-ot rak (am�g tud)
                if ("*".equals(levelCopy[row][column])) {// * van valahol
                    if (".".equals(levelCopy[row - 1][column])) {
                        levelCopy[row - 1][column] = "*";
                        changed = true;
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
        boolean[][] mask = new boolean [height][width]; //alap�rtelmezetten csupa false-val van tele
        //v�gigmegyek a levelCopy-n, �s ha tal�lok valahol csillagot, ott a mask-ot true-ra �ll�tom:
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if ("*".equals(levelCopy[row][column])) {
                    mask[row][column] = true;

                }
            }
        }
        boolean changed = false;
        for (int row = 0; row < height; row++) {

            for (int column = 0; column < width; column++) {

                // a k�r�l�tte l�v� helyekre *-ot rak (am�g tud)
                if ("*".equals(levelCopy[row][column]) && mask[row][column]) {// * van valahol �S a mask az true
                    if (".".equals(levelCopy[row - 1][column]) || " ".equals(levelCopy[row - 1][column])) {//" "
                        levelCopy[row - 1][column] = "*";
                        changed = true;//ez �gy mindig csak 1-et l�p
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

    public Direction getShortestPath(Direction defaultDirection, Coordinates from, Coordinates to) {
        // p�lya lem�sol�sa:
        String[][] levelCopy = copy(room);// 2D array lem�sol�sa
        // els� csillag lehelyez�se
        levelCopy[to.getRow()][to.getColumn()] = "*";
        while(spreadAsterisksWithCheck(levelCopy)) {
            if("*".equals(levelCopy[from.getRow() -1][from.getColumn()])) {//ha a visszafel� terjed� csillagok k�z�l az els� fel�lr�l
                //jelent meg akkor felfele megy�nk*
                return Direction.UP;
            }
            if("*".equals(levelCopy[from.getRow() +1][from.getColumn()])) {//ha a visszafel� terjed� csillagok k�z�l az els� alulr�l
                //jelent meg akkor lefele megy�nk*
                return Direction.DOWN;
            }
            if("*".equals(levelCopy[from.getRow()][from.getColumn() -1])) {//ha a visszafel� terjed� csillagok k�z�l az els� balr�l
                //jelent meg akkor balra megy�nk*
                return Direction.LEFT;
            }
            if("*".equals(levelCopy[from.getRow()][from.getColumn()+1])) {//ha a visszafel� terjed� csillagok k�z�l az els� jobbra
                //jelent meg akkor jobbra megy�nk*
                return Direction.RIGHT;
            }
        }
        return defaultDirection;

    }

    public boolean isPassable() {
        return isPassable(false);

    }

    public boolean isPassable(boolean drawAsterisks) {
        // p�lya lem�sol�sa
        String[][] levelCopy = copy(room);// 2D array lem�sol�sa
        // els� sz�k�z megkeres�se �s *-al t�rt�n� helyettes�t�se
        outer: for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (levelCopy[row][column].equals(".")) {
                    levelCopy[row][column] = "*";

                    break outer;
                    // break;
                }
            }
        }

        // stars spreading
        // nem tud alul/fel�lindexel�dni mert sz�ls�s�ges esetben 1,1-r�l vagy
        // max-1,max-1 r�l indul

        while(spreadAsterisks(levelCopy)) {
            if(drawAsterisks) {
                draw2DArray2(levelCopy);
            }
        }
        //draw2DArray(levelCopy);
        //p�lyam�solat vizsg�lata: maradt-e pont valahol

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (levelCopy[row][column].equals(".")) {
                    return false;
                }
            }
        }

        //System.exit(0);
        //return false;
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
