package dungeon;

public class Forsee implements IAttack {
	
	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		if (Math.random() <= 1)
		{
			opponent.setChanceToHit(0);
			System.out.println("\n" + hero.getName() + " looks into the future seeing all of " + opponent.getName() + "'s attacks!");
			System.out.println(hero.getName() + " will dodge all future attacks in this battle.");
			System.out.println();
		}
		else
		{
			System.out.println("\n" + hero.getName() + " failed to look into " + opponent.getName() + "'s mind.");
			System.out.println();
		}
		
	}
	
	public String toString() 
	{
		return "Forsee";
	}
	
}
