/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problem2;
import java.math.BigInteger;
import java.util.*;
/**
 *
 * @author jeremyolsen
 */
public class HashListItem {
    
    //Array to store StateNodes that are for this HLI
    ArrayList<StateNode> nodeArray;
    
    public HashListItem(){
        
        //Instantiate nodeArray
        nodeArray =  new ArrayList<>();
    }
    
    public Boolean update(StateNode node){
        if(this.nodeArray.isEmpty()){
            this.nodeArray.add(node);
            //indicate that this node is not in the table
            return false;
        }
        
        //Grab the nodes state value to find it's proper placement
        BigInteger valToFind = node.stateValue();
        
        //Grab the cost of getting to state
        BigInteger cost = node.fOfN();
        
        //Start binary search of this.nodeArray
        int low = 0;
        int high = this.nodeArray.size() - 1;
        
        while(low <= high){
            //caclulate mid point
            int mid = (low+high)/2;
            
            
            
            //if stateValue found, the cost is less than the currently stored
            //value in the hash list item, and the state hasn't been closed, then update
            if(this.nodeArray.get(mid).stateValue().equals(valToFind) &&
                    this.nodeArray.get(mid).fOfN().compareTo(cost) > 1 &&
                    !this.nodeArray.get(mid).closed()){
                this.nodeArray.get(mid).setCost(cost);
                this.nodeArray.get(mid).setParentStateValue(node.parentStateValue());
                this.nodeArray.get(mid).setAlpha(node.alpha());
                this.nodeArray.get(mid).setFOfN(node.fOfN());
                //node was in the hash table and updated
                return true;
            }
            else if(this.nodeArray.get(mid).stateValue().equals(valToFind)){
                //found in table, however no update was necessary
                return true;
            }
            //If the stateValue > than current 
            if(this.nodeArray.get(mid).stateValue().compareTo(valToFind) == -1){
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
            
            if(low > high){
                //node not found
                if(this.nodeArray.get(mid).stateValue().compareTo(valToFind) == -1){
                    this.nodeArray.add(mid+1, node);
                }
                else{
                    this.nodeArray.add(mid, node);
                }
                return false;
            }
            
        }
        return false;
    }
}
