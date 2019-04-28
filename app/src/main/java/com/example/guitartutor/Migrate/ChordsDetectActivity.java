package com.example.guitartutor.Migrate;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guitartutor.AudioProcessor;
import com.example.guitartutor.DialogUtils;
import com.example.guitartutor.Migrate.Adapters.QuestionsList;
import com.example.guitartutor.Pitch;
import com.example.guitartutor.R;
import com.example.guitartutor.Tuning;
import com.example.guitartutor.Utils;
import com.github.airsaid.library.widget.Chord;
import com.github.airsaid.library.widget.ChordView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChordsDetectActivity extends AppCompatActivity {

    //c2 to c6
    private final double[] noteFrequencies = new double[]{
            65.4064, 69.2957,73.4162, 77.7817, 82.4069, 87.3071, 92.4986, 97.9989, 103.826, 110.000, 116.541, 123.471,
            130.813, 138.591, 146.832, 155.563, 164.814, 174.614, 184.997, 195.998, 207.652, 220.000,233.082, 246.942,
            261.626, 277.183, 293.665, 311.127, 329.628, 349.228, 369.994, 391.995,415.305, 440.000, 466.164, 493.883,
            523.251, 554.365, 587.330, 622.254, 659.255, 698.456, 739.989, 783.991, 830.609, 880.000, 932.328, 987.767,
            1046.50};

    //e2 to g#4   fret open to 4 | string 6 to 1
    private final double[] noteFrequencies2 = new double[]{
            82.4069, 87.3071, 92.4986, 97.9989, 103.826,
            110.000, 116.541, 123.471, 130.813, 138.591,
            146.832, 155.563, 164.814, 174.614, 184.997,
            195.998, 207.652, 220.000, 233.082, 246.942,
            246.942, 261.626, 277.183, 293.665, 311.127,
            329.628, 349.228, 369.994, 391.995, 415.305};

    private final String[] noteName = new String[]{
            "E2", "F2", "F#2", "G2", "G#2",
            "A2", "A#2", "B2", "C3", "C#3",
            "D3", "D#3", "E3", "F3", "F#3",
            "G3", "G#3", "A3", "A#3", "B3",
            "B3", "C4 ", "C#4", "D4", "D#4",
            "E4", "F4", "F#4", "G4", "G#4",
    };

    private ArrayList<String> instructions = new ArrayList<>();
    private ArrayList<Integer> textviewID = new ArrayList<>();

    private Button button1;
    private EditText chordText;
    private TextView mFrequencyView;
    private LinearLayout ll_instructions;

    private Thread chordDetect;

    ChordView mChordView;
    ArrayList<QuestionsList> chordList = QuestionsList.createQuestionList();

    private boolean mProcessing = false;
    private AudioProcessor mAudioProcessor;
    private static final int PERMISSION_REQUEST_RECORD_AUDIO = 443;
    private Tuning mTuning;

    private int mPitchIndex;
    private float mLastFreq;

    private ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    private ExecutorService mExecutor2 = Executors.newSingleThreadExecutor();

    Context context;

    private String pitchName;

    public static final String STATE_PITCH_INDEX = "pitch_index";
    public static final String STATE_LAST_FREQ = "last_freq";

    @Override
    protected void onStart() {
        super.onStart();
        if(Utils.checkPermission(this, Manifest.permission.RECORD_AUDIO)) {
            startAudioProcessing();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mProcessing) {
            mAudioProcessor.stop();
            mProcessing = false;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void requestPermissions() {
        if (!Utils.checkPermission(this, Manifest.permission.RECORD_AUDIO)) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {

                DialogUtils.showPermissionDialog(this, getString(R.string.permission_record_audio), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(ChordsDetectActivity.this,
                                new String[]{Manifest.permission.RECORD_AUDIO},
                                PERMISSION_REQUEST_RECORD_AUDIO);
                    }
                });

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        PERMISSION_REQUEST_RECORD_AUDIO);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_RECORD_AUDIO: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startAudioProcessing();
                }
                break;
            }

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chords_detect);

        chordText = findViewById(R.id.editTextChordName);
        mChordView = findViewById(R.id.chordView1);
        mFrequencyView = findViewById(R.id.frequency_view);
        ll_instructions = findViewById(R.id.ll_instructions);

        button1 = findViewById(R.id.buttonSearch);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(chordText.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

                searchChord();
            }
        });


        mTuning = Tuning.getTuning(this, "Custom");
        requestPermissions();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_PITCH_INDEX, mPitchIndex);
        outState.putFloat(STATE_LAST_FREQ, mLastFreq);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mFrequencyView.setText(String.format("%.02fHz", savedInstanceState.getFloat(STATE_LAST_FREQ)));
    }


    private void searchChord()
    {
        Boolean found = false;
        int[] string = null;
        int[] fret = null;
        for(QuestionsList list: chordList)
        {
            if(list.getName().equalsIgnoreCase(chordText.getText().toString()))
            {
                Chord chord = new Chord(list.getFret(), list.getString());
                mChordView.setChord(chord);
                found = true;

                string = list.getString();
                fret = list.getFret();
            }
        }

        if(!found)
        {
            Toast.makeText(this, "Chord not found.", Toast.LENGTH_SHORT).show();
        }
        else {
            playChord(string, fret);
        }
    }

    @SuppressLint("ResourceType")
    private void playChord(int[] string, int[] fret){
        ArrayList<Integer> playIndex = new ArrayList<Integer>();
        instructions.clear();
        ll_instructions.removeAllViews();

        int index = -1;
        int increment = 0;

        for(int string2: string){
            index++;
            if(string2 > 0){
                playIndex.add((increment * 5) + fret[index]);
                instructions.add("Play string number " + Math.abs(increment-6));
            }
            else if(string2 == 0){
                playIndex.add((increment * 5));
                instructions.add("Play string number " + Math.abs(increment-6));
            }
            increment++;
        }

        textviewID.clear();
        int x = 0;
        for(String instructions2: instructions) {
            TextView tv = new TextView(context);
            tv.setText(instructions2);
            tv.setId(x);
            textviewID.add(x);
            ll_instructions.addView(tv);
            x++;
            //Log.w("Index", instructions2 + "");
        }


        final ArrayList<Integer>  playIndex2 = playIndex;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Boolean goodChord = false;
                int x = 0;
                for(final Integer string2: playIndex2) {
                    //Log.w("Index", noteFrequencies2[string2] + "");

                    final int y = x;
                    runOnUiThread(new Runnable() {
                        public void run() {
                            TextView tv = findViewById(textviewID.get(y));
                            tv.setTypeface(null, Typeface.BOLD);
                            tv.setText(tv.getText() + "("+ noteName[string2]+")");
                        }
                    });

                    pitchName = "";

                    while(!pitchName.equals(noteName[string2])) {
                        //startAudioProcessing();
                        Log.w("Index", noteName[string2] + "");
                    }

                    runOnUiThread(new Runnable() {
                        public void run() {
                            TextView tv = findViewById(textviewID.get(y));
                            tv.setTypeface(null, Typeface.NORMAL);
                            tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                            Toast.makeText(context, "String played properly.", Toast.LENGTH_SHORT).show();
                        }
                    });

                    x++;
                }

                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(context, "CHORD OK", Toast.LENGTH_SHORT).show();

                        TextView tv = new TextView(context);
                        tv.setText("\nChord played properly!");
                        tv.setTypeface(null, Typeface.BOLD);
                        ll_instructions.addView(tv);
                    }
                });

            }
        };

        mExecutor2.execute(runnable);
    }

    private void startAudioProcessing() {
        if (mProcessing)
            return;

        mAudioProcessor = new AudioProcessor();
        mAudioProcessor.init();
        mAudioProcessor.setPitchDetectionListener(new AudioProcessor.PitchDetectionListener() {
            @Override
            public void onPitchDetected(final float freq, double avgIntensity) {

                final int index = mTuning.closestPitchIndex(freq);

                final Pitch pitch = mTuning.pitches[index];
                double interval = 1200 * Utils.log2(freq / pitch.frequency); // interval in cents
                final float needlePos = (float) (interval / 100);
                final boolean goodPitch = Math.abs(interval) < 5.0;


                runOnUiThread(new Runnable() {
                    public void run() {
                        //Toast.makeText(context, String.format("%.02fHz", freq) + " / " + pitch.name, Toast.LENGTH_SHORT).show();
                        pitchName = pitch.name;
                    }
                });

                runOnUiThread(new Runnable() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void run() {
                        //mTuningView.setSelectedIndex(index, true);
                        //mNeedleView.setTickLabel(0.0F, String.format("%.02fHz", pitch.frequency));
                        //mNeedleView.animateTip(needlePos);

                        //mFrequencyView.setText(String.format("%.02fHz", freq));

                        final View goodPitchView = findViewById(R.id.good_pitch_view);
                        if (goodPitchView != null) {
                            if (goodPitch) {
                                if (goodPitchView.getVisibility() != View.VISIBLE) {
                                    Utils.reveal(goodPitchView);
                                }
                            } else if (goodPitchView.getVisibility() == View.VISIBLE) {
                                Utils.hide(goodPitchView);
                            }
                        }
                    }
                });

                mPitchIndex = index;
                mLastFreq = freq;

            }
        });
        mProcessing = true;
        mExecutor.execute(mAudioProcessor);
    }
}
