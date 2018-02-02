package blacklinden.kanvasz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Vitya on 2018. 01. 17..
 */

public class FractalTreeSView extends SurfaceView implements SurfaceHolder.Callback {

   int angle=30;
   double length =128;
   double scale =0.7;
   int level = 7;
   float BX1,BY1;
    float BX2,BY2;
    float TX, TY;
    float R = (float)20.0;
    float W,H;
    double degree = Math.PI/180.0;
    SurfaceHolder holder;
    Paint paint, paintBG, paintDragBox;

public FractalTreeSView(Context context){
    super(context);
    holder = getHolder();
    holder.addCallback(this);
    holder.setFixedSize(getWidth(), getHeight());
    setFocusable(true);

    paint = new Paint();
    paintBG = new Paint();
    paintDragBox = new Paint();
    paint.setStrokeWidth(3);
    paintBG.setStrokeWidth(1);
    paintDragBox.setStrokeWidth(1);

    paint.setStyle(Paint.Style.FILL);
    paintBG.setStyle(Paint.Style.FILL);
    paintDragBox.setStyle(Paint.Style.FILL);
    paint.setColor(Color.RED);
    paintBG.setColor(Color.CYAN);
    paintDragBox.setColor(Color.BLUE);
}

public void surfaceCreated(SurfaceHolder h){
    doDraw(h);
}
@SuppressLint("WrongCall")
private void doDraw(SurfaceHolder h){
    Canvas c = h.lockCanvas();
    onDraw(c);//lehet h ondraw
    h.unlockCanvasAndPost(c);
}
public boolean onTouchEvent(MotionEvent event){
    switch(event.getAction()){
        case MotionEvent.ACTION_MOVE:
    TX = event.getX();
    TY = event.getY();
    if( TX > (BX1-R) && TX < (BX1+R) ){
        if( TY > (BY1-R) && TY < (BY1+R) ){
            length = H - TY;
            break;
        }
    }
    //一次の枝の終点がドラッグされたら
    if( TX > (BX2-R) && TX < (BX2+R) ){
        if( TY > (BY2-R) && TY < (BY2+R) ){
            float x = TX-BX1; float y = BY1-TY;
            scale = Math.sqrt( x*x + y*y )/length;
            angle = (int) Math.toDegrees( Math.atan2(x, y) );
            break;
        }
        break;
    }
}
    doDraw( holder ); return true;
}

    protected void onDraw(Canvas c){
        c.save();
        c.drawRect(0, 0, W, H, paintBG); //背景色で塗りつぶして画面消去
        BX1=(float) (W*0.5); BY1= (float) (H-length); //幹の終点座標
        drawTree( c, level, W/2, H, length, 0); //Fractal Tree
        c.drawRect(BX1-R, BY1+R, BX1+R, BY1-R, paintDragBox); //幹のドラッグ BOX 描画
        c.drawRect(BX2-R, BY2+R, BX2+R, BY2-R, paintDragBox); //枝のドラッグ BOX 描画
        c.restore();
    }
    //Fractal Tree を描く再帰アルゴリズム
    public void drawTree(Canvas c, int n, double x1, double y1, double len, double stand){

        double x2 = x1 + len*Math.sin( Math.toRadians(stand) );
        double y2 = y1 - len*Math.cos( Math.toRadians(stand) );
        if(n == level-1 ){ BX2=(float) x2; BY2=(float) y2; } // 一次の枝の終点座標
        c.drawLine( (int)x1, (int)y1, (int)x2, (int)y2, paint );
        if( n>=1 ){
            drawTree( c, n-1, x2, y2, len*scale, stand - angle );
            drawTree( c, n-1, x2, y2, len*scale, stand + angle );
        }
    }
    public void setLevel(int lev){ level = lev; doDraw(holder);}
    public void setWH(float w, float h){W = w; H = h; length=(int)h/3; doDraw(holder);}
    public void surfaceDestroyed(SurfaceHolder holder) { }
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,int arg3) {}


}


