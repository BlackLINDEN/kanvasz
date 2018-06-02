package blacklinden.kanvasz.kor;

/**
 * Created by Vitya on 2018. 03. 21..
 */

public class Fény {
   public static float irány=-0.2f;
   private static int lux;
   public static int watt=100;
   private static float hőSötétben=25;
   private static int kelvin;
   private static float táv;
   private static String fajta;
   private static Égő égő;

    public Fény(float irány,String fajta){
        Fény.irány =irány;
        Fény.fajta=fajta;
        switch(fajta){
            case "led":égő=Égő.LED;
                break;
            case "cfl":égő=Égő.CFL;
                break;
            case "hps":égő=Égő.HALOGEN;
                break;
            case "inc":égő=Égő.INCANDESCENT;
                break;
        }

    }

    public static float hőmérséklet(){

        return (hőSötétben+((watt*0.00052656506684073f)*égő.getHőszor_()));
    }

    public static void setIrány(float d){
        irány=d;
    }

    public int getLux(){
        return lux;
    }
}
