package com.castaware.castabattle.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.castaware.castabattle.dao.HighScoreDao;
import com.castaware.castabattle.domain.Board;
import com.castaware.castabattle.domain.CPUPlayer;
import com.castaware.castabattle.domain.CellType;

@Controller
@RequestMapping("/game") // .../pswebproj/spring/game
public class GameController
{	
	private final Integer bSize = 10;
	private Board eBoard;
	private Board pBoard;
	
	@Autowired
	private HighScoreDao hsDao;
	
	@RequestMapping("/start") // .../pswebproj/spring/game/start
	public ModelAndView start(HttpServletRequest request)
	{
		/*
		if( RequestContextHolder.getRequestAttributes().getAttribute("7_6", RequestContextHolder.getRequestAttributes().SCOPE_REQUEST) != null ) {
			CellType[][] playerBoard = new CellType[this.bSize][this.bSize];
			//TODO - ler entrada da tela 'createBoard' para criacao de board pelo usuario
		}*/
		ModelAndView mv = new ModelAndView("/castabattle.jsp");
		
		//Com a entrada do usuario na tela create board, cria-se um tabuleiro pro usuario com os dados fornecidos por ele
		Map<String, String[]> posicoes = request.getParameterMap();
		
		//instancia tabuleiros cpu e player
		pBoard = new Board();
		eBoard = new Board();
		
		Collection<Integer[]> coordenadasP = new ArrayList<Integer[]>();
		Collection<Integer[]> coordenadasC = new ArrayList<Integer[]>();
		
		for (Iterator it = posicoes.keySet().iterator(); it.hasNext();) {
			String posicao = (String) it.next();
			String[] coordenada = posicao.split("_");//(x,y)
			
			//0 eh o tabuleiro do player
			if (coordenada[0].equals("0") )
				coordenadasP.add(new Integer[]{Integer.valueOf(coordenada[1]),Integer.valueOf(coordenada[2]) });//faz cast para integer e coloca na colecao
			
			//1 eh o tabuleiro da CPU
			if (coordenada[0].equals("1"))
				coordenadasC.add(new Integer[]{Integer.valueOf(coordenada[1]),Integer.valueOf(coordenada[2]) });//faz cast para integer e coloca na colecao
		}
		if(coordenadasP.size() > 0)
			pBoard.initBoard(Board.getMadeBoard(coordenadasP));
		else //Caso n haja nenhum input do usuario inicia com o board template
			pBoard.initBoard(Board.getTemplateBoard());
		
		if(coordenadasC.size() > 0)
			eBoard.initBoard(Board.getMadeBoard(coordenadasC));
		else 
			eBoard.initBoard(Board.getTemplateBoard());
		

		mv.addObject("eBoard",eBoard);
		mv.addObject("pBoard",pBoard);
		
		return mv;
	}	
	
	@RequestMapping("/reset") // .../pswebproj/spring/game/reset
	public ModelAndView reset()
	{
		return start(null);
	}
	
	@RequestMapping("/fire") // .../pswebproj/spring/game/fire
	public ModelAndView helloRequestParam
	                           (HttpServletRequest request,
	                        	@RequestParam int    line,
								@RequestParam String column)
	{
		CellType typeE = eBoard.fire(column, line); //Player atira no board inimigo
		
		Integer [] a = CPUPlayer.makeMove(pBoard, 0);
		CellType typeP = pBoard.fire(Board.columnInt2Str(a[0]), a[1]);//CPU atira no board do player
				
		if (eBoard.hasShip() && pBoard.hasShip() )
		{
			ModelAndView mv = new ModelAndView("/castabattle.jsp");
			//atira no tabuleiro inimigo
			mv.addObject("target",typeE);
			mv.addObject("eBoard",eBoard);
			
			//Inimigo atira no tabuleiro do player
			mv.addObject("target",typeP);
			mv.addObject("pBoard",pBoard);
			
			//Envia uma mensagem para ser lida pelo player
			String mensagem = "O inimigo atirou em:" + (a[1]-1) + " - " + Board.columnInt2Str(a[0]);
			mv.addObject("mensagem",mensagem);
			
			//faz a contagem do numero de jogadas
			request.getSession().setAttribute("nJogadas", ((Integer)request.getSession().getAttribute("nJogadas")+1) );
			return mv;
		}
		else
		{
			//ModelAndView mv = new ModelAndView("/endgame.jsp");
			ModelAndView mv = new ModelAndView("/castabattle.jsp");
			Integer size = 10;
			mv.addObject("size",size);
			
			String mensagem = "GAME OVER!";
			if(!eBoard.hasShip())
				mensagem += " VOCÊ VENCEU!";
			else
				mensagem += " CPU VENCEU!";
			
			mv.addObject("mensagem",mensagem);
			
			//Salva o score (numero de jogadas)
			HighScoreDao.saveNumJogadas((Integer)request.getSession().getAttribute("nJogadas"));
			
			
			return mv;
		}
	}
	
	@RequestMapping("/createBoard")
	public ModelAndView createBoard(HttpServletRequest request) {
		request.getSession().setAttribute("nJogadas", 0);
		ModelAndView mv = new ModelAndView("/createBoard.jsp");
		mv.addObject("bSize",this.bSize);
		return mv;
	}
	
	@RequestMapping("/highscores")
	public ModelAndView highScores(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/highscores.jsp");
		List highscores = HighScoreDao.getScores();
		mv.addObject(highscores);
		return mv; 
	}
}











	
	
	
	
	
	