package main;

import system.AbstractPlayer;
import system.TowerDefense;
import org.nisshiee.towerdefense.NisshieePlayer;

/**
 * メインクラス
 */
public class Main {

	
	public static void main(String[] args) {
		AbstractPlayer nisshiee = new NisshieePlayer("Nisshiee");
		
		TowerDefense towerDefense = new TowerDefense(nisshiee);
		//ゲーム開始
		towerDefense.startGame();

	}

}
