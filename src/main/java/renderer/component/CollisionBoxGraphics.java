package renderer.component;

import org.joml.Vector2f;
import org.joml.Vector2i;

public class CollisionBoxGraphics {
    private float[] points;
    private int[] indices;

    public CollisionBoxGraphics(Vector2f position, Vector2i indices) {
        this.points = generatePoints(position);
        this.indices = generateIndices(indices);
    }



}
