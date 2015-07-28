package com.example.anoop.hackpoll;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;


public class LoginActivity extends AppCompatActivity {

    TextView uNameText,passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Context context=this;

        final SharedPreferences loggedIn=getSharedPreferences("loggedIn",Context.MODE_PRIVATE);
        boolean isLogged=loggedIn.getBoolean("loggedIn", false);

        if (isLogged) {  //Already Logged in
                   //Go directly to MainPage
            Intent intent=new Intent(context,MainActivity.class);
            startActivity(intent);
            finish();
        }


        uNameText=(TextView)findViewById(R.id.user_name);
        passwordText=(TextView)findViewById(R.id.password);

        Button signIn=(Button)findViewById(R.id.sign_in);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uName=uNameText.getText().toString();
                String password=passwordText.getText().toString();



                RequestParams params=new RequestParams();
                params.put("username",uName);
                params.put("password", password);

                AsyncHttpClient client=new AsyncHttpClient();
               client.post("http://0.0.0.0/login.php", params, new AsyncHttpResponseHandler() {
                   @Override
                   public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                       //If username and password is verefied
                       Log.d("Success", "Success");


                       SharedPreferences.Editor editor=loggedIn.edit();
                       editor.putBoolean("loggedIn",true); // Used to check whether user has already logged in or not
                       editor.commit();
                       Intent intent=new Intent(context,MainActivity.class);
                       startActivity(intent);
                   }

                   @Override
                   public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                       Toast.makeText(context,"Login Failed",Toast.LENGTH_LONG).show();
                       Intent intent=new Intent(context,MainActivity.class);
                       startActivity(intent);
                   }
               });


            }
        });

        Button register=(Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,RegisterActivity.class);
                startActivity(intent);
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
