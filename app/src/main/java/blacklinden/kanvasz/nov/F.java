package blacklinden.kanvasz.nov;

import android.graphics.Color;
import android.graphics.Paint;

import blacklinden.kanvasz.kor.Fény;

/**
 * Created by Vitya on 2018. 03. 20..
 */

public class F extends Növény {


    private float x=0.1f;
    private float ép;

    private int szint;
    private Paint p;

    public F() {
        super("F");
    p = new Paint();
    p.setAntiAlias(true);
    p.setStyle(Paint.Style.FILL_AND_STROKE);
        ép=0;
    }
    public F(int i){
        super("F");
        ép=i;
        this.szint=i;
        p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
    }


    @Override
    public void élet() {

        ép+=Kender.cukrozó(1);
        hossz();
        fejl();
    }

    @Override
    public float vastagság() {

        return (hossz()*0.0003f);
    }

    @Override
    public float hossz() {

        x+=40;
        //ép-=0.002f;

        return x;
    }

    @Override
    public float szög() {
        return Fény.irány;
    }

    @Override
    public Paint szín() {
        if(ép<80)
            p.setColor(Color.GREEN);
        else
            p.setColor(Color.argb(255,133,79,0));
        return p;
    }

    @Override
    public float fejl() {

         return ép;
    }

    @Override
    public float vízigény() {
        return 0;
    }

    @Override
    public float fényigény() {
        return 0;
    }

    @Override
    public float hőigény() {
        float hi=22.5f;
        float lvns=(Math.abs(hi)-Math.abs(Fény.hőmérséklet()))/10;
           ép-=lvns;
        return hi;
    }

    @Override
    public float tápigény() {
        return 0;
    }

    @Override
    public float légz() {
        return 0;
    }

    @Override
    public int szint() {
        return szint;
    }


}
