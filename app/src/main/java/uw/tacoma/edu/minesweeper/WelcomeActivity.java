

package uw.tacoma.edu.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * The first screen for the game.
 *
 * @author Tony Richardson
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
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

/*    public void login(){

    }*/






}