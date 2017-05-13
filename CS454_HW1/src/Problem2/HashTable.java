/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problem2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jeremyolsen
 */
public class HashTable {
    private HashListItem[][] hashList;
    private int MAX_SIZE_ARR = 100000;
    private int MAX_SIZE_SUB = Integer.MAX_VALUE;
    
    public HashTable(BigInteger multiplier){
        
        //Determine the size of the 2ND dimension of the hashList table
        //multiplier/MAX_SIZE_ARR/MAX_SIZE_SUB
        BigInteger twoD = multiplier.divide(new BigInteger(Integer.toString(MAX_SIZE_ARR)));
        twoD = twoD.divide(new BigInteger(Integer.toString(MAX_SIZE_SUB)));
        int size = twoD.intValue()+1;
        this.hashList = new HashListItem[this.MAX_SIZE_ARR][size];
        
    }
    
    public Boolean update(StateNode node){
        //Compute the 1st dimension index
        //stateVAlue%this.MAX_SIZE_ARR
        BigInteger arrIdxBI = new BigInteger(Integer.toString(this.MAX_SIZE_ARR));
        BigInteger nodeState = node.stateValue();
        arrIdxBI = nodeState.mod(arrIdxBI);
        int arrIndex = arrIdxBI.intValue();
        
        //Compute the 2nd dimension index
        //stateValue/this.MAX_SIZE_ARR/this.MAX_SIZE_SUB
        arrIdxBI = new BigInteger(Integer.toString(this.MAX_SIZE_SUB));
        BigInteger maxArr = new BigInteger(Integer.toString(this.MAX_SIZE_ARR));
        maxArr = nodeState.divide(maxArr);
        arrIdxBI = maxArr.divide(arrIdxBI);
        
        int subIndex = arrIdxBI.intValue();
        
        //Check to see if HashListItem at given indecies are null, if so create new
        //instance
        if(this.hashList[arrIndex][subIndex] == null){
            this.hashList[arrIndex][subIndex] = new HashListItem();
        }
        
        //Update the hash table, return false if stateValue is not found,
        //true otherwise
        return this.hashList[arrIndex][subIndex].update(node);
    }
    
    public static void main(String args[]){
    }
}
