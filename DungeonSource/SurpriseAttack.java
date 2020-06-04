
public class SurpriseAttack implements IAttack
{
	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		double surprise = Math.random();
		if (surprise <= .4)
		{
			System.out.println("Surprise attack was successful!\n" + hero.name + " gets an additional turn.");
			((Hero)hero).setNumTurns(((Hero)hero).getNumTurns() + 1);
			attack(hero, opponent);
		}//end surprise
		else if (surprise >= .9)
		{
			System.out.println("Uh oh! " + opponent.getName() + " saw you and blocked your attack!");
		}
		else
		    attack(hero, opponent);
	}//end attack method
}
