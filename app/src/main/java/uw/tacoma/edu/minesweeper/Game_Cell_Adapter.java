package uw.tacoma.edu.minesweeper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author Sopheaktra Danh
 * @version 1
 * Game Cell that creates the 'Cell' Buttons used in Game_Board_Activity
 */
public class Game_Cell_Adapter extends BaseAdapter{

    private final Context mContext;
    private final String[] Game_Cells;

    /**
     * Public Constructor
     * @param context - the context which in this case is Game_Board_Activity.
     * @param Cells - the list of names/postions used to create the cells.
     */
    public Game_Cell_Adapter(Context context, String[] Cells){
        this.mContext = context;
        this.Game_Cells = Cells;
    }

    /**
     *Returns the number of Game Cells for the given game.
     * @return an int - the number of Game Cells
     */
    @Override
    public int getCount() {
        return Game_Cells.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Overwrite of getView. Game_Cell_Adapter's getView returns a button assigned to a specific
     * name/position. The button also gets the behavior to disappear when clicked.
     * @param position - the position of the Cell.
     * @param convertView
     * @param parent
     * @return button - a button assigned to the specific name/position that will disappear when clicked.
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Button button = new Button(mContext);
        button.setText(Game_Cells[position]);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, button.getText(), Toast.LENGTH_SHORT).show();
                button.setVisibility(View.GONE);
            }
        });
        return button;

    }
}
