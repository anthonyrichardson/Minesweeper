package uw.tacoma.edu.minesweeper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Game_Cell_Adapter extends BaseAdapter{

    private final Context mContext;
    private final String[] Game_Cells;

    public Game_Cell_Adapter(Context context, String[] Cells){
        this.mContext = context;
        this.Game_Cells = Cells;
    }
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
