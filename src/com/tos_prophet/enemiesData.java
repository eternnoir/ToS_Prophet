package com.tos_prophet;

public class enemiesData {
	private int _id;
	private String _name;
	private String _lootItem;
	
	public enemiesData(int id, String name, String lootItrm){
		_id = id;
		_name = name;
		_lootItem = lootItrm;
	}
	
	public int getId(){
		return _id;
	}
	public String getName(){
		return _name;
	}
	public String getLootItem(){
		return _lootItem;
	}
}
