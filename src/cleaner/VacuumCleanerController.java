package cleaner;

public class VacuumCleanerController {

	static Coordinates c = new Coordinates();

	static int loopCounter = 0;
	static int mainLoopCounter = 0;

	static int height = 15;
	static int width = 15;
	private static Room room;
	private static VacuumCleaner vc;

	public static void cleaning(Room room, VacuumCleaner vc) throws InterruptedException {
		
		while (room.checkRoom(".")) {
			
				vc.cleaningARoom1(room);
				Thread.sleep(100);
				room.draw();
			//}
		}
		/*
		Coordinates c2 = new Coordinates();
		outer: for (int row = 1; row < height; row++) {

			inner: for (int column = 1; column < width; column++) {
				Coordinates newCoordinates = new Coordinates(row, column);

				if (room.isMark(newCoordinates, ".")) {

					c.setRow(row);
					c.setColumn(column);

					while (!vc.getCoordinates().isSame(c)) {// && room.checkRoom(".")) {

						loopCounter++;

						vc.setDirection(room.getShortestPath(vc.getDirection(), vc.getCoordinates(), c));

						vc.cleaningARoom(room);

						room.draw();
						System.out.println("-------" + loopCounter + "b-----");

					}

				}

			}

		}
		*/
	//}//while outer
		
		cleanerGoHome(room, vc);
		//stepCounter2(room, vc);
	}


	/*
	private static Coordinates closestCoordinate(Room room, VacuumCleaner vc)throws InterruptedException {
			
		int ctr = 0;
		HashMap<Coordinates, Integer> sm = new HashMap<Coordinates, Integer>();
		Coordinates target = new Coordinates();
		for (int k = 1; k < height - 1; k++) {
			for (int m = 1; m < width - 1; m++) {
				Coordinates newCoordinates = new Coordinates(k, m);

				if (room.isMark(newCoordinates, ".")) {
					target = new Coordinates();
					target.setRow(k);
					target.setColumn(m);

					while (!vc.getCoordinates().isSame(target)) {
						

						vc.setDirection(room.getShortestPath(vc.getDirection(), vc.getCoordinates(), target));
						//vc.scanner(room);
						//room.draw();
						//System.out.println("-------" + ctr + "c2-----");
						sm.put(target, ctr);
						ctr++;
					}
				}

			}
			// break;
		}
		
		List<Map.Entry<Coordinates, Integer>> singleList = sm.entrySet().stream().sorted(Map.Entry.comparingByValue())
				
				.collect(Collectors.toList());
		System.out.println(singleList.get(0).getKey().getRow());
		System.out.println(singleList.get(0).getKey().getColumn());
		System.out.println("sm_érdekes"+sm);

		return singleList.get(0).getKey();

	}
	*/
	
	private static void cleanerGoHome(Room room, VacuumCleaner vc) throws InterruptedException {
		//HashMap<Coordinates, Integer> sm = new HashMap<Coordinates, Integer>();
		Coordinates c2 = new Coordinates();
		int ctr = 0;
		//int ctr2 = 2;

		//for (int k = 1; k <4 ; k++) {//height - 1
			//for (int m = 1; m <4 ; m++) {//width - 1
				//Coordinates newCoordinates = new Coordinates(k, m);
				//c2 = new Coordinates();
		c2.setRow(1);
		c2.setColumn(1);
		
			for (int i = 2; i < width; i++) {
				if (room.isMark(c2, "X")) {
					c2.setColumn(i);
					//break;
				}
			}
		
				//if (room.isMark(newCoordinates, " ")) {
					//Thread.sleep(1000);
		
					//c2 = new Coordinates();
					
					
					while (!vc.getCoordinates().isSame(c2)) {
						
						vc.setDirection(room.getShortestPath(vc.getDirection(), vc.getCoordinates(), c2));
						vc.cleaningARoom(room);
						room.draw();
						System.out.println("-------" + ctr + "c-----");
						// sm.put(c2, ctr);
						//System.out.println("sm_CleanerGoHome_inWhile: "+sm);
						Thread.sleep(100);
						ctr++;
						//ctr = 0;
					}
					//ctr2++;
			//Thread.sleep(100);
					//System.out.println("-------" + ctr2 + "c3-----");
				//}//if
				/*	
				Thread.sleep(1000);
				c2 = new Coordinates();
				c2.setRow(8);
				c2.setColumn(13);
				while (!vc.getCoordinates().isSame(c2)) {
					ctr++;
					vc.setDirection(room.getShortestPath(vc.getDirection(), vc.getCoordinates(), c2));
					vc.cleaningARoom(room);
					room.draw();
					System.out.println("-------" + ctr + "c-----");
					sm.put(c2, ctr);
					Thread.sleep(100);
				}
				Thread.sleep(1000);
				*/
			//}  
				 
			//} // for

		//} // for
		/*
		 * List<Map.Entry<Coordinates, Integer>> singleList =
		 * sm.entrySet().stream().sorted(Map.Entry.comparingByValue())
		 * 
		 * .collect(Collectors.toList());
		 * System.out.println("singleList_size: "+singleList.size());
		 * System.out.println("sm_CleanerGoHome: "+sm);
		 * System.out.println("singleList: "+singleList);
		 * System.out.println("singleList_get(0): "+singleList.get(0));
		 * System.out.println("cleaner_ row: "+singleList.get(0).getKey().getRow());
		 * System.out.println("cleaner_ column: "+singleList.get(0).getKey().getColumn()
		 * );
		 * 
		 * 
		 * System.out.println("ctr_cleanerGoHome: " + ctr);
		 */
	}
	
	

}
