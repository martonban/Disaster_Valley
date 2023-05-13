package renderer;


import core.os.AssetPool;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15C.glBindBuffer;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;



public class LineRenderBatch {

    private final int FLOAT_SIZE_IN_BYTES = 4;
    private final int POINT_COORDS_SIZE = 2;
    private final int POINT_COLOR_SIZE = 4;
    private final int POINT_SIZE = 6;
    private final int POINT_SIZE_IN_BYTES = POINT_SIZE * FLOAT_SIZE_IN_BYTES;
    private final int POINT_COORDS_OFFSET = 0;
    private final int POINT_COLOR_OFFSET = POINT_COORDS_OFFSET + POINT_COLOR_SIZE * Float.BYTES;

    private float[] points;

    private int vaoID, vboID;
    private Shader shader;

    public LineRenderBatch(float[] points) {
        shader = AssetPool.getShader("assets/shaders/line.glsl");
        shader.compile();
        this.points = points;
    }

    public void start() {
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, points.length * Float.BYTES, GL_DYNAMIC_DRAW);

        int eboID = glGenBuffers();
        int[] indices = generateIndices();

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

        glVertexAttribPointer(0, POINT_COORDS_SIZE, GL_FLOAT, false, POINT_SIZE_IN_BYTES, POINT_COORDS_OFFSET);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, POINT_COLOR_SIZE, GL_FLOAT, false, POINT_SIZE_IN_BYTES, POINT_COLOR_OFFSET);
        glEnableVertexAttribArray(1);
    }

    public void render() {
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferSubData(GL_ARRAY_BUFFER, 0, points);

        shader.use();

        glBindVertexArray(vaoID);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_LINES, points.length/2, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);

    }

    private int[] generateIndices() {
        int numberOFPoints = points.length/6;
        int[] elements = new int[numberOFPoints];
        for(int i = 0; i < numberOFPoints; i++) {
            elements[i] = i;
        }
        return elements;
    }
}
