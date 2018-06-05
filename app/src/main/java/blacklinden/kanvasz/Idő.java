package blacklinden.kanvasz;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vitya on 2018. 02. 06..
 */

public class Idő {
    long kezdet;

    private Idő(){
        nulláz();
    }
    public static Idő indul(){
        return new Idő();
    }

    public Idő nulláz(){
        kezdet = System.nanoTime();
        return this;
    }
    public long idő(){
        long vég = System.nanoTime();
        return vég - kezdet;
    }
    public long idő(TimeUnit unit){
        return unit.convert(idő(),TimeUnit.NANOSECONDS);
    }
    public String vált(){
        return String.format("%d min,%d sec",idő(TimeUnit.MINUTES),
                idő(TimeUnit.SECONDS)-idő(TimeUnit.MINUTES));
    }
}
