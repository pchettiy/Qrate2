package sangam.project.qrate;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class EditSubmissionActivity extends AppCompatActivity {

    TextView textViewTrack;
    TextView textViewMainTopic;
    TextView textViewSubTopic;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_submission);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textViewTrack = (TextView) findViewById(R.id.textview_track);
        textViewMainTopic = (TextView) findViewById(R.id.textview_main_topic);
        textViewSubTopic = (TextView) findViewById(R.id.textview_sub_topic);
        ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
