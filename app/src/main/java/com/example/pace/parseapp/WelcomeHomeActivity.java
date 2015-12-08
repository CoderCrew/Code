package com.example.pace.parseapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by pace on 2/11/15.
 */

public class WelcomeHomeActivity extends Activity{

    EditText userName;
    EditText password;
    Button login;
    Button signUp;
    Button fbLogin;
    String name;
    String pass;


    final String YOUR_FLURRY_API_KEY= "9PXJJ9KSSSK56FCZ589G";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        //Initializinf flurry analytics

      /**  FlurryAgent.init(this,YOUR_FLURRY_API_KEY);
        FlurryAgent.onStartSession(this, YOUR_FLURRY_API_KEY);
        FlurryAgent.getReleaseVersion();
        FlurryAgent.isSessionActive();
        FlurryAgent.onPageView();
        FlurryAgent.setVersionName("version mee");
*/
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "kGVQFW1Lwi8tHmNigtogAG7Hub961HE7nLwg0yUP", "nqi665Wb0KiAOWLRj0ZynlKhT8QQPWzxRkfsi8U6");
        userName = (EditText) findViewById(R.id.etName);
        password = (EditText) findViewById(R.id.etPassword);
        login = (Button) findViewById(R.id.btnLogin);
        signUp = (Button) findViewById(R.id.btnSignUp);
        fbLogin = (Button) findViewById(R.id.btnFBLogin);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                name = userName.getText().toString();
                pass = password.getText().toString();


                userName.setText("");
                password.setText("");

                if(name.equals("")|| pass.equals("")){

                    Toast.makeText(WelcomeHomeActivity.this, "Enter name and password ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ParseUser user = new ParseUser();
                    user.setUsername(name);
                    user.setPassword(pass);

                    user.signUpInBackground(new SignUpCallback() {

                        public void done(ParseException e) {
                            if (e == null) {
                                // Hooray! Let them use the app now.
                                Toast.makeText(WelcomeHomeActivity.this, "Successfull!!! Please Log In", Toast.LENGTH_SHORT).show();


                            } else {
                                // Sign up didn't succeed. Look at the ParseException
                                // to figure out what went wrong
                                Log.e("error", "Parse exception........." + e);
                                Toast.makeText(WelcomeHomeActivity.this, "Sign up error - username already taken", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = userName.getText().toString();
                pass = password.getText().toString();
                ParseUser.logInInBackground(name, pass, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            userName.setText("");
                            password.setText("");
                            Intent intent = new Intent(WelcomeHomeActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            Log.e("error", "Parse exception........." + e);
                            Toast.makeText(WelcomeHomeActivity.this, "Wrong Credentials"+e, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        fbLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    protected void onStop() {
        super.onStop();

    }
}
