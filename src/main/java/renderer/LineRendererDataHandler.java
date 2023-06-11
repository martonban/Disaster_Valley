package renderer;

import java.util.ArrayList;

public class LineRendererDataHandler {
    private ArrayList <Float> points = new ArrayList<>();
    private static ArrayList <Integer> indices = new ArrayList<>();

    private LineRender lineRender;



    public void render() {
        lineRender.render();
    }

    // TO-DO
    public void changePoint(){}

    public void addNewPointsToTheRenderer(float newPoints[]) {
        for(float i : newPoints) {
            this.points.add(i);
        }
    }

    public void addNewIndicesToTheRenderer(int newIndices[]) {
        for(int i : newIndices) {
            this.indices.add(i);
        }
    }

    public static int requestIndices() {
        return indices.get(indices.size());
    }

    public float[] getPointsData() {
        float[] pointsBuffer = new float[this.points.size()];
        for(int i = 0; i < this.points.size(); i++ ) {
            pointsBuffer[i] = points.get(i);
        }
        return pointsBuffer;
    }

    public int[] getIndicesData() {
        int[] indicesBuffer = new int[this.indices.size()];
        for(int i = 0; i < this.indices.size(); i++ ) {
            indicesBuffer[i] = indices.get(i);
        }
        return indicesBuffer;
    }

}
