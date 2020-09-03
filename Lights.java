
import java.util.ArrayList;
import static java.util.Collections.max;

/**
 * 07/02/2020
 * @author Thomas Hunt
 * @author Josh Mullin
 */

/**
 * Lights
 * Class that takes a circuit of lights and switches and finds a step by step solution
 * to turn all of the lights off
 */
public class Lights{

  
  private ArrayList<Character> switches;
  private ArrayList<String> connections;
  
  /*
   * Constructor
   * @param switches an ArrayList containing the switches of the circuit
   * @param connection an ArrayList conatining the connections of the circuit
   */
    public Lights(ArrayList<Character> switches, ArrayList<String> connections ) {
    this.switches = switches;
    this.connections = connections;
    }
  
  
  
  /*
   * This method finds a solution to turn all of the lights in a circuit off
   * @return steps an arrayList containing the steps to turn the lights off, or an ArrayList containing impossible if their is no solution
   */
  public ArrayList<Character> solve(){
   
    boolean[] states;//Stores the current state of each light, false = off, true = on
    
    
    Matrix matrix = new Matrix(switches.size());
    boolean[][] adjMatrix = matrix.getMatrix();
    states = new boolean[switches.size()];
    
    //Sets all lights to be on by default
    for(int i = 0; i < switches.size(); i++){
      states[i] = true; 
    }
    
    //Adds all of the circuit connections as edges in the adjaceny matrix
    for(int i = 0; i < switches.size(); i++){
      //uses positions of the entered swithces to determine their positions in the matrix
      matrix.addEdge(i,i);
      
    }
    //adds a connection for each light to itself
    for(int i = 0; i < connections.size(); i++){
        matrix.addEdge(switches.indexOf(connections.get(i).charAt(0)), switches.indexOf(connections.get(i).charAt(1)));
    }
    
    
    //find the number of connections that each switch has and store this in an arraylist
    ArrayList<Integer> numConnections = new ArrayList<Integer>();
    for(int i = 0; i < switches.size(); i++){
      int count = 0;
      for( int j = 0; j < switches.size(); j++){
        if (adjMatrix[i][j] == true){
          count ++;
        }
      }
      numConnections.add(count);
      }
    
    ArrayList<Character> steps = new ArrayList<Character>(); // ArrayList to store all the steps curently taken to turn lights off
    
    //Check a switches row to see if it has a connection to all of the switches, if so then that's the only step
    //Adds the one step to list of steps and returns.
    for(int i = 0; i < switches.size(); i++){
        if(areAllFalse(adjMatrix[i])){
            steps.add(switches.get(i));
            return(steps);
        }
    }
    
    
    //Find any switches that only connect to themselves and have no other connections to them,  and turns them off
    
    
    int counter = 0;
    for(int row = 0; row < switches.size(); row++){
      if(numConnections.get(row) == 1){
        for(int column = 0; column < switches.size(); column++){
          if(adjMatrix[column][row] == true){
            counter++;
          }
        }
        if(counter == 1){
          states[row] = false;
          steps.add(switches.get(row));
        }
      }
      counter = 0;
    }
    
          
    if(areAllFalse(states)){
      return steps;
    }
    
  
    
    //Check if all lights are off or not, if they are return the steps it took.
    if(areAllFalse(states)){
        return steps;
    }
    
    int count = 0;
    
    //This loop goes through and pushes the buttons in order of most connections to least connections
    while(areAllFalse(states) == false){
        for(int highest = max(numConnections); highest > 1; highest--){
          //If statement for behaviour if light only connects to itself
          if(highest == 1){
            for(int lightIndex = 0; lightIndex < states.length; lightIndex++){//loops through the num connectons to find index of light that only connects to itself
              if(numConnections.get(lightIndex) == highest){
                if(states[lightIndex] == true){ //if the light is on turn it off, otherwise leave it off
                  states[lightIndex] = false;
                  steps.add(switches.get(lightIndex));
                  if(areAllFalse(states)){
                    return steps;
                  }
                }
              }
            }
          }
          else{ //occurs for switches that have more than one connection
            
            for(int lightIndex = 0; lightIndex < states.length; lightIndex++){
                if(numConnections.get(lightIndex) == highest){
                    for(int j = 0; j < states.length; j++){
                        states[j] = adjMatrix[lightIndex][j] ^ states[j]; //XOR the row agaisnt the states
                    }
                    steps.add(switches.get(lightIndex));
                    if(areAllFalse(states)){
                        return steps;
                    }
                    count ++;
                    
                }
            }
          }
        }
        if(count == 300){ //assume their is no sollution once count reaches this value
            return null;
        }
        
        
    }
    
  
    
   return steps;
}
  /*
   * @param array an array of boolean values
   * method checks if the values in the given array are all false or not
   * */
  public static boolean areAllFalse(boolean[] array){
      for(int i = 0; i < array.length; i++){
          if(array[i] == true){
              return false;
          }
      }
      return true;
  }
  
}
    