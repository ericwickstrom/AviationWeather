package com.ericwickstrom.aviationweather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private final String website = "https://aviationweather.gov/adds/dataserver_current/httpparam?dataSource=metars&requestType=retrieve&format=xml&stationString=KMSP&hoursBeforeNow=1";
    private TextView airportTextView;
    private TextView metarTextView;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FetchWeatherTask fwt = new FetchWeatherTask();
        fwt.execute();

        airportTextView = (TextView) findViewById(R.id.airport_textview);
        metarTextView = (TextView) findViewById(R.id.metar_textview);
        updateButton = (Button) findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchWeatherTask fwt = new FetchWeatherTask();
                fwt.execute();
            }
        });



    }

    private class FetchWeatherTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            InputStream is = null;
            String xml = "";
            BufferedReader reader = null;
            HttpURLConnection urlConnection = null;

            try {
                URL url = new URL(website);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                xml = buffer.toString();
                reader.close();

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            } finally {
                urlConnection.disconnect();

            }


            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {

            super.onPostExecute(xml);
            String metar = WeatherXmlParser.parse(xml);
            metarTextView.setText(metar);
        }
    }
}
