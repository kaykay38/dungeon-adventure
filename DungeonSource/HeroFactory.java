
public class HeroFactory {
	public static Hero createHero(int choice) {
		
		AttackFactory attfac = new AttackFactory();
		
		switch(choice)
		{
			case 1: Hero warrior = new Warrior();
					warrior.setAttackBehavior(attfac.getAttack("CrushingBlow"));
					return warrior;

			case 2: Hero sorceress = new Sorceress();
					sorceress.setAttackBehavior(attfac.getAttack("IncreaseHitPoints"));
					return sorceress;

			case 3: Hero thief = new Thief();
					thief.setAttackBehavior(attfac.getAttack("SurpriseAttack"));
					return thief;
					
			case 4: Hero jstudent = new JavaStudent();
					jstudent.setAttackBehavior(attfac.getAttack("NullPointer"));
					return jstudent;
					
			case 5: Hero psychic = new Psychic();
					psychic.setAttackBehavior(attfac.getAttack("Forsee"));
					return psychic;

			default: System.out.println("invalid choice, returning Thief");
				     return new Thief();
		}//end switch
	}
}	
