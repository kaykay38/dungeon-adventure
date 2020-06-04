import java.util.HashMap;
import java.util.Map;


public class AttackFactory 
{
	private Map<IAttack, IAttack> attacks = new HashMap<IAttack, IAttack>();
	
	public IAttack getAttack(IAttack attackType)
	{
		IAttack attack = attacks.get(attackType);
		if (attack == null)
		{
			if (attackType instanceof Attack)
			{
				attack = new Attack();
				attacks.put(attackType, attack);
			}
			else if (attackType instanceof SurpriseAttack)
			{
				attack = new SurpriseAttack();
				attacks.put(attackType, attack);
			}
			else if (attackType instanceof Attack)
			{
				attack = new Attack();
				attacks.put(attackType, attack);
			}
			
			
		}
		return attack;
	}
	
}
