
public class NullPointer implements IAttack
{
	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		if (Math.random() <= .1)
		{
			int nullPoints = opponent.hitPoints;
			System.out.println(hero.name + " points at the monster nullifying it for " + nullPoints + " damage!");
			opponent.subtractHitPoints(nullPoints);
		}//end blow succeeded
		else
		{
			System.out.println(hero.name + " failed to nullify the monster.");
			System.out.println();
		}//blow failed

	}
	
	public String toString() 
	{
		return "Null Pointer";
	}
}
