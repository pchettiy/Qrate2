package sangam.project.qrate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
import java.util.List;

public class SubTopicActivity extends AppCompatActivity {

    int size;
    int color;
    SharedPreferences sharedPreferences;
    TabLayout tabLayout;
    ViewPager viewPager;
    ProgressDialog pd;
    String track,maintopic;
    ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        track=getIntent().getStringExtra("track");
        maintopic=getIntent().getStringExtra("maintopic");
        setContentView(R.layout.activity_sub_topic);
        sharedPreferences=getSharedPreferences("Colorprefs", Context.MODE_PRIVATE);
        color=sharedPreferences.getInt("color", Color.GRAY);
        Log.d("color",String.valueOf(color));

        viewPager= (ViewPager) findViewById(R.id.viewpager);
        adapter=new ViewPagerAdapter(getSupportFragmentManager());
        new AsyncgetSubTopics().execute(track,maintopic);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {

            mFragmentList.add(fragment);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }
    }
    public class AsyncgetSubTopics extends AsyncTask<String,Void,ArrayList> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd= ProgressDialog.show(SubTopicActivity.this,"Fetching data","Please wait..",true,false);
        }

        @Override
        protected ArrayList doInBackground(String... params ) {

            String result ="";
            ArrayList<String> topiclist=new ArrayList<String>();
            String downloadurl="https://spider.nitt.edu/~praba1110/qrate/maintopics.php";
            //String downloadurl="http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=44db6a862fba0b067b1930da0d769e98";
            try{

                URL url=new URL(downloadurl);
                try{
                    HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
                    String postParameters="track="+params[0].toLowerCase()+"&maintopic="+params[1].toLowerCase();
                    Log.d("Track",params[0]);
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

                        result+=line;
                    }
                    Log.d("DATA",result);
                    inputStream.close();
                    JSONArray details=new JSONArray(result);
                    Log.d("JSONArray",details.toString());



                    for(int i=0;i<details.length();i++) {
                        topiclist.add(details.get(i).toString());

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
        protected void onPostExecute(ArrayList output) {
            super.onPostExecute(output);

            pd.dismiss();
            if(output==null){
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
            else {
                for (int i = 0; i < output.size(); i++) {
                    adapter.addFragment(Subtopic_Frag.newInstance(track, maintopic,output.get(i).toString(),color),"");
                }
                viewPager.setAdapter(adapter);
               //topicslist=output;
            }
        }


    }
}
