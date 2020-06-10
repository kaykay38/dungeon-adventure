
public class Stheno extends Monster
{

	 public Stheno()
		{
			super("Stheno the Gorgon", 150, 7, .03, .7, 0, 0, 15, 30);

	    }//end constructor

	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		System.out.println(this.name + " glares at " + opponent.getName() + ":");
		
		if (Math.random() <= this.chanceToHit)
		{
			opponent.hitPoints = 0;
			System.out.println(opponent.name + " was turned to stone! " + opponent.name + "'s hitpoints remaining: 0");
			System.out.println();
		}
		else
		{
			System.out.println(this.name + " got hair in her face and couldn't see " + opponent.getName() + ".");
			System.out.println();
		}
			

	}//end override of attack
	
}
