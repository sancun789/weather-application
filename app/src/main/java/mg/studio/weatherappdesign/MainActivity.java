package mg.studio.weatherappdesign;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }


    public void btnClick(View view) {
        new DownloadUpdate().execute();
        AlertDialog.Builder builder = new Builder( this );
        builder.setIcon( R.drawable.button_shape_pressed );
        builder.setTitle( "Update Successful" );
        builder.setPositiveButton( "OK", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        } );
        AlertDialog b = builder.create();
        b.show();
    }


    private class DownloadUpdate extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            String stringUrl = "http://t.weather.sojson.com/api/weather/city/101040100?tdsourcetag=s_pcqq_aiomsg";
            HttpURLConnection urlConnection = null;
            BufferedReader reader;

            try {
                URL url = new URL( stringUrl );

                // Create the request to get the information from the server, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod( "GET" );
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader( new InputStreamReader( inputStream ) );

                String line;
                while ((line = reader.readLine()) != null) {
                    // Mainly needed for debugging
                    buffer.append( line + "\n" );
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                //The temperature
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String buffer) {
            //Update the temperature displayed
            String temperature, date,location;

            temperature = buffer.substring( buffer.indexOf( "wendu" ) + 8, buffer.indexOf( "ganmao" ) - 3 );
            ((TextView) findViewById( R.id.temperature_of_the_day )).setText( temperature );

            date = buffer.substring( buffer.indexOf( "time" ) + 7, buffer.indexOf( "cityInfo" ) - 11 );
            ((TextView) findViewById( R.id.tv_date )).setText( date );

            location = buffer.substring( buffer.indexOf( "cityInfo" ) + 19, buffer.indexOf( "cityId" ) - 3 );
            location=location.replace( "重庆市","ChongQingShi" );
            ((TextView) findViewById( R.id.tv_location )).setText( location );






            String date1, date2, date3, date4, today_week, weather;
            ImageView Ims = (ImageView) findViewById(R.id.weather0);
            weather="多";


            {

                int i;
                int con = 0;
                for (i = 1; i < 3; i++) {
                    con = buffer.indexOf( "week", con + 6 );
                }
                today_week = buffer.substring( con + 7, con + 10 );
                today_week=today_week.replace( "星期一","Mon" );
                today_week=today_week.replace( "星期二","Tue" );
                today_week=today_week.replace( "星期三","Wed" );
                today_week=today_week.replace( "星期四","Thu" );
                today_week=today_week.replace( "星期五","Fri" );
                today_week=today_week.replace( "星期六","Sat" );
                today_week=today_week.replace( "星期日","Sun" );
                ((TextView) findViewById( R.id.week_of_today )).setText( today_week );

                con = 0;
                for (i = 1; i < 3; i++) {
                    con = buffer.indexOf( "type", con + 6 );
                    weather=buffer.substring( con + 7, con + 8 );
                }

                if(weather.equals("晴")) {
                    Ims.setBackgroundResource(R.drawable.sunny_small);
                }
                if(weather.equals("小")) {
                    Ims.setBackgroundResource(R.drawable.rainy_small);
                }
                if(weather.equals("多")) {
                    Ims.setBackgroundResource(R.drawable.partly_sunny_small);
                }
                if(weather.equals("阴")) {
                    Ims.setBackgroundResource(R.drawable.windy_small);
                }
            }





            {
                int i;
                int con = 0;
                for (i = 1; i < 4; i++) {
                    con = buffer.indexOf( "week", con + 6 );
                }
                date1 = buffer.substring( con + 7, con + 10 );
                date1=date1.replace( "星期一","Mon" );
                date1=date1.replace( "星期二","Tue" );
                date1=date1.replace( "星期三","Wed" );
                date1=date1.replace( "星期四","Thu" );
                date1=date1.replace( "星期五","Fri" );
                date1=date1.replace( "星期六","Sat" );
                date1=date1.replace( "星期天","Sun" );
                ((TextView) findViewById( R.id.tomorrow1 )).setText( date1 );


                con = 0;
                Ims = (ImageView) findViewById(R.id.weather1);
                for (i = 1; i < 4; i++) {
                    con = buffer.indexOf( "type", con + 6 );
                    weather=buffer.substring( con + 7, con + 8 );
                }

                if(weather.equals("晴")) {
                    Ims.setBackgroundResource(R.drawable.sunny_small);
                }
                if(weather.equals("小")) {
                    Ims.setBackgroundResource(R.drawable.rainy_small);
                }
                if(weather.equals("多")) {
                    Ims.setBackgroundResource(R.drawable.partly_sunny_small);
                }
                if(weather.equals("阴")) {
                    Ims.setBackgroundResource(R.drawable.windy_small);
                }

            }

            {
                int i;
                int con = 0;
                for (i = 1; i < 5; i++) {
                    con = buffer.indexOf( "week", con + 6 );
                }
                date2 = buffer.substring( con + 7, con + 10 );
                date2=date2.replace( "星期一","Mon" );
                date2=date2.replace( "星期二","Tue" );
                date2=date2.replace( "星期三","Wed" );
                date2=date2.replace( "星期四","Thu" );
                date2=date2.replace( "星期五","Fri" );
                date2=date2.replace( "星期六","Sat" );
                date2=date2.replace( "星期天","Sun" );
                ((TextView) findViewById( R.id.tomorrow2 )).setText( date2 );

                con = 0;
                Ims = (ImageView) findViewById(R.id.weather2);
                for (i = 1; i < 5; i++) {
                    con = buffer.indexOf( "type", con + 6 );
                    weather=buffer.substring( con + 7, con + 8 );
                }

                if(weather.equals("晴")) {
                    Ims.setBackgroundResource(R.drawable.sunny_small);
                }
                if(weather.equals("小")) {
                    Ims.setBackgroundResource(R.drawable.rainy_small);
                }
                if(weather.equals("多")) {
                    Ims.setBackgroundResource(R.drawable.partly_sunny_small);
                }
                if(weather.equals("阴")) {
                    Ims.setBackgroundResource(R.drawable.windy_small);
                }
            }
            {
                int i;
                int con = 0;
                for (i = 1; i < 6; i++) {
                    con = buffer.indexOf( "week", con + 6 );
                }
                date3 = buffer.substring( con + 7, con + 10 );
                date3=date3.replace( "星期一","Mon" );
                date3=date3.replace( "星期二","Tue" );
                date3=date3.replace( "星期三","Wed" );
                date3=date3.replace( "星期四","Thu" );
                date3=date3.replace( "星期五","Fri" );
                date3=date3.replace( "星期六","Sat" );
                date3=date3.replace( "星期天","Sun" );
                ((TextView) findViewById( R.id.tomorrow3 )).setText( date3 );

                con = 0;
                Ims = (ImageView) findViewById(R.id.weather3);
                for (i = 1; i < 6; i++) {
                    con = buffer.indexOf( "type", con + 6 );
                    weather=buffer.substring( con + 7, con + 8 );
                }

                if(weather.equals("晴")) {
                    Ims.setBackgroundResource(R.drawable.sunny_small);
                }
                if(weather.equals("小")) {
                    Ims.setBackgroundResource(R.drawable.rainy_small);
                }
                if(weather.equals("多")) {
                    Ims.setBackgroundResource(R.drawable.partly_sunny_small);
                }
                if(weather.equals("阴")) {
                    Ims.setBackgroundResource(R.drawable.windy_small);
                }
            }
            {
                int i;
                int con = 0;
                for (i = 1; i < 7; i++) {
                    con = buffer.indexOf( "week", con + 6 );
                }
                date4 = buffer.substring( con + 7, con + 10 );
                date4=date4.replace( "星期一","Mon" );
                date4=date4.replace( "星期二","Tue" );
                date4=date4.replace( "星期三","Wed" );
                date4=date4.replace( "星期四","Thu" );
                date4=date4.replace( "星期五","Fri" );
                date4=date4.replace( "星期六","Sat" );
                date4=date4.replace( "星期天","Sun" );
                ((TextView) findViewById( R.id.tomorrow4 )).setText( date4 );

                con = 0;
                Ims = (ImageView) findViewById(R.id.weather4);
                for (i = 1; i < 7; i++) {
                    con = buffer.indexOf( "type", con + 6 );
                    weather=buffer.substring( con + 7, con + 8 );
                }

                if(weather.equals("晴")) {
                    Ims.setBackgroundResource(R.drawable.sunny_small);
                }
                if(weather.equals("小")) {
                    Ims.setBackgroundResource(R.drawable.rainy_small);
                }
                if(weather.equals("多")) {
                    Ims.setBackgroundResource(R.drawable.partly_sunny_small);
                }
                if(weather.equals("阴")) {
                    Ims.setBackgroundResource(R.drawable.windy_small);
                }
            }


        }
    }
}
