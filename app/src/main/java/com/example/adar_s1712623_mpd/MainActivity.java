package com.example.adar_s1712623_mpd;
/**Student: Adrianna Dar
 Student ID: S1712623
 Date: 18/08/2020 Trimester C
 Mobile Platform Development Assignment Resit
 **/

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ViewFlipper;
import java.util.ArrayList;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

   MenuItem menuItemHome;
    ViewFlipper viewFlipper;
    RadioButton radioGlasgow, radioLondon, radioMauritius, radioOman, radioBangladesh,radioNewYork;
    ListView GlasgowForecastListView, LondonForecastListView, MauritiusForecastListView, OmanForecastListView, BangladeshForecastListView, NewYorkForecastListView;
    String[] result;
    Integer[] imageId =
            {
                    R.drawable.cloud2,
                    R.drawable.dawn,
                    R.drawable.rain,
                    R.drawable.warm,
                    R.drawable.cold,
                    R.drawable.snowflake,
            };

    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;


    //* GCU Locations URLs

   /* private String urlSourceGlasgow = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2648579";
    private String urlSourceNewYork = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/5128581";
    private String urlSourceBangladesh = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/1185241";
    private String urlSourceMauritius = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/934154";
    private String urlSourceOman = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/287286";
    private String urlSourceLondon = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2643743";**/


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(  this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();



        //grabbing elements from View main_activity

        menuItemHome = (MenuItem) findViewById(R.id.nav_Home);
       viewFlipper = (ViewFlipper) findViewById(R.id.ViewFlipper01);
        GlasgowForecastListView = (ListView) findViewById(R.id.GlasgowList);
       LondonForecastListView = (ListView) findViewById(R.id.LondonList);
        MauritiusForecastListView = (ListView) findViewById(R.id.MauritiusList);
        OmanForecastListView=(ListView) findViewById(R.id.OmanList);
        BangladeshForecastListView=(ListView) findViewById(R.id.BangladeshList);
        NewYorkForecastListView=(ListView) findViewById(R.id.NewYorkList);

        radioGlasgow =(RadioButton)  findViewById(R.id.rGla);
        radioLondon =(RadioButton)  findViewById(R.id.rLdn);
        radioMauritius=(RadioButton) findViewById(R.id.rMau);
        radioOman=(RadioButton) findViewById(R.id.rOman);
        radioBangladesh=(RadioButton)  findViewById(R.id.rBan);
        radioNewYork=(RadioButton) findViewById(R.id.rNY);


        radioGlasgow.setOnClickListener(radio_listener);//Onclicker for both RadioButtons
        radioLondon.setOnClickListener(radio_listener);
        radioMauritius.setOnClickListener(radio_listener);
        radioOman.setOnClickListener(radio_listener);
        radioBangladesh.setOnClickListener(radio_listener);
        radioNewYork.setOnClickListener(radio_listener);

        ArrayList<String> GLA = new ArrayList<>(); //parsed data goes here
        GlasgowForecast3 RSSreadGLA = new GlasgowForecast3(); //redirect to the GlasgowForecast3 class
        RSSreadGLA.execute();
        GLA = RSSreadGLA.fetch();//Setting up new ArrayList to hold the Parsed XML from Glasgow Feed

        //source ref: https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView

        final ArrayAdapter Glasgow3Day = new ArrayAdapter(this, android.R.layout.simple_list_item_1, GLA);
        //simple TextView as the layout for each of the items.
        //adding the parsed information to an ArrayAdapter
        GlasgowForecastListView.setAdapter(Glasgow3Day);//set ListView to display data fetched


       ArrayList<String> LDN = new ArrayList<>();
        LondonForecast3 RSSreadLDN = new LondonForecast3();
        RSSreadLDN.execute();
        LDN = RSSreadLDN.fetch();


       final ArrayAdapter London3Days = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LDN);
        LondonForecastListView.setAdapter(London3Days);

     //  CustomList London3Days = new CustomList(MainActivity.this, result, imageId);
      // LondonForecastListView = (ListView) findViewById(R.id.LondonList);
     //  LondonForecastListView.setAdapter(adapter);

        ArrayList<String> MAU = new ArrayList<>();
        MauritiusForecast3 RSSreadMAU = new MauritiusForecast3(); //redirect to the GlasgowForecast3 class
        RSSreadMAU.execute();
        MAU = RSSreadMAU.fetch();

        //source ref: https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView

        final ArrayAdapter Mauritius3Day = new ArrayAdapter(this, android.R.layout.simple_list_item_1, MAU);
        //simple TextView as the layout for each of the items.
        //adding the parsed information to an ArrayAdapter
        MauritiusForecastListView.setAdapter(Mauritius3Day);


        ArrayList<String> OMAN = new ArrayList<>();
        OmanForecast3 RSSreadOMAN = new OmanForecast3(); //redirect to the GlasgowForecast3 class
        RSSreadOMAN.execute();
        OMAN = RSSreadOMAN.fetch();

        //source ref: https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView

        final ArrayAdapter Oman3Day = new ArrayAdapter(this, android.R.layout.simple_list_item_1, OMAN);
        //simple TextView as the layout for each of the items.
        //adding the parsed information to an ArrayAdapter
        OmanForecastListView.setAdapter(Oman3Day);


        ArrayList<String> BAN = new ArrayList<>();
        BangladeshForecast3 RSSreadBAN = new BangladeshForecast3();
        RSSreadBAN.execute();
        BAN = RSSreadBAN.fetch();
        //source ref: https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView

        final ArrayAdapter Bangladesh3Day = new ArrayAdapter(this, android.R.layout.simple_list_item_1, BAN);
        //simple TextView as the layout for each of the items.
        //adding the parsed information to an ArrayAdapter
        BangladeshForecastListView.setAdapter(Bangladesh3Day);


        ArrayList<String> NY = new ArrayList<>();
        NewYorkForecast3 RSSreadNY = new NewYorkForecast3();
        RSSreadNY.execute();
       NY = RSSreadNY.fetch();

        //source ref: https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView

        final ArrayAdapter NewYork3Day = new ArrayAdapter(this, android.R.layout.simple_list_item_1, NY);

        NewYorkForecastListView.setAdapter(NewYork3Day);


        GlasgowForecastListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//Setting Up onHolditem on the Current ListView to display a link to the Traffic Scotland Twitter Feed
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplication(), "Redirecting to BBC Glasgow Weather",
                        Toast.LENGTH_SHORT).show();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.bbc.co.uk/weather/2648579"));
                                          startActivity(browserIntent);

                    }
                }, 1000);

                ;
            }



        }

        );

    } //end of onCreate method

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch(menuItem.getItemId()){ //add more cases later

            case R.id.nav_Home:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

                break;

            case R.id.nav_Contact:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ContactFragment()).commit();

                break;

        }

return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){

            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }}
    public void setSupportActionBar(Toolbar toolbar) {
    }

private View.OnClickListener radio_listener = new View.OnClickListener() {// This allows for the View to be changed with the use of Radio Buttons
    public void onClick(View v) {  //Change Views by radio button clicks

        switch (v.getId()) {
        case R.id.rGla:
        viewFlipper.setDisplayedChild(0);
        break;

        case R.id.rLdn:
         viewFlipper.setDisplayedChild(1);
          break;

            case R.id.rMau:
                viewFlipper.setDisplayedChild(2);
                 break;

            case R.id.rOman:
                viewFlipper.setDisplayedChild(3);
                break;

            case R.id.rBan:
                viewFlipper.setDisplayedChild(4);
                break;

            case R.id.rNY:
                viewFlipper.setDisplayedChild(5);
                break;
        }

        };};}











