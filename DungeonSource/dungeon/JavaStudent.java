package dungeon;

public class JavaStudent extends Hero {

	public final int MIN_ADD = 25;
	public final int MAX_ADD = 50;

//-----------------------------------------------------------------
    public JavaStudent()
	{
		super("Java Student", 1000, 7, .9, 45, 80, .1);


    }//end constructor


//-----------------------------------------------------------------
	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		System.out.println("\n" + hero.getName() + " throws " + opponent.getName() + " out of the bounds:");
		super.attack(opponent);
	}//end override of attack method
	
}
