package dungeon;

public class Stheno extends Monster
{

	 public Stheno()
		{
			super("Stheno the Gorgon", 150, 7, .03, .7, 0, 0, 15, 30, .5);

	    }//end constructor

	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		System.out.println("\n" + this.getName() + " glares at " + opponent.getName() + ":");
		
		if (Math.random() <= this.getChanceToHit())
		{
			opponent.setHitPoints(0);
			System.out.println("\n" + opponent.getName() + " was turned to stone! " + opponent.getName() + "'s hitpoints remaining: 0");
			System.out.println();
		}
		else
		{
			System.out.println("\n" + this.getName() + " got hair in her face and couldn't see " + opponent.getName() + ".");
			System.out.println();
		}
			

	}//end override of attack
	
}
