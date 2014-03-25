package fr.ups.l3info.l3info_catchgameactivity;

import java.util.ArrayList;

import fr.ups.l3info.l3info_catchgametemplate.R;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TwoLineListItem;


public class CatchGameScoreActivity extends Activity {
	
	private ListView liste;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		
	/*	// Structure to save scores.
		SharedPreferences prefs = this.getSharedPreferences("scores", Context.MODE_PRIVATE);
		
		ArrayList<String> values = new ArrayList<>();
		int i = 1;
		for (Integer score : prefs.getAll().values()) {
			values.add("#"+i+" - "+score);
			i++;
		}
		//int lastScore = prefs.getInt(prefs.getAll().size()-1, 0);
		//int bestScore = prefs.getAll().;
		//liste.set
		
		/*String[] values = new String[] { "#1 - 125", 
                "#2 - 118",
                "#3 - 105",
                "#4 - 97", 
                "#5 - 74", 
                "#6 - 52", 
                "#7 - 15", 
                "#8 - 14" 
               };*/
		
		liste = (ListView)findViewById(R.id.listView1);
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, values);


	    // Assign adapter to ListView
		//liste.setAdapter(adapter); 
	}

	@Override
	protected void onDestroy() {
		super.onDestroy(); 
	}

	@Override
	protected void onPause() {
		super.onPause(); 
	}

	@Override
	protected void onResume() {
		super.onResume(); 
	}
	@Override
	protected void onStart() {
		super.onStart(); 
	}

	@Override
	protected void onStop() {
		super.onStop(); 
	} 

}
