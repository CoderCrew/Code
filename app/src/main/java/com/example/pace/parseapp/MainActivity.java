package com.example.pace.parseapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    final String YOUR_FLURRY_API_KEY= "9PXJJ9KSSSK56FCZ589G";
    ListView mListView;
    List<Concert> concertList;
    Adapter mAdapter;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mListView = (ListView) findViewById(R.id.listView);
        concertList = new ArrayList<Concert>();
        logout = (Button) findViewById(R.id.btnLogOut);


        // Enable Local Datastore.
      //  Parse.enableLocalDatastore(this);
      //  Parse.initialize(this, "kGVQFW1Lwi8tHmNigtogAG7Hub961HE7nLwg0yUP", "nqi665Wb0KiAOWLRj0ZynlKhT8QQPWzxRkfsi8U6");
        getConcerts();

        //Parse.initialize(this, "jFA7FDsQEWeSY7JpHNZgiyi4Y0Th27xCV4ExNFxK", "dxscmXfWoYIMqDGMwaHShppzdKsQbK3krb3utvF3");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseAnalytics.trackAppOpenedInBackground(getIntent());


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOutInBackground();
                finish();

                }
            });

    }

    private void getConcerts() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Concerts");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> concertParseList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + concertParseList.size() + " scores");

                    for (ParseObject parseObject : concertParseList) {
                        String title = (String) parseObject.get("title");
                        String link = (String) parseObject.get("link");
                        String image_link = (String) parseObject.get("image_link");
                        Concert concert = new Concert(title, link, image_link);
                        concertList.add(concert);

                    }

                    mAdapter = new Adapter(MainActivity.this, (ArrayList<Concert>) concertList);
                    mListView.setAdapter(mAdapter);
                } else {
                    Log.d("concert", "Error: " + e.getMessage());
                }
            }
        });


    }





}
