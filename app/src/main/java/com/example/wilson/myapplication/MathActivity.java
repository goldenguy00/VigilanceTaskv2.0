package com.example.wilson.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MathActivity extends AppCompatActivity {

    public static String EXTRA_CORRECT = "com.example.wilsongolden.CORRECT";
    public static String EXTRA_HITS = "com.example.wilsongolden.HITS";
    public static String EXTRA_MISSES = "com.example.wilsongolden.MISSES";
    public static String EXTRA_FALSE_STARTS = "com.example.wilsongolden.FALSE_STARTS";
    public static String EXTRA_REACTION_ARRAY = "com.example.wilsongolden.REACTIONS";
    TextView whatIs;
    TextView problem;
    EditText answer;
    Button submit;
    Intent intent;
    int x;
    int y;
    int correct;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.x
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        intent = getIntent();


        x = (int) (Math.random() * 20 + 1);
        y = (int) (Math.random() * 20 + 1);
        whatIs = (TextView) findViewById(R.id.whatIs);
        problem = (TextView) findViewById(R.id.problem);
        problem.setText(x + " + " + y + "?");
        answer = (EditText) findViewById(R.id.answer);

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new clickListener());
    }

    private class clickListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                float answerS = Float.parseFloat(answer.getText().toString());
                if (answerS == (x + y)) {
                    correct++;
                }
                x = (int) (Math.random() * 20 + 1);
                y = (int) (Math.random() * 20 + 1);
                if(x < y) {
                    int temp = x;
                    x = y;
                    y = temp;
                }
                problem.setText(x + " - " + y + "?");

                submit.setOnClickListener(new clickListenerTwo());

            }
    }

    private class clickListenerTwo implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            float answerS = Float.parseFloat(answer.getText().toString());
            if (answerS == (x - y)) {
                correct++;
            }
            newIntent();
        }
    }
    public void newIntent() {
        Intent newIntent = new Intent(this, ResultsActivity.class);
        newIntent.putExtra(EXTRA_CORRECT, correct);
        newIntent.putExtra(EXTRA_REACTION_ARRAY, intent.getDoubleArrayExtra(MainActivity.EXTRA_REACTION_ARRAY));
        newIntent.putExtra(EXTRA_HITS, intent.getIntExtra(MainActivity.EXTRA_HITS, 0));
        newIntent.putExtra(EXTRA_MISSES, intent.getIntExtra(MainActivity.EXTRA_MISSES, 0));
        newIntent.putExtra(EXTRA_FALSE_STARTS, intent.getIntExtra(MainActivity.EXTRA_FALSE_STARTS, 0));
        setIntent(newIntent);
        startActivity(newIntent);
    }
}