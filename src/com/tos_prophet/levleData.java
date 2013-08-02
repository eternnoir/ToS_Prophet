package com.tos_prophet;

import java.util.ArrayList;

public class levleData {
	private String _level ;
	private ArrayList<enemiesData> _emeList;
	public levleData(String level){
		_level = level; 
		_emeList = new ArrayList<enemiesData>();
	}
	
	public void addEnemies(enemiesData em){
		_emeList.add(em);
	}
	public ArrayList<enemiesData> getEnemiesList(){
		return _emeList;
	}
}
