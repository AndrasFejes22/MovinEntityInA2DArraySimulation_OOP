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

        System.out.println("belép a mowingTheLawn-ba: ");
        System.out.println();
        setMark("@");
        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object
        //leáll a program ha rálép a macsekra
        if(getCoordinates().equals(movingentity.getCoordinates())) {
            System.out.println("ERROR! Please call for help! A life is in danger!");
            System.exit(0);
        }
        // új, kerüli a macskát

        //figyelni kell az egymáshoz viszonyított helyzetüket is (c<c, rowLM > rowMe, stb)
        System.out.println("getDirection() kitérés előtt_mowingTheLawn: " + getDirection());// új
        System.out.println("movingentity.getDirection()_mowingTheLawn: " + movingentity.getDirection());// új
        if (Coordinates.coordinateDistance(getCoordinates(), movingentity.getCoordinates(), 2)) { //<= distance
            //a getGivenDirection()-t okosítani kell, lásd errorGarden.txt

            getGivenDirection(garden, getDirection(), movingentity);
			/*getGivenDirection(garden, getDirection(), movingentity);
			if ((movingentity.getCoordinates().getColumn() == getCoordinates().getColumn())
					&& movingentity.getCoordinates().getRow() > getCoordinates().getRow()) {
				getGivenDirection(garden, Direction.UP, movingentity);
			} else if ((movingentity.getCoordinates().getColumn() == getCoordinates().getColumn())
					&& movingentity.getCoordinates().getRow() < getCoordinates().getRow()) {
				getGivenDirection(garden, Direction.DOWN, movingentity);
			} else

			if ((movingentity.getCoordinates().getRow() == getCoordinates().getRow())
					&& movingentity.getCoordinates().getColumn() > getCoordinates().getColumn()) {
				getGivenDirection(garden, Direction.LEFT, movingentity);
			} else if (movingentity.getCoordinates().getColumn() > getCoordinates().getColumn()) {
				getGivenDirection(garden, Direction.LEFT, movingentity);
			} else {
				getGivenDirection(garden, Direction.RIGHT, movingentity);
			}
			*/
            System.out.println("KITÉR_mowingTheLawn");
            System.out.println("getDirection() kitérés után_mowingTheLawn: " + getDirection());// új
            System.out.println("row_után_mowingTheLawn: "+getCoordinates().getRow());
            System.out.println("column_után_mowingTheLawn: "+getCoordinates().getColumn());
            //a kitérés előtt után pl UP/UP nem feltétlen rossz, a döntő az egymáshoz viszonyított koordináták helyzete!!!

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
                searchingNextLawnTwoObjects(garden, movingentity);
                // movingentity.makeMove(garden);

            }
        }


    }

    public void searchingNextLawnTwoObjects(Garden garden, MovingEntity movingentity) throws InterruptedException {//működik
        //Coordinates newCoordinates1 = new Coordinates(getCoordinates());// new Coordinate object
        //leáll a program ha rálép a macsekra,új
        if (getCoordinates().equals(movingentity.getCoordinates())) {
            System.out.println("ERROR_SRC");
            System.exit(0);
        }//leáll a program ha rálép a macsekra,új
        // új, kerüli a macskát

        //figyelni kell az egymáshoz viszonyított helyzetüket is (c<c, rowLM > rowMe, stb)
        System.out.println("getDirection() kitérés előtt_SRC: " + getDirection());// új
        System.out.println("movingentity.getDirection()_SRC: " + movingentity.getDirection());// új

        if (Coordinates.coordinateDistance(getCoordinates(), movingentity.getCoordinates(),2)) {
            getGivenDirection(garden, getDirection(), movingentity);

            System.out.println("KITÉR_SRC");
            System.out.println("getDirection() kitérés után_SRC: " + getDirection());// új

        } else {// új, kerüli a macskát
            movingentity.makeMove(garden);
            Coordinates c2 = new Coordinates();
            //c2 = fewestSteps(room); //nem jo
            //System.out.println("c2 in searchingNextLawn: "+ c2);

            //Coordinates c2 = new Coordinates();

            outer:
            for (int k = 1; k < garden.getHeight(); k++) {//height - 1
                for (int m = 1; m < garden.getWidth(); m++) {//width - 1
                    Coordinates newCoordinates = new Coordinates(k, m);
                    //c2 = new Coordinates();

                    if (garden.isMark(newCoordinates, ".") && !garden.isMark(newCoordinates, "C")) {//új

                        Thread.sleep(30);

                        c2.setRow(k);
                        c2.setColumn(m);

                        while (!getCoordinates().isSame(c2)) {//c2 a jó

                            setDirection(garden.getShortestPath(getDirection(), getCoordinates(), c2)); //////////// SMART MOVEMENT///////////
                            moveToTheNextLawnTwoObjects(garden, "M", movingentity);

                            garden.drawTwoObjects();//ha nem rajzolunk, olyan mintha ugrana, akár 10 lépést is!

                            break outer;//**found the following "."

                        }

                    }


                }

            }


            mowingTheLawnTwoObjects(garden, movingentity);//**takarít, eredeti, működik
        }
    }

        public void moveToTheNextLawnTwoObjects(Garden garden, String mark, MovingEntity movingentity) throws InterruptedException {
            movingentity.makeMove(garden);
            //movingentity.makeMove(garden);//új
            //mark = "M";
            setMark(mark);
            //System.out.println("moving to the next dirt");//kiírja, akkor is, ha nincs dirt, mert ez egy mozgató method, és ez a cleanerGoHome() is!
            Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object
            if(getCoordinates().equals(movingentity.getCoordinates())) {
                System.out.println("ERROR");
                System.exit(0);
            }
            // új, kerüli a macskát

            //figyelni kell az egymáshoz viszonyított helyzetüket is (c<c, rowLM > rowMe, stb)
            System.out.println("getDirection() kitérés előtt: " + getDirection());// új
            //System.out.println("movingentity.getDirection(): " + movingentity.getDirection());// új
            if (Coordinates.coordinateDistance(getCoordinates(), movingentity.getCoordinates(), 2)) {
                getGivenDirection(garden, getDirection(), movingentity);

                System.out.println("KITÉR");
                System.out.println("getDirection() kitérés után: " + getDirection());// új

            } else {

                switch (getDirection()) {
                    case UP:
                        if (garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),".")
                                || garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn())," ")){
                            newCoordinates.setRow(getCoordinates().getRow() - 1); //set newCoordinates

                        }
                        break;
                    case DOWN:
                        if (garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()),".")
                                ||garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn())," ")){
                            newCoordinates.setRow(getCoordinates().getRow() + 1);

                        }
                        break;
                    case LEFT:
                        if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1),".")
                                || garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1)," ")){
                            newCoordinates.setColumn(getCoordinates().getColumn() - 1);

                        }
                        break;
                    case RIGHT:
                        if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1),".")
                                || garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1)," ")){
                            newCoordinates.setColumn(getCoordinates().getColumn() + 1);

                        }
                        break;

                }
                setCoordinates(newCoordinates);
                Thread.sleep(30);


            }
        }

    //}// új, kerüli a macskát, else vége

    public void getGivenDirection(Garden garden, Direction direction, MovingEntity movingEntity) throws InterruptedException{
        System.out.println("belép a getGivenDirection-ba");
        System.out.println();
        System.out.println("getGivenDirection: " + direction);
        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object
        switch (direction) {
            case UP:

                if ((garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),".")
                        || garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn())," "))
                        && (getCoordinates().getRow() - 1) < movingEntity.getCoordinates().getRow()){
                    newCoordinates.setRow(getCoordinates().getRow() - 1); //set newCoordinates
                    setCoordinates(newCoordinates);
                    setDirection(Direction.UP);// új
                    System.out.println("_UP_");
                } else {
                    System.out.println("_UP_else");
                    //setCoordinates(stepAwayFromTheMovinObject(garden, movingEntity));
                    if ((garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()),".")
                            || garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn())," "))
                            && (getCoordinates().getRow() + 1) > movingEntity.getCoordinates().getRow()){
                        newCoordinates.setRow(getCoordinates().getRow() + 1); //set newCoordinates
                        setCoordinates(newCoordinates);
                        setDirection(Direction.DOWN);// új
                        System.out.println("_DOWN_");
                    } else if
                    ((garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1),".")
                                    || garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1)," "))
                                    && (getCoordinates().getColumn() - 1) < movingEntity.getCoordinates().getColumn()){
                        newCoordinates.setColumn(getCoordinates().getColumn() - 1);
                        setCoordinates(newCoordinates);
                        setDirection(Direction.LEFT);// új
                        System.out.println("_LEFT_");
                    } else if
                    ((garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1),".")
                                    || garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1)," "))
                                    && (getCoordinates().getColumn() + 1) > movingEntity.getCoordinates().getColumn()){
                        newCoordinates.setColumn(getCoordinates().getColumn() + 1);
                        setCoordinates(newCoordinates);
                        setDirection(Direction.RIGHT);// új
                        System.out.println("_RIGHT_");
                    } else {
                        mowing2(garden, movingEntity); //mower stop
                    }
                }
                break;

            case DOWN:
                if ((garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()),".")
                        || garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn())," "))
                        && (getCoordinates().getRow() + 1) > movingEntity.getCoordinates().getRow()){
                    newCoordinates.setRow(getCoordinates().getRow() + 1); //set newCoordinates
                    setCoordinates(newCoordinates);
                    setDirection(Direction.DOWN);// új
                    System.out.println("_DOWN_");
                } else {
                    System.out.println("_DOWN_else");
                    //setCoordinates(stepAwayFromTheMovinObject(garden, movingEntity));
                    if ((garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),".")
                            || garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn())," "))
                            && (getCoordinates().getRow() - 1) < movingEntity.getCoordinates().getRow()){
                        newCoordinates.setRow(getCoordinates().getRow() - 1); //set newCoordinates
                        setCoordinates(newCoordinates);
                        setDirection(Direction.UP);// új
                        System.out.println("_UP_");
                    } else if
                    ((garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1),".")
                                    || garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1)," "))
                                    && (getCoordinates().getColumn() - 1) < movingEntity.getCoordinates().getColumn()){
                        newCoordinates.setColumn(getCoordinates().getColumn() - 1);
                        setCoordinates(newCoordinates);
                        setDirection(Direction.LEFT);// új
                        System.out.println("_LEFT_");
                    } else if
                    ((garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1),".")
                                    || garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1)," "))
                                    && (getCoordinates().getColumn() + 1) > movingEntity.getCoordinates().getColumn()){
                        newCoordinates.setColumn(getCoordinates().getColumn() + 1);
                        setCoordinates(newCoordinates);
                        setDirection(Direction.RIGHT);// új
                        System.out.println("_RIGHT_");
                    } else {
                        mowing2(garden, movingEntity); //mower stop
                    }
                }
                break;
            case LEFT:
                if ((garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1),".")
                        || garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1)," "))
                        && (getCoordinates().getColumn() - 1) < movingEntity.getCoordinates().getColumn()){
                    newCoordinates.setColumn(getCoordinates().getColumn() - 1);
                    setCoordinates(newCoordinates);
                    setDirection(Direction.LEFT);// új
                    System.out.println("_LEFT_");
                } else {
                    System.out.println("_LEFT_else");
                    //setCoordinates(stepAwayFromTheMovinObject(garden, movingEntity));
                    if ((garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),".")
                            || garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn())," "))
                            && (getCoordinates().getRow() - 1) > movingEntity.getCoordinates().getRow()){
                        newCoordinates.setRow(getCoordinates().getRow() - 1); //set newCoordinates
                        setCoordinates(newCoordinates);
                        setDirection(Direction.UP);// új
                        System.out.println("_UP_");
                    } else if
                    ((garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()),".")
                                    || garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn())," "))
                                    && (getCoordinates().getRow() + 1) < movingEntity.getCoordinates().getRow()){
                        newCoordinates.setRow(getCoordinates().getRow() + 1); //set newCoordinates
                        setCoordinates(newCoordinates);
                        setDirection(Direction.DOWN);// új
                        System.out.println("_DOWN_");
                    } else if
                    ((garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1),".")
                                    || garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1)," "))
                                    && (getCoordinates().getColumn() + 1) > movingEntity.getCoordinates().getColumn()){
                        newCoordinates.setColumn(getCoordinates().getColumn() + 1);
                        setCoordinates(newCoordinates);
                        setDirection(Direction.RIGHT);// új
                        System.out.println("_RIGHT_");
                    } else {
                        mowing2(garden, movingEntity); //mower stop
                    }
                }
                break;
            case RIGHT:
                if ((garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1),".")
                        || garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1)," "))
                        && (getCoordinates().getColumn() + 1) > movingEntity.getCoordinates().getColumn()){
                    newCoordinates.setColumn(getCoordinates().getColumn() + 1);
                    setCoordinates(newCoordinates);
                    setDirection(Direction.RIGHT);// új
                    System.out.println("_RIGHT_");
                } else {
                    System.out.println("_RIGHT_else");
                    //setCoordinates(stepAwayFromTheMovinObject(garden, movingEntity));
                    if ((garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),".")
                            || garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn())," "))
                            && (getCoordinates().getRow() - 1) < movingEntity.getCoordinates().getRow()){
                        newCoordinates.setRow(getCoordinates().getRow() - 1); //set newCoordinates
                        setCoordinates(newCoordinates);
                        setDirection(Direction.UP);// új
                        System.out.println("_UP_");
                    } else if
                    ((garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()),".")
                                    || garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn())," "))
                                    && (getCoordinates().getRow() + 1) > movingEntity.getCoordinates().getRow()){
                        newCoordinates.setRow(getCoordinates().getRow() + 1); //set newCoordinates
                        setCoordinates(newCoordinates);
                        setDirection(Direction.DOWN);// új
                        System.out.println("_DOWN_");
                    } else if
                    ((garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1),".")
                                    || garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1)," "))
                                    && (getCoordinates().getColumn() - 1) < movingEntity.getCoordinates().getColumn()){
                        newCoordinates.setColumn(getCoordinates().getColumn() - 1);
                        setCoordinates(newCoordinates);
                        setDirection(Direction.LEFT);// új
                        System.out.println("_LEFT_");
                    } else {
                        mowing2(garden, movingEntity); //mower stop
                    }
                }
                break;

        }
        System.out.println("egyik sem fut le_getGivenDirection");
        //setDirection(getDirection());// új
        //setCoordinates(stepAwayFromTheMovinObject(garden, movingEntity));
        System.out.println("row_utan_getGivenDirection: "+newCoordinates.getRow());
        System.out.println("column_utan_getGivenDirection: "+newCoordinates.getColumn());


    }

    public void stepAwayFromTheMovinObject(Garden garden) throws InterruptedException {//Deprecated
        Coordinates firstCoordinates = new Coordinates(1,1);
        setMark("E");
        garden.setCleaned(firstCoordinates);
        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object

        //if(getCoordinates().equals(movingentity.getCoordinates())) {
        //    System.out.println("ERROR! Please call for help! A life is in danger!");
        //    System.exit(0);
        //}

        if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1), ".")
                || garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1), " ")) {
            newCoordinates.setColumn(getCoordinates().getColumn() + 1);
            setCoordinates(newCoordinates);// setCoordinates(newCoordinates);
            garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
            setDirection(Direction.RIGHT);// új
        } else

        if (garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()), ".")
                || garden.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()), " ")) {
            newCoordinates.setRow(getCoordinates().getRow() - 1);
            setCoordinates(newCoordinates);
            garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
            setDirection(Direction.UP);// új
        } else

        if (garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()), ".")
                || garden.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()), " ")) {
            newCoordinates.setRow(getCoordinates().getRow() + 1);
            setCoordinates(newCoordinates);
            garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
            setDirection(Direction.DOWN);// új
        } else

        if (garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1), ".")
                || garden.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1), " ")) {
            newCoordinates.setColumn(getCoordinates().getColumn() - 1);
            setCoordinates(newCoordinates);
            garden.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
            setDirection(Direction.LEFT);// új
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

    //itt csak a cica mozog, a mower leááltt, mert túl közelmet a cicához
    public void mowing2(Garden garden, MovingEntity movingentity) throws InterruptedException {
        System.out.println("kezdeti string_me: "+garden.getCell(movingentity.getCoordinates()));
        System.out.println("kezdeti string_lm: "+garden.getCell(getCoordinates()));
        //movingentity.meMoving(garden);//új
        //Coordinates meCoordinates = new Coordinates(5, 5);
        //MovingEntity movingentity = new MovingEntity("C", meCoordinates, Direction.UP); //új
        while (garden.checkRoom(".")) {
            //mowingTheLawn(garden, movingentity);
            movingentity.setCoordinates(movingentity.getCoordinates());
            movingentity.makeMove(garden);//új
            Thread.sleep(50);
            garden.draw();
            //garden.draw2();//új
        }
        //mowerGoHome(garden, movingentity);
    }

    public void mowingTwoObjects(Garden garden, MovingEntity movingentity) throws InterruptedException {
        System.out.println("kezdeti string_me: "+garden.getCell(movingentity.getCoordinates()));
        System.out.println("kezdeti string_lm: "+garden.getCell(getCoordinates()));
        //movingentity.meMoving(garden);//új
        //Coordinates meCoordinates = new Coordinates(5, 5);
        //MovingEntity movingentity = new MovingEntity("C", meCoordinates, Direction.UP); //új
        while (garden.checkRoom(".")) {
            mowingTheLawnTwoObjects(garden, movingentity);
            movingentity.makeMove(garden);//új
            Thread.sleep(50);
            //garden.draw();
            garden.drawTwoObjects();//új
        }
        mowerGoHomeTwoObjects(garden, movingentity);
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

    public void mowerGoHomeTwoObjects(Garden garden, MovingEntity movingentity) throws InterruptedException {
        Coordinates c2 = new Coordinates();
        int ctr = 0;
        c2.setRow(1);
        c2.setColumn(1);

        while (!getCoordinates().isSame(c2)) {

            setDirection(garden.getShortestPath(getDirection(),getCoordinates(), c2));
            moveToTheNextLawnTwoObjects(garden, "H", movingentity);
            garden.draw();
            System.out.println("----------" + ctr + "---------");
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
