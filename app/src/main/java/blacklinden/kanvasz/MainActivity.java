package blacklinden.kanvasz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {

    private CanvasView customCanvas;
    private TextView tv;
    private Button gmb;
    private ImageButton forw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.startActivity( new Intent(this,min3d3d.class));
        setContentView(R.layout.activity_main);

        tv=(TextView)findViewById(R.id.txt);
        gmb=(Button)findViewById(R.id.gmb);
        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);
        gmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i = new Intent(getBaseContext(),Main2Activity.class);
            startActivity(i);
            }
        });
        forw=(ImageButton)findViewById(R.id.Forward);
        forw.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                });


                //customCanvas.setLayoutParams(new FrameLayout.LayoutParams(200, 200));
    }

    public void clearCanvas(View v) {
        customCanvas.clearCanvas();
    }



}