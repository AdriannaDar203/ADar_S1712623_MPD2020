package com.example.adar_s1712623_mpd;
/**Student: Adrianna Dar
 Student ID: S1712623
 Date: 18/08/2020 Trimester C
 Mobile Platform Development Assignment Resit
 **/

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class GlasgowForecast3 extends AsyncTask {

    URL url;

    String container = "";
    String title = "";
    String description = "";

    ArrayList<String> RSSheaders = new ArrayList();
    ArrayList<String> result = new ArrayList<>();

/** I did not managed to fix this methods to be working successfully


        Context context;

        ProgressDialog progressDialog = new ProgressDialog(GlasgowForecast3.this);

        //Information displayed while user waits for feed to be displayed

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            progressDialog.setMessage("Preparing data to display...");
            progressDialog.show();


        }**/

        @Override
    protected Object doInBackground(Object[] objects) {

        try {
            url = new URL("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2648579");

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser pull = factory.newPullParser();

            pull.setInput(getInputStream(url), "UTF_8");
            boolean inner = false;


            int eventType = pull.getEventType();

                   while (eventType != XmlPullParser.END_DOCUMENT) {

                       if (eventType == XmlPullParser.START_TAG) {

                    //filtering rss feed to look for appropriate tags to fetch into arrayList
                    if (pull.getName().equalsIgnoreCase("item")) {

                               inner = true;

                    } else if (pull.getName().equalsIgnoreCase("title")) {

                             if (inner) title = (pull.nextText());

                    } else if (pull.getName().equalsIgnoreCase("description")) {

                                if (inner) description = (pull.nextText());

                        container = title + "\n"+"\n" + description + "\n";

                        result.add(container); //add item_list instance
                    }

                } else if (eventType == XmlPullParser.END_TAG && pull.getName().equalsIgnoreCase("item")) {
                    inner = false;
                }

                eventType = pull.next();
            }

        } //end of try

        catch (MalformedURLException e) { e.printStackTrace();}
        catch (XmlPullParserException e) { e.printStackTrace();}
        catch (IOException e) { e.printStackTrace();}//testing

        result.set(0, "Weather Three Days Forecast for : Glasgow, Scotland.");//info to user
        return result; //stored in ArrayList

/** I did not managed to make it working so CustomList Class is not used within application

            @Override
            protected void onPostExecute(Boolean success) {

                               if (success) {

                     = new CustomList(MainActivity.this, 0, GlasgowForecastListView);
                    progressDialog.dismiss();

                                } else {
                    Toast.makeText(MainActivity.this,
                            "ERROR",

                                  Toast.LENGTH_LONG).show();

                }


            }**/

        } //end of AsyncTask method




    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        }   catch (IOException e) {
            return null;
        }
    }

    public ArrayList<String> fetch()
    {
        return result;
    } //send back data to main activity class for display in listview within Flipper View

} // end of GlasgowForecast3 class


