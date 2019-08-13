package teamdjg.wildescape.main;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

//
import teamdjg.wildescape.worldborder.WorldborderMechanics;
import teamdjg.wildescape.worldborderCommands.WorldborderCentercommand;
import teamdjg.wildescape.worldborderCommands.WorldborderPauzecommand;
import teamdjg.wildescape.worldborderCommands.WorldborderResumecommand;
import teamdjg.wildescape.worldborderCommands.WorldborderSetupcommand;
import teamdjg.wildescape.worldborderCommands.WorldborderStartercommand;
import teamdjg.wildescape.worldborderCommands.WorldborderStopcommand;

public class Main extends JavaPlugin implements Listener 
{
	public String pluginPrefix = ChatColor.DARK_GRAY +  "[" + ChatColor.BLUE + "DJG TWE" + ChatColor.DARK_GRAY + "]";
	
	//world border variables -------------------------
	public WorldborderMechanics _WorldborderMechanics;	
	public String WorldBorderWorldName;	
	public long WorldBorderSpeed;	
	public boolean MakeBorderSmallerOnMidNight = false;
	public Player ContactPlayerForWorldBorder;
	public boolean GameRunning = false;
	
	public int WorldBorderMax;
	public int WorldBorderMin;
	public int WorldBorderCurrent;
	public int WordBorderDistance;
	
	public boolean WorldborderSetupCheck = false;
	public boolean WorldborderCenterCheck = false;
	//------------------------------------------------
	
	//game variables ---------------------------------
	public int gameDifficulty = 1;
	public long gameStartTime = 0; //range from 0 - 18000
	//------------------------------------------------
	
	@Override
	public void onEnable() 
	{	
		//TODO load from configfile the worldborder values		
		
		
		//set up references
		new Eventhandler(this);
		_WorldborderMechanics = new WorldborderMechanics(this);
		
		//Load-in the commands:	
		
		//Team Commands
			//this.getCommand("CreateTeam").setExecutor(new TeamCreate(this));
		//
		
		//Border commands
		this.getCommand("bordersetup").setExecutor(new WorldborderSetupcommand(this));
		this.getCommand("bordercenter").setExecutor(new WorldborderCentercommand(this));
		this.getCommand("borderstart").setExecutor(new WorldborderStartercommand(this));
		this.getCommand("borderstop").setExecutor(new WorldborderStopcommand(this));
		this.getCommand("borderpauze").setExecutor(new WorldborderPauzecommand(this));
		this.getCommand("borderresume").setExecutor(new WorldborderResumecommand(this));
		//
		
		System.out.println(pluginPrefix + "PLUGIN ENABLED!");
		
	}
	
	@Override
	public void onDisable() 
	{	
		//TODO save the current worldborder values to the configfile
		
		System.out.println(pluginPrefix + "PLUGIN DISABLED!");
	}
	
	//Time checker for midnight
	public void BorderTimer(World world)
		{
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

	public void ClearChat(Player p)
	{
		for(int i = 0; i > 20; i++)
		{
			p.sendMessage("=");
		}
	}

	public String ChatLine()
	{
		String st = ChatColor.DARK_GRAY + "<-------------------------------------------->";
		return st;
	}
}
