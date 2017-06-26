package com.castaware.castabattle.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.castaware.castabattle.domain.HighScore;



@Repository
public class HighScoreDao extends _CrudDaoHibernate<HighScore> 
{
	@Autowired
	private static HibernateTemplate hibernateTemplate;
	
	@Override
	public Class<HighScore> getPersistentClass() 
	{
		return HighScore.class;
	}

	public static void saveNumJogadas(Integer nJogadas) {
		String jogador = "player";
		Integer pontos = nJogadas;
		
		HighScore score = new HighScore();
		score.setJogador("Player");
		score.setPontos(pontos);
		
		hibernateTemplate.saveOrUpdate(HighScore.class.getName(), score);
	}
	
	public static List getScores() {
		return hibernateTemplate.loadAll(HighScore.class);
	}
}
