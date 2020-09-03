

/**
 * 07/02/2020
 * @author Thomas Hunt
 * @author Josh Mullin
 */

/**
 * Class Matrix
 * Used to create an adjaceny matrix showing connections between different objects
 */
public class Matrix{
  private boolean adjacencyMatrix[][];
  private int numVertices; //Used only in toString
  public Matrix(int size){
    adjacencyMatrix = new boolean[size][size];
    numVertices = size; //Used only in toString
  }
  
  /**
   * Adds and edge or connection to the matrix
   */
  public void addEdge(int i, int j){
    adjacencyMatrix[i][j] = true;
    //adjacencyMatrix[j][i] = true;
    //above commented out line is only used for an undirected graph
  }
  
  /**
   * Removes and edge from the matrix
   */
  public void removeEdge(int i, int j){
    adjacencyMatrix[i][j] = false;
    //adjacencyMatrix[j][i] = false;
  }
  
  /**
   * Return true if there is a connection between two switches
   */
  public boolean isEdge(int i, int j){
    return adjacencyMatrix[i][j];
  }
  
  /**
   *Print a representation of the adjacency matrix
   */
  public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < numVertices; i++) {
            s.append(i + ": ");
            for (boolean j : adjacencyMatrix[i]) {
                s.append((j?1:0) + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
  
  /**
   * Getter method
   * @returns the adjacency matrix
   */
  public boolean[][] getMatrix(){
    return adjacencyMatrix;
  }
}