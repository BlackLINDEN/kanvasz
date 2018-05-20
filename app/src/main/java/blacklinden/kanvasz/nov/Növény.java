package blacklinden.kanvasz.nov;


import android.graphics.Paint;

/**
 * Created by Vitya on 2018. 03. 20..
 */

public abstract class Növény {
    public String n;

     Növény(String n){
    this.n=n;
    }

    public abstract void élet();

    public abstract float vastagság();

    public abstract float hossz();

    public abstract float szög();

    public abstract Paint szín();

    public abstract float fejl();

    public abstract float vízigény();

    public abstract float fényigény();

    public abstract float hőigény();

    public abstract float tápigény();

    public abstract float légz();

    public abstract int szint();



}
