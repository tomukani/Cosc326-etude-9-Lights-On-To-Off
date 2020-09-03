

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 07/02/2020
 * @author Thomas Hunt
 * @author Josh Mullin
 */
public class Main {
  
  
  
  public static boolean isAlpha(String string){
     return string.matches("[a-zA-Z]+");
   }

   public static void main(String[] args) {
   
  ArrayList<Character> switches = new ArrayList<Character>();
  ArrayList<String> connections = new ArrayList<String>();
  Scanner scan = new Scanner(System.in);
  //        if (args
  for (;;){
    switches.clear();
    connections.clear();
    
    try {
      System.out.println("Enter a set of lights");
      String switchline = scan.nextLine().replace(" ", "");
      if(switchline.matches("[a-zA-Z]+") == false){ //Checks that input is valid letters
        System.out.println("Error: Please enter single space English letters only");
      }
      else{
      for (int i = 0; i < switchline.length(); i++){
        switches.add(new Character(switchline.charAt(i)));
      }
      System.out.println("Enter a set of edges");
      
      String connectionLine = scan.nextLine();
      if(connectionLine.matches("[a-zA-Z ]+") == false){ // Checks that input is valid
        System.out.println("Error: Please enter single space English letters only");
      }
      else{
      String[] connectionLineArr = connectionLine.split(" ");
      for (String str: connectionLineArr){
        connections.add(str);
      }
      
      Lights lights = new Lights(switches, connections);
      
      //System.out.println("switches: " + switches.toString());
      //System.out.println("connections: " + connections.toString() + "\n");
      ArrayList<Character> result = lights.solve();
      if(result == null){
        System.out.println("Impossible");
      }
      else{
        for(int i = 0; i < result.size(); i++){
          System.out.print(result.get(i) + " ");
        }
        System.out.println(" ");
      }
      }
      }
    } catch (java.util.NoSuchElementException e){
      System.err.println("EOF Recieved/Reached");
      break;
    }
  }
}
   
   
}
