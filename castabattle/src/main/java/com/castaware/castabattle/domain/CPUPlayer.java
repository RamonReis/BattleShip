package com.castaware.castabattle.domain;

import java.util.Random;

public class CPUPlayer {
	
	/**
	 * Returns an array of size 2 containing the coordinates
	 * 
	 * @param tabuleiro contains the board that this CPUPlayer must play on.
	 * @param tipoJogada selects the strategy of the CPUPlayer.
	 * @return
	 */
	public static Integer[] makeMove(Board tabuleiro, Integer tipoJogada) {
		//Por enquanto ele apenas joga aleatoriamente
		return makeRandomMove(tabuleiro);
	}
	
	/**
	 * Returns an array of size 2 containing two Integers, on the first index 0-9 and on the second 1-10
	 * 
	 * @param tabuleiro
	 * @return
	 */
	private static Integer[] makeRandomMove(Board tabuleiro) {
		Random rand = new Random();
		Integer boardLimit = tabuleiro.getBoardGame().length -1; //-1 porque na funcao de random o 0 esta incluso
		Integer [] target = {
			rand.nextInt(boardLimit),
			rand.nextInt(boardLimit)+1//da forma que esta modelado uma linha deve ser um valor entre 1 e 10.
		};
		return target;
	}
	
	/**
	 * Por enquanto nao eh possivel determinar uma melhor estrategia, ja que nao ha informacoes a cerca dos tipos dos navios no tabuleiro de uma partida
	 * Este metodo implementaria uma selecao mais adequada do tile a ser bombardeado. 
	 * @param tabuleiro
	 * @return
	 */
	private static Board makeSmartMove(Board tabuleiro) {
		
		return null;
	}
}
