
public class Drake extends Monster {

	public Drake()
	{
		super("Dyrus the Drake", 300, 2, .5, .2, 40, 50, 10, 20);


    }//end constructor

	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		System.out.println(name + " swings its tail at " + opponent.getName() + ":");
		super.attack(opponent);

	}//end override of attack
	
}
