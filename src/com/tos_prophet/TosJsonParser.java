package com.tos_prophet;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class TosJsonParser {

	public TosJsonParser() {

	}

	public ArrayList<levleData> getItemList(String str) {
		str = checkFormat(str);
		ArrayList<levleData> ret = new ArrayList<levleData>();
		try {
			JSONArray jsonArray = new JSONArray(str);
			Log.i("JsonParser",
					"Number of entries " + jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				JSONArray emeArray = jsonObject.getJSONArray("enemies");
				
				
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
