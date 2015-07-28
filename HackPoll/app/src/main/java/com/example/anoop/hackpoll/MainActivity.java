package com.example.anoop.hackpoll;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    String branch;
    RecyclerView.Adapter adapter;
    List<String> quesList,authorList,timeList;
    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log.d("Hello","Main");
        quesList =new ArrayList<String>();
        authorList=new ArrayList<String>();
        timeList=new ArrayList<String>();
        quesList.add("Question 1:Am i the most awesome person on earth");
        quesList.add("Qestion 2:Am i the most awesome person on earth");
        quesList.add("Qestion 3:Am i the most awesome person on earth");

        authorList.add("Anoop");
        authorList.add("Anoop");
        authorList.add("Anoop");

        timeList.add("10:00 12/12/12");
        timeList.add("10:00 12/12/12");
        timeList.add("10:00 12/12/12");

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter=new CardAdapter(quesList,authorList,timeList,this);
        recyclerView.setAdapter(adapter);

        final Context context=this;

       // Intent intent=new Intent(this,LoginActivity.class);
        //startActivity(intent);

        button=(FloatingActionButton)findViewById(R.id.ask_ques);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,AskQuestion.class);
                startActivity(intent);
            }
        });



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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(final View v) {
        int qid=(int)v.getTag();
        Intent intent=new Intent(this,DescriptionActivity.class);
        intent.putExtra("qid",qid);
        startActivity(intent);
    }
}
