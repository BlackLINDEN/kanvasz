package blacklinden.kanvasz;

import android.app.Activity;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class DearKen extends Activity {
    GLSurfaceView mySurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dear_ken);
         mySurfaceView = (GLSurfaceView) findViewById(R.id.my_surface_view);
        mySurfaceView.setEGLContextClientVersion(2);


        mySurfaceView.setRenderer(new GLSurfaceView.Renderer() {
            kendR endR;
            @Override
            public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
                mySurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
                endR = new kendR(getApplicationContext());
            }

            @Override
            public void onSurfaceChanged(GL10 gl10, int i, int i1) {
                GLES20.glViewport(0,0, i, i1);
            }

            @Override
            public void onDrawFrame(GL10 gl10) {
                endR.draw();
            }
            // More code goes here
        });
    }
}
