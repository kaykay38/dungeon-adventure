public class Room 
{
    
    private int row, col, itemCount;
    private String item;
	private boolean hasHealthPOTION, hasVisionPOTION;	//Health & Vision potions
	private boolean isENTRANCE, isEXIT;	
	private boolean hasPILLAR_A, hasPILLAR_E, hasPILLAR_I, hasPILLAR_P;		//The pillars of O.O
	private boolean hasMONSTER, hasPIT;	//Utility to check if there are ANY items in a given room
    private int pillarChoice;

	public Room() {
        this.row = 0; this.col = 0; this.itemCount = 0; this.item = "E";
		this.hasHealthPOTION = false; this.hasVisionPOTION = false;	
		this.isENTRANCE = false; this.isEXIT = false;	
		this.hasPILLAR_A = false; this.hasPILLAR_I = false;	this.hasPILLAR_E = false; this.hasPILLAR_P = false;
		this.hasMONSTER = false; this.hasPIT = false; pillarChoice = 1;
	} //end of EVC
	
	public boolean equals(Object o) {
		if (o instanceof Room) {
			Room that = (Room) o;
			if (this.row == that.row && this.col == that.col) {
				return true;
			}
		}
		return false;
	}
	
/** Data field GETTERS **/
	public int getRow() { return this.row; }
	public int getCol() { return this.col; }
	public String getItem() { return this.item; }
	public boolean hasPILLAR_A() { return this.hasPILLAR_A; }
	public boolean hasPILLAR_E() { return this.hasPILLAR_E; }
	public boolean hasPILLAR_I() { return this.hasPILLAR_I; }
	public boolean hasPILLAR_P() { return this.hasPILLAR_P; }
	public boolean isENTRANCE() { return this.isENTRANCE; }
	public boolean isEXIT() { return this.isEXIT; }
	public boolean hasMONSTER() { return this.hasMONSTER; }
	public boolean hasPIT() { return this.hasPIT; }
	public boolean hasHealthPOTION() { return this.hasHealthPOTION; }
	public boolean hasVisionPOTION() { return this.hasVisionPOTION; }

/** Data field SETTERS **/
	public void setRoomCoords(int row, int col) { this.row = row; this.col = col; }
	public void setItem(String item) { this.item = item; }
	public void setHasPILLAR_A(boolean hasPILLAR_A) { this.hasPILLAR_A = hasPILLAR_A; }
	public void setHasPILLAR_E(boolean hasPILLAR_E) { this.hasPILLAR_E = hasPILLAR_E; }
	public void setHasPILLAR_I(boolean hasPILLAR_I) { this.hasPILLAR_I = hasPILLAR_I; }
	public void setHasPILLAR_P(boolean hasPILLAR_P) { this.hasPILLAR_P = hasPILLAR_P; }
	public void setIsEntrance(boolean isENTRANCE) { this.isENTRANCE = isENTRANCE; if(isENTRANCE) {this.item = "I"; this.itemCount=1;} } 
	public void setIsExit(boolean isEXIT) { this.isEXIT = isEXIT; if(isEXIT) {this.item = "O"; this.itemCount=1;} }
	public void setHasMONSTER(boolean hasMONSTER) { this.hasMONSTER = hasMONSTER; }
	public void setHasPIT(boolean hasPIT) { this.hasPIT = hasPIT; }
	public void setHasHealthPOTION(boolean hasHealthPOTION) { this.hasHealthPOTION = hasHealthPOTION; }
	public void setHasVisionPOTION(boolean hasVisionPOTION) { this.hasVisionPOTION = hasVisionPOTION; }
	
	public void setRoomItem(String item) {
		setItem(item.toUpperCase());
		switch(item) {
			case "v":
				this.hasVisionPOTION =true;
				this.itemCount=1;
				break;
			case "x":
				this.hasMONSTER =true;
				this.itemCount=1;
				break;
			case "p":
				this.hasPIT =true;
				this.itemCount=1;
				break;
			case "h":
			this.hasHealthPOTION =true;
			this.itemCount=1;
			break;
		}
	}
	
	/** Displays the contents of the room **/
	public String displayStat() 
	{
		return 
			  "\n-------------------------------------------------------\n"
			+ this.toString()
			+ "\n-------------------------------------------------------"
			+ "\nRoom: (" + this.row + "," + this.col + ")" + "          | Item Count: " + this.itemCount + "      | Item Symbol: " + this.item
			+ "\nMonster: " + this.hasMONSTER + "       | Pit: " + this.hasPIT 
			+ "\nEntrance: " + this.isENTRANCE + "      | Exit: " + this.isEXIT 
			+ "\nHealth Potion: " + this.hasHealthPOTION + " | Vision Potion: " + this.hasVisionPOTION
			+ "\nPillar A: " + this.hasPILLAR_A + "      | Pillar E: " + this.hasPILLAR_E
			+ "\nPillar I: " + this.hasPILLAR_I + "      | Pillar P: " + this.hasPILLAR_P 
			+ "\n-------------------------------------------------------";
	}
	
	/** Generates items to be placed in valid rooms **/
	public void generateRoomItems() {
		if(!this.isENTRANCE && !this.isEXIT) {
			double randTemp = (Math.random());
			if(randTemp <= 0.15) {
				this.hasHealthPOTION = true;
				this.itemCount++;
			}
			randTemp = (Math.random());
			if(randTemp <= 0.15) {
				this.hasVisionPOTION = true;
				this.itemCount++;
			}
			randTemp = (Math.random());
			if(randTemp <= 0.2) {
				this.hasPIT = true;
				this.itemCount++;
			}
			randTemp = (Math.random());
			if(randTemp <= 0.35) {
				this.hasMONSTER = true;
				this.itemCount++;
			}
		}
		setItem();
	}

	/** Places a predetermined pillar in a room WITHOUT any other pillar(s) **/
	public void placeRandomPillar() {
		//This should select a random pillar from list of Pillars available and denote that
		//it has been used already...
		
		switch(pillarChoice) 
		{
		case 1:
			this.hasPILLAR_A = true;
			this.itemCount++;
			pillarChoice++;
			break;
		case 2:
			this.hasPILLAR_E = true;
			this.itemCount++;
			pillarChoice++;
			break;
		case 3:
			this.hasPILLAR_I = true;
			this.itemCount++;
			pillarChoice++;
			break;
		case 4:
			this.hasPILLAR_P = true;
			this.itemCount++;
			break;
		}
	}

	/** Sets appropriate item symbol for the current room **/
	public void setItem() 
	{
		if(this.itemCount == 1)
		{
			//Any room that isn't empty.
			if(hasPILLAR_A)
			{
				this.item = "Y";
			}
			if(hasPILLAR_E)
			{
				this.item = "Y";
			}
			if(hasPILLAR_I)
			{
				this.item = "Y";
			}
			if(hasPILLAR_P)
			{
				this.item = "Y";
			}
			if(hasHealthPOTION)
			{
				this.item = "H";
			}
			if(hasVisionPOTION)
			{
				this.item = "V";
			}
			if(hasMONSTER)
			{
				this.item = "X";
			}
			if(hasPIT)
			{
				this.item = "P";
			}
		}	
		if(this.itemCount>1) 
		{
			this.item = "M";
		}
		else if(this.itemCount == 0) 
		{
			this.item = "E";
		}
	}

	/** Decrement item method for easy decrementation in external classes*/
	public void minusItem() {
		this.itemCount--;
	}

	/** Displays graphic of a room **/
	@Override
	public String toString() {
        String str = "";
        //Interior room, not on outer edge of Dungeon
		if(row > 0 && row < 4 && col > 0 && col < 4) 
		{
			str = "\t\t\t* - *\n\t\t\t| " + item + " |\n\t\t\t* - *";
        }
        //Bottom wall of the Dungeon
        if(col > 0 && col < 4 && row == 4)
        {
            str = "\t\t\t* - *\n\t\t\t| " + item + " |\n\t\t\t* * *";
        }
        //Top wall of the Dungeon
        if(col > 0 && col < 4 && row == 0)
        {
            str = "\t\t\t* * *\n\t\t\t| " + item + " |\n\t\t\t* - *";
        }
        //Left wall of the Dungeon
        if(row > 0 && row < 4 && col == 0)
        {
            str = "\t\t\t* - *\n\t\t\t* " + item + " |\n\t\t\t* - *";
        }
        //Right wall of the Dungeon
        if(row > 0 && row < 4 && col == 4)
        {
            str = "\t\t\t* - *\n\t\t\t| " + item + " *\n\t\t\t* - *";
        }
        //Top left room of the Dungeon
        if(row == 0 && col == 0)
        {
            str = "\t\t\t* * *\n\t\t\t* " + item + " |\n\t\t\t* - *";
        }
        //Top right room of the Dungeon
        if(row == 0 && col == 4)
        {
            str = "\t\t\t* * *\n\t\t\t| " + item + " *\n\t\t\t* - *";
        }
         //Bottom left room of the Dungeon
        if(row == 4 && col == 0)
        {
           str = "\t\t\t* - *\n\t\t\t* " + item + " |\n\t\t\t* * *";
        }
        //Bottom right room of the Dungeon
        if(row == 4 && col == 4)
        { 
            str = "\t\t\t* - *\n\t\t\t| " + item + " *\n\t\t\t* * *";
        }
        return str;
	}
	
}	//end of CLASS