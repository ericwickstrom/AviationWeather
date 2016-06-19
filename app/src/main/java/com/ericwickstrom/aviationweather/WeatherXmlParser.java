package com.ericwickstrom.aviationweather;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

/**
 * Created by beardsmcgee on 6/18/16.
 */
public class WeatherXmlParser {

    private static String testXml = "<response xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XML-Schema-instance\" version=\"1.2\" xsi:noNamespaceSchemaLocation=\"http://aviationweather.gov/adds/schema/metar1_2.xsd\">\n" +
            "<request_index>40550284</request_index>\n" +
            "<data_source name=\"metars\"/>\n" +
            "<request type=\"retrieve\"/>\n" +
            "<errors/>\n" +
            "<warnings/>\n" +
            "<time_taken_ms>4</time_taken_ms>\n" +
            "<data num_results=\"1\">\n" +
            "<METAR>\n" +
            "<raw_text>\n" +
            "KMSP 181953Z VRB03KT 10SM FEW080 SCT250 30/19 A3016 RMK AO2 SLP208 T03000189\n" +
            "</raw_text>\n" +
            "<station_id>KMSP</station_id>\n" +
            "<observation_time>2016-06-18T19:53:00Z</observation_time>\n" +
            "<latitude>44.88</latitude>\n" +
            "<longitude>-93.23</longitude>\n" +
            "<temp_c>30.0</temp_c>\n" +
            "<dewpoint_c>18.9</dewpoint_c>\n" +
            "<wind_dir_degrees>0</wind_dir_degrees>\n" +
            "<wind_speed_kt>3</wind_speed_kt>\n" +
            "<visibility_statute_mi>10.0</visibility_statute_mi>\n" +
            "<altim_in_hg>30.159449</altim_in_hg>\n" +
            "<sea_level_pressure_mb>1020.8</sea_level_pressure_mb>\n" +
            "<quality_control_flags>\n" +
            "<auto_station>TRUE</auto_station>\n" +
            "</quality_control_flags>\n" +
            "<sky_condition sky_cover=\"FEW\" cloud_base_ft_agl=\"8000\"/>\n" +
            "<sky_condition sky_cover=\"SCT\" cloud_base_ft_agl=\"25000\"/>\n" +
            "<flight_category>VFR</flight_category>\n" +
            "<metar_type>METAR</metar_type>\n" +
            "<elevation_m>265.0</elevation_m>\n" +
            "</METAR>\n" +
            "</data>\n" +
            "</response>";

    public static String parse(String xml) {

        String rString = "";
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xml));
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
            /*
                    if(eventType == XmlPullParser.START_DOCUMENT) {
                        Log.v("XML", "Start document");
                    } else if(eventType == XmlPullParser.END_DOCUMENT) {
                        Log.v("XML", "End document");
                    } else if(eventType == XmlPullParser.START_TAG) {
                        Log.v("XML", "Start tag "+xpp.getName());
                        if("raw_text".equals(xpp.getName())){
                            Log.v("METAR","************** METAR ************** ");
                            xpp.next();
                            Log.v("METAR", xpp.getText());
                        }
                    } else if(eventType == XmlPullParser.END_TAG) {
                        Log.v("XML", "End tag "+xpp.getName());
                    } else if(eventType == XmlPullParser.TEXT) {
                        Log.v("XML", "Text "+xpp.getText());
                    }

            */
                if (eventType == XmlPullParser.START_TAG) {
                    if ("raw_text".equals(xpp.getName())) {
                        xpp.next();
                        return xpp.getText();
                    }
                }


                eventType = xpp.next();
            }

        } catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
        return "derps";
    }

}
