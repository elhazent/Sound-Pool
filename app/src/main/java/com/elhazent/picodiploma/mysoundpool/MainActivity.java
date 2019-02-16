package com.elhazent.picodiploma.mysoundpool;

import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSound;
    SoundPool soundPool;
    int soundId;
    boolean spLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSound = findViewById(R.id.button);
        soundPool = new SoundPool.Builder().setMaxStreams(10).build();

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (status == 0){
                    spLoaded = true;
                } else {
                    Toast.makeText(MainActivity.this,"Load Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        soundId = soundPool.load(this,R.raw.sound, 1);

        btnSound.setOnClickListener(myListener);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (spLoaded){
                soundPool.play(soundId,1,1,0,0,1);
            }
        }
    };
}
