package id.ac.ui.cs.mobileprogramming.jeremy.OpenGLTutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private GLSurfaceView svGL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        svGL = new SurfaceViewGL(this);
        setContentView(svGL);
    }
}