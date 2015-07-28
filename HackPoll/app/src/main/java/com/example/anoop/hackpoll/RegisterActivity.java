package com.example.anoop.hackpoll;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;


public class RegisterActivity extends AppCompatActivity {

    Spinner branch;
    String branchName;
    String name,uName,password;
    TextView nameText,uNameText,passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Context context=this;

        branch=(Spinner)findViewById(R.id.branch);
        ArrayAdapter adapter=ArrayAdapter.createFromResource(this, R.array.branch, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch.setAdapter(adapter);

        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position)!=parent.getItemAtPosition(0))
                    branchName=(String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
         nameText=(TextView)findViewById(R.id.author_name);
         uNameText=(TextView)findViewById(R.id.user_name);
         passwordText=(TextView)findViewById(R.id.password);

        Button registerButton=(Button)findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=(String)nameText.getText().toString();
                uName=(String)uNameText.getText().toString();
                password=(String)passwordText.getText().toString();

                RequestParams params=new RequestParams();
                params.add("name",name);
                params.add("username",uName);
                params.add("password",password);

                AsyncHttpClient client=new AsyncHttpClient();
                client.put("http://103.227.97.85/login.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        SharedPreferences preferences=getSharedPreferences("loogedIn",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putBoolean("loggedIn", true);
                        editor.commit();

                        SharedPreferences preferences1=getSharedPreferences("authorDetails",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1=preferences1.edit();
                        editor1.putString("userName",uName);
                        editor1.commit();

                        Intent intent=new Intent(context,MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        Toast.makeText(context,"Registration Failed",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(context,MainActivity.class);
                        startActivity(intent);

                    }
                });
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
}
