package com.example.adar_s1712623_mpd;
/**Student: Adrianna Dar
 Student ID: S1712623
 Date: 18/08/2020 Trimester C
 Mobile Platform Development Assignment Resit
 **/

import android.app.Activity;
import android.os.AsyncTask;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class LondonForecast3 extends AsyncTask {
    URL url;
    String title = "";
    String Desc = "";
    String Rez = "";
    ArrayList<String> result = new ArrayList<>();

    @Override
    protected Object doInBackground(Object[] objects) {
        //variables and pullparser



        try {
            url = new URL("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2643743");

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser pull = factory.newPullParser();
            pull.setInput(getInputStream(url), "UTF_8");//

            boolean initem = false;
            int eventType = pull.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    //searching the XML for specific tags and adding to ArrayList and Variables
                    if (pull.getName().equalsIgnoreCase("item")) {
                        initem = true;
                    } else if (pull.getName().equalsIgnoreCase("title")) {
                        if (initem)
                            title = (pull.nextText());
                    } else if (pull.getName().equalsIgnoreCase("description")) {
                        if (initem)
                            Desc = (pull.nextText());
                        Rez = title + "\n" + "\n" + Desc + "\n";
                        result.add(Rez);
                    }
                } else if (eventType == XmlPullParser.END_TAG && pull.getName().equalsIgnoreCase("item")) {
                    initem = false;
                }

                eventType = pull.next();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.set(0, "Weather Three Days Forecast for: London United Kingdom ");
        return result;

    }


    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

    public ArrayList<String> fetch()
    {
        //this is used to pass ArrayList to MainActivity

        return result;
    }

}


