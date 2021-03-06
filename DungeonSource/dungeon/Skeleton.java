package dungeon;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class Skeleton extends Monster
{

    public Skeleton()
	{
		super("Sargath the Skeleton", 100, 3, .8, .3, 30, 50, 30, 50,.2);

    }//end constructor

	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		System.out.println("\n" + this.getName() + " slices his rusty blade at " + opponent.getName() + ":");
		super.attack(opponent);

	}//end override of attack


}//end class Skeleton