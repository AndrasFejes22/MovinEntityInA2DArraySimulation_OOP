package cleaner;

import java.util.Random;

public class VacuumCleaner {
	
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
    public VacuumCleaner(String mark, Coordinates coordinates, Direction direction) {
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
        //ellenõrzés
        this.mark = mark;
    }


    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void cleaningARoom(Room room) throws InterruptedException {
    	System.out.println("moving to the next dirt");//kiírja, akkor is, ha nincs dirt, mert ez egy mozgató method, és ez a cleanerGoHome() is!
        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object
        
                switch (getDirection()) {
                    case UP:
                        if (room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),".")
                        || room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn())," ")){
                            newCoordinates.setRow(getCoordinates().getRow() - 1); //set newCoordinates
                            //room.setCleaned2(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()));//már nem kell
                        }
                        break;
                    case DOWN:
                        if (room.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()),".")
                        ||room.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn())," ")){
                            newCoordinates.setRow(getCoordinates().getRow() + 1); //set newCoordinates
                            //room.setCleaned2(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()));
                        }
                        break;
                    case LEFT:
                        if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1),".")
                        || room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1)," ")){
                            newCoordinates.setColumn(getCoordinates().getColumn() - 1);
                            //room.setCleaned2(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
                        }
                        break;
                    case RIGHT:
                        if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1),".")
                        || room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1)," ")){
                            newCoordinates.setColumn(getCoordinates().getColumn() + 1);
                            //room.setCleaned2(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
                        }
                        break;
            }
            setCoordinates(newCoordinates);
            Thread.sleep(250);
      

    }
    
    public void cleaningARoom1(Room room) throws InterruptedException {
        Coordinates newCoordinates = new Coordinates(getCoordinates());// new Coordinate object
        /*
        switch (getDirection()) {
        		case UP:
               
                        if (room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),".")){
                        //|| room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn())," ")){
                            newCoordinates.setRow(getCoordinates().getRow() - 1); //set newCoordinates
                            setCoordinates(newCoordinates);
                            room.setCleaned(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()));
                        }else
                   
                        if (room.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()),".")){
                        //||room.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn())," ")){
                            newCoordinates.setRow(getCoordinates().getRow() + 1);
                            setCoordinates(newCoordinates);
                            room.setCleaned(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()));
                        }else
                      
                 
                        if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1),".")){
                        //|| room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1)," ")){
                            newCoordinates.setColumn(getCoordinates().getColumn() - 1);
                            setCoordinates(newCoordinates);
                            room.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
                        }else
                 
                   
                        if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1),".")){
                        //|| room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1)," ")){
                            newCoordinates.setColumn(getCoordinates().getColumn() + 1);
                            setCoordinates(newCoordinates);
                            room.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
                        }else {
                        	setDirection(direction.RIGHT);
                        	 //goToTheNextDirt(room);
                        }
                        break;
        		
        		 case DOWN:      
        			 if (room.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn()),".")){
                             //|| room.isMark(new Coordinates(getCoordinates().getRow() + 1, getCoordinates().getColumn())," ")){
                                 newCoordinates.setRow(getCoordinates().getRow() + 1); //set newCoordinates
                                 setCoordinates(newCoordinates);
                                 room.setCleaned(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()));
                             }else
                        
                             if (room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),".")){
                             //||room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn())," ")){
                                 newCoordinates.setRow(getCoordinates().getRow() - 1);
                                 setCoordinates(newCoordinates);
                                 room.setCleaned(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()));
                             }else
                           
                      
                             if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1),".")){
                             //|| room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() - 1)," ")){
                                 newCoordinates.setColumn(getCoordinates().getColumn() - 1);
                                 setCoordinates(newCoordinates);
                                 room.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
                             }else
                      
                        
                             if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1),".")){
                             //|| room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1)," ")){
                                 newCoordinates.setColumn(getCoordinates().getColumn() + 1);
                                 setCoordinates(newCoordinates);
                                 room.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
                             }else {
                             	setDirection(direction.LEFT);
                             	 //goToTheNextDirt(room);
                             }
                             break;
        		 
        		 case LEFT:
        			 if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()-1),".")){
                             //|| room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()-1)," ")){
                                 newCoordinates.setColumn(getCoordinates().getColumn()-1); //set newCoordinates
                                 setCoordinates(newCoordinates);
                                 room.setCleaned(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()));
                             }else
                        
                             if (room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),".")){
                             //||room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn())," ")){
                                 newCoordinates.setRow(getCoordinates().getRow() - 1);
                                 setCoordinates(newCoordinates);
                                 room.setCleaned(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()));
                             }else
                           
                      
                             if (room.isMark(new Coordinates(getCoordinates().getRow()+1, getCoordinates().getColumn()),".")){
                            // || room.isMark(new Coordinates(getCoordinates().getRow()+1, getCoordinates().getColumn())," ")){
                                 newCoordinates.setRow(getCoordinates().getRow()+1);
                                 setCoordinates(newCoordinates);
                                 room.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
                             }else
                      
                        
                             if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1),".")){
                             //|| room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn() + 1)," ")){
                                 newCoordinates.setColumn(getCoordinates().getColumn() + 1);
                                 setCoordinates(newCoordinates);
                                 room.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
                             }else {
                             	setDirection(direction.UP);
                             	 //goToTheNextDirt(room);
                             }
                             break;
        		 				
        		 case RIGHT:
        			 //System.out.println("RIGHT");
        			 */
        			 if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()+1),".")){
                             //|| room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()+1)," ")){
        				 			newCoordinates.setColumn(getCoordinates().getColumn() + 1);  
        				 			setCoordinates(newCoordinates);//setCoordinates(newCoordinates);
                                 room.setCleaned(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()));
                             }else
                        
                             if (room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn()),".")){
                            	 //System.out.println("RIGHT2"); 
                             //||room.isMark(new Coordinates(getCoordinates().getRow() - 1, getCoordinates().getColumn())," ")){
                                 newCoordinates.setRow(getCoordinates().getRow() - 1);
                                 setCoordinates(newCoordinates);
                                 room.setCleaned(new Coordinates(getCoordinates().getRow() , getCoordinates().getColumn()));
                             }else
                           
                      
                             if (room.isMark(new Coordinates(getCoordinates().getRow()+1, getCoordinates().getColumn()),".")){
                            	 //System.out.println("RIGHT3");
                             //|| room.isMark(new Coordinates(getCoordinates().getRow()+1, getCoordinates().getColumn())," ")){
                                 newCoordinates.setRow(getCoordinates().getRow()+1);
                                 setCoordinates(newCoordinates);
                                 room.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
                             }else
                      
                        
                             if (room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()-1),".")){
                            	 //System.out.println("RIGHT4");
                             //|| room.isMark(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()-1)," ")){
                                 newCoordinates.setColumn(getCoordinates().getColumn()-1);
                                 setCoordinates(newCoordinates);
                                 room.setCleaned(new Coordinates(getCoordinates().getRow(), getCoordinates().getColumn()));
                             }else {
                            	 System.out.println("goToTheNextDirt");
                            	 //setDirection(direction.DOWN);
                            	 goToTheNextDirt(room);
                             }
                             //break;
                             //default:
                            	 //goToTheNextDirt(room);
        			//}//switch    
        			//goToTheNextDirt(room);
        		 
            }
            
      

    

    public void goToTheNextDirt(Room room) throws InterruptedException {
    	//System.out.println("goToTheNextDirt");
    	
		Coordinates c2 = new Coordinates();
		int ctr = 0;
		//int ctr2 = 2;

		outer : for (int k = 1; k <15 ; k++) {//height - 1
			for (int m = 1; m <15 ; m++) {//width - 1
				Coordinates newCoordinates = new Coordinates(k, m);
				//c2 = new Coordinates();

				if (room.isMark(newCoordinates, ".")) {
					
					Thread.sleep(100);
					//c2 = new Coordinates();
					c2.setRow(k);
					c2.setColumn(m);
					while (!getCoordinates().isSame(c2)) {
						
						setDirection(room.getShortestPath(getDirection(), getCoordinates(), c2));
						cleaningARoom(room);
						//cleaningARoom1(room);
						room.draw();//ha nem rajzolunk, olyan mintha ograna, akár 10 lépést is!
						System.out.println("-------" + ctr + "dirt-----");
						break outer;//KELL
						//
						
						
						//ctr++;
						//ctr = 0;
					}
			
				}//if
				
				 
			} // for

		} // for
		cleaningARoom1(room);
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
