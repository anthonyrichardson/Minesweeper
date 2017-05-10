package uw.tacoma.edu.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

/**
 * @author Sopheaktra Danh
 * @version 1
 * Game_Board Activity that holds the Minesweeper Game Itself.
 */
public class Game_Board_Activity extends AppCompatActivity {

    /**
     * The GridView used to represent the 5 column Minesweeper Gameboard with A rows.
     */
    GridView gridView;

    /**
     * An array used to name each 'Cell' Button int the game board.
     */
    static final String[] numbers = new String[] {
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    /**
     * Overwrite of OncCreate. Uses the Game_Cell_Adapter to populatre the gridview with
     * clickable buttons.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game__board_);

        gridView = (GridView) findViewById(R.id.game_board);

        Game_Cell_Adapter GCadapter = new Game_Cell_Adapter(this, numbers);

        gridView.setAdapter(GCadapter);

    }
}
