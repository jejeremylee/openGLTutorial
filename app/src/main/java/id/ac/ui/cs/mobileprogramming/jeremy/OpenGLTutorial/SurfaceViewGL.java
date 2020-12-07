package id.ac.ui.cs.mobileprogramming.jeremy.OpenGLTutorial;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class SurfaceViewGL extends GLSurfaceView {
    private final SurfaceViewGLRenderer viewRenderer;

    public SurfaceViewGL(Context context) {
        super(context);

        setEGLContextClientVersion(2);
        viewRenderer = new SurfaceViewGLRenderer();
        setRenderer(viewRenderer);

    }
}
