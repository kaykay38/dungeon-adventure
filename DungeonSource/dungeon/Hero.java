package dungeon;

/*
 * Title: Hero.java
 *
 * Description: Abstract base class for a hierarchy of heroes.  It is derived
 *  from DungeonCharacter.  A Hero has battle choices: regular attack and a
 *  special skill which is defined by the classes derived from Hero.
 *
 *  class variables (all are directly accessible from derived classes):
 *    chanceToBlock -- a hero has a chance to block an opponents attack
 *    numTurns -- if a hero is faster than opponent, their is a possibility
 *                for more than one attack per round of battle
 *
 *  class methods (all are public):
 *    public Hero(String name, int hitPoints, int attackSpeed,
				     double chanceToHit, int damageMin, int damageMax,
					 double chanceToBlock)
	  public void readName()
	  public boolean defend()
	  public void subtractHitPoints(int hitPoints)
	  public void battleChoices(DungeonCharacter opponent)

 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */


public abstract class Hero extends DungeonCharacter
{
	private double chanceToBlock;
	private int healthPotions, visionPotions, ooPillars; //Fundamental Hero info
	private int numTurns;
	private int row, col;


/*-----------------------------------------------------------------
calls base constructor and gets name of hero from user*/

  public Hero(String name, int hitPoints, int attackSpeed, double chanceToHit, int damageMin, int damageMax, double chanceToBlock)
  {
	  super(name, hitPoints, attackSpeed, chanceToHit, damageMin, damageMax);
	  this.chanceToBlock = chanceToBlock;
	  this.healthPotions = 0;
	  this.visionPotions = 0;
	  this.ooPillars = 0;
	  readName();
  }
  public void setPosition(int row, int col) {
	  this.row = row;
	  this.col = col;
  }
  public double getChanceToBlock() {
	return this.chanceToBlock;
}

public void setChanceToBlock(double chanceToBlock) {
	this.chanceToBlock = chanceToBlock;
}

//Added for SurpriseAttack class to get numTurns. (Nick 6/4/20)
public int getNumTurns() { 
	return this.numTurns; 
} 
  //Added for SurpriseAttack class to set numTurns. (Nick 6/4/20)
  public void setNumTurns(int newNumTurns) { 
	  this.numTurns = newNumTurns; 
} 
public int getHealthPotions() {
	return this.healthPotions;
}

public void setHealthPotions(int healthPotions) {
	this.healthPotions = healthPotions;
}

public int getVisionPotions() {
	return this.visionPotions;
}

public void setVisionPotions(int visionPotions) {
	this.visionPotions = visionPotions;
}

public int getOoPillars() {
	return this.ooPillars;
}

public void setOoPillars(int ooPillars) {
	this.ooPillars = ooPillars;
}

public int getRow() {
	return this.row;
}

public void setRow(int row) {
	this.row = row;
}

public int getCol() {
	return this.col;
}

public void setCol(int col) {
	this.col = col;
}

/*-------------------------------------------------------
readName obtains a name for the hero from the user

Receives: nothing
Returns: nothing

This method calls: nothing
This method is called by: hero constructor
---------------------------------------------------------*/
  public void readName()
  {
		System.out.print("\nEnter character name: ");
		this.setName(Keyboard.readString());
  }//end readName method

  
  
/****************************************************
 * stats() displays fundamental hero information *
 ****************************************************/
	public String stats() {
	  return "\n\t\t----- " +  this.getName() + "'s Stats -----" 
	  + " \n\t\t  Hitpoints: " + this.getHitPoints()
	  + " \n\t\t  Healing Potions: " + this.getHealthPotions()
	  + " \n\t\t  Vision Potions: " + this.getVisionPotions()
	  + " \n\t\t  OO Pillars found: " + this.getOoPillars() + "\n";
}
  
  
/*-------------------------------------------------------
defend determines if hero blocks attack

Receives: nothing
Returns: true if attack is blocked, false otherwise

This method calls: Math.random()
This method is called by: subtractHitPoints()
---------------------------------------------------------*/
  public boolean defend()
  {
		return Math.random() <= chanceToBlock;

  }//end defend method

/*-------------------------------------------------------
subtractHitPoints checks to see if hero blocked attack, if so a message
is displayed, otherwise base version of this method is invoked to
perform the subtraction operation.  This method overrides the method
inherited from DungeonCharacter promoting polymorphic behavior

Receives: hit points to subtract
Returns: nothing

This method calls: defend() or base version of method
This method is called by: attack() from base class
---------------------------------------------------------*/
public void subtractHitPoints(int hitPoints)
	{
		if (defend())
		{
			System.out.println(this.getName() + " BLOCKED the attack!");
		}
		else
		{
			super.subtractHitPoints(hitPoints);
		}


	}//end method

/*-------------------------------------------------------
battleChoices will be overridden in derived classes.  It computes the
number of turns a hero will get per round based on the opponent that is
being fought.  The number of turns is reported to the user.  This stuff might
go better in another method that is invoked from this one...

Receives: opponent
Returns: nothing

This method calls: getAttackSpeed()
This method is called by: erowternal sources
---------------------------------------------------------*/
	public void battleChoices(DungeonCharacter opponent)
	{
	    numTurns = this.getAttackSpeed()/opponent.getAttackSpeed();
		if (numTurns == 0)
			numTurns++;
		System.out.println("Number of turns this round is: " + numTurns);
		
		int choice;

		do
		{
		    System.out.println("1. Attack Opponent");
		    System.out.println("2. " + this.getAttackBehavior());
		    System.out.print("Choose an option: ");
		    choice = Keyboard.readInt();

		    switch (choice)
		    {
			    case 1: attack(this, opponent);
			        break;
			    case 2: this.getAttackBehavior().attack(this, opponent);
			        break;
			    default:
			        System.out.println("invalid choice!");
		    }//end switch

			numTurns--;
			if (numTurns > 0)
			    System.out.println("Number of turns remaining is: " + numTurns);

		} while(numTurns > 0);

	}//end battleChoices

	
//** Player choices methods 
 /* @param player **/	

/** Health potion methods **/	
	
	public int hpPotion() { return (int)( Math.random() * 11) + 5; }
	
	/** Vision Potion method by Mia Hunt 06/09/2020 */
	
}//end Hero class