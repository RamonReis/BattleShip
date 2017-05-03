package com.castaware.castabattle.domain;

import java.util.Arrays;

public class Board 
{
	private static int SIZE = 10;
	
	private CellType[][] boardGame = new CellType[SIZE][SIZE];
	private CellType[][] boardTemplate = new CellType[SIZE][SIZE];

	private int hitPoints;
	
	public CellType[][] getBoardGame() {
		return boardGame;
	}
	
	public CellType[][] getBoardTemplate() {
		return boardTemplate;
	}
	
	public void initBoard(CellType[][] boardTemplate)
	{
		for (int i=0;i<SIZE;i++)
		{
			for (int j=0;j<SIZE;j++)
			{
				boardGame[i][j] = CellType.HIDDEN;
			}
		}
		
		this.boardTemplate = boardTemplate;
		
		for (int i=0;i<SIZE;i++)
		{
			for (int j=0;j<SIZE;j++)
			{
				CellType target = boardTemplate[i][j];
				
				if (target.isShip())
					hitPoints++;
			}
		}
	}
	
	public CellType fire(String column,int line)
	{
		int y = translateLine(line);		
		int x = translateColumn(column);
		
		CellType target = boardTemplate[y][x];
		
		// Se atingiu alguma embarcação, marca com fogo
		if (target.isShip())
		{
			boardGame[y][x] = CellType.FIRE;
			hitPoints--;
		}
		else
		{
			boardGame[y][x] = CellType.WATER;
		}		
				
		return target;
	}
	
	public CellType readInGame(String column,int line)
	{
		int y = translateLine(line);		
		int x = translateColumn(column);
		
		CellType target = boardGame[y][x];
		
		return target;
	}
	
	public CellType readInTemplate(String column,int line)
	{
		int y = translateLine(line);		
		int x = translateColumn(column);
		
		CellType target = boardTemplate[y][x];
		
		return target;
	}
	
	public boolean hasShip()
	{
		return hitPoints > 0;
	}
	
	@Override
	public String toString()
	{
		String result =   "BOARD\n";
		result = result + "=====\n";
		
		for(int i=0;i<10;i++)
		{
			result = result + Arrays.toString(boardGame[i]) + "\n";
		}
		
		return result;
	}	
	
	private int translateColumn(String column) 
	{
		int x;
		
		column = column.toUpperCase();
		if 		(column.equals("A")) x = 0;
		else if (column.equals("B")) x = 1;
		else if (column.equals("C")) x = 2;
		else if (column.equals("D")) x = 3;
		else if (column.equals("E")) x = 4;
		else if (column.equals("F")) x = 5;
		else if (column.equals("G")) x = 6;
		else if (column.equals("H")) x = 7;
		else if (column.equals("I")) x = 8;
		else if (column.equals("J")) x = 9;
		else
			throw new IllegalArgumentException("Invalid column definition: "+column);
		return x;
	}

	private int translateLine(int line) 
	{
		int y = line-1;
		
		if (y<0 || y>9)
		{
			throw new IllegalArgumentException("Invalid line definition: "+line);
		}
		return y;
	}
	
	
}



















