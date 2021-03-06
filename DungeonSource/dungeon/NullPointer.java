package dungeon;

public class NullPointer implements IAttack
{
	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		if (Math.random() <= .1)
		{
			int nullPoints = opponent.getHitPoints();
			System.out.println("\n" + hero.getName() + " points at the monster nullifying it for " + nullPoints + " damage!");
			opponent.subtractHitPoints(nullPoints);
		}//end blow succeeded
		else
		{
			System.out.println("\n" + hero.getName() + " failed to nullify the monster.");
			System.out.println();
		}//blow failed

	}
	
	@Override
	public String toString() 
	{
		return "Null Pointer";
	}
}
