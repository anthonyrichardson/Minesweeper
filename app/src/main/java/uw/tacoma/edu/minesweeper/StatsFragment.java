package uw.tacoma.edu.minesweeper;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends Fragment {




    private final static String STATS_URL = "http://cssgate.insttech.washington.edu/~danhs/login.php?";
    public static final String USERNAME_KEY = "USERNAME";

    // keys for the prefrences that store data globally
    public static final String GAMES_KEY = "GAMES";
    public static final String WON_KEY = "WON";
    public static final String LOST_KEY = "LOST";

    private TextView mGames;
    private TextView mWon;
    private TextView mLost;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private StatsListener mListener;

    public StatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_stats, container, false);

        mGames = (TextView) view.findViewById(R.id.games);
        mWon = (TextView) view.findViewById(R.id.won);
        mLost = (TextView) view.findViewById(R.id.lost);

        mGames.setText(getStatsPrefrences(GAMES_KEY));
        mWon.setText(getStatsPrefrences(WON_KEY));
        mLost.setText(getStatsPrefrences(LOST_KEY));






        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof StatsListener) {
            mListener = (StatsListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement StatsListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




    /**
     * Makes a url to get information from a server.
     * @param v the view.
     * @return The url.
     */
    private String buildStatsURL(View v) {

        StringBuilder sb = new StringBuilder(STATS_URL);

        try {

            String username = getPrefrences();
            sb.append("username=");
            sb.append(username);


            Log.i("StatsFragment", sb.toString());
        }
        catch(Exception e) {
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
        return sb.toString();
    }



    /**
     * Gets the username from the global Preferences.
     * @return The username.
     */
    public String getPrefrences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return preferences.getString(USERNAME_KEY, null);
    }


    public String getStatsPrefrences(String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        /*
        if(key ==  GAMES_KEY) {
            return preferences.getString(key, null);
        } else if(key == WON_KEY) {
            return preferences.getString(key, null);
        } else if(key == LOST_KEY) {
            return preferences.getString(key, null);
        } else {
            return "";
        }
        */
        return preferences.getString(key, null);
    }


    public interface StatsListener {
        // TODO: Update argument type and name
        public void stats(String uri);
    }
}
