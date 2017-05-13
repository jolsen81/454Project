
// StateNode class for our hash table
package Problem2;
import java.math.BigInteger;
import java.util.*;
//import java.util.ArrayList;

public class StateNode implements Comparable{

  // class member variables
  private BigInteger childStateValue;
  private StateNode parentValue;
  private Boolean closed;
  private BigInteger fOfN;
  private BigInteger cost;
  private int alpha; //This is the alphabet character that created this state


  // constructor
  public StateNode(BigInteger cSV, StateNode pV, BigInteger cost, int alpha){
    this.childStateValue = cSV;
    this.parentValue = pV;
    this.closed = false;
    this.cost = cost.add(new BigInteger("1"));
    this.fOfN = this.cost;
    this.alpha = alpha;
  }
  
  public BigInteger stateValue(){
      return this.childStateValue;
  }
  
  public StateNode parentStateValue(){
      return this.parentValue;
  }
  
  public void setParentStateValue(StateNode newP){
      this.parentValue = newP;
  }
  
  public Boolean closed(){
      return this.closed;
  }
  
  public BigInteger cost(){
      return this.cost;
  }
  
  public void setCost(BigInteger newCost){
      this.fOfN = newCost;
  }
  
  public BigInteger fOfN(){
      return this.fOfN;
  }
  
  public void setFOfN(BigInteger newF){
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
      StateNode hNode = (StateNode) o;
      
      //Sort by fOfN
      if(!this.fOfN.equals(hNode)){
          return this.fOfN.compareTo(hNode.fOfN);
      }
      //If both value are equal, sort by alphabet
      return this.alpha - hNode.alpha;
    
  }
  
}
