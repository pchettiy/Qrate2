package sangam.project.qrate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rb on 23/2/16.
 */
public class UnreadAdapter extends ArrayAdapter {

    ArrayList<String> trackName;
    ArrayList<Integer> unreadCount;
    TextView textViewTrackName,textViewUnreadCount;
    public UnreadAdapter(Context context, int resource
            , ArrayList<String> trackName, ArrayList<Integer> unreadCount) {
        super(context, resource, trackName);
        this.trackName = trackName;
        this.unreadCount = unreadCount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.adapter_unread,parent,false);
        textViewTrackName = (TextView)view.findViewById(R.id.textview_track_name);
        textViewUnreadCount = (TextView)view.findViewById(R.id.textview_unread_count);
        textViewTrackName.setText(trackName.get(position));
        textViewUnreadCount.append(unreadCount.get(position)+"");
        return view;
    }
}
