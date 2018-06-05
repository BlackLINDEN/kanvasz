package blacklinden.kanvasz.nov;

public class Kender {

    static float co2;
    static float h2o;
    private static float cukor=1000000;
    static float fény=0;

public Kender(){

}
//fény
//6CO2+6H2O->C6H12O6+6O2
private static float calvinKör(){
if(co2%6==0)co2-=6;

    return 0;
}


//pozitív szám paraméter
public static float cukrozó(float levonás){
cukor = cukor<0 ? 0:cukor;

if(cukor<levonás)
    return 0;
else{
    cukor-=levonás;
    return levonás;
}
}


}
