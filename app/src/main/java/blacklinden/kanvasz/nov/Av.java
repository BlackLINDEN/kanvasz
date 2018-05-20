package blacklinden.kanvasz.nov;

import android.graphics.Paint;

/**
 * Created by Vitya on 2018. 05. 01..
 */

public class Av extends Növény {
    private int szint;
    private float ép;
    public Av(int szint) {
        super("AV");
        this.szint=szint+1;
    }

    @Override
    public void élet() {
        //ép++;
        fejl();
    }

    @Override
    public float vastagság() {
        return 0;
    }

    @Override
    public float hossz() {
        return 0;
    }

    @Override
    public float szög() {
        return 0;
    }

    @Override
    public Paint szín() {
        return new Paint();
    }

    @Override
    public float fejl() {
        ép+=(1/szint);
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
        return 0;
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
