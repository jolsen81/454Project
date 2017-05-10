/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problem2;
import java.math.BigInteger;
import java.util.*;
import java.util.Scanner;
/**
 *
 * @author jeremyolsen
 */
public class LargeIntegerLowestMultiple {
    private BigInteger val;
    private ArrayList<Integer> alphabet;
    private ArrayList<Integer> augAlphabet;
    private Boolean valueExits;
    private HashTable hTable;
    
    public LargeIntegerLowestMultiple(String integer, ArrayList<Integer> alphabet){
        this.val = new BigInteger(integer);
        this.alphabet = alphabet;
        this.alphabet.sort(null);
        this.createAugLang();
        this.hTable = new HashTable();
        this.minString();
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
        StateNode v = new StateNode(new BigInteger("0"), null, this.augAlphabet, 0, 0, val.toString().length());
        ArrayList<StateNode> openSet = this.transitions(v);
        Collections.sort(openSet);
//    	//Pop front of open set into hashnode v, change to closed
//        
        v = openSet.remove(0);
        v.setClosed();
        ArrayList<StateNode> temp = this.transitions(v);
                for(int i = 0; i < temp.size(); i++){
                    openSet.add(temp.get(i));
                }
                Collections.sort(openSet);
        for(int i = 0; i < openSet.size(); i++){
            System.out.println("State Value: " + openSet.get(i).stateValue().toString() +
                    " alpha: " + openSet.get(i).alpha() + " c(n): " + openSet.get(i).cost());
        }
//        v.setClosed();
//    	//if v is goal node, resultFound = true
        Boolean resultFound = v.stateValue().equals(new BigInteger("0"));
        if(!resultFound){
            do{
                
                v = openSet.remove(0);
                v.setClosed();
                if(v.stateValue().equals(new BigInteger("11"))){
                    System.out.println("v = 11");
                }
                temp = this.transitions(v);
                for(int i = 0; i < temp.size(); i++){
                    openSet.add(temp.get(i));
                }
//                for(int i = 0; i < openSet.size(); i++){
//            System.out.println("State Value: " + openSet.get(i).stateValue().toString() +
//                    " alpha: " + openSet.get(i).alpha() + " c(n): " + openSet.get(i).cost());
//        }
                Collections.sort(openSet);
//                System.out.println(openSet.size());
                resultFound = v.stateValue().equals(new BigInteger("0"));
            }while(!openSet.isEmpty() && !resultFound);
        }
    	//if resultFound = true; begin backtrace based on parent nodes state value
    	//else return "no lowest multiple was found given alphabet"
        if(resultFound){
            System.out.print("Minstring found: ");
            this.backTrace(v);
        }
        else{
            System.out.println("No minimum string found");
        }
    	
    }
    
    private ArrayList<StateNode> transitions(StateNode n){
	    ArrayList<StateNode> trans = new ArrayList<>();
	    
	    for(int i = 0; i < this.alphabet.size(); i++){
	    	StateNode temp;
	    	BigInteger ten = new BigInteger("10");
	    	BigInteger parentVal = n.stateValue();
	    	BigInteger alphabetVal = new BigInteger(Integer.toString(this.alphabet.get(i)));
	    	BigInteger childStateVal = new BigInteger("0");
	    	childStateVal = parentVal.multiply(ten);
	    	childStateVal = childStateVal.add(alphabetVal);
                childStateVal = childStateVal.mod(this.val);
	    
	    	temp = new StateNode(childStateVal, n, this.augAlphabet, n.cost(), 
                        this.alphabet.get(i), this.val.toString().length());
                
                if(!this.hTable.update(temp)){
                    trans.add(temp);
                }
	    }
	    
	    return trans;
    }
    
    private void backTrace(StateNode n){
        if(n.parentStateValue() == null){
            System.out.print("0");
            return;
        }
        ArrayList <Integer> string = new ArrayList<>();
        StateNode temp = n;
        while(temp.parentStateValue() != null){
            string.add(0, temp.alpha());
            temp = temp.parentStateValue();
        }
        
        for(int i = 0; i < string.size(); i++){
            System.out.print(string.get(i));
        }
        
        System.out.println();
    }
    
    public static void main(String args[]){
        System.out.print("Enter an integer value: ");
        Scanner reader = new Scanner(System.in);
        String intStr =  reader.nextLine();
//        int val = Integer.parseInt(intStr);
        System.out.print("Enter a numbered alphabet 0 - 9 and press enter: ");
        reader = new Scanner(System.in);
        String alphabet = reader.nextLine();
        String[] alpha = alphabet.split(" ");
        ArrayList<Integer> a = new ArrayList<>();
        for(int i = 0; i < alpha.length; i++){
            a.add(Integer.parseInt(alpha[i]));
        }
        LargeIntegerLowestMultiple lILM = new LargeIntegerLowestMultiple(intStr, a);
//        DFASolver dSolver = new DFASolver(val, a);
        
    }
}
