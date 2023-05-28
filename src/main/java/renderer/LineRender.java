package renderer;

import core.os.memory.AssetPool;
import core.os.components.Window;
import org.lwjgl.BufferUtils;
import renderer.component.Shader;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15C.glBindBuffer;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;


public class LineRender {

    private final int FLOAT_SIZE_IN_BYTES = 4;
    private final int POINT_COORDS_SIZE = 2;
    private final int POINT_COLOR_SIZE = 4;
    private final int POINT_SIZE = 6;
    private final int POINT_SIZE_IN_BYTES = POINT_SIZE * FLOAT_SIZE_IN_BYTES;
    private final int POINT_COORDS_OFFSET = 0;
    private final int POINT_COLOR_OFFSET = POINT_COORDS_OFFSET + POINT_COORDS_SIZE * Float.BYTES;

    private float[] points = {128.0f, 64.0f, 1.0f, 0.0f, 0.0f, 1.0f,
            192.0f, 64.0f, 1.0f, 0.0f, 0.0f, 1.0f,
            192.0f, 64.0f, 1.0f, 0.0f, 0.0f, 1.0f,};
    private int[] indices = {
            0, 1,
            1, 2

    };

    private int vaoID, vboID;
    private Shader shader;

    public LineRender(float[] points) {
        shader = AssetPool.getShader("assets/shaders/line.glsl");
        shader.compile();
        start();
    }

    public void start() {
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(points.length);
        vertexBuffer.put(points).flip();

        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indices.length);
        indicesBuffer.put(indices).flip();

        int eboID = glGenBuffers();

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer , GL_STATIC_DRAW);

        glVertexAttribPointer(5, POINT_COORDS_SIZE, GL_FLOAT, false, POINT_SIZE_IN_BYTES, 0);
        glEnableVertexAttribArray(5);

        glVertexAttribPointer(6, POINT_COLOR_SIZE, GL_FLOAT, false, POINT_SIZE_IN_BYTES, POINT_COLOR_OFFSET * Float.BYTES);
        glEnableVertexAttribArray(6);
    }

    public void render() {

        //FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(points.length);
        //vertexBuffer.put(points).flip();

        //glBufferData(GL_ARRAY_BUFFER, points.length, GL_DYNAMIC_DRAW);
        //glBufferSubData(GL_ARRAY_BUFFER, 0, points);

        shader.uploadMat4f("uProjection", Window.getScene().camera().getProjectionMatrix());
        shader.uploadMat4f("uView", Window.getScene().camera().getViewMatrix());
        shader.use();

        glBindVertexArray(vaoID);
        glEnableVertexAttribArray(5);
        glEnableVertexAttribArray(6);

        glDrawElements(GL_LINES, points.length, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(5);
        glDisableVertexAttribArray(6);
        glBindVertexArray(0);
        shader.detach();
    }
}
