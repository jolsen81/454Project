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
    HashListItem next;
    ArrayList<hashNode> nodeArray;
    
    public HashListItem(){
        HashListItem next = null;
        nodeArray =  new ArrayList<>();
    }
    
    public Boolean update(hashNode node, int atListItem){
        //Move through the list to find where node can be placed if not at last item
        if(atListItem > 0){
            //If destination is beyond the end of the list, extend the list
            if(this.next == null){
                this.next = new HashListItem();
            }
            return this.next.update(node, atListItem-1);
        }
        
        return this.update(node);
    }
    
    private Boolean update(hashNode node){
        if(this.nodeArray.isEmpty()){
            this.nodeArray.add(node);
            //indicate that this node is not in the table
            return false;
        }
        
        BigInteger valToFind = node.stateValue();
        int cost = node.fOfN();
        
        int low = 0;
        int high = this.nodeArray.size()-1;
        
        while(low <= high){
            int mid = (low+high)/2;
            if(this.nodeArray.get(mid).stateValue().equals(valToFind) &&
                    this.nodeArray.get(mid).fOfN() > cost &&
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
            if(this.nodeArray.get(mid).stateValue().compareTo(valToFind) == -1){
                low = mid + 1;
            }
            else{
                high = mid -1;
            }
            
            if(low > high){
                //node not found
                this.nodeArray.add(mid+1, node);
                return false;
            }
        }
        return false;
    }
}
