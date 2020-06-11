
public class HeroFactory {
	public static Hero createHero(int choice) {
		
		AttackFactory attfac = new AttackFactory();
		
		switch(choice)
		{
			case 1: Hero warrior = new Warrior();
					warrior.setAttackBehavior(attfac.getAttack("CrushingBlow"));
					System.out.println("\nA warrior named "+ warrior.getName() + " has been created.");
					return warrior;

			case 2: Hero sorceress = new Sorceress();
					sorceress.setAttackBehavior(attfac.getAttack("IncreaseHitPoints"));
					System.out.println("\nA sorceress named "+ sorceress.getName() + " has been created.");
					return sorceress;

			case 3: Hero thief = new Thief();
					thief.setAttackBehavior(attfac.getAttack("SurpriseAttack"));
					System.out.println("\nA thief named "+ thief.getName() + " has been created.");
					return thief;
					
			case 4: Hero jstudent = new JavaStudent();
					jstudent.setAttackBehavior(attfac.getAttack("NullPointer"));
					System.out.println("\nA Java student named "+ jstudent.getName() + " has been created.");
					return jstudent;
					
			case 5: Hero psychic = new Psychic();
					psychic.setAttackBehavior(attfac.getAttack("Forsee"));
					System.out.println("\nA psychic named "+ psychic.getName() + " has been created.");
					return psychic;

			default: System.out.println("\nInvalid choice, returning Thief");
					Hero thiefDefault = new Thief();
					thiefDefault.setAttackBehavior(attfac.getAttack("SurpriseAttack"));
					System.out.println("\nA thief named "+ thiefDefault.getName() + " has been created.");
					return thiefDefault;
		}//end switch
	}
}	
