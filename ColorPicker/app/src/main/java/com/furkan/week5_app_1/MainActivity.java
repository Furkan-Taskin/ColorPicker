package com.furkan.week5_app_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

/*
* - Color Picker -
* Furkan Taşkın
* 06.11.2021
*/

public class MainActivity extends AppCompatActivity {

    SeekBar seekBarRed, seekBarGreen, seekBarBlue;
    ConstraintLayout constLayout, rgbLayout;
    Button btnRGB;
    int red, green, blue; // initializing global int values for RGB values, so we can save them


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBarRed = findViewById(R.id.seekBarRed);
        seekBarGreen = findViewById(R.id.seekBarGreen);
        seekBarBlue = findViewById(R.id.seekBarBlue);
        rgbLayout = findViewById(R.id.rgbLayout);
        constLayout = findViewById(R.id.constLayout);
        btnRGB = findViewById(R.id.btnRGB);

        // setting layouts' background colors
        constLayout.setBackgroundColor(Color.rgb(49, 52, 53));
        rgbLayout.setBackgroundColor(Color.rgb(0, 0, 0));

        // setting seekbars' colors.
        seekBarRed.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        seekBarRed.getThumb().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        seekBarGreen.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        seekBarGreen.getThumb().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        seekBarBlue.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        seekBarBlue.getThumb().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

        seekBarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rgbLayout.setBackgroundColor(Color.rgb(progress, seekBarGreen.getProgress(), seekBarBlue.getProgress()));
                btnRGB.setText("RGB(" + seekBarRed.getProgress() + ", " + seekBarGreen.getProgress() + ", " + seekBarBlue.getProgress() + ")");
                red = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBarGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rgbLayout.setBackgroundColor(Color.rgb(seekBarRed.getProgress(), progress, seekBarBlue.getProgress()));
                btnRGB.setText("RGB(" + seekBarRed.getProgress() + ", " + seekBarGreen.getProgress() + ", " + seekBarBlue.getProgress() + ")");
                green = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rgbLayout.setBackgroundColor(Color.rgb(seekBarRed.getProgress(), seekBarGreen.getProgress(), progress));
                btnRGB.setText("RGB(" + seekBarRed.getProgress() + ", " + seekBarGreen.getProgress() + ", " + seekBarBlue.getProgress() + ")");
                blue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void copyRGB(View view){
        CharSequence text = "rgb(" + red + ", " + green + ", " + blue + ")";

        // copying to the clipboard
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", text);
        clipboard.setPrimaryClip(clip);

        // after successful copying, a toast message is displayed
        Toast.makeText(getApplicationContext(), "RGB Copied", Toast.LENGTH_SHORT).show();
    }

}