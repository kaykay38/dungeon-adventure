public class Room 
{
	private boolean hasNORTH, hasEAST, hasSOUTH, hasWEST;
	private boolean hasHealthPOTION, hasVisionPOTION;	//Health & Vision potions
	private boolean hasENTRANCE, hasEXIT;	
	private boolean hasPILLAR_A, hasPILLAR_E, hasPILLAR_I, hasPILLAR_P;		//The pillars of O.O
	private boolean hasMONSTER, hasPIT;
	private boolean hasANY_ITEM;	//Utility to check if there are ANY items in a given room
	private int x, y;
	private static int pillarChoice = 1;


	public Room()
	{
		this.hasHealthPOTION = false; this.hasVisionPOTION = false;	
		this.hasENTRANCE = false; this.hasEXIT = false;	
		this.hasPILLAR_A = false; this.hasPILLAR_I = false;	this.hasPILLAR_E = false; this.hasPILLAR_P = false;
		this.hasMONSTER = false; this.hasPIT = false;
		this.hasNORTH = false; this.hasEAST = false; this.hasSOUTH = false; this.hasWEST = false;
		this.x = 0; this.y = 0;

	} //end of EVC
	
	
/** Data field GETTERS **/
	public boolean roomHasPILLAR_A(boolean hasPILLAR_A) { return this.hasPILLAR_A; }
	public boolean roomHasPILLAR_E(boolean hasPILLAR_E) { return this.hasPILLAR_E; }
	public boolean roomHasPILLAR_I(boolean hasPILLAR_I) { return this.hasPILLAR_I; }
	public boolean roomHasPILLAR_P(boolean hasPILLAR_P) { return this.hasPILLAR_P; }
	public boolean roomHasENTRANCE(boolean hasENTRANCE) { return this.hasENTRANCE; }
	public boolean roomHasEXIT(boolean hasEXIT) { return this.hasEXIT; }
	public boolean roomHasMONSTER(boolean hasMONSTER) { return this.hasMONSTER; }
	public boolean roomHasPIT(boolean hasPIT) { return this.hasPIT; }
	public boolean roomHasHealthPOTION(boolean hasHealthPOTION) { return this.hasHealthPOTION; }
	public boolean roomHasVisionPOTION(boolean hasVisionPOTION) { return this.hasVisionPOTION; }
	public boolean roomHasAny_ITEM(boolean hasANY_ITEM) { return this.hasANY_ITEM; }

/** Data field SETTERS **/	
	public void setRoomHasPILLAR_A(boolean hasPILLAR_A) { this.hasPILLAR_A = hasPILLAR_A; }
	public void setRoomHasPILLAR_E(boolean hasPILLAR_E) { this.hasPILLAR_E = hasPILLAR_E; }
	public void setRoomHasPILLAR_I(boolean hasPILLAR_I) { this.hasPILLAR_I = hasPILLAR_I; }
	public void setRoomHasPILLAR_P(boolean hasPILLAR_P) { this.hasPILLAR_P = hasPILLAR_P; }
	public void setRoomHasEntrance(boolean hasENTRANCE) { this.hasENTRANCE = hasENTRANCE; }
	public void setRoomHasExit(boolean hasEXIT) { this.hasEXIT = hasEXIT; }
	public void setRoomHasMonster(boolean hasMONSTER) { this.hasMONSTER = hasMONSTER; }
	public void setRoomHasPit(boolean hasPIT) { this.hasPIT = hasPIT; }
	public void setRoomHasHealthPOTION(boolean hasHealthPOTION) { this.hasHealthPOTION = hasHealthPOTION; }
	public void setRoomHasVisionPOTION(boolean hasVisionPOTION) { this.hasVisionPOTION = hasVisionPOTION; }
	public void setRoomHasANY_ITEM(boolean hasANY_ITEM) { this.hasANY_ITEM = hasANY_ITEM; }
	public void setRoomCoords(int x, int y) { this.x = x; this.y = y; }
	public void setPillarChoice(int pillarChoice) { Room.pillarChoice = pillarChoice; }
	
	
	/** Generates a list of items within the hero's room **/
	public String toString() 
	{
		String str = "\nMonster: " + this.hasMONSTER + "       | Pit: " + this.hasPIT 
			   	+ "\nEntrance: " + this.hasENTRANCE + "      | Exit: " + this.hasEXIT 
			+ "\nHealth Potion: " + this.hasHealthPOTION + " | Vision Potion: " + this.hasVisionPOTION
				+ "\nPillar A: " + this.hasPILLAR_A + "      | Pillar E: " + this.hasPILLAR_E
				+ "\nPillar I: " + this.hasPILLAR_I + "      | Pillar P: " + this.hasPILLAR_P;

		
		return str;
	}
	
	/** Generates items to be placed in valid rooms **/
	public void generateRoomContents() {
		
		if(!this.hasENTRANCE && !this.hasEXIT) {
			int randTemp = (int)(Math.random() * 10);
			if(randTemp == 0) {
				this.hasHealthPOTION = true;
				this.hasANY_ITEM = true;
			}
			randTemp = (int)(Math.random() * 20);
			if(randTemp == 0) {
				this.hasVisionPOTION = true;
				this.hasANY_ITEM = true;
			}
			randTemp = (int)(Math.random() * 10);
			if(randTemp == 0) {
				this.hasPIT = true;
				this.hasANY_ITEM = true;
			}
			randTemp = (int)(Math.random() * 10);
			if(randTemp == 0) {
				this.hasMONSTER = true;
				this.hasANY_ITEM = true;
			}
		}
	}

	/** Places a predetermined pillar in a room WITHOUT any other pillar(s) **/
	public void placeRandomPillar() {
		//This should select a random pillar from list of Pillars available and denote that
		//it has been used already...
		
		switch(pillarChoice) 
		{
		case 1:
			this.hasPILLAR_A = true;
			this.hasANY_ITEM = true;
			pillarChoice++;
			break;
		case 2:
			this.hasPILLAR_E = true;
			this.hasANY_ITEM = true;
			pillarChoice++;
			break;
		case 3:
			this.hasPILLAR_I = true;
			this.hasANY_ITEM = true;
			pillarChoice++;
			break;
		case 4:
			this.hasPILLAR_P = true;
			this.hasANY_ITEM = true;
			pillarChoice = 1;
			break;
		}
	}

	/** Compiles information about current room to display **/
	public static void printRoom(Room heroRoom)
	{
		
		if(heroRoom.x > 0 && heroRoom.x < 4 && heroRoom.y > 0 && heroRoom.y < 4) 
		{
			//This is an interior room, not on outer edge of Dungeon
			String RoomSymbol = calculateSymbol(heroRoom);
			String RoomLocation = "interior";
			generateRoomGraphic(RoomSymbol, RoomLocation);
		}
			if(heroRoom.y > 0 && heroRoom.y < 4 && heroRoom.x == 4)
			{
				//Bottom wall of the Dungeon
				String RoomSymbol = calculateSymbol(heroRoom);
				String RoomLocation = "bottom";
				generateRoomGraphic(RoomSymbol, RoomLocation);

			}
			if(heroRoom.y > 0 && heroRoom.y < 4 && heroRoom.x == 0)
			{
				//Top wall of the Dungeon
				String RoomSymbol = calculateSymbol(heroRoom);
				String RoomLocation = "top";
				generateRoomGraphic(RoomSymbol, RoomLocation);

			}
			if(heroRoom.x > 0 && heroRoom.x < 4 && heroRoom.y == 0)
			{
				//Left wall of the Dungeon
				String RoomSymbol = calculateSymbol(heroRoom);
				String RoomLocation = "left";
				generateRoomGraphic(RoomSymbol, RoomLocation);

			}
			if(heroRoom.x > 0 && heroRoom.x < 4 && heroRoom.y == 4)
			{
				//Right wall of the Dungeon
				String RoomSymbol = calculateSymbol(heroRoom);
				String RoomLocation = "right";
				generateRoomGraphic(RoomSymbol, RoomLocation);

			}
			if(heroRoom.x == 0 && heroRoom.y == 0)
			{
				//Top left room of the Dungeon
				String RoomSymbol = calculateSymbol(heroRoom);
				String RoomLocation = "topleft";
				generateRoomGraphic(RoomSymbol, RoomLocation);

			}
			if(heroRoom.x == 0 && heroRoom.y == 4)
			{
				//Top right room of the Dungeon
				String RoomSymbol = calculateSymbol(heroRoom);
				String RoomLocation = "topright";
				generateRoomGraphic(RoomSymbol, RoomLocation);

			}
			if(heroRoom.x == 4 && heroRoom.y == 0)
			{
				//Bottom left room of the Dungeon
				String RoomSymbol = calculateSymbol(heroRoom);
				String RoomLocation = "bottomleft";
				generateRoomGraphic(RoomSymbol, RoomLocation);

			}
			if(heroRoom.x == 4 && heroRoom.y == 4)
			{
				//Bottom right room of the Dungeon
				String RoomSymbol = calculateSymbol(heroRoom);
				String RoomLocation = "bottomright";
				generateRoomGraphic(RoomSymbol, RoomLocation);

			}
			
			/** DEBUG: Prints out text info until 2D graphics configured **/
//			System.out.println("____________________________________________________________\n" 
//							 + "|||||||||||||||||||||Current Room|||||||||||||||||||||||||||\n" + heroRoom.toString() 
//						   + "\n____________________________________________________________");
	}

	/** Generates the 2D graphic representing the hero's current room **/
	private static void generateRoomGraphic(String roomSymbol, String roomLocation) 
	{
		if(roomLocation.equalsIgnoreCase("topleft"))
		{
			System.out.println("\t\t      ***\n\t\t      *" + roomSymbol + "|\n\t\t      *-*");
		}
		if(roomLocation.equalsIgnoreCase("top"))
		{
			System.out.println("\t\t      ***\n\t\t      |" + roomSymbol + "|\n\t\t      *-*");
		}
		if(roomLocation.equalsIgnoreCase("topright"))
		{
			System.out.println("\t\t      ***\n\t\t      |" + roomSymbol + "*\n\t\t      *-*");
		}
		if(roomLocation.equalsIgnoreCase("left"))
		{
			System.out.println("\t\t      *-*\n\t\t      *" + roomSymbol + "|\n\t\t      *-*");
		}
		if(roomLocation.equalsIgnoreCase("bottomleft"))
		{
			System.out.println("\t\t      *-*\n\t\t      *" + roomSymbol + "|\n\t\t      ***");
		}
		if(roomLocation.equalsIgnoreCase("bottom"))
		{
			System.out.println("\t\t      *-*\n\t\t      |" + roomSymbol + "|\n\t\t      ***");
		}
		if(roomLocation.equalsIgnoreCase("bottomright"))
		{
			System.out.println("\t\t      *-*\n\t\t      |" + roomSymbol + "*\n\t\t      ***");
		}
		if(roomLocation.equalsIgnoreCase("right"))
		{
			System.out.println("\t\t      *-*\n\t\t      |" + roomSymbol + "*\n\t\t      *-*");
		}
		if(roomLocation.equalsIgnoreCase("interior"))
		{
			System.out.println("\t\t      *-*\n\t\t      |" + roomSymbol + "|\n\t\t      *-*");

		}
		System.out.println("");
	}

	/** Determines the symbol appropriate for the hero's current room **/
	private static String calculateSymbol(Room heroRoom) 
	{
		String roomSymbol = "E";
		int itemCount = 0;
		
		if(heroRoom.hasANY_ITEM)
		{
			//Any room that isn't empty.
			if(heroRoom.hasENTRANCE)
			{
				roomSymbol = "I";
			}
			if(heroRoom.hasEXIT)
			{
				roomSymbol = "O";
			}
			if(heroRoom.hasPILLAR_A)
			{
				roomSymbol = "Y";
				itemCount++;
			}
			if(heroRoom.hasPILLAR_E)
			{
				roomSymbol = "Y";
				itemCount++;
			}
			if(heroRoom.hasPILLAR_I)
			{
				roomSymbol = "Y";
				itemCount++;
			}
			if(heroRoom.hasPILLAR_P)
			{
				roomSymbol = "Y";
				itemCount++;
			}
			
			if(heroRoom.hasHealthPOTION)
			{
				roomSymbol = "H";
				itemCount++;
			}
			if(heroRoom.hasVisionPOTION)
			{
				roomSymbol = "V";
				itemCount++;
			}
			if(heroRoom.hasMONSTER)
			{
				roomSymbol = "X";
				itemCount++;
			}
			if(heroRoom.hasPIT)
			{
				roomSymbol = "P";
				itemCount++;
			}
			if(itemCount > 1)
			{
				return "M";
			}
			else
				return roomSymbol;
		} else
			return roomSymbol;
	}
	

	
	
}	//end of CLASS