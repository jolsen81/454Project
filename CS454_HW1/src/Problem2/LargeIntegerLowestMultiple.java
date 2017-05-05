/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problem2;
import java.math.BigInteger;
import java.util.*;
import java.io.*;
/**
 *
 * @author jeremyolsen
 */
public class LargeIntegerLowestMultiple {
    private BigInteger val;
    private ArrayList<Integer> alphabet;
    private ArrayList<Integer> augAlphabet;
    private Boolean valueExits;
    
    public LargeIntegerLowestMultiple(String integer, ArrayList<Integer> alphabet){
        this.val = new BigInteger(integer);
        this.alphabet = alphabet;
        this.alphabet.sort(null);
        this.createAugLang();
    }
    
    private void createAugLang(){
        this.augAlphabet = new ArrayList<>();
        this.augAlphabet.add(0);
        this.augAlphabet.add(2);
        this.augAlphabet.add(3);
        this.augAlphabet.add(4);
        this.augAlphabet.add(6);
        this.augAlphabet.add(7);
        this.augAlphabet.add(8);
        
        for(int i = 0; i < this.alphabet.size(); i++){
           if(this.alphabet.get(i)%2 != 0 && this.alphabet.get(i) != 3 & this.alphabet.get(i) != 7){
               this.augAlphabet.add(this.alphabet.get(i));
           }
       }
        this.augAlphabet.sort(null);
    }
    
    private void minString(){
    	//boolean result found
    	//put hashtable here: each node will need a closed boolean, parent node value and the alphabet value that resulted for that state
    	
    	//initialize a starting hashnode that starts with zero
    	//find hashnode states from starting hashnode and insert into hashtable and open set and sort open set
    	//These nodes should have a parent of -1 to show they are the first nodes, therefore the end of the possible string value
    	//Pop front of open set into hashnode v, change to closed
    	//if v is goal node, resultFound = true
    	//while openset != 0 and !resultNode
			//Create transition states of v into hasnodes u, see if nodes u is in hashtable, if ! in hashtable, insert into hashtable and openset
			//if statevalue is in hastable and not closed, update value
			//else check next u in v
			//resort openset
    		//pop next item in openset and set to closed
    		//if next v is goal node, resultFound = true
    	
    	//if resultFound = true; begin backtrace based on parent nodes state value
    	//else return "no lowest multiple was found given alphabet"
    	
    }
    
    //private ArrayList<HashNode> transitions(HashNode n){
	    //ArrayList<HashNode> trans = new ArrayList<>();
	    
	    //for(int i = 0; i < this.augAlphabet.size(); i++){
	    //	HashNode temp;
	    //	BigInteger ten = new BigInteger("10");
	    //	BigInteger parentVal = n.stateValue();
	    //	BigInteger alphabetVal = new BigInteger(Integer.toString(this.augAlphabet.get(i);
	    //	BigInteger childStateVal = new BigInteger();
	    //	childStateVal = parentVal.multiply(ten);
	    //	childStateVal = childStateVal.add(alphabetVal);
	    
	    //	temp = new HashNode(childStateVal, parentVal, this.augAlphabet);
	    // trans.add(temp);
	    //}
	    
	    //return trans;
    //}
}
