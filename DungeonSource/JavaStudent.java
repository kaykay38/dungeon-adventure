
public class JavaStudent extends Hero {

	public final int MIN_ADD = 25;
	public final int MAX_ADD = 50;

//-----------------------------------------------------------------
    public JavaStudent()
	{
		super("Java Student", 100, 7, .9, 45, 80, .1);


    }//end constructor


//-----------------------------------------------------------------
	public void attack(DungeonCharacter hero, DungeonCharacter opponent)
	{
		System.out.println(hero.name + " throws " + opponent.getName() + " out of the bounds:");
		super.attack(opponent);
	}//end override of attack method
	
}