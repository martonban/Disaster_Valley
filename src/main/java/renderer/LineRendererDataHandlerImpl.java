package renderer;

import java.util.ArrayList;

public class LineRendererDataHandlerImpl implements LineRendererDataHandler {
    private ArrayList <Float> points = new ArrayList<>();
    private ArrayList <Integer> indices = new ArrayList<>();


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

    public int requestIndices() {
        return indices.get(indices.size() - 1);
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
