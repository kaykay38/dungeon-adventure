import java.util.HashMap;
import java.util.Map;


public class AttackFactory 
{
	private Map<String, IAttack> attacks = new HashMap<String, IAttack>();
	
	public IAttack getAttack(String attackType)
	{
		IAttack attack = attacks.get(attackType);
		if (attack == null)
		{
			if (attackType.equalsIgnoreCase("SurpriseAttack"))
			{
				attack = new SurpriseAttack();
				attacks.put(attackType, attack);
			}
			else if (attackType.equalsIgnoreCase("CrushingBlow"))
			{
				attack = new CrushingBlow();
				attacks.put(attackType, attack);
			}
			else if (attackType.equalsIgnoreCase("IncreaseHitPoints"))
			{
				attack = new IncreaseHitPoints();
				attacks.put(attackType, attack);
			}
			else if (attackType.equalsIgnoreCase("NullPointer"))
			{
				attack = new NullPointer();
				attacks.put(attackType, attack);
			}
			else if (attackType.equalsIgnoreCase("Forsee"))
			{
				attack = new Forsee();
				attacks.put(attackType, attack);
			}
		}
		return attack;
	}
	
}
