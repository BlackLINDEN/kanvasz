package blacklinden.kanvasz;

/**
 * Created by Vitya on 2018. 01. 12..
 */



        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Path;

        import android.os.Handler;
        import android.os.Looper;
        import android.util.AttributeSet;

        import android.view.MotionEvent;
        import android.view.View;

        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Iterator;
        import java.util.Objects;

        import blacklinden.kanvasz.nov.A;
        import blacklinden.kanvasz.nov.Av;
        import blacklinden.kanvasz.nov.F;
        import blacklinden.kanvasz.nov.Kender;
        import blacklinden.kanvasz.nov.L;
        import blacklinden.kanvasz.nov.M;
        import blacklinden.kanvasz.nov.Növény;
        import blacklinden.kanvasz.nov.T;
        import blacklinden.kanvasz.nov.V;
        import blacklinden.kanvasz.nov.X;


public class CanvasView extends View {


    private Bitmap mBitmap;

    private Path mPath;
    Context context;
    private Paint mPaint;
    Canvas canvas;
    float delta_theta=0f;
    public static int ism=1;

    ArrayList<Növény> al = new ArrayList<>();


    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;

        new Kender();
        //oo.run();
        F ff = new F();
        al.add(ff);
        al.add(new M());
        al.add(new L(true,1));
        al.add(new T());
        al.add(new M());
        al.add(new T());
        al.add(new M());
        al.add(new L(false,1));
        al.add(new T());

        // we set a new Path
        mPath = new Path();

        // and we set a new Paint with the desired attributes
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        mPaint.setStrokeWidth(50f);



    }




    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        //mCanvas = new Canvas(mBitmap);

    }

    // Lrendszer("FFFF[[+FFF-€€X]-FF[FF-X]-F-X¤]FFF[[+FF-€X]-F-$X¤]F€€FF[-X]BB-FF-$X",1)
    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        T(canvas,al);



    }
    Handler handler = new Handler(Looper.myLooper());
    Runnable oo = new Runnable() {
        @Override
        public void run() {

            invalidate();
            if(ism==1000) {
                handler.removeCallbacks(this);


            }
            else if(!Kender.Halott_e()){
                ism();
                A(al,ism);
                handler.postDelayed(this, 1000);
            }
        }
    };
    private void ism(){
        ism++;

    }

    private ArrayList<Növény> A(ArrayList<Növény> aa, int sm){
        ArrayList<Növény> a = new ArrayList<>();

        if(ism<800){
        for(Növény x:aa){
        Kender.update();
        x.élet();
        if(Objects.equals(x.n, "F")&&x.fejl()==50){
            a.add(x);
            a.add(new A(x.szint()));
        }
        else if(Objects.equals(x.n,"A")&&x.szint()<500&&x.fejl()>5){

            //System.out.println("szint: "+x.szint());
            a.add(new M());
            a.add(new X(true,x.szint()));
            a.add(new L(true,x.szint()));
            a.add(new T());
            a.add(new M());
            a.add(new T());
            a.add(new M());
            a.add(new X(false,x.szint()));
            a.add(new L(false,x.szint()));
            a.add(new T());
            a.add(new F(x.szint()));


        }else if(Objects.equals(x.n,"X")&&x.szint()==5){
           a.add(new F(x.szint()));
            /*a.add(new X(true,x.szint()/2));
            a.add(new A(1));*/

        }else if(Objects.equals(x.n,"X")&&(int)x.fejl()==300){

            a.add(new X(x.szög()<0,301,x.hossz()/2,x.szög(),3));
            a.add(new L(x.szög()<0,4));
            a.add(new X(x.szög()<0,301,x.hossz()/2,x.szög(),3));
        }

        else {
            a.add(x);
        }
        } al.clear();
            al.addAll(a);
        }else {
            for (int i = aa.size()-1; i >=0; i--) {
                Kender.update();
                Növény y = aa.get(i);
                y.élet();
                if (Objects.equals(y.n, "F")&&y.szint()>5) {
                    System.out.println(" AAAA"+i+"  "+y.szint());
                    a.add(new Av(19%y.szint()));
                    a.add(y);
                } else if (Objects.equals(y.n, "AV")&&y.fejl()>1) {
                    //y.élet();
                    a.add(new V());
                } else {
                    a.add(y);
                }
            }

            al.clear();
            Collections.reverse(a);
            al.addAll(a);
        }


        return a;
    }



   @Override
    public boolean onTouchEvent(MotionEvent event) {

        return true;
    }

    public void nulláz(){
        ism=1;
        al.clear();
        al.add(new F());
        al.add(new M());
        al.add(new L(true,1));
        al.add(new T());
        al.add(new M());
        al.add(new T());
        al.add(new M());
        al.add(new L(false,1));
        al.add(new T());
        invalidate();
    }




    public void T(Canvas c,ArrayList<Növény> a){

        Teknős t = new Teknős(c.getWidth()/2,c.getHeight()-200,90);




        for (Növény ch : a) {

            switch (ch.n) {
                case "F":
                case "X":
                    c.rotate(ch.szög(), (float) t.x, (float) t.y);
                    t.előreRajz(ch.vastagság(),-ch.hossz(), c, ch.szín(), ism);

                    break;

                case "L":
                    if(ch.szint()<2) {
                        c.rotate(ch.szög(), (float) t.x, (float) t.y);
                        t.levElRajz(-ch.hossz(), c, ch.szín());
                    }else if(ch.szint()==2){
                        t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                        c.rotate(ch.szög()-65, (float) t.x, (float) t.y);
                        t.levElRajz(-ch.hossz()/3,c,ch.szín());
                        t.betöltés(c);
                        t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                        c.rotate(ch.szög(), (float) t.x, (float) t.y);
                        t.levElRajz(-ch.hossz(), c, ch.szín());
                        t.betöltés(c);
                        t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                        c.rotate(ch.szög()+65, (float) t.x, (float) t.y);
                        t.levElRajz(-ch.hossz()/3, c, ch.szín());
                        t.betöltés(c);

                    }else {
                        t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                        c.rotate(ch.szög()-130, (float) t.x, (float) t.y);
                        t.levElRajz(-ch.hossz()/6,c,ch.szín());
                        t.betöltés(c);
                        t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                        c.rotate(ch.szög()-65, (float) t.x, (float) t.y);
                        t.levElRajz(-ch.hossz()/3, c, ch.szín());
                        t.betöltés(c);
                        t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                        c.rotate(ch.szög(), (float) t.x, (float) t.y);
                        t.levElRajz(-ch.hossz(), c, ch.szín());
                        t.betöltés(c);
                        t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                        c.rotate(ch.szög()+65, (float) t.x, (float) t.y);
                        t.levElRajz(-ch.hossz()/3,c,ch.szín());
                        t.betöltés(c);
                        t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                        c.rotate(ch.szög()+130, (float) t.x, (float) t.y);
                        t.levElRajz(-ch.hossz()/6, c, ch.szín());
                        t.betöltés(c);
                    }
                    break;

                case "V":
                        t.mentés(c, (int)t.x,(int)t.y,(int)delta_theta);
                        c.rotate(ch.szög(), (float) t.x, (float) t.y);
                        t.virágzás(c,ch.szín());
                        t.betöltés(c);
                    break;
                case "[":
                    t.mentés(c, (int) t.x, (int) t.y, (int) delta_theta);
                    break;
                case "]":
                    t.betöltés(c);
                    break;
            }



        }
    }



}
