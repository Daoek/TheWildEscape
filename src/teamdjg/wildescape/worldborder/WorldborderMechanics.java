package teamdjg.wildescape.worldborder;

import org.bukkit.entity.Player;

import teamdjg.wildescape.main.Main;

public class WorldborderMechanics 
{
	Main mainclass;
	
	public WorldborderMechanics(Main plugin)
	{
		mainclass = plugin;
	}
	
	public void MakeBorderSmaller(Player p, int amount, long time)
	{
		p.getWorld().getWorldBorder().setSize(amount, time);
		
	}
}
