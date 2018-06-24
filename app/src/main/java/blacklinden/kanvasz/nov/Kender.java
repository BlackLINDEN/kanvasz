package blacklinden.kanvasz.nov;

import blacklinden.kanvasz.kor.Lég;

public class Kender {

    private static float co2;
    private static float h2o=6;

    private static int hő;
    

    public static boolean Halott_e() {
        return halott_e;
    }

    private static boolean halott_e=false;


    public static void setRost(float rost) {
        Kender.rost = rost;
    }

    private static float rost;
    private static float cukor;
    public static float fény=0;//százalék

static {
    cukor=0;//1000000;
    rost=100;
    co2=10;
}
//fény
//6CO2+6H2O->C6H12O6+6O2

public static void update(){
    calvinKör();
    rostbanCukorTárolás();
    if(cukor<=0&&rost<=0)
        halott_e=true;

}



private static void calvinKör(){

    if (fény>70&&co2 > 6 && h2o % 6 == 0) {
        co2 -= 6;
        //h2o -= 6;
        cukor+=10;
        Lég._O2();
    }

}

private static void rostbanCukorTárolás(){
    if(cukor>1000){
        rost+=cukor-1000;
        cukor-=1000;
    }
}

//pozitív szám paraméter
public static float cukrozó(float levonás){

if(cukor<0){
    cukor=rost;
    rost=0;
}

if(cukor<levonás)
    return 0;
else{
    cukor-=levonás;
    return levonás;
}
}

public static void CO2(float co2PPM){
    Kender.co2+=co2PPM;
}


}
