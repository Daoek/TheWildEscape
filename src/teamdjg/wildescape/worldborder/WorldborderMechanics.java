package teamdjg.wildescape.worldborder;

import net.md_5.bungee.api.ChatColor;
import teamdjg.wildescape.main.Main;

public class WorldborderMechanics 
{
	Main mainclass;
	
	public WorldborderMechanics(Main plugin)
	{
		mainclass = plugin;
	}
	
	public void MakeBorderSmaller()
	{
		int amountToMove;
		
		if((mainclass.WorldBorderCurrent - mainclass.WordBorderDistance) >= mainclass.WorldBorderMin )
		{
			amountToMove = mainclass.WorldBorderCurrent - mainclass.WordBorderDistance;
		}
		else
		{
			amountToMove = mainclass.WorldBorderMin;
			mainclass.MakeBorderSmallerOnMidNight = false;
			mainclass.getServer().broadcastMessage(mainclass.pluginPrefix + ChatColor.GREEN + "The border will not move anymore");
		}		
		
		mainclass.WorldBorderWorld.getWorldBorder().setSize(amountToMove, mainclass.WorldBorderSpeed);
		
		mainclass.WorldBorderCurrent = amountToMove;
		
	}
	
	public void MakeBorderBigger()
	{
		int amountToMove;
		
		if((mainclass.WorldBorderCurrent + mainclass.WordBorderDistance) <= mainclass.WorldBorderMax)
		{
			amountToMove = mainclass.WorldBorderCurrent + mainclass.WordBorderDistance;
		}
		else
		{
			amountToMove = mainclass.WorldBorderMax;
		}
		
		mainclass.WorldBorderWorld.getWorldBorder().setSize(amountToMove, 1);
		
		mainclass.WorldBorderCurrent = amountToMove;
	}

	public void BorderReset()
	{
		mainclass.WorldBorderWorld.getWorldBorder().setSize(mainclass.WorldBorderMax, 30);
		mainclass.WorldBorderCurrent = mainclass.WorldBorderMax;
	}

	public void BorderStopMoving()
	{
		mainclass.MakeBorderSmallerOnMidNight = false;
	}

	public void BorderResumeMoving()
	{
		mainclass.MakeBorderSmallerOnMidNight = true;
	}
}
