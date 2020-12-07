package id.ac.ui.cs.mobileprogramming.jeremy.OpenGLTutorial;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Segitiga {

    private FloatBuffer vertexBuff;
    private final int glProgram;
    private int positionHandle;
    private int colorHandle;
    static final int COORDS_PER_VERTEX = 3;

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    // membuat koordinat untuk bentuk segitiga
    static float koordinatSegitiga[] = {
            0.0f, 0.622008459f, 0.0f,
            -0.5f, -0.311004243f, 0.0f,
            0.5f, -0.311004243f, 0.0f
    };

    // menentukan warna dari segitiga dengan format r,g,b dan opacity
    // Saya mencoba warna merah dengan opacity 1
    float warna[] = { 1f, 0f, 0f, 1.0f };

    private final int vertexCount = koordinatSegitiga.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4;


    public void draw() {
        // menambahkan program ke environment OpenGL ES
        GLES20.glUseProgram(glProgram);

        positionHandle = GLES20.glGetAttribLocation(glProgram, "vPosition");


        GLES20.glEnableVertexAttribArray(positionHandle);

        // Menyiapkan koordinat segitiga
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuff);

        colorHandle = GLES20.glGetUniformLocation(glProgram, "vColor");


        GLES20.glUniform4fv(colorHandle, 1, warna, 0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        GLES20.glDisableVertexAttribArray(positionHandle);
    }

    public Segitiga() {
        int vertexShader = SurfaceViewGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = SurfaceViewGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        // membuat semua program OpenGL kosong
        glProgram = GLES20.glCreateProgram();

        // menambahkan vertext shader pada program
        GLES20.glAttachShader(glProgram, vertexShader);

        // menambahkan fragment shader pada program
        GLES20.glAttachShader(glProgram, fragmentShader);

        // membuat progam GL dapat dieksekusi
        GLES20.glLinkProgram(glProgram);

        ByteBuffer bb = ByteBuffer.allocateDirect(
                koordinatSegitiga.length * 4);

        bb.order(ByteOrder.nativeOrder());
        vertexBuff = bb.asFloatBuffer();

        //menaruh koordinat segitiga pada buffer
        vertexBuff.put(koordinatSegitiga);
        vertexBuff.position(0);
    }
}

