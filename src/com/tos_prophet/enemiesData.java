package com.tos_prophet;

public class enemiesData {
	private int _id;
	private int _level;
	private String _name;
	private String _lootItem;
	private String _iconPath;
	
	public enemiesData(int id, int level, String name, String lootItrm){
		_id = id;
		_name = name;
		_lootItem = lootItrm;
		_level = level;
		_iconPath = String.format("%03d", id) +"i.png"; //001 002 003
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
	public int getLevel(){
		return _level;
	}
	public String getIconPath(){
		return _iconPath;
	}
}
