package branchandbound;


import util.tsp.TSPInstance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Compute the cheapest incident edges lower bound for the TSP
 * For each node, the cheapest incoming edge is selected.
 * The sum of these edges is a lower bound on the TSP
 */
public class CheapestIncidentLowerBound implements TSPLowerBound {




    public CheapestIncidentLowerBound() {
    }

    @Override
    public TSPLowerBoundResult compute(double [][] distanceMatrix, boolean [][] excludedEdges) {
        int n = distanceMatrix.length;
        double lowerBound = 0;
        List<Edge> selectedEdges = new LinkedList<>(); // List to store the selected edges

        for (int i = 0; i < n; i++) {
            double min = Double.POSITIVE_INFINITY;
            int IndOfMin = -1;
            for (int j = 0; j < n; j++) {
                if (i!=j && !excludedEdges[j][i] ){
                    if (distanceMatrix[i][j] < min) {
                        min = distanceMatrix[i][j];
                        IndOfMin = j;
                    }
                }
            }
            selectedEdges.add(new Edge(IndOfMin, i, min));
            lowerBound += distanceMatrix[i][IndOfMin];
        }
        return new TSPLowerBoundResult(lowerBound, selectedEdges);
    }
}
