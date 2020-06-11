
public class Psychic extends Hero {
	
	public final int MIN_ADD = 25;
	public final int MAX_ADD = 50;

//-----------------------------------------------------------------
    public Psychic()
	{
		super("Psychic", 100, 5, .5, 30, 55, 0);


    }//end constructor


//-----------------------------------------------------------------
	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		System.out.println(hero.getName() + " forces " + opponent.getName() + " to attack itself:");
		super.attack(opponent);
	}//end override of attack method

}
