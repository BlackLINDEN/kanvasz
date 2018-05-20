package blacklinden.kanvasz.kor;

/**
 * Created by Vitya on 2018. 03. 21..
 */

public enum Égő {
        INCANDESCENT(0.11f),HALOGEN(0.13f),LED(0.05f),CFL(0.03f);

    public float getHőszor_() {
        return hőszor_;
    }

    private float hőszor_;

        Égő(float hőszor_){
        this.hőszor_=hőszor_;
        }
}
