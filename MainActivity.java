package com.zybooks.yatzeejava;

import android.annotation.SuppressLint;
import android.app.Presentation;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView lockImage1, lockImage2, lockImage3, lockImage4,lockImage5;
    Button threeKindButton, fourKindButton, fullHouseButton, smallStraightButton, largeStraightButton, chanceButton,yahtzeeButton;
    Button hold1, hold2, hold3, hold4, hold5;
    Button enterButton, rollButton, newGameButton;
    Button aceButton, twoButton, threeButton, fourButton, fiveButton, sixButton;
    TextView singleScoreView, totalScoreTextView, rollView, messageTextView;
    TextView die1, die2, die3, die4, die5;

    int lastPressed = 0;
    int totalRolls = 0;
    int amountDisabled = 0;

    //hold variables
    boolean h1 = false;
    boolean h2 = false;
    boolean h3 = false;
    boolean h4 = false;
    boolean h5 = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //lock images linking to the gui
        lockImage1 = findViewById(R.id.lockImage1);
        lockImage2 = findViewById(R.id.lockImage2);
        lockImage3 = findViewById(R.id.lockImage3);
        lockImage4 = findViewById(R.id.lockImage4);
        lockImage5 = findViewById(R.id.lockImage5);

        lockImage1.setVisibility(View.INVISIBLE);
        lockImage2.setVisibility(View.INVISIBLE);
        lockImage3.setVisibility(View.INVISIBLE);
        lockImage4.setVisibility(View.INVISIBLE);
        lockImage5.setVisibility(View.INVISIBLE);

        //dice images linking to the gui
        die1 = findViewById(R.id.die1);
        die2 = findViewById(R.id.die2);
        die3 = findViewById(R.id.die3);
        die4 = findViewById(R.id.die4);
        die5 = findViewById(R.id.die5);

        //regular number set buttons linking to gui
        aceButton = findViewById(R.id.aceButton);
        twoButton = findViewById(R.id.twoButton);
        threeButton = findViewById(R.id.threeButton);
        fourButton = findViewById(R.id.fourButton);
        fiveButton = findViewById(R.id.fiveButton);
        sixButton = findViewById(R.id.sixButton);

        //sequence buttons linking to the gui
        threeKindButton = findViewById(R.id.threeKindButton);
        fourKindButton = findViewById(R.id.fourKindButton);
        fullHouseButton = findViewById(R.id.fullHouseButton);
        smallStraightButton = findViewById(R.id.smallStraightButton);
        largeStraightButton = findViewById(R.id.largeStraightButton);
        chanceButton = findViewById(R.id.chanceButton);
        yahtzeeButton = findViewById(R.id.yahtzeeButton);

        //hold buttons linking to the gui
        hold1 = findViewById(R.id.hold1);
        hold2 = findViewById(R.id.hold2);
        hold3 = findViewById(R.id.hold3);
        hold4 = findViewById(R.id.hold4);
        hold5 = findViewById(R.id.hold5);

        //game play buttons linking to gui
        enterButton = findViewById(R.id.enterButton);
        enterButton.setEnabled(false);
        rollButton = findViewById(R.id.rollButton);
        newGameButton = findViewById(R.id.newGameButton);

        //linking the text view to the gui
        singleScoreView = findViewById(R.id.singleScoreView);
        totalScoreTextView = findViewById(R.id.totalScoreTextViewy);
        rollView = findViewById(R.id.rollView);
        messageTextView = findViewById(R.id.messageTextView);


        //runs when the enter button is pressed
        enterButton.setOnClickListener(
                new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View view) {
                        if(lastPressed != 0){
                            hold1.setEnabled(false);
                            hold2.setEnabled(false);
                            hold3.setEnabled(false);
                            hold4.setEnabled(false);
                            hold5.setEnabled(false);
                            enterButton.setEnabled(false);
                            rollButton.setEnabled(false);
                            resetHolds();
                            switch (lastPressed) {
                                case 1:
                                    aceButton.setEnabled(false);
                                    amountDisabled++;
                                    break;
                                case 2:
                                    twoButton.setEnabled(false);
                                    amountDisabled++;
                                    break;
                                case 3:
                                    threeButton.setEnabled(false);
                                    amountDisabled++;
                                    break;
                                case 4:
                                    fourButton.setEnabled(false);
                                    amountDisabled++;
                                    break;
                                case 5:
                                    fiveButton.setEnabled(false);
                                    amountDisabled++;
                                    break;
                                case 6:
                                    sixButton.setEnabled(false);
                                    amountDisabled++;
                                    break;
                                case 7:
                                    threeKindButton.setEnabled(false);
                                    amountDisabled++;
                                    break;
                                case 8:
                                    fourKindButton.setEnabled(false);
                                    amountDisabled++;
                                    break;
                                case 9:
                                    fullHouseButton.setEnabled(false);
                                    amountDisabled++;
                                    break;
                                case 10:
                                    smallStraightButton.setEnabled(false);
                                    amountDisabled++;
                                    break;
                                case 11:
                                    largeStraightButton.setEnabled(false);
                                    amountDisabled++;
                                    break;
                                case 12:
                                    chanceButton.setEnabled(false);
                                    amountDisabled++;
                                    break;
                                case 13:
                                    yahtzeeButton.setEnabled(false);
                                    amountDisabled++;
                                    break;
                            }
                            lastPressed = 0;
                            int totalScore = Integer.parseInt(totalScoreTextView.getText().toString());
                            totalScore += Integer.parseInt(singleScoreView.getText().toString());
                            totalScoreTextView.setText(Integer.toString(totalScore));
                            singleScoreView.setText("0");
                            totalRolls = 0;
                            rollView.setText("0");
                            if(amountDisabled >= 100){
                                messageTextView.setText("Game Finished");
                                rollButton.setEnabled(false);
                            } else {
                                rollButton.setEnabled(true);
                            }
                        }
                    }
                }
        );

        newGameButton.setOnClickListener(
                new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View view) {
                        lastPressed = 0;
                        totalRolls = 0;
                        amountDisabled = 0;

                        resetHolds();

                        hold1.setEnabled(false);
                        hold2.setEnabled(false);
                        hold3.setEnabled(false);
                        hold4.setEnabled(false);
                        hold5.setEnabled(false);

                        enterButton.setEnabled(false);
                        rollButton.setEnabled(true);

                        aceButton.setEnabled(false);
                        twoButton.setEnabled(false);
                        threeButton.setEnabled(false);
                        fourButton.setEnabled(false);
                        fiveButton.setEnabled(false);
                        sixButton.setEnabled(false);
                        threeKindButton.setEnabled(false);
                        fourKindButton.setEnabled(false);
                        fullHouseButton.setEnabled(false);
                        smallStraightButton.setEnabled(false);
                        largeStraightButton.setEnabled(false);
                        chanceButton.setEnabled(false);
                        yahtzeeButton.setEnabled(false);

                        resetHolds();

                        singleScoreView.setText("0");
                        totalScoreTextView.setText("0");
                        rollView.setText("0");

                        messageTextView.setText("");
                    }
                }
        );

        rollButton.setOnClickListener(
                new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View view) {
                        hold1.setEnabled(true);
                        hold2.setEnabled(true);
                        hold3.setEnabled(true);
                        hold4.setEnabled(true);
                        hold5.setEnabled(true);
                        enterButton.setEnabled(true);
                        aceButton.setEnabled(true);
                        twoButton.setEnabled(true);
                        threeButton.setEnabled(true);
                        fourButton.setEnabled(true);
                        fiveButton.setEnabled(true);
                        sixButton.setEnabled(true);
                        threeKindButton.setEnabled(true);
                        fourKindButton.setEnabled(true);
                        fullHouseButton.setEnabled(true);
                        smallStraightButton.setEnabled(true);
                        largeStraightButton.setEnabled(true);
                        chanceButton.setEnabled(true);
                        yahtzeeButton.setEnabled(true);

                        singleScoreView.setText("0");

                        lastPressed = 0;

                        int rand = new Random().nextInt(6)+1;

                        if(!h1){
                            die1.setText(Integer.toString(rand));
                            rand = new Random().nextInt(6)+1;
                        }
                        if(!h2){
                            die2.setText(Integer.toString(rand));
                            rand = new Random().nextInt(6)+1;
                        }
                        if(!h3){
                            die3.setText(Integer.toString(rand));
                            rand = new Random().nextInt(6)+1;
                        }
                        if(!h4){
                            die4.setText(Integer.toString(rand));
                            rand = new Random().nextInt(6)+1;
                        }
                        if(!h5){
                            die5.setText(Integer.toString(rand));
                            totalRolls++;
                        }
                        if(totalRolls > 3){
                            rollButton.setEnabled(false);
                        } else {
                            rollView.setText(Integer.toString(totalRolls));
                        }

                    }
                }
        );

        hold1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                h1 = !h1;
                if(h1){
                    lockImage1.setVisibility(View.VISIBLE);
                }
                else{
                    lockImage1.setVisibility(View.INVISIBLE);
                }
            }
        });

        hold2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                h2 = !h2;
                if(h2){
                    lockImage2.setVisibility(View.VISIBLE);
                }
                else{
                    lockImage2.setVisibility(View.INVISIBLE);
                }
            }
        });

        hold3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                h3 = !h3;
                if(h3){
                    lockImage3.setVisibility(View.VISIBLE);
                }
                else{
                    lockImage3.setVisibility(View.INVISIBLE);
                }
            }
        });

        hold4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                h4 = !h4;
                if(h4){
                    lockImage4.setVisibility(View.VISIBLE);
                }
                else{
                    lockImage4.setVisibility(View.INVISIBLE);
                }
            }
        });

        hold5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                h5 = !h5;
                if(h5){
                    lockImage5.setVisibility(View.VISIBLE);
                }
                else{
                    lockImage5.setVisibility(View.INVISIBLE);
                }
            }
        });

        aceButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                calculateNumber(1);
            }
        });

        twoButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                calculateNumber(2);
            }
        });

        threeButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                calculateNumber(3);
            }
        });

        fourButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                calculateNumber(4);
            }
        });

        fiveButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                calculateNumber(5);
            }
        });

        sixButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                calculateNumber(6);
            }
        });

        threeKindButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                lastPressed = 7;
                int x = 0;
                for(int i = 0; i <= 6; i++ ){
                    x = 0;
                    if(die1.getText() == Integer.toString(i)){
                        x+=1;
                    }
                    if(die2.getText() == Integer.toString(i)){
                        x+=1;
                    }
                    if(die3.getText() == Integer.toString(i)){
                        x+=1;
                    }
                    if(die4.getText() == Integer.toString(i)){
                        x+=1;
                    }
                    if(die5.getText() == Integer.toString(i)){
                        x+=1;
                    }
                    if(x>=3){
                        totalDice();
                        break;
                    }
                    if(i == 6){
                        singleScoreView.setText("0");
                    }
                }

            }
        });

        fourKindButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                lastPressed = 7;
                int x = 0;
                for(int i = 0; i <= 6; i++ ){
                    x = 0;
                    if(die1.getText() == Integer.toString(i)){
                        x+=1;
                    }
                    if(die2.getText() == Integer.toString(i)){
                        x+=1;
                    }
                    if(die3.getText() == Integer.toString(i)){
                        x+=1;
                    }
                    if(die4.getText() == Integer.toString(i)){
                        x+=1;
                    }
                    if(die5.getText() == Integer.toString(i)){
                        x+=1;
                    }
                    if(x>=4){
                        totalDice();
                        break;
                    }
                    if(i == 6){
                        singleScoreView.setText("0");
                    }
                }
            }
        });

        fullHouseButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                lastPressed = 9;
                CharSequence x = die1.getText();
                CharSequence y = die1.getText();

                if(die2.getText() != die1.getText()){
                    y = die2.getText();
                }
                else if(die3.getText() != die1.getText()){
                    y = die3.getText();
                }
                else if (die4.getText() != die1.getText()){
                    y = die4.getText();
                }

                if(x != y){
                    int f1 = 0;
                    int f2 = 0;

                    if(die1.getText() == x){
                        f1 +=1;
                    } else if(die1.getText() == y){
                        f2 += 1;
                    }

                    if(die2.getText() == x){
                        f1 += 1;
                    } else if(die2.getText() == y){
                        f2 += 1;
                    }

                    if(die3.getText() == x){
                        f1 += 1;
                    }else if(die3.getText() == y){
                        f2 += 1;
                    }

                    if(die4.getText() == x){
                        f1 += 1;
                    } else if(die4.getText() == y){
                        f2 += 1;
                    }

                    if(die5.getText() == x){
                        f1 += 1;
                    }else if(die5.getText() == y){
                        f2 += 1;
                    }
                    if((f1 == 3 && f2 ==2) || (f1 == 2 && f2 == 3)){
                        singleScoreView.setText("25");
                    } else{
                        singleScoreView.setText("0");
                    }
                }else{
                    singleScoreView.setText("0");
                }
            }
        });

        smallStraightButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                lastPressed = 10;
                Integer[] sortedDice = new Integer[] {
                        Integer.parseInt(die1.getText().toString()),
                        Integer.parseInt(die2.getText().toString()),
                        Integer.parseInt(die3.getText().toString()),
                        Integer.parseInt(die4.getText().toString()),
                        Integer.parseInt(die5.getText().toString())
                };

                Arrays.sort(sortedDice);

                int length = 1;

                for(int i = 0; i<=3; i++){
                    if((sortedDice[i]+1)==sortedDice[i+1]){
                        length++;
                    }
                    else if(sortedDice[i] != sortedDice[i+1]){
                        length =1;
                    }
                    if(length == 4){
                        singleScoreView.setText("30");
                        break;
                    }
                    if(i == 3){
                        singleScoreView.setText("0");
                    }
                }
            }
        });

        largeStraightButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                lastPressed = 11;

                Integer[] sortedDice = new Integer[] {
                        Integer.parseInt(die1.getText().toString()),
                        Integer.parseInt(die2.getText().toString()),
                        Integer.parseInt(die3.getText().toString()),
                        Integer.parseInt(die4.getText().toString()),
                        Integer.parseInt(die5.getText().toString())
                };
                Arrays.sort(sortedDice);

                int length = 1;

                for(int i = 0; i<=3; i++){
                    if((sortedDice[i]+1)==sortedDice[i+1]){
                        length++;
                    } else if(sortedDice[i] != sortedDice[i+1]){
                        length =1;
                    }
                    if(length == 5){
                        singleScoreView.setText("40");
                        break;
                    }
                    if(i == 3){
                        singleScoreView.setText("0");
                    }
                }
            }
        });

        chanceButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                lastPressed = 12;
                totalDice();
            }
        });

        yahtzeeButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                lastPressed = 13;
                String[] dice = new String[] {(die1.getText().toString()), (die2.getText().toString()), (die3.getText().toString()), (die4.getText().toString()),(die5.getText().toString())};
                String die1 = dice[0];
                boolean fail = false;

                for(int i = 0; i <= 3; i++){
                    if(dice[i] != die1){
                        fail = true;
                    }
                }

                if(fail){
                    singleScoreView.setText("0");
                }
                else{
                    singleScoreView.setText("50");
                }
            }
        });
    }


    //calculating the score for the regular number section
    @SuppressLint("SetTextI18n")
    private void calculateNumber(Integer num){
        lastPressed = num;

        Integer[] dice = new Integer[] {
                Integer.parseInt(die1.getText().toString()),
                Integer.parseInt(die2.getText().toString()),
                Integer.parseInt(die3.getText().toString()),
                Integer.parseInt(die4.getText().toString()),
                Integer.parseInt(die5.getText().toString())
        };
        int total = 0;

        for(int i = 0; i < dice.length; i++){
            if(dice[i] == num){
                total += num;
            }
        }
        singleScoreView.setText(Integer.toString(total));
    }

    //gets the total values of all the "dice" number textview
    @SuppressLint("SetTextI18n")
    private void totalDice(){
        int total = 0;
        total += Integer.parseInt(die1.getText().toString());
        total += Integer.parseInt(die2.getText().toString());
        total += Integer.parseInt(die3.getText().toString());
        total += Integer.parseInt(die4.getText().toString());
        total += Integer.parseInt(die5.getText().toString());
        singleScoreView.setText(Integer.toString(total));
    }

    private void resetHolds(){
        h1 = false;
        h2 = false;
        h3 = false;
        h4 = false;
        h5 = false;

        lockImage1.setVisibility(View.INVISIBLE);
        lockImage2.setVisibility(View.INVISIBLE);
        lockImage3.setVisibility(View.INVISIBLE);
        lockImage4.setVisibility(View.INVISIBLE);
        lockImage5.setVisibility(View.INVISIBLE);
    }


}
