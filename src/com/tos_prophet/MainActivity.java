package com.tos_prophet;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
	private final String xmlid = "MH_CACHE_RUNTIME_DATA_CURRENT_FLOOR_WAVES";
	private Resources resources;
	private String idFlieStr;
	private ItemAdapter adapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(null);
		setTheme(android.R.style.Theme_Black);
		setContentView(R.layout.activity_main);
		setUpInfo();
	}

	@Override
	public void onStart() {
		super.onStart();
		super.onCreate(null);
		setTheme(android.R.style.Theme_Black);
		setContentView(R.layout.activity_main);
		setUpInfo();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void setUpInfo() {
		FileLoader.setContext(this);
		ServiceCenter _sc = new ServiceCenter();
		IdList.addList(loadIdFile());
		ArrayList<levleData> result_level = _sc.getLevelData();
		if (result_level == null) {
			Builder MyAlertDialog = new AlertDialog.Builder(this);
			MyAlertDialog.setTitle("Error");
			MyAlertDialog.setMessage("Are You Root?");
			MyAlertDialog.show();
		} else {
			// Retrive the ExpandableListView from the layout
			ExpandableListView listView = (ExpandableListView) findViewById(R.id.monstersListView);
			ArrayList<levleData> ldList = _sc.getLevelData();
			
			ArrayList<String> groups = new ArrayList<String>();
			ArrayList<ArrayList<enemiesData>> children = new ArrayList<ArrayList<enemiesData>>();
			for(levleData ld : ldList){
				groups.add("Level "+ld.getLevel()+"");
				ArrayList<enemiesData> emList = ld.getEnemiesList();
				children.add(emList);
			}
			adapter = new ItemAdapter(this, groups,
					children);



			// Initialize the adapter with blank groups and children
			// We will be adding children on a thread, and then update the
			// ListView
			/*
			adapter = new ItemAdapter(this, new ArrayList<String>(),
					new ArrayList<ArrayList<enemiesData>>());
			for(levleData ld : ldList){
				for(enemiesData ed : ld.getEnemiesList()){
					adapter.addItem(ed);
				}
			}
			*/
			// Set this blank adapter to the list view
			listView.setAdapter(adapter);
			this.autoExpendViewList();
			
		}

	}

	public String loadIdFile() {
		try {
			// Load the file from the raw folder - don't forget to OMIT the
			// extension
			idFlieStr = LoadFile("ToS_IDlist.txt", true);
			// output to LogCat
			// Log.i("test", idFlieStr);
		} catch (IOException e) {
			// display an error toast message
			Toast toast = Toast.makeText(this, "File: not found!",
					Toast.LENGTH_LONG);
			toast.show();
		}
		return idFlieStr;
	}

	public String LoadFile(String fileName, boolean loadFromRawFolder)
			throws IOException {
		// Create a InputStream to read the file into
		AssetManager am = this.getAssets();
		String result = null;
		InputStream inputStream = am.open("ToS_IDlist.txt");
		result = getStringFromInputStream(inputStream);
		result.replace("\r", "");
		return result;
	}

	private String getStringFromInputStream(InputStream is) throws IOException {

		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is,
						"UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {
			return "";
		}
	}
	
	private void autoExpendViewList(){
		ExpandableListView listView = (ExpandableListView) findViewById(R.id.monstersListView);
		int count = adapter.getGroupCount();
		for (int position = 1; position <= count; position++)
			listView.expandGroup(position - 1);
	}


}
