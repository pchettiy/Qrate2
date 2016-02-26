package sangam.project.qrate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;



public class Subtopic_Frag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String track;
    private String maintopic;
    private String subtopic;
    public int color;
    SharedPreferences sharedPreferences;


    public Subtopic_Frag() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Subtopic_Frag newInstance(String track, String maintopic,String subtopic,int color) {
        Subtopic_Frag fragment = new Subtopic_Frag();
        Bundle args = new Bundle();
        args.putString("track", track);
        args.putString("maintopic", maintopic);
        args.putString("subtopic",subtopic);
        args.putInt("color",color);
        Log.d("colortest",String.valueOf(color));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            track = getArguments().getString("track");
            maintopic = getArguments().getString("maintopic");
            subtopic=getArguments().getString("subtopic");
            color=getArguments().getInt("color");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_subtopic_, container, false);

        TextView textView = (TextView) v.findViewById(R.id.card_text);
        textView.setText(subtopic);
        /*if(track.equalsIgnoreCase("webdev")){
            color= Color.CYAN;
        }*/
        LinearLayout cardlayout= (LinearLayout) v.findViewById(R.id.subtopic_layout);
        LinearLayout bglayout= (LinearLayout) v.findViewById(R.id.framelayout);
        Log.d("layoutcolor",String.valueOf(color));
        bglayout.setBackgroundColor(color);
        cardlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ResourcesActivity.class);
                intent.putExtra("track",track);
                intent.putExtra("subtopic",subtopic);
                intent.putExtra("maintopic",maintopic);
                startActivity(intent);
            }
        });
        return v;
    }




}
