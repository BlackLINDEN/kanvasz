package blacklinden.kanvasz.kor;

/**
 * Created by Vitya on 2018. 03. 21..
 */

public class Víz {

    static float cserépMéret;
    static int hetiHányÖnt=2;
    static float vízMennyiség=30;


    public Víz(){

    }

   public static float hetiVíz(){
        return ((vízMennyiség*hetiHányÖnt)-((vízMennyiség*hetiHányÖnt)*(0.05f*(Fény.hőmérséklet()/5))));
   }

}
