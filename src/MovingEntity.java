import java.util.Random;

public class MovingEntity {

    private String mark;

    private Coordinates coordinates;

    private Direction direction;

    @Override
    public String toString() {
        return "VacuumCleaner{" +
                "mark='" + mark + '\'' +
                ", coordinates=" + coordinates +

                ", direction=" + direction +
                '}';
    }


    public MovingEntity() {

    }


    public MovingEntity(String mark, Coordinates coordinates, Direction direction) {
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setMark(String mark) {
        //ellenőrzés
        this.mark = mark;
    }


    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    //maga a movingEntity mozgása
    //megoldandó: arra az egy pillnatra egy "."-->"C" cserét kéne csinálni, különben a "@" csak "."-ot lát és rálép a "C"-re
    //valami pályamásolat kéne
    // csak akkor látja őt a mower ha pályatartozék, DE HA pályatartozék, akkor elfedi a pályát, és nem lehet a setCleaned3()-at beáálítani, mert az MINDIG "C" lesz!
    public void makeMove(Garden garden) throws InterruptedException{
        String[][] levelCopy = garden.copy(garden.getGarden());
        Thread.sleep(50);
        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object

        //newCoordinates.setRow(oldCoordinates.getRow());//setting old coordinates
        //newCoordinates.setColumn(oldCoordinates.getColumn());
        Direction direction = RandomDirection();
        switch (direction) {
            case UP:
                if (garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),".")//ide léphet
                        || garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn())," ")) { //can go UP
                    newCoordinates.setRow(getCoordinates().getRow() - 1); //set newCoordinates
                    setCoordinates(newCoordinates);
                    setDirection(Direction.UP);// új
                    //uj
                    //String cell = levelCopy[getCoordinates().getRow() -1 ][getCoordinates().getColumn()];//új, nem jó, mert ez az AHOVA tud lépni
                    //String cell = garden.getCell(new Coordinates(getCoordinates().getRow() + 1 , getCoordinates().getColumn()));//új
                    //Coordinates tempCoordinates = new Coordinates(getCoordinates());//új
                    //tempCoordinates.setRow(getCoordinates().getRow() + 1);//új
                    //garden.setCleaned3(tempCoordinates, cell);//új
                }
                break;
            case DOWN:
                if (garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()), ".")
                        || garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()), " ")) { //can go DOWN
                    newCoordinates.setRow(getCoordinates().getRow() + 1);
                    setCoordinates(newCoordinates);
                    setDirection(Direction.DOWN);// új
                    //uj
                    //String cell = garden.getCell(new Coordinates(getCoordinates().getRow() - 1 , getCoordinates().getColumn()));//új
                    //String cell = levelCopy[getCoordinates().getRow() + 1][getCoordinates().getColumn()];//új
                    //Coordinates tempCoordinates = new Coordinates(getCoordinates());//új
                    //tempCoordinates.setRow(getCoordinates().getRow() - 1);//új
                    //garden.setCleaned3(tempCoordinates, cell);//új
                    //garden.setCleaned3(tempCoordinates, ".");//új
                }
                break;
            case LEFT:
                if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1),".")
                        || garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1)," ")) { //can go LEFT
                    newCoordinates.setColumn(getCoordinates().getColumn() - 1);
                    setCoordinates(newCoordinates);
                    setDirection(Direction.LEFT);// új
                    //lekérjük milyen cella volt ott,uj
                    //String cell = garden.getCell(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1));//új
                    //String cell = levelCopy[getCoordinates().getRow()][getCoordinates().getColumn() - 1];//új
                    //Coordinates tempCoordinates = new Coordinates(getCoordinates());//új
                    //tempCoordinates.setColumn(getCoordinates().getColumn()+1);//új
                    //garden.setCleaned3(tempCoordinates, cell);//új
                    //garden.setCleaned3(tempCoordinates, ".");//új
                }
                break;
            case RIGHT:
                if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1),".")
                        || garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1)," ")) { //can go RIGHT
                    newCoordinates.setColumn(getCoordinates().getColumn() + 1);
                    setCoordinates(newCoordinates);
                    setDirection(Direction.RIGHT);// új
                    //uj
                    //String cell = garden.getCell(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1));//új
                    //String cell = levelCopy[getCoordinates().getRow()][getCoordinates().getColumn() + 1];//új
                    //Coordinates tempCoordinates = new Coordinates(getCoordinates());//új
                    //tempCoordinates.setColumn(getCoordinates().getColumn()-1);//új
                    //garden.setCleaned3(tempCoordinates, cell);//új
                }
                break;
        }
        setCoordinates(newCoordinates);
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



    public void meMoving(Garden garden) throws InterruptedException {
        setMark("K");

        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object

        if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1), ".")) {
            newCoordinates.setColumn(getCoordinates().getColumn() + 1);
            setCoordinates(newCoordinates);// setCoordinates(newCoordinates);
            System.out.println("Right");
        } else

        if (garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()), ".")) {
            newCoordinates.setRow(getCoordinates().getRow() - 1);
            setCoordinates(newCoordinates);
            System.out.println("UP");
        } else

        if (garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()), ".")) {
            newCoordinates.setRow(getCoordinates().getRow() + 1);
            setCoordinates(newCoordinates);
            System.out.println("DOWN");
        } else

        if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1), ".")) {
            newCoordinates.setColumn(getCoordinates().getColumn() - 1);
            setCoordinates(newCoordinates);
            System.out.println("LEFT");
        } else {
            System.out.println("else");
        }
    }
}
