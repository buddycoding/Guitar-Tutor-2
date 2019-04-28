package com.example.guitartutor.Migrate;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guitartutor.AudioProcessor;
import com.example.guitartutor.DialogUtils;
import com.example.guitartutor.Migrate.Adapters.QuestionsList;
import com.example.guitartutor.Pitch;
import com.example.guitartutor.Preferences;
import com.example.guitartutor.R;
import com.example.guitartutor.Tuning;
import com.example.guitartutor.Utils;
import com.github.airsaid.library.widget.Chord;
import com.github.airsaid.library.widget.ChordView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChordsDetectActivity extends AppCompatActivity {

    private Button button1;
    private EditText chordText;
    private TextView mFrequencyView;

    ChordView mChordView;
    ArrayList<QuestionsList> chordList = QuestionsList.createQuestionList();

    private boolean mProcessing = false;
    private AudioProcessor mAudioProcessor;
    private static final int PERMISSION_REQUEST_RECORD_AUDIO = 443;
    private Tuning mTuning;

    private int mPitchIndex;
    private float mLastFreq;

    private ExecutorService mExecutor = Executors.newSingleThreadExecutor();

    Context context;

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

        button1 = findViewById(R.id.buttonSearch);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(chordText.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

                searchChord();
            }
        });


        mTuning = Tuning.getTuning(this, Preferences.getString(this, getString(R.string.pref_tuning_key), getString(R.string.standard_tuning_val)));
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
        for(QuestionsList list: chordList)
        {
            if(list.getName().equalsIgnoreCase(chordText.getText().toString()))
            {
                Chord chord = new Chord(list.getFret(), list.getString());
                mChordView.setChord(chord);
                found = true;
            }
        }

        if(!found)
        {
            Toast.makeText(this, "Chord not found.", Toast.LENGTH_SHORT).show();
        }
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
                        Toast.makeText(context, String.format("%.02fHz", freq) + " / " + pitch.name, Toast.LENGTH_SHORT).show();
                    }
                });

                runOnUiThread(new Runnable() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void run() {
                        //mTuningView.setSelectedIndex(index, true);
                        //mNeedleView.setTickLabel(0.0F, String.format("%.02fHz", pitch.frequency));
                        //mNeedleView.animateTip(needlePos);
                        mFrequencyView.setText(String.format("%.02fHz", freq));

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
