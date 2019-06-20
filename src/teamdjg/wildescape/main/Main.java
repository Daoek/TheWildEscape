package teamdjg.wildescape.main;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener	
{
	public String pluginPrefix = "[DJG TWE] ";
	
	//plugin enabled
	@Override
	public void onEnable() 
	{		
		System.out.println(pluginPrefix + "PLUGIN ENABLED!");
		
		//give the Eventhandler the instance
		new Eventhandler(this);
	}
	
	//plugin disabled
	@Override
	public void onDisable() 
	{		
		System.out.println(pluginPrefix + "PLUGIN DISABLED!");
	}
}
