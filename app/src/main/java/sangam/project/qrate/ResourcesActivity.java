package sangam.project.qrate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ResourcesActivity extends AppCompatActivity {

    ListView list;
    String maintopic,subtopic,track;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        maintopic=getIntent().getStringExtra("maintopic");
        subtopic=getIntent().getStringExtra("subtopic");
        track=getIntent().getStringExtra("track");
        setContentView(R.layout.activity_resources);
        list= (ListView) findViewById(R.id.listView3);

    }
}
