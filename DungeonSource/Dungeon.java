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
		
		System.out.print("\t\t\tChoose a hero:\n\t\t\t" 
		+ "1. Warrior\n\t\t\t" 
		+ "2. Sorceress\n\t\t\t" 
		+ "3. Thief\n\t\t\t" 
		+ "4. Java Student\n\t\t\t" 
		+ "5. Psychic\n\n\t\t\t"
		+ ">>");
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
			System.out.println(theHero.getName() + " was victorious!\n" + theHero.toString());
			
		if (!theHero.isAlive())
			System.out.println(theHero.getName() + " was defeated :-(");
	
	}//end battle method


	
	/** Game Dependent Objects Spawning methods **/
//Places the Entrance, the Exit, and the Pillars of OO Pieces. 
//	RULES: (1) The entrance and exit are empty rooms. 
//   	   (2) The Pillar pieces cannot be at the entrance or the exit. 
//  	   (3) Pillar pieces must not occur in the same room.
	
	/** Player Tracking method **/
// Maintains location of the Hero in the Dungeon
	
public String getRoomItem(int row, int col) {
	return this.dungeonRooms[row][col].getItem();
}
	/** toString method**/
// Builds a String containing information about the ENTIRE dungeon.
@Override
public String toString() {
	String str = "\n-----------[Dungeon Map]----------\n\n";
	int roomCol, roomRow = 0;
	for(int row = 0; row < 11 ; row++) {
		roomCol = 0;
		for(int col =0; col < 11 ; col++) {
			
			if(col == 0 || col == 10) {            //East and West Walls
				str += " * ";
			}
			else if(row == 0 || row == 10) {    //North and South Walls
				str += " * ";
			}
			else {                                //Internals of the Maze
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
				str += " "+getRoomItem(roomRow, roomCol)+" "; 
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