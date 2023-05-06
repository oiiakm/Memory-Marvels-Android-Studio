package com.example.memory_marvels;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private CardAdapter cardAdapter;
    private ArrayList<Card> cards;
    private Card firstCard;
    private Card secondCard;
    private int numberOfPairs;
    private TextView timerTextView;
    private TextView scoreTextView;
    private CountDownTimer countDownTimer;
    private int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);

        timerTextView = findViewById(R.id.timerTextView);
        numberOfPairs = 0;

        cards = new ArrayList<>();

        cards.add(new Card(R.drawable.hulk));
        cards.add(new Card(R.drawable.wanda));
        cards.add(new Card(R.drawable.thor));
        cards.add(new Card(R.drawable.thanos));
        cards.add(new Card(R.drawable.cyborg));
        cards.add(new Card(R.drawable.groot));
        cards.add(new Card(R.drawable.captainamerica));
        cards.add(new Card(R.drawable.drstrange));
        cards.add(new Card(R.drawable.scarlett));

        cards.add(new Card(R.drawable.hulk));
        cards.add(new Card(R.drawable.wanda));
        cards.add(new Card(R.drawable.thanos));
        cards.add(new Card(R.drawable.captainamerica));
        cards.add(new Card(R.drawable.cyborg));
        cards.add(new Card(R.drawable.thor));
        cards.add(new Card(R.drawable.drstrange));
        cards.add(new Card(R.drawable.scarlett));
        cards.add(new Card(R.drawable.groot));

        Collections.shuffle(cards);

        cardAdapter = new CardAdapter(this, cards);
        gridView.setAdapter(cardAdapter);

        scoreTextView = findViewById(R.id.scoreTextView);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (firstCard == null) {
                    firstCard = cards.get(position);
                    firstCard.setFlipped(true);
                    cardAdapter.notifyDataSetChanged();
                } else if (secondCard == null) {
                    secondCard = cards.get(position);
                    secondCard.setFlipped(true);
                    cardAdapter.notifyDataSetChanged();

                    if (firstCard.getImageId() == secondCard.getImageId()) {
                        numberOfPairs++;
                        score += 10;

                        scoreTextView.setText("Score: " + score);

                        if (numberOfPairs == cards.size() / 2) {
                            Toast.makeText(MainActivity.this, "You won!", Toast.LENGTH_SHORT).show();
                            countDownTimer.cancel();
                        }
                        firstCard = null;
                        secondCard = null;
                    } else {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                firstCard.setFlipped(false);
                                secondCard.setFlipped(false);
                                firstCard = null;
                                secondCard = null;
                                cardAdapter.notifyDataSetChanged();
                            }
                        }, 500);
                    }
                }
            }
        });

        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Time Left: " + (millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                intent.putExtra("score", numberOfPairs * 10);
                startActivity(intent);
                finish();
            }
        };
        countDownTimer.start();
    }
}
