
public class IncreaseHitPoints implements IAttack
{
	public final int MIN_ADD = 25;
	public final int MAX_ADD = 50;
	
	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		int hPoints;

		hPoints = (int)(Math.random() * (MAX_ADD - MIN_ADD + 1)) + MIN_ADD;
		hero.addHitPoints(hPoints);
		System.out.println("\n" + hero.getName() + " added [" + hPoints + "] points.\n" + "Total hit points remaining are: "	+ hero.getHitPoints());
		System.out.println();
	}
	
	@Override
	public String toString() 
	{
		return "Increase Hit Points";
	}
}
