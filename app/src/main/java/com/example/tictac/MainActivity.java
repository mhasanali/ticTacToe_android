package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    TextView tv2;
    public enum States{
        Cross ,
        Circle,
        Blank
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    protected Boolean gameLeft(){
        Boolean result=true;
        for (String val:gameState) {
            if(val==States.Blank.toString())
            {
               result = true;
            }
            else {
                result=false;
            }
        }
        return result;
    }
    String [] gameState={"Blank","Blank","Blank","Blank","Blank","Blank","Blank","Blank","Blank"};
    int [] [] winningState = {{0,1,2},{3,4,5},{6,7,8},
                              {0,3,6},{1,4,7},{2,5,8},
                              {0,4,8},{2,4,6}};
    int player=0;
    Boolean hasWon=false;
    public void imgTap(View view) {
        tv2=findViewById(R.id.textView2);

        ImageView img = (ImageView) view;
        int tag = Integer.parseInt(img.getTag().toString())-1;

//        for(String var:gameState){
//            Log.d("arr" ,var);
//        }
        if(!hasWon && gameLeft()) {

            switch (player) {

                case 0:
                    //For player 0 = Circle
                    if (gameState[tag] == States.Blank.toString()) {
                        //  Log.d("tag",gameState[tag]);
                        //for player 0
                        //  img.setTranslationX(-1000);
                        img.setImageResource(R.drawable.o);
                        gameState[tag] = States.Circle.toString();
                        //     img.animate().translationYBy(1000).setDuration(500);
                        player = 1;

                    }
                    break;
                case 1:
                    //For player 1 = Cross
                    if (gameState[tag] == States.Blank.toString()) {
                        //  Log.d("tag",gameState[tag]);
                        //for player 0
                        img.setImageResource(R.drawable.x);
                        gameState[tag] = States.Cross.toString();
                        player = 0;
                    }
                    break;
            }
            tv2.setText("Player : " + player +"'s turn!");

            for (int[] val : winningState) {
                if (gameState[val[0]] == gameState[val[1]] && gameState[val[1]] == gameState[val[2]] && gameState[val[0]] != States.Blank.toString()) {
                    hasWon = !hasWon;
                    if (gameState[val[0]] == States.Circle.toString()) {
                        player = 0;
                    } else {
                        player = 1;
                    }
                }
            }
            if (hasWon) {
              //  Toast.makeText(this, player + " has WON", Toast.LENGTH_SHORT).show();
                Snackbar bar=Snackbar.make(view,player+" has won!",5000);
                View sbView=bar.getView();
                sbView.setBackgroundColor(Color.RED);
                bar.show();
            }
            if (!gameLeft()) {
                Toast.makeText(this, "Game has tied, Click to reset", Toast.LENGTH_LONG).show();
            }

        }
        else{
            resetGame();
        }

    }
    protected  void resetGame(){
        hasWon=false;
        player=0;
        tv2.setText(R.string.status);
      //  ImageView img = (ImageView) view;
        for(int i=0; i< gameState.length; i++){
            gameState[i]=States.Blank.toString();

        }
        ((ImageView) findViewById(R.id.imgV0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imgV1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imgV2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imgV3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imgV4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imgV5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imgV6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imgV7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imgV8)).setImageResource(0);

    }
}
