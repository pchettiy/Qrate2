package sangam.project.qrate;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SubmissionsActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submissions);
        listView = (ListView)findViewById(R.id.listView);
    }
    public class AsyncGetSubmissionsTask extends AsyncTask<Void,Void,Void> {
        ArrayList<String> submissionTitle;
        ArrayList<String> url;
        ArrayList<String> rating;
        ArrayList<String> track;
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            SubmissionsAdapter adapter = new SubmissionsAdapter(SubmissionsActivity.this,R.layout.adapter_submissions,submissionTitle,url,rating,track);
            listView.setAdapter(adapter);
        }
    }
}
