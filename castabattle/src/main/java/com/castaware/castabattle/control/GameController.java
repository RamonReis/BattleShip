package com.castaware.castabattle.control;

import static com.castaware.castabattle.domain.CellType.CRUISER;
import static com.castaware.castabattle.domain.CellType.WATER;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.castaware.castabattle.domain.Board;
import com.castaware.castabattle.domain.CellType;

@Controller
@RequestMapping("/game") // .../pswebproj/spring/game
public class GameController
{
	// TODO - Temporário... Não suporta multiplayer...
	private Board board;
	
	@RequestMapping("/start") // .../pswebproj/spring/game/start
	public ModelAndView start()
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

		board = new Board();
		board.initBoard(template);
		
		ModelAndView mv = new ModelAndView("/castabattle.jsp");
		mv.addObject("board",board);
		return mv;
	}	
	
	@RequestMapping("/reset") // .../pswebproj/spring/game/reset
	public ModelAndView reset()
	{
		return start();
	}
	
	@RequestMapping("/fire") // .../pswebproj/spring/game/fire
	public ModelAndView helloRequestParam
	                           (@RequestParam int    line,
								@RequestParam String column)
	{
		CellType type = board.fire(column, line);
				
		if (board.hasShip())
		{
			ModelAndView mv = new ModelAndView("/castabattle.jsp");
			mv.addObject("target",type);
			mv.addObject("board",board);
			return mv;
		}
		else
		{
			ModelAndView mv = new ModelAndView("/endgame.jsp");
			return mv;
		}
	}
}











	
	
	
	
	
	