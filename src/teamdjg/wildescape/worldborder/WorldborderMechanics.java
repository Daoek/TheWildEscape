package teamdjg.wildescape.worldborder;

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
			mainclass.WorldBorderIsRunning = false;
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
		mainclass.WorldBorderWorld.getWorldBorder().setSize(mainclass.WorldBorderMax, 60);
		mainclass.WorldBorderCurrent = mainclass.WorldBorderMax;
		mainclass.WorldBorderIsRunning = false;
	}
}
