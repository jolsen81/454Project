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
    private BigInteger numStates;
    
    public LargeIntegerLowestMultiple(String integer, ArrayList<Integer> alphabet){
        this.val = new BigInteger(integer);
        this.alphabet = alphabet;
        this.alphabet.sort(null);
        this.createAugLang();
        this.hTable = new HashTable(this.val);
        this.numStates = new BigInteger("0");
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
    	//initialize a starting hashnode that starts with zero
        StateNode v = new StateNode(new BigInteger("0"), null, new BigInteger("0"), 0);
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
//    	//if v is goal node, resultFound = true
        Boolean resultFound = v.stateValue().equals(new BigInteger("0"));
        if(!resultFound){
            do{
                
                v = openSet.remove(0);
                v.setClosed();
                temp = this.transitions(v);
                for(int i = 0; i < temp.size(); i++){
                    openSet.add(temp.get(i));
                }
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
	    
	    	temp = new StateNode(childStateVal, n, n.cost(), 
                        this.alphabet.get(i));
                
                if(!this.hTable.update(temp)){
                    this.numStates = this.numStates.add(new BigInteger("1"));
                    if(this.numStates.compareTo(this.val) == 1){
                        System.out.println("numStates is too large: " + this.numStates.toString());
                    }
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
            string.add(temp.alpha());
            temp = temp.parentStateValue();
        }
        System.out.println();
        System.out.println("String size: " + string.size());
        ListIterator<Integer> iterator = string.listIterator(string.size());
       
        while(iterator.hasPrevious()){
            System.out.print(iterator.previous());
        }
        
        System.out.println();
        System.out.println("Number of states created: " + this.numStates.toString());
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
