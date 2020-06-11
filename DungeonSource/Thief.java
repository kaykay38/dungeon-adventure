

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class Thief extends Hero
{

    public Thief()
	{
		super("Thief", 75, 6, .8, 20, 40, .5);



    }//end constructor

	
	// Added to provide consistency across characters having unique attack messages (Nick 6/4/20)
	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		System.out.println(hero.getName() + " throws their dagger at " + opponent.getName() + ":");
		super.attack(opponent);
	}//end override of attack method
	
   
}