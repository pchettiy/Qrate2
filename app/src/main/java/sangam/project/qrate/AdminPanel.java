package sangam.project.qrate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AdminPanel extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        listView = (ListView)findViewById(R.id.listview_tasks_unread);
        new AsyncGetTrackStatusTask().execute();
    }
    public class AsyncGetTrackStatusTask extends AsyncTask<String,Void,String> {
        ArrayList<String> trackName;
        ArrayList<Integer> unreadCount;
        @Override
        protected String doInBackground(String... params) {
            String result="";
            String url = "https://spider.nitt.edu/~praba1110/qrate/entries.php";
            try {
                URL URL=new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection) URL.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                InputStream inputStream = urlConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    Log.d("line", line);
                    result += line;
                }
                Log.d("DATA", result);
                inputStream.close();
            } catch(Exception e) {
                Log.d("EXCEPTION",e.toString());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            trackName = new ArrayList<>();
            unreadCount = new ArrayList<>();
            super.onPostExecute(result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                unreadCount.add(Integer.parseInt(jsonObject.getString("webdev")));
                unreadCount.add(Integer.parseInt(jsonObject.getString("appdev")));
                unreadCount.add(Integer.parseInt(jsonObject.getString("python")));
            }catch (Exception e) {
                Log.e("EXCEPTION",e+"");
            }
            trackName.add("webdev");
            trackName.add("appdev");
            trackName.add("python");
            UnreadAdapter adapter = new UnreadAdapter(AdminPanel.this,R.layout.adapter_unread,trackName,unreadCount);
            listView.setAdapter(adapter);
        }
    }
}
