
// hashNode class for our hash table
package Problem2;
import java.math.BigInteger;
import java.util.*;
//import java.util.ArrayList;

public class hashNode implements Comparable{

  // class member variables
  private BigInteger childStateValue;
  private hashNode parentValue;
  private ArrayList <Integer> augmentedAlphabet;
  private Boolean closed;
  private int fOfN;
  private int cost;
  private int alpha; //This is the alphabet character that created this state


  // constructor
  public hashNode(BigInteger cSV, hashNode pV, ArrayList <Integer> augAlph, int cost, int alpha, int minStrLength){
    this.childStateValue = cSV;
    this.parentValue = pV;
    this.augmentedAlphabet = augAlph;
    this.closed = false;
    this.cost = cost + 1;
    this.fOfN = cost;// + (minStrLength - this.childStateValue.toString().length());
    this.alpha = alpha;
  }
  
  public BigInteger stateValue(){
      return this.childStateValue;
  }
  
  public hashNode parentStateValue(){
      return this.parentValue;
  }
  
  public void setParentStateValue(hashNode newP){
      this.parentValue = newP;
  }
  
  public Boolean closed(){
      return this.closed;
  }
  
  public int cost(){
      return this.cost;
  }
  
  public void setCost(int newCost){
      this.fOfN = newCost;
  }
  
  public int fOfN(){
      return this.fOfN;
  }
  
  public void setFOfN(int newF){
      this.fOfN = newF;
  }
  
  public void setAlpha(int newAlpha){
      this.alpha = newAlpha;
  }
  
  public int alpha(){
      return this.alpha;
  }
  
  public void setClosed(){
      this.closed = true;
  }
  
  @Override
  public int compareTo(Object o){
      hashNode hNode = (hashNode) o;
      if(this.fOfN != hNode.fOfN){
          return this.fOfN - hNode.fOfN;
      }
      
      return this.alpha - hNode.alpha;
    
  }
  
}
