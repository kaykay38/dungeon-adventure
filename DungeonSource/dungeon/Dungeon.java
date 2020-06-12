package dungeon;

/**
 * Title: Dungeon.java
 *
 * Description: Driver file for Heroes and Monsters project
 *
 * Copyright:    Copyright (c) 2001
 * Company: Code Dogs Inc.
 * I.M. Knurdy
 *
 * History:
 *  11/4/2001: Wrote program
 *    --created DungeonCharacter class
 *    --created Hero class
 *    --created Monster class
 *    --had Hero battle Monster
 *    --fixed attack quirks (dead monster can no longer attack)
 *    --made Hero and Monster abstract
 *    --created Warrior
 *    --created Ogre
 *    --made Warrior and Ogre battle
 *    --added battleChoices to Hero
 *    --added special skill to Warrior
 *    --made Warrior and Ogre battle
 *    --created Sorceress
 *    --created Thief
 *    --created Skeleton
 *    --created Gremlin
 *    --added game play features to Dungeon.java (this file)
 *  11/27/2001: Finished documenting program
 * version 1.0
 */



/*
  This class is the driver file for the Heroes and Monsters project.  It will
  do the following:

  1.  Allow the user to choose a hero
  2.  Randomly select a monster
  3.  Allow the hero to battle the monster

  Once a battle concludes, the user has the option of repeating the above

*/
public class Dungeon
{
	Room[][] dungeonRooms;
	
	public Dungeon()
	{
		this.dungeonRooms = new Room[5][5];
	}

/*-------------------------------------------------------------------
chooseHero allows the user to select a hero, creates that hero, and
returns it.  It utilizes a polymorphic reference (Hero) to accomplish
this task
---------------------------------------------------------------------*/
	public static Hero chooseHero()
	{
		int choice;
		Hero theHero;
		
		System.out.print("\nChoose a hero:\n" 
		+ "1. Warrior\n" 
		+ "2. Sorceress\n" 
		+ "3. Thief\n" 
		+ "4. Java Student\n" 
		+ "5. Psychic\n\n"
		+ ">> ");
		choice = Keyboard.readInt();
		theHero = HeroFactory.createHero(choice);
 		return theHero;

	}//end chooseHero method


/** Moved monster generation to Factory **/

/*-------------------------------------------------------------------
Removed playAgain() -Mia Hunt 06/09/2020
---------------------------------------------------------------------*/

/*-------------------------------------------------------------------
battle is the actual combat portion of the game.  It requires a Hero
and a Monster to be passed in.  Battle occurs in rounds.  The Hero
goes first, then the Monster.  At the conclusion of each round, the
user has the option of quitting.
---------------------------------------------------------------------*/
	public static void battle(Hero theHero, Monster theMonster)
	{
		System.out.println(theHero.getName() + " battles " + theMonster.getName());
		System.out.println("---------------------------------------------");
		//do battle
		while (theHero.isAlive() && theMonster.isAlive())
		{
		    //hero goes first
			theHero.battleChoices(theMonster);

			//monster's turn (provided it's still alive!)
			if (theMonster.isAlive())
			    theMonster.attack(theHero);

		}//end battle loop

		if (!theMonster.isAlive())
			System.out.println(theHero.getName() + " was victorious!\n");
			
		if (!theHero.isAlive())
			System.out.println(theHero.getName() + " was defeated :-(\n");
	
	}//end battle method

	/** Returns a String of room item for toString method -Mia Hunt 06/10/2020 **/
	public String getRoomItem(int row, int col) {
		return this.dungeonRooms[row][col].getItem();
	}
	public String visionSurroundingRooms(int row, int col) {
		String str = "\n------ Vision Potion Map Reveal -----\n\n";
		// Top left corner wall (2x2 rooms)
		if (row == 0 && col == 0) {
			str += "\t* * * * *"
			   +"\n\t* "+ getRoomItem(row, col) +" | "+ getRoomItem(row, col+1) +" |"
			   +"\n\t* - * - *"
			   +"\n\t* "+ getRoomItem(row+1, col) +" | "+ getRoomItem(row+1, col+1) +" |"
			   +"\n\t* - * - *";
		}
		// Top center wall(2x3 rooms)
		else if (row == 0 && (col > 0 && col < 4)) {
			str += "\t* * * * * * *"
			   +"\n\t| "+ getRoomItem(row, col-1) +" | "+ getRoomItem(row, col) +" | "+ getRoomItem(row, col+1) +" |"
			   +"\n\t* - * - * - *"
			   +"\n\t| "+ getRoomItem(row+1, col-1) +" | "+ getRoomItem(row+1, col) +" | "+ getRoomItem(row+1, col+1) +" |"
			   +"\n\t* - * - * - *";
		}
		// Top right corner wall (2x2 rooms)
		else if (row == 0 && col == 4) {
			str += "\t* * * * *"
			   +"\n\t| "+ getRoomItem(row, col-1) +" | "+ getRoomItem(row, col) +" *"
			   +"\n\t* - * - *"
			   +"\n\t| "+ getRoomItem(row+1, col-1) +" | "+ getRoomItem(row+1, col) +" *"
			   +"\n\t* - * - *";
		}	
		// Left center wall (3x2 rooms)
		else if (col == 0 && (row > 0 && row < 4)) {
			str += "\t* - * - *"
			   +"\n\t* "+ getRoomItem(row-1, col) +" | "+ getRoomItem(row-1, col+1) +" |"
			   +"\n\t* - * - *"
			   +"\n\t* "+ getRoomItem(row, col) +" | "+ getRoomItem(row, col+1) +" |"
			   +"\n\t* - * - *"
			   +"\n\t* "+ getRoomItem(row+1, col) +" | "+ getRoomItem(row+1, col+1) +" |"
			   +"\n\t* - * - *";
		}
		// Right center wall (3x2 rooms)
		else if (col == 4 && (row > 0 && row < 4)) {
			str += "\t* - * - *"
			   +"\n\t| "+ getRoomItem(row-1, col-1) +" | "+ getRoomItem(row-1, col) +" *"
			   +"\n\t* - * - *"
			   +"\n\t| "+ getRoomItem(row, col-1) +" | "+ getRoomItem(row, col) +" *"
			   +"\n\t* - * - *"
			   +"\n\t| "+ getRoomItem(row+1, col-1) +" | "+ getRoomItem(row+1, col) +" *"
			   +"\n\t* - * - *";
		}
		// Bottom left corner wall (2x2 rooms)
		else if (row == 4 && col == 0) {
			str += "\t* - * - *"
			   +"\n\t* "+ getRoomItem(row-1, col) +" | "+ getRoomItem(row-1, col+1) +" |"
			   +"\n\t* - * - *"
			   +"\n\t* "+ getRoomItem(row, col) +" | "+ getRoomItem(row, col+1) +" |"
			   +"\n\t* * * * *";
		}
		// Bottom center wall (2x3 rooms)
		else if (row == 4 && (col > 0 && col < 4)) {
			str += "\t* - * - * - *"
			   +"\n\t| "+ getRoomItem(row-1, col-1) +" | "+ getRoomItem(row-1, col) +" | "+ getRoomItem(row-1, col+1) +" |"
			   +"\n\t* - * - * - *"
			   +"\n\t| "+ getRoomItem(row, col-1) +" | "+ getRoomItem(row, col) +" | "+ getRoomItem(row, col+1) +" |"
			   +"\n\t* * * * * * *";
		}
		// Bottom right corner wall (2x2 rooms)
		else if (row == 4 && col == 4) {
			str += "\t* - * - *"
			+"\n\t| "+ getRoomItem(row-1, col-1) +" | "+ getRoomItem(row-1, col) +" *"
			+"\n\t* - * - *"
			+"\n\t| "+ getRoomItem(row, col-1) +" | "+ getRoomItem(row, col) +" *"
			+"\n\t* * * * *";	
		}
		// Interior room (3x3 rooms)
		else {
			if (row == 1)
				str += "\t* * * * * * *";
			else
				str += "\t* - * - * - *";
			
			if (col == 1)
				str += "\n\t* "+ getRoomItem(row-1, col-1) +" - "+ getRoomItem(row-1, col) +" - "+ getRoomItem(row-1, col+1) +" |";
			else if (col == 3)
				str += "\n\t| "+ getRoomItem(row-1, col-1) +" | "+ getRoomItem(row-1, col) +" | "+ getRoomItem(row-1, col+1) +" *";
			else
				str += "\n\t| "+ getRoomItem(row-1, col-1) +" | "+ getRoomItem(row-1, col) +" | "+ getRoomItem(row-1, col+1) +" |";
			
			str += "\n\t* - * - * - *";
			
			if (col == 1)
				str += "\n\t* "+ getRoomItem(row-1, col-1) +" - "+ getRoomItem(row-1, col) +" - "+ getRoomItem(row-1, col+1) +" |";
			else if (col == 3)
				str += "\n\t| "+ getRoomItem(row-1, col-1) +" | "+ getRoomItem(row-1, col) +" | "+ getRoomItem(row-1, col+1) +" *";
			else
				str += "\n\t| "+ getRoomItem(row-1, col-1) +" | "+ getRoomItem(row-1, col) +" | "+ getRoomItem(row-1, col+1) +" |";
			
			str += "\n\t* - * - * - *";
			
			if (col == 1)
				str += "\n\t* "+ getRoomItem(row-1, col-1) +" - "+ getRoomItem(row-1, col) +" - "+ getRoomItem(row-1, col+1) +" |";
			else if (col == 3)
				str += "\n\t| "+ getRoomItem(row-1, col-1) +" | "+ getRoomItem(row-1, col) +" | "+ getRoomItem(row-1, col+1) +" *";
			else
				str += "\n\t| "+ getRoomItem(row-1, col-1) +" | "+ getRoomItem(row-1, col) +" | "+ getRoomItem(row-1, col+1) +" |";
			
			if (row == 3)
				str += "\n\t* * * * * * *";
			else
				str +="\n\t* - * - * - *";
		}
		str += "\n---------------------------\n\n";
		return str;
	}
		
	
	/**Builds a String containing information about the ENTIRE dungeon.*/
	@Override
	public String toString() {
		String str = "\n----------- Dungeon Map ----------\n\n";
		int roomCol, roomRow = 0;
		for(int row = 0; row < 11 ; row++) {
			roomCol = 0;
			for(int col =0; col < 11 ; col++) {
				
				if(col == 0 || col == 10 || row == 0 || row == 10) {            // Outer Walls
					str += " * ";
				}
				else {                              //Internals of the Maze
					if(col%2==0) {
						if(row%2==0) {
							str += " * ";
						}
						else
						{
						str += " | ";
						}
					} 
					else if(row%2==0) {
						str += " - ";
					}
					else {
					str += " " + getRoomItem(roomRow, roomCol) + " "; 
					roomCol++; 
					}
				}
			}//end of Columns
			if(row%2==1) {
				roomRow++;
			}
			str += "\n";
		}//end of Rows
		str += "\n---------------------------------\n\n";
		return str;
	}
}//end Dungeon class