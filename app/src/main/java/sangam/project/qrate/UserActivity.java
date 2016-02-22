package sangam.project.qrate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    ListView tracklistview;
    ArrayList<String> tracklist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tracklistview= (ListView) findViewById(R.id.listView);
        tracklist=new ArrayList<String>();
        tracklist.add("WebDev");
        tracklist.add("AppDev");
        tracklist.add("Python");
        getSupportActionBar().setTitle("Select Track");
        tracklistview.setAdapter(new trackAdapter(this,tracklist));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(UserActivity.this,UserSuggest.class);
                startActivity(intent);
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }
    public class trackAdapter extends ArrayAdapter{

        ArrayList<String> names;
        public trackAdapter(Context context, ArrayList<String> names) {
            super(context, R.layout.single_track_card,names);
            this.names=names;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=LayoutInflater.from(getContext());
            View v=inflater.inflate(R.layout.single_track_card,parent,false);
            TextView text= (TextView)v.findViewById(R.id.track_text);
            text.setText(names.get(position));
            ImageView image= (ImageView)v.findViewById(R.id.track_pic);
            String url="";
            if(position==0){
                url="http://brinddatechnologies.com/images/siteimg/web-dev.png";
            }
            else if(position==1){
                url="http://www.wired.com/images_blogs/business/2011/12/androids.jpg";
            }
            else
                url="https://realpython.com/learn/python-first-steps/images/pythonlogo.jpg";

            Picasso.with(getContext()).load(url).
                    into(image);
            RelativeLayout layout= (RelativeLayout) v.findViewById(R.id.track_layout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return v;
        }
    }

}
