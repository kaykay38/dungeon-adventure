
public class SurpriseAttack implements IAttack
{
	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		double surprise = Math.random();
		if (surprise <= .4)
		{
			System.out.println("\nSurprise attack was successful!\n" + hero.getName() + " gets an additional turn.");
			((Hero)hero).setNumTurns(((Hero)hero).getNumTurns() + 1);
			attack(hero, opponent);
		}//end surprise
		else if (surprise >= .9)
		{
			System.out.println("\nUh oh! " + opponent.getName() + " saw you and blocked your attack!");
		}
		else
		    attack(hero, opponent);
	}//end attack method
	
	@Override	
	public String toString() 
	{
		return "Surprise Attack";
	}
	
}
