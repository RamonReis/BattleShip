package com.castaware.castabattle;

import static com.castaware.castabattle.domain.CellType.BOAT;
import static com.castaware.castabattle.domain.CellType.CRUISER;
import static com.castaware.castabattle.domain.CellType.WATER;

import java.util.Scanner;

import com.castaware.castabattle.domain.Board;
import com.castaware.castabattle.domain.CellType;

public class GameConsole 
{
	public static void main(String[] args) 
	{
		CellType[][] template = new CellType[][]  { {WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},   
													{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},	
													{WATER, WATER,   WATER ,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},		                                      
													{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},		
													{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},		
													{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER , WATER, WATER, WATER},		
													{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},		
													{WATER, CRUISER, CRUISER, CRUISER, CRUISER, WATER, WATER, WATER, WATER, WATER},		
													{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},		
													{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER} };

		Board board = new Board();
		board.initBoard(template);		
		
		Scanner sc = new Scanner(System.in);
		
		while (board.hasShip())
		{
			System.out.println(board+"\n");
			System.out.print("Line: ");
			int line = sc.nextInt();
			sc.nextLine(); // Gasta o buffer de leitura... MMUUAAHUUAAHUUUA
			System.out.print("Column: ");
			String column = sc.nextLine();
			
			CellType type = board.fire(column, line);
			if (type.isShip())
				System.out.println("FOGO!!");
			else
				System.out.println("ÁGUA...");
			
			System.out.println();			
		}
		
		System.out.println("Frota Destruída!");
	}
}
