package com.tos_prophet;

import java.util.ArrayList;

public class levleData {
	private int _level ;
	private ArrayList<enemiesData> _emeList;
	public levleData(int level){
		_level = level; 
		_emeList = new ArrayList<enemiesData>();
	}
	
	public int getLevel(){
		return _level;
	}
	
	public void addEnemies(enemiesData em){
		_emeList.add(em);
	}
	public ArrayList<enemiesData> getEnemiesList(){
		return _emeList;
	}
}
