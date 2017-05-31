package com.castaware.castabattle.domain;

import static com.castaware.castabattle.domain.CellType.CRUISER;
import static com.castaware.castabattle.domain.CellType.WATER;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

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
				this.boardGame[i][j] = CellType.HIDDEN;
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
		
		// Se atingiu alguma embarcao, marca com fogo
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
		String result =   "";
		
		for(int i=0;i< this.boardGame.length;i++)
		{
			for (int j = 0; j < this.boardGame.length; j++) {
				result += this.boardGame[i][j].toString();
			}
			result += "\n";
			//result = result + Arrays.toString(boardGame[i]) + "\n";
		}
		
		return result;
	}	
	
	/**
	 * Must be a letter between 'A' and 'J'
	 * @param column
	 * @return
	 */
	public static int translateColumn(String column) 
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
	
	/**
	 * Maps an Integer to a letter (String) (range between 0 and 9)
	 * @param column
	 * @return
	 */
	public static String columnInt2Str(Integer column) {
		String x ="";
		
		if 		(column.equals(0)) x = "A";
		else if (column.equals(1)) x = "B";
		else if (column.equals(2)) x = "C";
		else if (column.equals(3)) x = "D";
		else if (column.equals(4)) x = "E";
		else if (column.equals(5)) x = "F";
		else if (column.equals(6)) x = "G";
		else if (column.equals(7)) x = "H";
		else if (column.equals(8)) x = "I";
		else if (column.equals(9)) x = "J";
		else
			throw new IllegalArgumentException("Invalid column definition: "+column);
		return x;
	}

	/**
	 * 'line' must be a number between 1 and 10
	 * @param line
	 * @return
	 */
	public static int translateLine(int line) 
	{
		int y = line-1;
		
		if (y<0 || y>9)
		{
			throw new IllegalArgumentException("Invalid line definition: "+line);
		}
		return y;
	}
	
	/**
	 * Meant to return this template board only.
	 * @return
	 */
	public static CellType[][] getTemplateBoard() {
		CellType[][] template = new CellType[][]  { 
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},//1   
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},//2
			{WATER, WATER,   WATER ,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},//3		                                      
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},//4	
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},//5	
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER , WATER, WATER, WATER},//6		
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},//7	
			{WATER, CRUISER, CRUISER, CRUISER, CRUISER, WATER, WATER, WATER, WATER, WATER},//8	
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},//9	
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER} }//10
		;
			
		return template;
	}
	
	public static CellType[][] getMadeBoard(Collection<Integer[]> coordenadas) {
		CellType[][] madeBoard = new CellType[][]  { 
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},//1   
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},//2
			{WATER, WATER,   WATER ,  WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},//3		                                      
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},//4	
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},//5	
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER ,WATER, WATER, WATER},//6		
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},//7	
			{WATER, WATER,   WATER,   WATER,   WATER, 	WATER, WATER, WATER, WATER, WATER},//8	
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},//9	
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER} }//10
		;
		for (Iterator it = coordenadas.iterator(); it.hasNext();) {
			Integer[] coordenada = (Integer[]) it.next();
			madeBoard[coordenada[0]][coordenada[1]] = CellType.BOAT;
		}
		return madeBoard;
	}
}



















