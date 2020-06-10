
public class Forsee implements IAttack {
	
	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		if (Math.random() <= 1)
		{
			opponent.chanceToHit = 0;
			System.out.println(hero.name + " looks into the future seeing all of " + opponent.name + "'s attacks!");
			System.out.println(hero.name + " will dodge all future attacks this battle.");
			System.out.println();
		}
		else
		{
			System.out.println(hero.name + " failed to look into " + opponent.name + "'s mind.");
			System.out.println();
		}
		
	}
	
	public String toString() 
	{
		return "Forsee";
	}
	
}
