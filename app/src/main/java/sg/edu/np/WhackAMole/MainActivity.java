package sg.edu.np.WhackAMole;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public int tScore=0;
    private Button buttonLeft;
    private Button buttonMiddle;
    private Button buttonRight;
    private static final String TAG = "TripleButton";



    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The function doCheck() also decides if the user qualifies for the advance level and triggers for a dialog box to ask for user to decide.
        - The function nextLevelQuery() builds the dialog box and shows. It also triggers the nextLevel() if user selects Yes or return to normal state if user select No.
        - The function nextLevel() launches the new advanced page.
        - Feel free to modify the function to suit your program.
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLeft=(Button)findViewById(R.id.Button1) ;
        buttonMiddle=(Button)findViewById(R.id.Button2) ;
        buttonRight=(Button)findViewById(R.id.Button3) ;

        Log.v(TAG, "Finished Pre-Initialisation!");


    }
    @Override
    protected void onStart(){
        super.onStart();
        setNewMole();
        Log.v(TAG, "Starting GUI!");
        buttonLeft.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.v(TAG, "ButtonLeft click,validating");
                doCheck(buttonLeft);
            }
        });

        buttonMiddle.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.v(TAG, "ButtonMiddle click,validating");
                doCheck(buttonMiddle);
            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.v(TAG, "ButtonRight click,validate");
                doCheck(buttonRight);
            }
        });

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAG, "Paused Whack-A-Mole!");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAG, "Stopped Whack-A-Mole!");
        finish();
    }



    private void nextLevelQuery() {
        /*
        Builds dialog box here.
        Log.v(TAG, "User accepts!");
        Log.v(TAG, "User decline!");
        Log.v(TAG, "Advance option given to user!");
        belongs here*/
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Would you like to play advanced whack a mole?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG,"User accepts");
                nextLevel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG,"User reject");
            }
        });
        AlertDialog alert=builder.create();
        alert.setTitle("Enter advanced whack a mole");
        alert.show();
    }


    private void nextLevel() {
        /* Launch advanced page */
        Intent nextPage=new Intent(MainActivity.this,Main2Activity.class);
        nextPage.putExtra("Score",tScore);
        Log.v(TAG,"Sending score");
        startActivity(nextPage);

    }

    private void setNewMole() {
        Random rand = new Random();
        int randomNum = rand.nextInt(3);
        if (randomNum == 0) {
            buttonLeft.setText("*");
            buttonMiddle.setText("O");
            buttonRight.setText("O");
        } else if (randomNum == 1) {
            buttonLeft.setText("O");
            buttonMiddle.setText("*");
            buttonRight.setText("O");
        } else {
            buttonLeft.setText("O");
            buttonMiddle.setText("O");
            buttonRight.setText("*");
        }
    }
    private void doCheck(Button bCheck) {
        String select=bCheck.getText().toString();
        if(select == "*"){
            Log.v(TAG, "Score add");
            tScore += 1;
            setNewMole();

            if (tScore %10==0){
                nextLevelQuery();
            }
        }
        else{
            if (tScore >0){
                tScore -= 1;
            }
            Log.v(TAG, "Minus score");
            setNewMole();
        }
    }

}