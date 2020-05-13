package sg.edu.np.WhackAMole;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    private static String TAG = "Whack a mole v2";
    private int advancedScore;
    CountDownTimer myCountDown;

    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 8.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The functions readTimer() and placeMoleTimer() are to inform the user X seconds before starting and loading new mole.
        - Feel free to modify the function to suit your program.
    */


    private void readyTimer() {
        /*  HINT:
            The "Get Ready" Timer.
            Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);
            Toast message -"Get Ready In X seconds"
            Log.v(TAG, "Ready CountDown Complete!");
            Toast message - "GO!"
            belongs here.
            This timer countdown from 10 seconds to 0 seconds and stops after "GO!" is shown.
         */
        myCountDown=new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);
                Toast.makeText(getApplicationContext(),"Your game starts in "+millisUntilFinished,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFinish() {
                Log.v(TAG,"Countdown completed");
                myCountDown.cancel();
                setNewMole();
                placeMoleTimer();

            }
        };
        myCountDown.start();
    }

    private void placeMoleTimer() {
        /* HINT:
           Creates new mole location each second.
           Log.v(TAG, "New Mole Location!");
           setNewMole();
           belongs here.
           This is an infinite countdown timer.
         */
        myCountDown=new CountDownTimer(1000,100) {
            @Override
            public void onTick(long l) {
                Log.v(TAG,"NEw mole location");
                setNewMole();

            }

            @Override
            public void onFinish() {
                placeMoleTimer();

            }
        };
        myCountDown.start();
    }
    private static final int[] BUTTON_IDS = {
            /* HINT:
                Stores the 9 buttons IDs here for those who wishes to use array to create all 9 buttons.
                You may use if you wish to change or remove to suit your codes.*/
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Hint:
            This starts the countdown timers one at a time and prepares the user.
            This also prepares the existing score brought over.
            It also prepares the button listeners to each button.
            You may wish to use the for loop to populate all 9 buttons with listeners.
         */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent welcomePage=getIntent();
        advancedScore=welcomePage.getIntExtra("Score",0);

        Log.v(TAG, "Current User Score: " + String.valueOf(advancedScore));


        for(final int id : BUTTON_IDS) {
            /*  HINT:
            This creates a for loop to populate all 9 buttons with listeners.
            You may use if you wish to remove or change to suit your codes.
            */
            for (int populate:BUTTON_IDS){
                final Button buttonids=(Button)findViewById(populate);
                buttonids.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        doCheck(buttonids);
                        setNewMole();
                    }
                });
            }
        };

    }
    @Override
    protected void onStart(){
        super.onStart();
        readyTimer();
    }
    private void doCheck(Button checkButton)
    {
        /* Hint:
            Checks for hit or miss
            Log.v(TAG, "Hit, score added!");
            Log.v(TAG, "Missed, point deducted!");
            belongs here.
        */
        String select=checkButton.getText().toString();
        if(select == "*"){
            Log.v(TAG, "Score add");
            advancedScore += 1;
            setNewMole();
        }
        else{
            if (advancedScore >0){
                advancedScore-= 1;
            }
            Log.v(TAG, "Minus score");
        }
    }

    public void setNewMole()
    {
        /* Hint:
            Clears the previous mole location and gets a new random location of the next mole location.
            Sets the new location of the mole.
         */
        Random ran = new Random();
        int randomLocation = ran.nextInt(9);
        int a=0;
        for (int count:BUTTON_IDS){
            Button butt=(Button)findViewById(count);
            if (randomLocation==a){
                butt.setText("*");
            }
            else{
                butt.setText("O");
            }
            a+=1;
        }

    }
}

