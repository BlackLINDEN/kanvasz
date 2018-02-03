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
        import android.graphics.drawable.Drawable;
        import android.graphics.drawable.VectorDrawable;
        import android.util.AttributeSet;
        import android.view.MotionEvent;
        import android.view.View;

        import java.util.Stack;

public class CanvasView extends View {


    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    Context context;
    private Paint mPaint;
    private float mX, mY;
    private static final float TOLERANCE = 5;
    float theta = 45f;
    float delta_theta=0f;


    String consts = "+-[]¤fr€$B";
    String levél="fFB€FFBB€FFFBBB€FFFFFBBBBB€FFFBBB€FFBBr";




    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;



        // we set a new Path
        mPath = new Path();


        // and we set a new Paint with the desired attributes
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);



    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    // Lrendszer("FFFF[[+FFF-€€X]-FF[FF-X]-F-X¤]FFF[[+FF-€X]-F-$X¤]F€€FF[-X]BB-FF-$X",1)
    @Override
    protected void onDraw(Canvas canvas) {

        teknős(canvas,Lrendszer("F",3),20);

        super.onDraw(canvas);


    }

    private String Lrendszer(String axióma,int ism){
        ism--;
        StringBuilder f= new StringBuilder();
        for(char ch:axióma.toCharArray()) {
            if (consts.contains(Character.toString(ch)))
                f.append(ch);

            else if(ch=='B')
                f.append("BBX");

            else if(ch=='F')
                f.append("FF");

            else if(ch=='0')
                f.append("F[]");



        }System.out.println(f.toString());

        if(ism==0)
            return f.toString();
        else
            return Lrendszer(f.toString(),ism);
    }

    public void teknős(Canvas c,String s,int i){

        Teknős t = new Teknős(c.getWidth()/2,c.getHeight()-200,90);

        Drawable d =  getResources().getDrawable(R.drawable.w);
        d.setBounds((int)t.x, (int)t.y, 1, 1);


        for (char ch : s.toCharArray()) {

            if (ch == 'F'||ch=='X') {
                t.előreRajz(-i,c,mPaint);



            } else if (ch == 'B') {
                t.előre(-i,c);
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

            else if (ch == '¤') {
                d.draw(c);
            }


            else if (ch == '+') {

                c.rotate(-theta,(float)t.x,(float)t.y);
                delta_theta-=theta;


            }

            else if (ch == '$') {
                c.rotate(25,(float)t.x,(float)t.y);

            }

            else if (ch == '€') {
                c.rotate(-25,(float)t.x,(float)t.y);

            }

            else if (ch == '-') {

                c.rotate(theta,(float)t.x,
                        (float)t.y);
                delta_theta+=theta;

            }
            else if (ch == '['){
                //c.save(Canvas.ALL_SAVE_FLAG);//
                t.mentés(c,(int)t.x,(int)t.y,(int)delta_theta);
                delta_theta=90;

            }

            else if( ch == ']'){
               //c.restoreToCount(c.getSaveCount());
               t.betöltés(c);

            }

        }//c.drawPath(mPath, mPaint);
    }


    // when ACTION_DOWN start touch according to the x,y values
    private void startTouch(float x, float y) {
        mPath.moveTo(x, y);
        mX = x;

        mY = y;
    }

    // when ACTION_MOVE move touch according to the x,y values
    private void moveTouch(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;

            //theta=mY;
        }

    }

    public void clearCanvas() {
        mPath.reset();
        invalidate();
    }

    // when ACTION_UP stop touch
    private void upTouch() {

        mPath.lineTo(mX, mY);

    }

    //override the onTouchEvent
   @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                startTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;


        }
        return true;
    }
private String kf(String s){
    return s;
}


    }
