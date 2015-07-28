package com.example.anoop.hackpoll;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;


public class DescriptionActivity extends AppCompatActivity{

    int opinion=-1,numUpVotes,numDownVotes;
    TextView upVotes,downVotes;
    int selected=-1; //-1 if none is selected, 1 if upvote and 0 if downvote
    String uName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        SharedPreferences preferences=getSharedPreferences("authorDetails", MODE_PRIVATE);
        uName=preferences.getString("username","");

        Intent intent=this.getIntent();
        int qid=intent.getIntExtra("qid", -1);
        String qidStr= ""+qid;
        RequestParams params=new RequestParams("qid",qidStr);
        AsyncHttpClient client=new AsyncHttpClient();
        client.put("serverLink", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        final ImageButton upVote=(ImageButton)findViewById(R.id.upvote);
        final ImageButton downVote=(ImageButton)findViewById(R.id.downvote);

        upVotes=(TextView)findViewById(R.id.num_upVotes);
        downVotes=(TextView)findViewById(R.id.num_downVotes);

        upVotes.setText(""+numUpVotes);
        downVotes.setText(""+numDownVotes);

        if (opinion==0) {
            downVote.setImageResource(R.drawable.ic_action_bad_selected);
            selected=0;
        }
        if (opinion==1) {
            upVote.setImageResource(R.drawable.ic_action_good_selected);
            selected=1;
        }
        upVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upVote.setImageResource(R.drawable.ic_action_good_selected);
                downVote.setImageResource(R.drawable.ic_action_bad);
                if (selected == 0)
                    numDownVotes -= 1;
                if (selected!=1)
                    numUpVotes = numUpVotes + 1;
                selected = 1;
                upVotes.setText("" + numUpVotes);
                downVotes.setText("" + numDownVotes);
               // Log.d("hello",""+numUpVotes);
            }
        });

        downVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downVote.setImageResource(R.drawable.ic_action_bad_selected);
                upVote.setImageResource(R.drawable.ic_action_good);
                if (selected == 1)
                    numUpVotes-=1;
                if (selected!=0)
                    numDownVotes += 1;
                selected=0;
                upVotes.setText("" + numUpVotes);
                downVotes.setText("" + numDownVotes);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_description, menu);
        return true;
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        RequestParams params=new RequestParams();
        params.add("username",uName);
        switch (selected){
            case 0:params.add("selected",""+0);
                break;
            case 1:params.add("selected",""+1);
                break;
            default:params.add("selected","");
        }
        AsyncHttpClient client=new AsyncHttpClient();
        client.put("url", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
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
}
