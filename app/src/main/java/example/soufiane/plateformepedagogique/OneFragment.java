package example.soufiane.plateformepedagogique;

/**
 * Created by Soufiane on 22/03/2016.
 */
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import xyz.hanks.library.SmallBang;


public class OneFragment extends Fragment {
    private ImageView wiki;
    private ImageView ressources;
    private ImageView climat;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        final SmallBang mSmallBang = SmallBang.attach2Window(getActivity());


        wiki = (ImageView) view.findViewById(R.id.imageView);
        ressources = (ImageView) view.findViewById(R.id.imageView4);
        climat = (ImageView) view.findViewById(R.id.imageView2);

        wiki.setImageResource(R.drawable.wiki3);
        ressources.setImageResource(R.drawable.ressources3);
        climat.setImageResource(R.drawable.climat3);

        wiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSmallBang.bang(view);
                Intent i2 = new Intent(getActivity().getApplicationContext(), Wiki.class);
                Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity().getApplicationContext(), R.anim.fade_in, R.anim.fade_out).toBundle();
                getActivity().startActivity(i2, bundle);
            }
        });

        climat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSmallBang.bang(view);
                Intent i2 = new Intent(getActivity().getApplicationContext(), BdList.class);
                Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity().getApplicationContext(), R.anim.fade_in, R.anim.fade_out).toBundle();
                getActivity().startActivity(i2, bundle);
            }
        });

        ressources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSmallBang.bang(view);
                Intent i2 = new Intent(getActivity().getApplicationContext(), RessourceNum.class);
                Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity().getApplicationContext(), R.anim.fade_in, R.anim.fade_out).toBundle();
                getActivity().startActivity(i2, bundle);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }




}