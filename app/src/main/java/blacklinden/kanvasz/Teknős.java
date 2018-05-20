package blacklinden.kanvasz;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
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
    private Paint levél;
    private Paint levélKör;
    private Path path;
    // start at (x0, y0), facing a0 degrees counterclockwise from the x-axis



    public Teknős(double x0, double y0, double a0) {
        x = x0;
        y = y0;
        angle = a0;
        path = new Path();
        path.setFillType(Path.FillType.WINDING);

        levél = new Paint();
        levél.setARGB(255,133,235,93);
        //levél.setShadowLayer(0.1f,0.1f,0.1f,1);
        levél.setStyle(Paint.Style.FILL);

        levélKör = new Paint();
        levélKör.setColor(Color.BLACK);
        levélKör.setStyle(Paint.Style.STROKE);
        levélKör.setStrokeWidth(7f);

    }




    void előreRajz(float v,double step, Canvas canvas,Paint paint,int ism) {
        float oldx =(float) x;
        float oldy =(float) y;
        x += ((float)step / ism)* Math.cos(Math.toRadians(angle));
        y +=((float)step / ism)* Math.sin(Math.toRadians(angle));
        paint.setStrokeWidth(v);
        canvas.drawLine(oldx, oldy, (float)x,(float)y,paint);

    }

    void virágzás(Canvas canvas,Paint p){
        float oldx =(float) x;
        float oldy =(float) y;
        x += (20)* Math.cos(Math.toRadians(angle));
        y +=(20)* Math.sin(Math.toRadians(angle));
        //canvas.drawCircle((float)x,(float)y,20,levélKör);

        canvas.drawCircle((float)x,(float)y,20,levél);
        canvas.drawLine((float)x, (float)y, oldx,oldy,p);

    }

    void levElRajz(double step, Canvas canvas, Paint szín) {
        float oldx =(float) x;
        float oldy =(float) y;
        x += ((float)step)* Math.cos(Math.toRadians(angle));
        y +=((float)step)* Math.sin(Math.toRadians(angle));

                canvas.drawOval(oldx - 10, (float) y - 50, (oldx + 10), oldy + 10, szín);
                canvas.drawOval(oldx - 10, (float) y - 50, (oldx + 10), oldy + 10, levélKör);

    }

    public void el(double step, Canvas canvas,Paint paint, int ism) {
        float oldx =(float) x;
        float oldy =(float) y;
        x += (float)step * Math.cos(Math.toRadians(angle));
        y +=(float) step * Math.sin(Math.toRadians(angle));
        levél.setStrokeWidth(ism*2);

        //canvas.drawCircle((float)x,(float)y,ism*3,levél);
        //canvas.drawText(".^.",oldx,oldy,levél);
        canvas.drawArc(oldx,oldy,(float)(x+step),(float)(y+step),6f,12f,true,paint);




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

    public void teki(Canvas c,String s,int i,float theta, Paint mPaint){





            for (char ch : s.toCharArray()) {

                if (ch == 'F'||ch=='X') {
                   // előreRajz(-i,c,mPaint);



                } else if (ch == 'B') {
                    előre(-i,c);
                }

                else if (ch == 'f') {
                    mPaint.setColor(Color.GREEN);
                    mPaint.setStrokeWidth(8f);
                    mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                    mPaint.setStrokeCap(Paint.Cap.ROUND);
                }

                else if (ch == 'r') {
                    mPaint.setColor(Color.BLACK);
                    mPaint.setStrokeWidth(4f);
                    mPaint.setStyle(Paint.Style.STROKE);
                }



                else if (ch == '+') {

                    c.rotate(-theta,(float)x,(float)y);



                }

                else if (ch == '$') {
                    c.rotate(25,(float)x,(float)y);

                }

                else if (ch == '€') {
                    c.rotate(-25,(float)x,(float)y);

                }

                else if (ch == '-') {

                    c.rotate(theta,(float)x,
                            (float)y);


                }
                else if (ch == '['){
                    //c.save(Canvas.ALL_SAVE_FLAG);//
                    mentés(c,(int)x,(int)y,(int)theta);


                }

                else if( ch == ']'){
                    //c.restoreToCount(c.getSaveCount());
                    betöltés(c);

                }

            }//c.drawPath(mPath, mPaint);
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