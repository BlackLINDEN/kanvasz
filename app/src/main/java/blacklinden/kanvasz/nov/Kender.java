package blacklinden.kanvasz.nov;

public class Kender {

    static float vízháztartás;
    private static float cukor=1000000;
    static float fény=0;

public Kender(){

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
