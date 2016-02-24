package sangam.project.qrate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rb on 23/2/16.
 */
public class SubmissionsAdapter extends ArrayAdapter {
    ArrayList<String> title;
    ArrayList<String> shorturl;
    ArrayList<String> rating;
    ArrayList<String> track;
    TextView textViewTitle,textViewShortUrl,textViewRating;
    RatingBar ratingBar;

    public SubmissionsAdapter(Context context, int resource
            , ArrayList<String> title, ArrayList<String> shorturl,ArrayList<String> rating, ArrayList<String> track) {
        super(context, resource, title);
        this.title = title;
        this.shorturl = shorturl;
        this.rating = rating;
        this.track = track;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.adapter_track,parent,false);
        textViewTitle = (TextView)view.findViewById(R.id.textview_title);
        textViewShortUrl = (TextView)view.findViewById(R.id.textview_shorturl);
        textViewRating = (TextView)view.findViewById(R.id.textview_rating);
        ratingBar = (RatingBar)view.findViewById(R.id.rating_bar);
        textViewTitle.setText(title.get(position));
        textViewShortUrl.setText(shorturl.get(position));
        textViewRating.setText(rating.get(position));
        ratingBar.setNumStars(Integer.parseInt(rating.get(position)));
        return view;
    }
}
