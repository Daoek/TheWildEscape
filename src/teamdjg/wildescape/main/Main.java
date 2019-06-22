package teamdjg.wildescape.main;

import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import teamdjg.wildescape.worldborder.WorldborderMechanics;

public class Main extends JavaPlugin implements Listener 
{
	public String pluginPrefix = "[DJG TWE] ";
	
	public WorldborderMechanics _WorldborderMechanics;	
	public World WorldBorderWorld;	
	public long WorldBorderSpeed;	
	public boolean WorldBorderIsRunning;
	
	public int WorldBorderMax;
	public int WorldBorderMin;
	public int WorldBorderCurrent;
	public int WordBorderDistance;

	
	@Override
	public void onEnable() 
	{		
		//set up references
		new Eventhandler(this);
		_WorldborderMechanics = new WorldborderMechanics(this);
	
		
		System.out.println(pluginPrefix + "PLUGIN ENABLED!");
		
	}
	
	@Override
	public void onDisable() 
	{		
		System.out.println(pluginPrefix + "PLUGIN DISABLED!");
	}
	
	//Time checker for midnight
		public void scheduleTimer(Main plugin, World world) {
		    plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
		        public void run() {
		            long time = world.getTime();
		            
		            if(time == 17800 && WorldBorderIsRunning)
		            {
		            	// print a warning to all players that border will be moving
		            }
		            
		            if (time == 18000 && WorldBorderIsRunning) 
		            {
		            	// (print text and make border smaller) if enabled.
		            }
		            
		        }
		    }, 1, 1);
		}
}
