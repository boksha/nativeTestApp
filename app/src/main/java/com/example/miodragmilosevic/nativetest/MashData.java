package com.example.miodragmilosevic.nativetest;

/**
 * Created by miodrag.milosevic on 11/24/2017.
 */

public class MashData {
    private int _facetCount;

    public float[] VertexCoords;

    public MashData(int facetCount) {
        _facetCount = facetCount;

        VertexCoords = new float[facetCount];

        // fills up coords with dummy values
        for (int i = 0; i < facetCount; ++i) {
            VertexCoords[i] = 10.0f * i;
        }
    }

    public int getFacetCount() {
        return _facetCount;
    }
}
