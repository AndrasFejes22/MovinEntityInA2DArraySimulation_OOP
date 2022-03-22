import java.util.Random;

public class VacuumCleaner implements CleaningMachines{

    private static final Random RANDOM = new Random();
    private String mark;

    private Coordinates coordinates;

    private Room room;

    private Direction direction;

    @Override
    public String toString() {
        return "VacuumCleaner{" +
                "mark='" + mark + '\'' +
                ", coordinates=" + coordinates +
                ", room=" + room +
                ", direction=" + direction +
                '}';
    }

    /**
     * @param mark
     * @param coordinates


     */
    public VacuumCleaner(String mark, Coordinates coordinates, Direction direction) {
        super();
        this.mark = mark;
        this.coordinates = coordinates;

        this.direction = direction;
    }


    public String getMark() {
        return mark;
    }


    public Coordinates getCoordinates() {
        return coordinates;
    }



    public void setMark(String mark) {
        //ellenőrzés
        this.mark = mark;
    }


    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void cleaningARoom(Room room) throws InterruptedException {
        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object
        //newCoordinates.setRow(oldCoordinates.getRow());//setting old coordinates
        //newCoordinates.setColumn(oldCoordinates.getColumn());
        //Direction direction = Direction.UP;
        //Room room = new Room(RANDOM, 15, 15, new VacuumCleaner("@", newCoordinates,  Direction.RIGHT));
        room.addRandomWalls(2,2);
        int loopNumber = 0;
        while(room.checkRoom(".")) {
            loopNumber++;

            //if(loopNumber % 20 == 0){
                direction = RandomDirection();
            //}

            switch (direction) {
                case UP:
                    if (!room.isEmpty(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),"X")
                    && !room.isEmpty(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),"O")){
                    //if (room.isEmpty(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),".")){ //can go UP
                        newCoordinates.setRow(getCoordinates().getRow() - 1); //set newCoordinates
                        room.setCleaned(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()));
                    }else{
                        //direction = Direction.RIGHT;
                    }
                    break;
                case DOWN:
                    if (!room.isEmpty(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()),"X")
                    &&!room.isEmpty(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()),"O")) { //can go DOWN
                    //if (room.isEmpty(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()),".")) { //can go DOWN
                        newCoordinates.setRow(getCoordinates().getRow() + 1);
                        room.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
                    }else {
                        //direction = Direction.LEFT;
                    }
                    break;
                case LEFT:
                    if (!room.isEmpty(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1),"X")
                    &&!room.isEmpty(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1),"O")) { //can go LEFT
                    //if (room.isEmpty(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1),".")) { //can go LEFT
                        newCoordinates.setColumn(getCoordinates().getColumn() - 1);
                        room.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
                    }else {
                        //direction = Direction.UP;
                    }
                    break;
                case RIGHT:
                    if (!room.isEmpty(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1),"X")
                    && !room.isEmpty(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1),"O")) { //can go RIGHT
                    //if (room.isEmpty(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1),".")) { //can go RIGHT
                        newCoordinates.setColumn(getCoordinates().getColumn() + 1);
                        room.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
                        //room.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()-1));
                    }else{
                        //direction = Direction.DOWN;
                    }
                    break;
                //default:
                    //direction = RandomDirection();
            }
            setCoordinates(newCoordinates);
            //Thread.sleep(100);
            room.drawARoomAndACleaner();
            System.out.println("------" + loopNumber + "-----");
        }
        /////////MÁSIK//////////

    }

    public static Direction RandomDirection(){
        //Direction direction = Direction.RIGHT;
        Random r = new Random();
        int low = 1;
        int high = 5;
        int result = r.nextInt(high-low) + low;
        switch (result) {
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.LEFT;
            case 3:
                return Direction.UP;
            case 4:
                return Direction.DOWN;
        }
        return null;
    }

}
