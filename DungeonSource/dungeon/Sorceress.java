package dungeon;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */



public class Sorceress extends Hero
{
	public final int MIN_ADD = 25;
	public final int MAX_ADD = 50;

//-----------------------------------------------------------------
    public Sorceress()
	{
		super("Sorceress", 75, 5, .7, 25, 50, .3);


    }//end constructor


//-----------------------------------------------------------------
	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		System.out.println("\n" + hero.getName() + " casts a spell of fireball at " + opponent.getName() + ":");
		super.attack(opponent);
	}//end override of attack method


}//end class