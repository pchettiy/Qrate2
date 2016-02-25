package sangam.project.qrate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainTopics_user extends AppCompatActivity {


    ArrayList<classMainTopic> topicslist;
    String track;
    ListView list;
    int color;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        track=getIntent().getStringExtra("track");
        setContentView(R.layout.activity_main_topics_user);

        if(track.equals("WebDev")){
            color= Color.CYAN;
        }
        else if(track.equals("AppDev")){
            color= Color.GREEN;
        }
        else
            color=Color.YELLOW;
        topicslist=new ArrayList<classMainTopic>();
        list= (ListView) findViewById(R.id.listView2);
        new AsyncgetTopics().execute(track);


    }
    public class mainTopicsAdapter extends ArrayAdapter {

        ArrayList<classMainTopic> names;
        int color;
        public mainTopicsAdapter(Context context, ArrayList<classMainTopic> names, int color) {
            super(context, R.layout.single_track_card,names);
            this.names=names;
            this.color=color;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=LayoutInflater.from(getContext());
            View v=inflater.inflate(R.layout.single_track_card,parent,false);
            final TextView text= (TextView)v.findViewById(R.id.track_text);
            text.setText(names.get(position).title);
            ImageView image= (ImageView)v.findViewById(R.id.track_pic);
            String url=names.get(position).picurl;

            Picasso.with(getContext()).load(url).
                    into(image);
            RelativeLayout layout= (RelativeLayout) v.findViewById(R.id.track_layout);
            layout.setBackgroundColor(color);
            SharedPreferences sharedPreferences=getSharedPreferences("Colorprefs",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("color",color);
            editor.apply();
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getContext(),SubTopicActivity.class);
                    intent.putExtra("maintopic",topicslist.get(position).title);
                    intent.putExtra("track",track);
                    intent.putExtra("count",topicslist.size());
                    startActivity(intent);
                }
            });

            return v;
        }
    }
    public class AsyncgetTopics extends AsyncTask<String,Void,ArrayList<classMainTopic>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd= ProgressDialog.show(MainTopics_user.this,"Fetching data","Please wait..",true,false);
        }

        @Override
        protected ArrayList doInBackground(String... params ) {

            String result ="";
            ArrayList<classMainTopic> topiclist=new ArrayList<classMainTopic>();
            String downloadurl="https://spider.nitt.edu/~praba1110/qrate/tracks.php";
            //String downloadurl="http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=44db6a862fba0b067b1930da0d769e98";
            try{

                URL url=new URL(downloadurl);
                try{
                    HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
                    String postParameters="track="+params[0].toLowerCase();
                    Log.d("Track",params[0].toLowerCase());
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type",
                            "application/x-www-form-urlencoded");
                    urlConnection.setFixedLengthStreamingMode(
                            postParameters.getBytes().length);
                    PrintWriter out = new PrintWriter(urlConnection.getOutputStream());
                    out.print(postParameters);
                    out.close();

                    InputStream inputStream=urlConnection.getInputStream();

                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

                    String line="";
                    while((line=bufferedReader.readLine())!=null){
                        Log.d("line",line);
                        result+=line;
                    }

                    Log.d("DATA",result);
                    inputStream.close();
                    JSONArray details=new JSONArray(result);
                    Log.d("JSONArray",details.toString());



                    for(int i=0;i<details.length();i++) {
                        JSONObject single_item = details.getJSONObject(i);
                        classMainTopic topic=new classMainTopic();
                        topic.picurl=single_item.getString("url");
                        topic.title=single_item.getString("maintopic");
                        topiclist.add(topic);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return topiclist;
        }




        @Override
        protected void onPostExecute(ArrayList<classMainTopic> output) {
            super.onPostExecute(output);

            pd.dismiss();
            if(output==null){
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }

            topicslist=output;
            list.setAdapter(new mainTopicsAdapter(MainTopics_user.this,topicslist,color) );
        }


    }
}
