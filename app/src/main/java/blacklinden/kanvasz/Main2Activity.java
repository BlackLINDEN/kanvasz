package blacklinden.kanvasz;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

public class Main2Activity extends Activity {

    FractalTreeSView fractalTreeView; //surface View


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fractalTreeView = new FractalTreeSView(this);
        setContentView(fractalTreeView);
    }

    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        fractalTreeView.setWH(fractalTreeView.getWidth(),fractalTreeView.getHeight());
    }

    public boolean onKeyDown(int KeyCode, KeyEvent ev){
        switch (KeyCode){
            case KeyEvent.KEYCODE_0:
                fractalTreeView.setLevel(0);return true;
            case KeyEvent.KEYCODE_1:
                fractalTreeView.setLevel(1);return true;
            case KeyEvent.KEYCODE_2:
                fractalTreeView.setLevel(2);return true;
            case KeyEvent.KEYCODE_3:
                fractalTreeView.setLevel(3);return true;
            case KeyEvent.KEYCODE_4:
                fractalTreeView.setLevel(4);return true;
            case KeyEvent.KEYCODE_5:
                fractalTreeView.setLevel(5);return true;
            case KeyEvent.KEYCODE_6:
                fractalTreeView.setLevel(6);return true;
            case KeyEvent.KEYCODE_7:
                fractalTreeView.setLevel(7);return true;
            case KeyEvent.KEYCODE_8:
                fractalTreeView.setLevel(8);return true;
            case KeyEvent.KEYCODE_9:
                fractalTreeView.setLevel(9);return true;
        }
        return true;
    }





}
