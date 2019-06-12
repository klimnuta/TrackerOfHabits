package com.example.tracker1;


import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    public static List<Habit> Habits = new ArrayList<>();
    private habitAdapter adapter;


    SharedPreferences spref;

    final String Saved_text="saved text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits);
        Habits.clear();
        Habits.add(new Habit(1, getString(R.string.teeth) , R.drawable.teeth, 0));
        Habits.add(new Habit(1, getString(R.string.water) , R.drawable.water, 0));
        Habits.add(new Habit(1, getString(R.string.sport) , R.drawable.sport, 0));
        Habits.add(new Habit(1, getString(R.string.clock) , R.drawable.clock, 0));

        setInitialData();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

        // создаем адаптер
        adapter = new habitAdapter(this, Habits);

        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);




    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub


        switch (item.getItemId()) {
            case R.id.menu_new:

                // ДОБАВИТЬ КАРТОЧКУ

                Habits.add(new Habit(1, getString(R.string.new_s) , R.drawable.standart, 0));
                adapter.notifyDataSetChanged();
                return true;

            case R.id.menu_exit:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    protected void onDestroy(){
        super.onDestroy();
        saveText();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    void saveText() {
        Set<String> SaveList = new HashSet<>();
        for(int i= 4; i< Habits.size(); i++){
            Habit habit = Habits.get(i);
            SaveList.add(habit.getName());
        }
        spref=getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed=spref.edit();
        ed.putStringSet(Saved_text, SaveList);
        ed.commit();
    }


    private void setInitialData() {
        spref=getPreferences(MODE_PRIVATE);
        Set<String> ret = spref.getStringSet(Saved_text, new HashSet<String>());
        for(String r : ret) {
            Habits.add(new Habit(1, r, R.drawable.standart, 0));
        }
    }

}