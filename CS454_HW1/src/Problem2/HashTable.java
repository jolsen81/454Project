/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problem2;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author jeremyolsen
 */
public class HashTable {
    private HashListItem[] hashList;
    private int MAX_SIZE_ARR = 100000;
    private int MAX_SIZE_SUB = Integer.MAX_VALUE;
    
    public HashTable(){
        this.hashList = new HashListItem[this.MAX_SIZE_ARR];
        for(int i = 0; i < this.MAX_SIZE_ARR; i++){
            this.hashList[i] = new HashListItem();
        }
    }
    
    public Boolean update(StateNode node){
        BigInteger arrIdxBI = new BigInteger(Integer.toString(this.MAX_SIZE_ARR));
        BigInteger nodeState = node.stateValue();
        arrIdxBI = nodeState.mod(arrIdxBI);
        int arrIndex = arrIdxBI.intValue();
        
        arrIdxBI = new BigInteger(Integer.toString(this.MAX_SIZE_SUB));
        arrIdxBI = nodeState.divide(arrIdxBI);
        
        int subIndex = arrIdxBI.intValue();
        
        return this.hashList[arrIndex].update(node, subIndex);
    }
}
