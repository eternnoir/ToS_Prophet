package com.tos_prophet;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class TosJsonParser {

	public TosJsonParser() {

	}

	public ArrayList<levleData> getLevelData(String str) {
		str = checkFormat(str);
		ArrayList<levleData> ret = new ArrayList<levleData>();
		try {
			JSONArray jsonArray = new JSONArray(str);
			Log.i("JsonParser",
					"Number of entries " + jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {
				levleData ld = new levleData(i+1);
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				JSONArray emeArray = jsonObject.getJSONArray("enemies");
				for(int j =0;j<emeArray.length();j++){
					JSONObject emeOb = emeArray.getJSONObject(j);
					int mid = emeOb.getInt("monsterId");
					String name = IdList.findNameById(mid);
					String tloot = emeOb.getString("lootItem");
					String loot = "null";
					if(tloot.equals("null")){
						loot = "null";
					}else{
						JSONObject lootob = emeOb.getJSONObject("lootItem");
						if(lootob.getString("type").equals("money")){
							loot = "Money +" + lootob.getString("amount");
						}
						else if(lootob.getString("type").equals("monster")){
							loot = "Card";
						}
					}
					enemiesData ed = new enemiesData(mid, i,name, loot);
					ld.addEnemies(ed);
				}
				ret.add(ld);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	public String checkFormat(String str){
		int offset=0;
		for(int i=0;i<str.length();i++){
			if(str.charAt(i)=='['){
				offset = i;
				break;
			}
		}
		
		str = str.substring(offset);
		return str;
	}
}
