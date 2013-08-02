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
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private final String xmlid = "MH_CACHE_RUNTIME_DATA_CURRENT_FLOOR_WAVES";
	private Resources resources;
	private String idFlieStr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(null);
		setContentView(R.layout.activity_main);
		setUpInfo();
	}

	@Override
	public void onStart() {
		super.onStart();
		super.onCreate(null);
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
		ServiceCenter _sc = new ServiceCenter();
		IdList.addList(loadIdFile());
		ArrayList<String> result = _sc.getDisplayStringData();
		if (result == null) {
			Builder MyAlertDialog = new AlertDialog.Builder(this);
			MyAlertDialog.setTitle("Error");
			MyAlertDialog.setMessage("Are You Root?");
			MyAlertDialog.show();
		} else {
			LinearLayout linearLayout = (LinearLayout) findViewById(R.id.itemLayout);
			for (String str : result) {
				TextView valueTV = new TextView(this);
				valueTV.setText(str);
				linearLayout.addView(valueTV);
			}
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

}
