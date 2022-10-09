import java.util.*;

public class Lawnmower {

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

    /**
     * @param mark
     * @param coordinates


     */
    public Lawnmower(String mark, Coordinates coordinates, Direction direction) {
        super();
        this.mark = mark;
        this.coordinates = coordinates;
        this.direction = direction;
        //this.room = room;
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

    ////////////////////////// FŐ MOZGÁSOK //////////////////////////////////

    //1.: Körbenéz, hogy merre tud lépni
    public void mowingTheLawn(Garden garden) throws InterruptedException {
        setMark("@");

        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object

        if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1), ".")) {
            newCoordinates.setColumn(getCoordinates().getColumn() + 1);
            setCoordinates(newCoordinates);// setCoordinates(newCoordinates);
            garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
        } else

        if (garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()), ".")) {
            newCoordinates.setRow(getCoordinates().getRow() - 1);
            setCoordinates(newCoordinates);
            garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
        } else

        if (garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()), ".")) {
            newCoordinates.setRow(getCoordinates().getRow() + 1);
            setCoordinates(newCoordinates);
            garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
        } else

        if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1), ".")) {
            newCoordinates.setColumn(getCoordinates().getColumn() - 1);
            setCoordinates(newCoordinates);
            garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
        } else {

            searchingNextLawn(garden);

        }

    }

    public void mowingTheLawnTwoObjects(Garden garden, MovingEntity movingentity) throws InterruptedException {

        setMark("@");
        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object
        //leáll a program ha rálép a macsekra
        if(getCoordinates().equals(movingentity.getCoordinates())) {
            System.out.println("ERROR");
            System.exit(0);
        }
        // új, kerüli a macskát

        //figyelni kell az egymáshoz viszonyított helyzetüket is (c<c, rowLM > rowMe, stb)
        System.out.println("getDirection() kitérés előtt: " + getDirection());// új
        System.out.println("movingentity.getDirection(): " + movingentity.getDirection());// új
        if (Coordinates.coordinateDistance(getCoordinates(), movingentity.getCoordinates())) {
            //a getGivenDirection()-t okosítani kell, lásd errorGarden.txt

            if(movingentity.getCoordinates().getColumn() > getCoordinates().getColumn()) {
                getGivenDirection(garden, Direction.LEFT);
            } else {
                getGivenDirection(garden, Direction.RIGHT);
            }
            if(movingentity.getCoordinates().getRow() > getCoordinates().getRow()) {
                getGivenDirection(garden, Direction.UP);
            } else {
                getGivenDirection(garden, Direction.DOWN);
            }

            System.out.println("KITÉR");
            System.out.println("getDirection() kitérés után: " + getDirection());// új

        } else { // új, kerüli a macskát, vége


            //System.out.println("közbenső string_me: " + garden.getCell(movingentity.getCoordinates()));
            //System.out.println("közbenső string_lm: " + garden.getCell(getCoordinates()));

            if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1), ".")) {
                newCoordinates.setColumn(getCoordinates().getColumn() + 1);
                setCoordinates(newCoordinates);// setCoordinates(newCoordinates);
                garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
                setDirection(Direction.RIGHT);// új
            } else

            if (garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()), ".")) {
                newCoordinates.setRow(getCoordinates().getRow() - 1);
                setCoordinates(newCoordinates);
                garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
                setDirection(Direction.UP);// új
            } else

            if (garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()), ".")) {
                newCoordinates.setRow(getCoordinates().getRow() + 1);
                setCoordinates(newCoordinates);
                garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
                setDirection(Direction.DOWN);// új
            } else

            if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1), ".")) {
                newCoordinates.setColumn(getCoordinates().getColumn() - 1);
                setCoordinates(newCoordinates);
                garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
                setDirection(Direction.LEFT);// új
            } else {
                searchingNextLawn(garden, movingentity);
                // movingentity.makeMove(garden);

            }
        }

    }

    //2.: Ha elfogyott a gyep, megkeresi a következőt, itt van a getShortestPath, és a moveToTheNextLawn(garden) !!!!!
    public void searchingNextLawn(Garden garden) throws InterruptedException {

        setMark("?");
        Coordinates c2 = new Coordinates();
        outer : for (int k = 1; k < garden.getHeight() ; k++) {//height - 1
            for (int m = 1; m < garden.getWidth() ; m++) {//width - 1
                Coordinates newCoordinates = new Coordinates(k, m);
                if (garden.isMark(newCoordinates, ".")) {
                    Thread.sleep(100);
                    c2.setRow(k);
                    c2.setColumn(m);
                    while (!getCoordinates().isSame(c2)) {
                        setDirection(garden.getShortestPath(getDirection(), getCoordinates(), c2));
                        moveToTheNextLawn(garden); //felokosított mozgás
                        garden.draw();//ha nem rajzolunk, olyan mintha ugrana, akár 10 lépést is!
                        break outer;//**odaért a koszhoz
                    }
                }
            }
        }
        mowingTheLawn(garden);//**nyír
    }


    //3.: Megtalálta, elindul a gyep felé, minden körben megkapja a helyes directiont a /setDirection(garden.getShortestPath(getDirection(), getCoordinates(), c2));/ segítségével
    public void moveToTheNextLawn(Garden garden) throws InterruptedException {
        setMark("M");
        //System.out.println("moving to the next dirt");//kiírja, akkor is, ha nincs dirt, mert ez egy mozgató method, és ez a cleanerGoHome() is!
        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object

        switch (getDirection()) {
            case UP:
                if (garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),".")
                        || garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn())," ")){
                    newCoordinates.setRow(getCoordinates().getRow() - 1); //set newCoordinates
                    //room.setCleaned3(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()), "o");//csaka maze-hez!!!
                }
                break;
            case DOWN:
                if (garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()),".")
                        || garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn())," ")){
                    newCoordinates.setRow(getCoordinates().getRow() + 1); //set newCoordinates
                    // room.setCleaned3(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()), "o");//csaka maze-hez!!!
                }
                break;
            case LEFT:
                if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1),".")
                        || garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1)," ")){
                    newCoordinates.setColumn(getCoordinates().getColumn() - 1);
                    //room.setCleaned3(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()), "o");//csaka maze-hez!!!
                }
                break;
            case RIGHT:
                if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1),".")
                        || garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1)," ")){
                    newCoordinates.setColumn(getCoordinates().getColumn() + 1);
                    //room.setCleaned3(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()), "o");//csaka maze-hez!!!
                }
                break;
            //setCoordinates(newCoordinates);//komment
        }
        setCoordinates(newCoordinates);
        Thread.sleep(30);


    }

    //a tulajdonképpeni App
    public void mowing(Garden garden) throws InterruptedException {
        while (garden.checkRoom(".")) {
            mowingTheLawn(garden);
            Thread.sleep(100);
            garden.draw();
        }
        mowerGoHome(garden);
    }

    public void mowingTwoObjects(Garden garden, MovingEntity movingentity) throws InterruptedException {
        System.out.println("kezdeti string_me: "+garden.getCell(movingentity.getCoordinates()));
        System.out.println("kezdeti string_lm: "+garden.getCell(getCoordinates()));
        //movingentity.meMoving(garden);//új
        //Coordinates meCoordinates = new Coordinates(5, 5);
        //MovingEntity movingentity = new MovingEntity("C", meCoordinates, Direction.UP); //új
        while (garden.checkRoom(".")) {
            //mowingTheLawn(garden, movingentity);
            movingentity.makeMove(garden);//új
            Thread.sleep(50);
            garden.draw();
            //garden.draw2();//új
        }
        //mowerGoHome(garden, movingentity);
    }

    public void movingInAnEmptyGarden(Garden garden) throws InterruptedException {
        Coordinates firstCoordinates = new Coordinates(1,1);
        setMark("@");
        garden.setCleaned(firstCoordinates);
        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object

        if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1), ".")) {
            newCoordinates.setColumn(getCoordinates().getColumn() + 1);
            setCoordinates(newCoordinates);// setCoordinates(newCoordinates);
            garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
        } else

        if (garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()), ".")) {
            newCoordinates.setRow(getCoordinates().getRow() - 1);
            setCoordinates(newCoordinates);
            garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
        } else

        if (garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()), ".")) {
            newCoordinates.setRow(getCoordinates().getRow() + 1);
            setCoordinates(newCoordinates);
            garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
        } else

        if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1), ".")) {
            newCoordinates.setColumn(getCoordinates().getColumn() - 1);
            setCoordinates(newCoordinates);
            garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
        }



    }
    public void mowingTheLawnInAnEmptyGarden(Garden garden) throws InterruptedException {

        while (garden.checkRoom(".")) {

            movingInAnEmptyGarden(garden);
            Thread.sleep(30);
            garden.draw();

        }

        mowerGoHome(garden);

    }


    //ha lenyirta a füvet visszamegy a kiindulópontra
    public void mowerGoHome(Garden garden) throws InterruptedException {
        Coordinates c2 = new Coordinates();
        int ctr = 0;
        c2.setRow(1);
        c2.setColumn(1);

        while (!getCoordinates().isSame(c2)) {

            setDirection(garden.getShortestPath(getDirection(),getCoordinates(), c2));
            moveToTheNextLawn(garden);
            garden.draw();
            System.out.println("-------" + ctr + "c-----");
            Thread.sleep(60);
            ctr++;

        }

    }

    public void mazeRunner(Garden garden) throws InterruptedException {


        //while (!(getCoordinates().isSame(new Coordinates(19,19)))) {//labyrinth, find certain point
        while (garden.checkRoom(".")) {
            runMaze(garden);
            Thread.sleep(100);
            garden.draw();
        }
        System.out.println("goToTheStartPosition_while");
        goToTheStartPosition(garden);
    }

    private void goToTheStartPosition(Garden garden) throws InterruptedException {
        System.out.println("goToTheStartPosition");
        Coordinates c2 = new Coordinates();
        int ctr = 0;
        c2.setRow(1);
        c2.setColumn(1);

        while (!getCoordinates().isSame(c2)) {

            moveToTheStart(garden);
            garden.draw();
            System.out.println("-------" + ctr + "c-----");
            Thread.sleep(60);
            ctr++;
        }

    }

    public void moveToTheStart(Garden garden) throws InterruptedException {
        setMark("M");

        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object

        if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1), " ")) {
            newCoordinates.setColumn(getCoordinates().getColumn() + 1);
            setCoordinates(newCoordinates);//setCoordinates(newCoordinates);
            garden.setCleaned3(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()-1), "*");
        } else if (garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()), " ")) {
            newCoordinates.setRow(getCoordinates().getRow() - 1);
            setCoordinates(newCoordinates);
            garden.setCleaned3(new Coordinates(getCoordinates().getRow()+1, getCoordinates().getColumn()), "*");
        } else if (garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()), " ")) {
            newCoordinates.setRow(getCoordinates().getRow() + 1);
            setCoordinates(newCoordinates);
            garden.setCleaned3(new Coordinates(getCoordinates().getRow()-1, getCoordinates().getColumn()), "*");
        } else if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1), " ")) {
            newCoordinates.setColumn(getCoordinates().getColumn() - 1);
            setCoordinates(newCoordinates);
            garden.setCleaned3(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()+1), "*");

        }
        Thread.sleep(30);
    }



    public void runMaze(Garden garden) throws InterruptedException {
        garden.setCleaned(new Coordinates(1, 1));
        System.out.println(getCoordinates().getRow());
        System.out.println(getCoordinates().getColumn());

        setMark("@");

        System.out.println("Mark? " + getMark());

        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object
        if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1), ".")) {
            newCoordinates.setColumn(getCoordinates().getColumn() + 1);
            setCoordinates(newCoordinates);
            garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
        } else if (garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()), ".")) {
            newCoordinates.setRow(getCoordinates().getRow() - 1);
            setCoordinates(newCoordinates);
            garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
        } else if (garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()), ".")) {
            newCoordinates.setRow(getCoordinates().getRow() + 1);
            setCoordinates(newCoordinates);
            garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
        } else if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1), ".")) {
            newCoordinates.setColumn(getCoordinates().getColumn() - 1);
            setCoordinates(newCoordinates);
            garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
        } else {
            if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1), " ")) {
                newCoordinates.setColumn(getCoordinates().getColumn() - 1);
                setCoordinates(newCoordinates);

                garden.setCleaned2(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1), "-");
            } else if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1), " ")) {
                newCoordinates.setColumn(getCoordinates().getColumn() + 1);
                setCoordinates(newCoordinates);

                garden.setCleaned2(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1), "-");
            } else if (garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()), " ")) {
                newCoordinates.setRow(getCoordinates().getRow() + 1);
                setCoordinates(newCoordinates);

                garden.setCleaned2(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()), "-");
            } else if (garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()), " ")) {
                newCoordinates.setRow(getCoordinates().getRow() - 1);
                setCoordinates(newCoordinates);

                garden.setCleaned2(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()), "-");
            }

        }

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
