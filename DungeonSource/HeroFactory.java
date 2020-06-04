
public class HeroFactory {
	public HeroFactory() {}

	public Hero createWarrior() {
		return new Warrior();
	}
	
	public Hero createSorceress() {
		return new Sorceress();
	}
	
	public Hero createThief() {
		return new Thief();
	}
}
