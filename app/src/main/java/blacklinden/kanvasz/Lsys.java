package blacklinden.kanvasz;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Pair;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Vitya on 2018. 01. 14..
 */

public class Lsys {


        int steps = 0;

        String axiom;
        String rule;
        String production;

        float startLength;
        float drawLength;
        float theta;

        int generations;

        Lsys() {
        axiom = "F";
        rule = "F+F-F";
        startLength = 90.0f;
        theta = (float)Math.toRadians(120.0);
        reset();
    }

    void reset() {
        production = axiom;
        drawLength = startLength;
        generations = 0;
    }

    int getAge() {
        return generations;
    }

    void render(Paint paint, Canvas canvas) {
        canvas.translate(200/2, 200/2);
        steps += 5;
        if (steps > production.length()) {
            steps = production.length();
        }
        for (int i = 0; i < steps; i++) {
            char step = production.charAt(i);
            if (step == 'F') {
                canvas.drawRect(0, 0, -drawLength, -drawLength, paint);

                canvas.translate(0, -drawLength);
            }
            else if (step == '+') {
                canvas.rotate(theta);
            }
            else if (step == '-') {
                canvas.rotate(-theta);
            }
            else if (step == '[') {
                canvas.save();
            }
            else if (step == ']') {
                canvas.restore();
            }
        }
    }

    void simulate(int gen) {
        while (getAge() < gen) {
            production = iterate(production, rule);
        }
    }

    String iterate(String prod_, String rule_) {
        drawLength = drawLength * 0.6f;
        generations++;
        String newProduction = prod_;
        newProduction = newProduction.replaceAll("F", rule_);
        return newProduction;
    }
}