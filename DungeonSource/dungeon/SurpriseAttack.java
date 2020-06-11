package dungeon;

public class SurpriseAttack implements IAttack
{
	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		double surprise = Math.random();
		if (surprise <= .7)
		{
			System.out.println("\nSurprise attack was successful!\n" + hero.getName() + " gets an additional turn.\n");
			((Hero)hero).setNumTurns(((Hero)hero).getNumTurns() + 1);
			opponent.subtractHitPoints(20);
		}//end surprise
		else
		{
			System.out.println("\nUh oh! " + opponent.getName() + " saw you and blocked your attack!");
		}
	}//end attack method
	
	@Override	
	public String toString() 
	{
		return "Surprise Attack";
	}
	
}
