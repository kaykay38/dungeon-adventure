
public class Attack implements IAttack
{
	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		boolean canAttack;
		int damage;

		canAttack = Math.random() <= hero.chanceToHit;

		if (canAttack)
		{
			damage = (int)(Math.random() * (hero.damageMax - hero.damageMin + 1)) + hero.damageMin ;
			opponent.subtractHitPoints(damage);
			System.out.println();
		}//end if can attack
		else
		{
			System.out.println(hero.getName() + "'s attack on " + opponent.getName() + " failed!");
			System.out.println();
		}//end else

	}//end attack method
}
