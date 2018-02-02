package blacklinden.kanvasz;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Vitya on 2018. 01. 23..
 */

public class kendR {
    private List<String> verticesList;
    private List<String> facesList;
    private FloatBuffer verticesBuffer;
    private ShortBuffer facesBuffer;
    private String vertexShaderCode;
    private String fragmentShaderCode;
    private int program;

    public kendR(Context context) {
        verticesList = new ArrayList<>();
        facesList = new ArrayList<>();

        Scanner scanner = null;
        try {
            scanner = new Scanner(context.getAssets().open("raw/bobitatancol_obj"));
        } catch (IOException e) {
            e.printStackTrace();
        }

// Loop through all its lines
        if (scanner != null) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.startsWith("v ")) {
                    // Add vertex line to list of vertices
                    verticesList.add(line);
                } else if(line.startsWith("f ")) {
                    // Add face line to faces list
                    facesList.add(line);
                }
            }

        }
        // Close the scanner
        assert scanner != null;
        scanner.close();

        // Create buffer for vertices
        ByteBuffer buffer1 = ByteBuffer.allocateDirect(verticesList.size() * 3 * 4);
        buffer1.order(ByteOrder.nativeOrder());
        verticesBuffer = buffer1.asFloatBuffer();


        // Create buffer for faces
        ByteBuffer buffer2 = ByteBuffer.allocateDirect(facesList.size() * 3 * 2);
        buffer2.order(ByteOrder.nativeOrder());
        facesBuffer = buffer2.asShortBuffer();



        for(String vertex: verticesList) {
            String coords[] = vertex.split(" ",12); // Split by space
            float x = Float.parseFloat(coords[1]);
            float y = Float.parseFloat(coords[2]);
            float z = Float.parseFloat(coords[3]);
            verticesBuffer.put(x);
            verticesBuffer.put(y);
            verticesBuffer.put(z);
        }
        verticesBuffer.position(0);

        for(String face: facesList) {
            String vertexIndices[] = face.split("[ /]+");

            int vertex1 = Integer.parseInt(vertexIndices[1]);
            int vertex2 = Integer.parseInt(vertexIndices[2]);
            int vertex3 = Integer.parseInt(vertexIndices[3]);
            facesBuffer.put((short)(vertex1 - 1));
            facesBuffer.put((short)(vertex2 - 1));
            facesBuffer.put((short)(vertex3 - 1));
        }
        facesBuffer.position(0);

        // Convert vertex_shader.txt to a string
        InputStream vertexShaderStream =
                context.getResources().openRawResource(R.raw.vertex_shader);
        try {
            vertexShaderCode =
                    IOUtils.toString(vertexShaderStream, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            vertexShaderStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

// Convert fragment_shader.txt to a string
        InputStream fragmentShaderStream =
                context.getResources().openRawResource(R.raw.fragment_shader);
        try {
            fragmentShaderCode =
                    IOUtils.toString(fragmentShaderStream, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fragmentShaderStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // More code goes here

        // Open the OBJ file with a Scanner

        int vertexShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        GLES20.glShaderSource(vertexShader,vertexShaderCode);

        int fragmentShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(fragmentShader, fragmentShaderCode);
    setProgram(vertexShader,fragmentShader);

    }

private void setProgram(int vertexShader, int fragmentShader){
    program = GLES20.glCreateProgram();
    GLES20.glAttachShader(program, vertexShader);
    GLES20.glAttachShader(program, fragmentShader);
    GLES20.glLinkProgram(program);
    GLES20.glUseProgram(program);
}

public void draw(){
    float[] projectionMatrix = new float[16];
    float[] viewMatrix = new float[16];

    float[] productMatrix = new float[16];

    Matrix.frustumM(projectionMatrix, 0,
            -1, 1,
            -1, 1,
            2, 9);

    Matrix.setLookAtM(viewMatrix, 0,
            0, 3, -4,
            0, 0, 0,
            0, 1, 0);

    Matrix.multiplyMM(productMatrix, 0,
            projectionMatrix, 0,
            viewMatrix, 0);

    int matrix = GLES20.glGetUniformLocation(program, "matrix");
    GLES20.glUniformMatrix4fv(matrix, 1, false, productMatrix, 0);

    int position = GLES20.glGetAttribLocation(program, "position");
    GLES20.glEnableVertexAttribArray(position);
    GLES20.glVertexAttribPointer(position,
            3, GLES20.GL_FLOAT, false, 3 * 4, this.verticesBuffer);

   GLES20.glDrawElements(GLES20.GL_TRIANGLES,
            facesList.size() * 3, GLES20.GL_UNSIGNED_SHORT, this.facesBuffer);

    GLES20.glDisableVertexAttribArray(position);
}


}
