package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int start = 1;
    public String[][] matrix = new String[4][4];
    String winner = null;
    String playerCross = "Player1";
    String playerZero = "Player2";

    private TextView p1;
    private TextView p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p1 = findViewById(R.id.player1);
        p2 = findViewById(R.id.player2);

        p1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(playerCross.trim() != "")
                    playerCross = p1.getText().toString();
            }
        });

        p2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(playerZero.trim() != "")
                    playerZero = p2.getText().toString();
            }
        });

    }



    public void playerClick(View view) {
        //String id = String.valueOf(view.getId());
        view.setVisibility(View.VISIBLE);
        String tag_id = view.getTag().toString();
        if(start%2 == 1){
            //first chance is of x
            view.setBackground(getDrawable(R.drawable.x));
            editMatrix("cross", tag_id);

        }
        else {
            view.setBackground(getDrawable(R.drawable.o));
            editMatrix("zero", tag_id);
        }

        if(start>4){
            checkWinner();
            if(winner != null)
            {
                if(winner == "cross") winner = playerCross;
                else if (winner == "zero") winner = playerZero;
                //Toast.makeText(MainActivity.this, winner, Toast.LENGTH_LONG);
                Intent intent = new Intent(MainActivity.this, result.class);
                intent.putExtra("winner", winner + " Wins !!!");
                startActivity(intent);
            }
        }


        
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //no action to be performed now
            }
        });
        
        start = start + 1;
        if(start==10)
        {
            checkWinner();
            if(winner==null) winner="Draw";
            else if(winner == "cross") winner = playerCross + " Wins !!!";
            else if (winner == "zero") winner = playerZero + " Wins !!!";

            Intent intent = new Intent(MainActivity.this, result.class);
            intent.putExtra("winner", winner);
            startActivity(intent);
        }


    }

    public void editMatrix(String value, String id) {
        //Log.d("b11", "editMatrix:"+matrix[1][1]);
        matrix[Integer.parseInt(id.substring(1,2))][Integer.parseInt(id.substring(2))] = value;
        //Log.d("b11", "editMatrix:"+matrix[1][1]);

    }

    public void checkWinner() {
        if(matrix[1][1]==matrix[1][2] && matrix[1][1]==matrix[1][3]){
            winner = matrix[1][1];
        }
        else if(matrix[2][1]==matrix[2][2] && matrix[2][1]==matrix[2][3]){
            winner = matrix[2][1];
        }
        else if(matrix[3][1]==matrix[3][2] && matrix[3][1]==matrix[3][3]){
            winner = matrix[3][1];
        }
        else if(matrix[1][1]==matrix[2][2] && matrix[1][1]==matrix[3][3]){
            winner = matrix[1][1];
        }
        else if(matrix[1][3]==matrix[2][2] && matrix[1][3]==matrix[3][1]){
            winner = matrix[1][3];
        }
        else if(matrix[1][1]==matrix[2][1] && matrix[1][1]==matrix[3][1]){
            winner = matrix[1][1];
        }
        else if(matrix[2][2]==matrix[1][2] && matrix[1][2]==matrix[3][2]){
            winner = matrix[1][2];
        }
        else if(matrix[1][3]==matrix[2][3] && matrix[1][3]==matrix[3][3]){
            winner = matrix[1][3];
        }
    }
}
