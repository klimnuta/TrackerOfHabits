package com.example.tracker1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class habitDone extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_open);

        final Button doneBtn = findViewById(R.id.done);
        final TextView tb= findViewById(R.id.textBox);
        final TextView tb1= findViewById(R.id.textBox1);
        int position = getIntent().getIntExtra("ItemPosition", 0);

        final Habit habitElement = MainActivity.Habits.get(position);
        int status = habitElement.getStatus();

        tb.setText(habitElement.getName());
        if (status == 0)
        {
//
            doneBtn.setText(R.string.Done);
            tb1.setText(R.string.nohabit);

        } else{
//
            doneBtn.setText(R.string.notDone);
            tb1.setText("");
        }

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(habitElement.getStatus() == 0)
                {

                    habitElement.setStatus(1);
                    doneBtn.setText(R.string.notDone);
                    tb1.setText("");
                } else
                {
                    habitElement.setStatus(0);
                    doneBtn.setText(R.string.Done);
                    tb1.setText(R.string.nohabit);
                }

            }
        });

    }
}
