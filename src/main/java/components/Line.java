package components;

import org.joml.Vector2f;
import org.joml.Vector4f;

public class Line {

    private Vector2f startPos;
    private Vector2f endPos;
    private Vector4f color;

    public Line(Vector2f startPos, Vector2f endPos, Vector4f color) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.color = color;
    }

    public Line(Vector2f startPos, Vector2f endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.color = new Vector4f(1.0f, 0.0f, 0.0f, 1.0f);
    }

    public Vector2f getStartPos() {
        return startPos;
    }

    public void setStartPos(Vector2f startPos) {
        this.startPos = startPos;
    }

    public Vector2f getEndPos() {
        return endPos;
    }

    public void setEndPos(Vector2f endPos) {
        this.endPos = endPos;
    }

    public Vector4f getColor() {
        return color;
    }

    public void setColor(Vector4f color) {
        this.color = color;
    }
}
