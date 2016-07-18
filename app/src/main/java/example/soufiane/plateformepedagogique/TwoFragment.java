package example.soufiane.plateformepedagogique;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * Created by Soufiane on 22/03/2016.
 */

public class TwoFragment extends Fragment {
    private Button button;
    ListView list;
    String[] web = {
            "imgvid1",
            "imgvid2",
            "imgvid3"

    } ;
    Integer[] imageId = {
            R.drawable.imgvid1,
            R.drawable.imgvid2,
            R.drawable.imgvid3
    };

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.bd_list, container, false);
        CustomList adapter = new CustomList(getActivity(), web, imageId);
        list=(ListView) view.findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(), "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(getActivity().getApplicationContext(), Video.class);
                Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity().getApplicationContext(), R.anim.move_right_in, R.anim.move_right_out).toBundle();
                startActivity(i2, bundle);
            }
        });

            return view ;
        }
    }

