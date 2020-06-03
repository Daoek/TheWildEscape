package teamdjg.wildescape.worldborderCommands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import teamdjg.wildescape.main.Main;

public class WorldborderStartercommand implements CommandExecutor {
	
	Main mainclass;
	
	public WorldborderStartercommand(Main plugin)
	{
		this.mainclass = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {		
		
		if(sender instanceof Player)
		{
			if(!sender.hasPermission(mainclass.borderstartPermission))
			{
				sender.sendMessage(mainclass.pluginPrefix + ChatColor.DARK_RED + "ERROR: you don't have permission for this plugin.");
				return true;
			}
		}
		
		if(mainclass.WorldborderCenterCheck == false)
		{
			if (sender instanceof Player) 
			{
				mainclass.ClearChat((Player)sender);
				sender.sendMessage(mainclass.ChatLine());
				sender.sendMessage(mainclass.pluginPrefix + ChatColor.DARK_RED + "ERROR:" + ChatColor.GOLD + "You first need to use the  - /bordercenter - before you can start the game");
				sender.sendMessage(mainclass.ChatLine());
			}
			else
			{
				System.out.println(mainclass.pluginPrefix + ChatColor.DARK_RED + "ERROR:" + ChatColor.GOLD + "You first need to use the  - /bordercenter - before you can start the game");
			}
			return true;
		}
		
		World gameWorld = mainclass.getServer().getWorld(mainclass.WorldBorderWorldName);
		
		//start game :
		
		mainclass.GameRunning = true;
		
		//change difficulty
		gameWorld.setDifficulty(Difficulty.getByValue(mainclass.gameDifficulty));
		
		//set game time
		gameWorld.setTime(mainclass.gameStartTime);
		
		
		//start border clock
		mainclass.getServer().getScheduler().cancelTasks(mainclass);
		mainclass.BorderTimer(gameWorld);
		mainclass._WorldborderMechanics.BorderResumeMoving();
		
		//start message
		mainclass.getServer().broadcastMessage(mainclass.pluginPrefix + ChatColor.GOLD + "The game has begon. The last team alive will win.");
		
		Random random = new Random();
		
		int minX = mainclass.WorldCenter.getBlockX() - (mainclass.WorldBorderMax/2);
		int minZ = mainclass.WorldCenter.getBlockZ() - (mainclass.WorldBorderMax/2);
		
		boolean tpCommandPlayer = true;
		
		if(args.length > 0)
		{
			if(args[0].equals("false"))
			{
				tpCommandPlayer = false;
			}
		}
		
		for(Player player : mainclass.getServer().getOnlinePlayers())
		{
			//spreadPlayer location calculation
			int X = minX + Math.round(mainclass.WorldBorderMax * random.nextFloat());
			int Z = minZ + Math.round(mainclass.WorldBorderMax * random.nextFloat());
			int Y = player.getWorld().getHighestBlockAt(new Location(player.getWorld(),X,0,Z)).getY();	
			//-----------
			
			//remove current potion effects
			Collection<PotionEffect> effects = new ArrayList<>();
			effects.addAll(player.getActivePotionEffects());
			PotionEffect[] effectsArray = (PotionEffect[])effects.toArray();
			
			for(int i = 0; 1 < effectsArray.length; i++)
			{
				player.removePotionEffect(effectsArray[i].getType());
			}
			
			effects.clear();
			effectsArray = null;
			//-------
			
			//Spread players
			if(tpCommandPlayer == false || !(sender instanceof Player))
			{				
				if(!(player.equals(sender)))
				{
					setupPlayer(player, X, Y, Z);
				}	
			}
			else
			{
				setupPlayer(player, X, Y, Z);
			}
			//------
			
		}

		//TODO change gamemode for the players
		
		//TODO maybe a starter kit for the players?
		
		return true;
		
	}
	
	public void setupPlayer(Player player, int xTpLocation, int yTpLocation, int zTpLocation)
	{
		player.teleport(new Location(player.getWorld(),xTpLocation,yTpLocation,zTpLocation));
		
		player.getInventory().clear();
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1200, 50));
		player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 19200, 4));
		player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1200, 4));
		
		player.setHealth(20);
		player.setFoodLevel(20);
		player.setGameMode(GameMode.SURVIVAL);
	}

}
