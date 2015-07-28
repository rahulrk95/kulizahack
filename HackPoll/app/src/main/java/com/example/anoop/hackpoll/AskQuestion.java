package com.example.anoop.hackpoll;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

/**
 * Created by anoop on 27/7/15.
 */
public class AskQuestion extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask_question);

        SharedPreferences preferences=getSharedPreferences("authorDetails", Context.MODE_PRIVATE);
        final String userName=preferences.getString("userName", "");

        EditText question=(EditText)findViewById(R.id.question);
        EditText description=(EditText)findViewById(R.id.description);

        final String ques=question.getText().toString();
        final String desc=description.getText().toString();

        final Context context=this;



        Button postButton=(Button)findViewById(R.id.post);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params=new RequestParams();
                params.add("question",ques);
                params.add("desc",desc);
                params.add("username",userName);

                AsyncHttpClient client=new AsyncHttpClient();
                client.put("server_link", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT);
                        finish();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(context,"Failure",Toast.LENGTH_SHORT);
                        finish();
                    }
                });
            }
        });
    }
}
