package blacklinden.kanvasz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import blacklinden.kanvasz.kor.Fény;

public class MainActivity extends Activity {

    private CanvasView customCanvas;
    private TextView tv;
    private SeekBar seekBar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.startActivity( new Intent(this,min3d3d.class));
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.txt);
        seekBar = findViewById(R.id.sb);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                               @Override
                                               public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                                   Fény.setIrány(seekBar.getProgress());
                                               }

                                               @Override
                                               public void onStartTrackingTouch(SeekBar seekBar) {

                                               }

                                               @Override
                                               public void onStopTrackingTouch(SeekBar seekBar) {

                                               }
                                           });
        customCanvas = findViewById(R.id.signature_canvas);


                //customCanvas.setLayoutParams(new FrameLayout.LayoutParams(200, 200));
    }

    public void clearCanvas(View v) {
        customCanvas.oo.run();
    }
    public void cl(View v) { customCanvas.nulláz();}



}