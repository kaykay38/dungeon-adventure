package dungeon;
public class CrushingBlow implements IAttack
{
	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		if (Math.random() <= .4)
		{
			int blowPoints = (int)(Math.random() * 76) + 100;
			System.out.println("\n" + hero.getName() + " lands a CRUSHING BLOW for " + blowPoints + " damage!");
			opponent.subtractHitPoints(blowPoints);
		}//end blow succeeded
		else
		{
			System.out.println("\n" + hero.getName() + " failed to land a crushing blow");
			System.out.println();
		}//blow failed

	}
	
	public String toString() 
	{
		return "Crushing Blow";
	}
}
