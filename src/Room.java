import java.util.Random;

public class Room {

    private final int height;

    private final int width;

    private String[][] room;

    private final Random RANDOM;

    private VacuumCleaner cleaner;



    /**
     * @param height
     * @param width
     * @param cleaner
     */
    public Room(Random random, int height, int width, VacuumCleaner cleaner) {
        super();
        this.RANDOM = random;
        this.height = height;
        this.width = width;
        this.cleaner = cleaner;
        this.room = new String[height][width];
        int lastRowIndex = height - 1;
        int lastColumnIndex = width - 1;
        for (int row = 0; row < height; row++) {//initLevel();
            for (int column = 0; column < width; column++) {
                if(row == cleaner.getCoordinates().getRow() && column == cleaner.getCoordinates().getColumn()){
                    room[row][column] = cleaner.getMark();
                }
                if(row == 0 ||  row == lastRowIndex || column == 0 || column == lastColumnIndex) {//walls
                    room[row][column] = "X"; //walls
                }else{
                    room[row][column] = "."; //dirt
                }
            }
        }

    }

    public void addRandomWalls(int numberOfHorizontalWalls, int numberOfWerticalWalls) {
        //TODO fal ne kerüljön a játékosokra
        for(int i = 0; i < numberOfHorizontalWalls; i++) {
            addHorizontalWall();
        }
        for(int i = 0; i < numberOfWerticalWalls; i++) {
            addVerticalWall();
        }
        /*
        for(int i = 0; i < numberOfDiagonalWalls; i++) {
            addDiagonalWall();
        }
        */

    }


    private void addHorizontalWall() {

        int wallWidth = RANDOM.nextInt(width-3);
        int wallRow = RANDOM.nextInt(height-2)+1;
        int wallColumn = RANDOM.nextInt(width-2-wallWidth);
        for(int i = 0; i < wallWidth; i++ ) {
            room[wallRow][wallColumn + i] = "O";
        }
    }

    private void addVerticalWall() {//diagonal wall

        int wallHeight = RANDOM.nextInt(height - 3) + 1;
        int wallRow = RANDOM.nextInt(height - 2 - wallHeight);
        int wallColumn = RANDOM.nextInt(width - 2) + 1;
        for (int i = 0; i < wallHeight; i++) {
            room[wallRow + i][wallColumn] = "O";
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
                if(room[row][column] ==toCheck){
                    return true;
                }
            }
        }
        return false;
    }

    public void drawARoomAndACleaner(){
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {//room[i].length

                Coordinates coordinatesToDraw = new Coordinates(row, column);

                if (coordinatesToDraw.isSame(cleaner.getCoordinates())) {
                    System.out.print(cleaner.getMark());

                } else {
                    System.out.print(getCell(coordinatesToDraw));
                }
            }
            System.out.println();
        }
    }

    public String getCell(Coordinates coordinates) {

        return room[coordinates.getRow()][coordinates.getColumn()];
    }

    public boolean isEmpty(Coordinates coordinates, String mark) {//isDot
        return mark.equals(room[coordinates.getRow()][coordinates.getColumn()]);
    }

    public String setCleaned(Coordinates coordinates) {

        return room[coordinates.getRow()][coordinates.getColumn()] = " ";
    }
}
