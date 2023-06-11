package renderer.component;

import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.LineRendererDataHandler;

public class CollisionBoxGraphics {
    private float[] points;
    private int[] indices;

    public CollisionBoxGraphics(Vector2f position, Vector2f size) {
        this.points = generatePoints(position, size);
        this.indices = generateIndices();
    }








    /*
    *  3-----------4
    *  |           |
    *  |           |
    *  |           |
    *  1-----------2
    *
    * */
    public int[] generateIndices() {
        int[] buffer = new int[10];

        buffer[0] = LineRendererDataHandler.requestIndices();
        buffer[1] = buffer[0] + 1;
        buffer[2] = buffer[0];
        buffer[3] = buffer[0] + 2;
        buffer[4] = buffer[0] + 2;
        buffer[5] = buffer[0] + 3;
        buffer[6] = buffer[0] + 3;
        buffer[7] = buffer[0] + 1;

        return buffer;
    }

    public float[] generatePoints (Vector2f pos, Vector2f size) {
        float[] buffer = new float[4 * 6];
        Vector4f color = new Vector4f (1.0f, 0.0f, 0.0f, 1.0f);
        Vector2f rightBottomPoint = new Vector2f(pos.x + size.x, pos.y);
        Vector2f leftUpPoint = new Vector2f(pos.x, pos.y + size.y);
        Vector2f rightUpPoint = new Vector2f(pos.x + size.x, pos.y + size.y);
        buffer[0] = pos.x;
        buffer[1] = pos.y;
        buffer[2] = color.x;
        buffer[3] = color.y;
        buffer[4] = color.z;
        buffer[5] = color.w;

        buffer[6] = rightBottomPoint.x;
        buffer[7] = rightBottomPoint.y;
        buffer[8] = color.x;
        buffer[9] = color.y;
        buffer[10] = color.z;
        buffer[11] = color.w;

        buffer[12] = leftUpPoint.x;
        buffer[13] = leftUpPoint.y;
        buffer[14] = color.x;
        buffer[15] = color.y;
        buffer[16] = color.z;
        buffer[17] = color.w;

        buffer[18] = rightUpPoint.x;
        buffer[19] = rightUpPoint.y;
        buffer[20] = color.x;
        buffer[21] = color.y;
        buffer[22] = color.z;
        buffer[23] = color.w;

        return buffer;
    }



}
