package dungeon;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */




public class Warrior extends Hero
{

    public Warrior()
	{

		super("Warrior", 125, 4, .8, 35, 60, .2);


    }//end constructor


	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		System.out.println(hero.getName() + " swings a mighty sword at " + opponent.getName() + ":");
		super.attack(opponent);
	}//end override of attack method


}//end Hero class