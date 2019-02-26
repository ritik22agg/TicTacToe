package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int count = 0;
    int board[] = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    public int horizontal_check(){
        // horizontal check
        for(int i = 0;i<9;i+=3){
            if(board[i] == board[i+1] && board[i+1] == board[i+2]){
                if(board[i] != -1) {
                    return board[i];
                }
            }
        }
        return -1;
    }

    public int diagonal_check(){
        if((board[0] == board[4] && board[4] == board[8]) || (board[2] == board[4] && board[4] == board[6])){
            if(board[4] != -1){
                return board[4];
            }
        }
        return -1;
    }

    public int vertical_check(){
        for(int i = 0;i<3;i++){
            if(board[i] == board[i+3] && board[i+3] == board[i+6]){
                if(board[i] != -1) {
                    return board[i];
                }
            }
        }
        return -1;
    }

    public int get_status(){
        int one = horizontal_check();
        int two = vertical_check();
        int three = diagonal_check();
        return (Math.max(one, Math.max(two, three)));
    }

    public void play_again(View view){
        num_turn = 0;
        for(int i = 0;i<9;i++){
            board[i] = -1;
        }
        count = 0;
        LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
        layout.setVisibility(View.INVISIBLE);

        GridLayout grid_Layout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0;i<9;i++){
            ImageView img =(ImageView) findViewById(R.id.gridLayout).findViewWithTag("" + i);
            img.setImageResource(0);
        }
      //  Toast.makeText(MainActivity.this, "" + grid_Layout.getChildCount(), Toast.LENGTH_SHORT).show();
    }
    int num_turn = 0;
    public void drop(View view){
        ImageView counter = (ImageView) view;
        int fo = Integer.parseInt(counter.getTag().toString());
        if(board[fo] == -1 && num_turn < 9) {
            num_turn++;
            counter.setTranslationY(-1000f);
            if (count == 0) {
                counter.setImageResource(R.drawable.cross);
                board[fo] = 0;
                count = 1;
            } else {
                counter.setImageResource(R.drawable.circle);
                board[fo] = 1;
                count = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);
            int stat = get_status();
            if(stat == 1 || stat == 0){
                TextView text = (TextView) findViewById(R.id.winnermessage);
                text.setText("player" + (stat + 1) + "Win");
                LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
                layout.setVisibility(View.VISIBLE);
            }


            //Toast.makeText(MainActivity.this, "" + stat, Toast.LENGTH_SHORT).show();
        }
        if(num_turn == 9 && get_status() == -1){
            TextView text = (TextView) findViewById(R.id.winnermessage);
            text.setText("Match Drawn");
            LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
            layout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
