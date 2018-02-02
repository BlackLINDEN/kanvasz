package blacklinden.kanvasz;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

class Szabály extends Lsys {

    String ruleF;
    Lsys l;


    Szabály() {
        axiom = "F3-F3-F3-F3-F";
        ruleF = "F3-F3-F45-F++F3-F";
        startLength = 450.0f;
        theta = (float)Math.toRadians(18);
        reset();
        l = new Lsys();

    }

    void useRule(String r_) {
        rule = r_;
    }

    void useAxiom(String a_) {
        axiom = a_;
    }

    void useLength(float l_) {
        startLength = l_;
    }

    void useTheta(float t_) {
        theta = (float) Math.toRadians(t_);
    }

    void reset() {
        production = axiom;
        drawLength = startLength;
        generations = 0;
    }

    int getAge() {
        return generations;
    }



    @Override
    void render(Paint paint, Canvas c) {
    l.render(paint,c);
        c.translate(400, 200);
        int repeats = 1;

        steps += 3;
        if (steps > production.length()) {
            steps = production.length();
        }

        for (int i = 0; i < steps; i++) {
            char step = production.charAt(i);
            if (step == 'F') {
                for (int j = 0; j < repeats; j++) {
                   c.drawLine(0,0,0, -drawLength,paint);
                    c.translate(0, -drawLength);
                }
                repeats = 1;
            }
            else if (step == '+') {
                for (int j = 0; j < repeats; j++) {
                    c.rotate(theta);
                }
                repeats = 1;
            }
            else if (step == '-') {
                for (int j =0; j < repeats; j++) {
                    c.rotate(-theta);
                }
                repeats = 1;
            }
            else if (step == '[') {
                c.save();
            }
            else if (step == ']') {
                c.restore();
            }
            else if ( (step >= 48) && (step <= 57) ) {
                repeats += step - 48;
            }
        }
    }

    @Override
    String iterate(String prod_, String rule_) {
        StringBuilder newProduction = new StringBuilder();
        for (int i = 0; i < prod_.length(); i++) {
            char step = production.charAt(i);
            if (step == 'F') {
                newProduction.append(ruleF);
            }
            else {
                newProduction.append(step);
            }
        }
        drawLength = drawLength * 0.4f;
        generations++;
        return newProduction.toString();
    }

}