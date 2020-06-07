
public class DungeonAdventure {

	private Dungeon curDungeon;
	private Hero player;
	
	public static void main(String[] args) 
	{
		DungeonAdventure curGame = new DungeonAdventure();
			curGame.player = null;
			curGame.curDungeon = null;
		
/** Game introduction Text **/
		System.out.println("_______________________________" + "\nWELCOME TO DUNGEON ADVENTURE!\n\n");
		System.out.println("Your quest to seek the four pillars of O.O. has brought you"
				         + "\nto the foulest, most dankest dungeon in all the land...\n  ");
		
		System.out.println("To escape the dungeon, you must brave the monsters, dodge the pits, "
						 + "\nand seek the four Pillars of O.O.");
		
		System.out.println("\n>Press any button to enter the Dungeon...");
																	//"or press L to load a saved game."
/** Could we implement the save game feature here? --Austin**/
		gameTutorial();
		
		
	}
	
	public static void gameTutorial() 
	{
System.out.println("\n");	//DEBUG: REMOVE ONCE MAIN MENU IS DONE
				
				System.out.println("|------------------Symbol Examples--------------------->");
				System.out.println("| Multiple Items = M      |  Empty room = E");
				System.out.println("| Entrance = I            |  Exit = O");
				System.out.println("| Monster = X             |  Pit = P");
				System.out.println("| Healing Potion = H      |  Vision Potion = V");
				System.out.println("|");
				System.out.println("|-----------------Pillars to be Found------------------->");
				System.out.println("| Pillar Abstraction = PA |  Pillar Encapsulation = PE");
				System.out.println("| Pillar Inheritance = PI |  Pillar Polymorphism = PP");
				System.out.println("| ");
				System.out.println("| ");
				System.out.println("|                  Example of Room");
				System.out.println("\t\t\t* - *\r\n" 
								+ "\t\t\t| P | \n\t\t\t* - *");
	 }//end of GameTutorial
	
	
	
}//end of CLASS
