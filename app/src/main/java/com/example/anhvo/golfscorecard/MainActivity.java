package com.example.anhvo.golfscorecard;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ListActivity {

    private static final String PREFS_FILE = "com.example.anhvo.golfscorecard";
    private static final String KEY_STOKECOUNT = "KEY_STOKECOUNT";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Hole[] mHoles = new Hole[18];
    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        int stroke = 0;
        for (int i =0; i < mHoles.length; i++){
            stroke = sharedPreferences.getInt(KEY_STOKECOUNT + i, 0);
            mHoles[i] =  new Hole("Hole " + (i +1) + ":", stroke);

        }

        mListAdapter = new ListAdapter(this, mHoles);
        setListAdapter(mListAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        for (int i =0; i < mHoles.length; i++){
            editor.putInt(KEY_STOKECOUNT + i, mHoles[i].getStokeCount() );
        }

        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear_strokes) {
            editor.clear();
            editor.apply();

            for (Hole hole:mHoles){
                hole.setStokeCount(0);
                mListAdapter.notifyDataSetChanged();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
