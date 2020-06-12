package dungeon;

public class Drake extends Monster {

	public Drake()
	{
		super("Dyrus the Drake", 300, 2, .5, .2, 35, 50, 10, 20, .6);


    }//end constructor

	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		System.out.println("\n" + this.getName() + " swings its tail at " + opponent.getName() + ":");
		super.attack(opponent);

	}//end override of attack
	
}
