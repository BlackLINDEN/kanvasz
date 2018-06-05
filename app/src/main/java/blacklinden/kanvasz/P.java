package blacklinden.kanvasz;

/**
 * Created by Vitya on 2018. 03. 15..
 */

import java.util.Random;

public class P {
    String n;
    float v;
    float szg;
    int t;


    public P(String n, float v,float szg, int t) {
        this.n=n;
        this.v=v;
        this.szg=szg;
        this.t=t;
    }

    public float stochastic(float legf,float lega) {
        Random r = new Random();
        return this.szg=r.nextInt((int) ((legf-lega)+1))+lega;

    }
    public float szigmoid(float t) {

        return (float)(10+(90/( 1 + Math.pow(Math.E,(8.1818*(6-t))))));
    }
    public float növ(int n){
        float c=1;
        return (float) (c*Math.pow(2,n));
    }



}