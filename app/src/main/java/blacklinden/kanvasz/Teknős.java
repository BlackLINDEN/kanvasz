package blacklinden.kanvasz;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.Stack;

public class Teknős  {
    public double x, y;     // turtle is at (x, y)
    private double angle;    // facing this many degrees counterclockwise from the x-axis
    private Stack<S> stack = new Stack<>();
    private int sz=0;
    private int számláló;
    // start at (x0, y0), facing a0 degrees counterclockwise from the x-axis
    public Teknős(double x0, double y0, double a0) {
        x = x0;
        y = y0;
        angle = a0;
    }



    public void előreRajz(double step, Canvas canvas,Paint paint) {
        float oldx =(float) x;
        float oldy =(float) y;
        x += (float)step * Math.cos(Math.toRadians(angle));
        y +=(float) step * Math.sin(Math.toRadians(angle));
        canvas.drawLine(oldx, oldy, (float)x,(float)y,paint);
    }
    public void előre(double step, Canvas c){
        x += (float)step * Math.cos(Math.toRadians(angle));
        y +=(float) step * Math.sin(Math.toRadians(angle));

        c.translate((float)0,(float)step);
    }


    public void mentés(Canvas c, int ix, int iy, int t){
        sz++;
        c.save();
        számláló=c.save();

        stack.push(new S(c,ix,iy,számláló));

    }

    public void betöltés(Canvas c){
        sz++;

        c.restoreToCount(stack.peek().t);
        számláló--;
        //c.translate((float)(x-stack.peek().x),((float)y-stack.peek().y));
        x = stack.peek().x;
        y = stack.peek().y;
        stack.pop();

        //System.out.println(sz+" stack t"+stack.peek().t+" x"+(x-stack.peek().x)+" y"+(stack.peek().y));
    }
    public void betöltés(Canvas c, boolean eredetiSzög){
        sz++;
       // c.restore();
        //c.translate((float)(x-stack.peek().x),((float)y-stack.peek().y));
        x = stack.peek().x;
        y = stack.peek().y;

        c.rotate(stack.peek().t,stack.peek().x,stack.peek().y);



    }

    public void pattintás(Canvas c){

        stack.pop();


    }
    public void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}