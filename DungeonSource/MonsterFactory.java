
public class MonsterFactory {
   
    /*-------------------------------------------------------------------
    generateMonster randomly selects a Monster and returns it.  It utilizes
    a polymorphic reference (Monster) to accomplish this task.
    ---------------------------------------------------------------------*/
	public static Monster generateMonster()
	{
		
		int choice = (int)(Math.random() * 5) + 1;

		switch(choice)
		{
			case 1: return new Ogre();

			case 2: return new Gremlin();

			case 3: return new Skeleton();
			
			case 4: return new Stheno();
			
			case 5: return new Drake();

			default: System.out.println("invalid choice, returning Skeleton");
				     return new Stheno();
		}//end switch
	}//end generateMonster method
}
