package teamdjg.wildescape.main;

import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

import teamdjg.wildescape.worldborder.WorldborderMechanics;

public class Main extends JavaPlugin implements Listener 
{
	public String pluginPrefix = ChatColor.DARK_GRAY +  "[" + ChatColor.BLUE + "DJG TWE" + ChatColor.GRAY + "]";
	
	public WorldborderMechanics _WorldborderMechanics;	
	public World WorldBorderWorld;	
	public long WorldBorderSpeed;	
	public boolean MakeBorderSmallerOnMidNight = false;
	
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
		public void BorderTimer(World world) {
		    this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
		        public void run() {
		            long time = world.getTime();
		            
		            if(time == 17800 && MakeBorderSmallerOnMidNight)
		            {
		            	// print a warning to all players that border will be moving
		            	getServer().broadcastMessage(pluginPrefix + ChatColor.RED + "WARNING the border will move in 10 seconds !!");
		            }
		            
		            if (time == 18000 && MakeBorderSmallerOnMidNight) 
		            {
		            	// (print text and make border smaller) if enabled.
		            	getServer().broadcastMessage(pluginPrefix + ChatColor.DARK_RED + "The border is becomming smaller !!"); 
		            	_WorldborderMechanics.MakeBorderSmaller();
		            }
		            
		        }
		    }, 1, 1);
		}
}
