

package uw.tacoma.edu.minesweeper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The first screen for the game.
 *
 * @author Tony Richardson
 */
public class WelcomeActivity extends AppCompatActivity implements StatsFragment.StatsListener {


    // keys for the prefrences that store data globally
    public static final String USERNAME_KEY = "USERNAME";
    public static final String GAMES_KEY = "GAMES";
    public static final String WON_KEY = "WON";
    public static final String LOST_KEY = "LOST";





    /**
     * Creates the Activity
     *
     * @param savedInstanceState For onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        Button leaderboardsButton = (Button) findViewById(R.id.leaderboards_button);
        leaderboardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StatsFragment statsFragment = new StatsFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, statsFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });




    }

    /**
     * Starts the minesweeper game board activity.
     *
     * @param view For starting an activity.
     */
    public void gotoGameBoard(View view) {
        Intent intent = new Intent(this, Game_Board_Activity.class);
        startActivity(intent);
    }


    /**
     * Starts the minesweeper game board activity.
     *
     * @param view For starting an activity.
     */
    public void gotoLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }






    /**
     * Gets the username from the global Preferences.
     * @return The username.
     */
    public String getPrefrences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString(USERNAME_KEY, null);
    }


    /**
     * Sets the statistics for the current account.
     * @param games Number of games played.
     * @param won Number of games won.
     * @param lost Number of games lost.
     */
    public void setStatsPrefrences(int games, int won, int lost) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(GAMES_KEY, games);
        editor.putInt(WON_KEY, won);
        editor.putInt(LOST_KEY, lost);
        editor.apply();

    }


    /**
     * Listener for the Leaderboards button. Starts the GetStatsTask async task.
     *
     * @param url url for the web server.
     */
    public void stats(String url) {
        Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG).show();
        //System.out.println(url);

        GetStatsTask getStatsTask = new GetStatsTask();
        getStatsTask.execute(new String[]{url.toString()});
        getSupportFragmentManager().popBackStackImmediate();
    }













    /**
     * Sends a query to the server. Gets the statistics for the current account.
     */
    private class GetStatsTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    // read in string representation of stats
                    // games, won, lost   three integers
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }





                } catch (Exception e) {
                    response = "Unable to get Stats, Reason: "
                            + e.getMessage();
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }

        /**
         * It checks to see if there was a problem with the URL(Network) which is when an
         * exception is caught. It tries to call the parse Method and checks to see if it was successful.
         * If not, it displays the exception.
         *
         * @param result
         */
        @Override
        protected void onPostExecute(String result) {
            // Something wrong with the network or the URL.
            try {
                JSONObject jsonObject = new JSONObject(result);

                int games = (int) jsonObject.get("games");
                int won = (int) jsonObject.get("won");
                int lost = (int) jsonObject.get("lost");

                setStatsPrefrences(games, won, lost);


            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Something wrong with the data" +
                        e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    //
}

