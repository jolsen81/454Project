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
        v = openSet.remove(0);
        v.setClosed();
        
        //Create transitions of v from alphabet
        ArrayList<StateNode> temp = this.transitions(v);
        for(int i = 0; i < temp.size(); i++){
            openSet.add(temp.get(i));
        }
        
        //Sort openSet by f(n) value
        Collections.sort(openSet);
        
        
//    	//if v is goal node, resultFound = true
        Boolean resultFound = v.stateValue().equals(new BigInteger("0"));
        
        //Check to see if first state it goal state, if not, keep expanding
        if(!resultFound){
            do{
                //Grab next lowest value from openSet and set to closed
                v = openSet.remove(0);
                v.setClosed();
                
                //Find transitions from v using alphabet
                temp = this.transitions(v);
                for(int i = 0; i < temp.size(); i++){
                    openSet.add(temp.get(i));
                }
                Collections.sort(openSet);
                
                //Check to see if current v is goal state, if so, exit while loop
                resultFound = v.stateValue().equals(new BigInteger("0"));
            }while(!openSet.isEmpty() && !resultFound);
        }
    	//if resultFound = true; begin backtrace based on parent nodes state value
    	//else return "no lowest multiple was found given alphabet"
        if(resultFound){
            System.out.print("Lowest multiple found: ");
            this.backTrace(v);
        }
        else{
            System.out.println("No multiple found.");
        }
    	
    }
    
    private ArrayList<StateNode> transitions(StateNode n){
        
        //Create a temporary array to hold new transition states
        ArrayList<StateNode> trans = new ArrayList<>();
        
        //Set the n's state value as the parent value for all transitions
        BigInteger parentVal = n.stateValue();
        
        //Loop through alphabet and create new transitions from n
        for(int i = 0; i < this.alphabet.size(); i++){
            //Temp StateNode for storing a transition from n
            StateNode temp;
            
            //transition = ((10*parentVal)+alphabet.get)(i)%this.val
            BigInteger ten = new BigInteger("10");
            BigInteger alphabetVal = new BigInteger(Integer.toString(this.alphabet.get(i)));
            BigInteger childStateVal = new BigInteger("0");
            childStateVal = parentVal.multiply(ten);
            childStateVal = childStateVal.add(alphabetVal);
            childStateVal = childStateVal.mod(this.val);

            //Set temps state value, parent StateNode, cost of getting to node, and 
            //the alphabet value that caused n to reach temp
            temp = new StateNode(childStateVal, n, n.cost(), 
                    this.alphabet.get(i));

            //If the state value of temp is not already in the hashtable, add
            //temp to the temporary array to be added to the openset
            if(!this.hTable.update(temp)){
                trans.add(temp);
            }
            
        }
        //Keep track of the number of transitions states created
        this.numStates = this.numStates.add(new BigInteger(Integer.toString(trans.size())));
        return trans;
    }
    
    private void backTrace(StateNode n){
        //Minimum multiple found, but is only a value of 0
        if(n.parentStateValue() == null){
            System.out.print("0");
            return;
        }
        ArrayList <Integer> string = new ArrayList<>();
        StateNode temp = n;
        //Backtrace and collect the string of the lowest multiple from the given alphabet
        //in reverse order
        while(temp.parentStateValue() != null){
            string.add(temp.alpha());
            temp = temp.parentStateValue();
        }
        System.out.println();
        
        //Tell the user the size of the string
        System.out.println("String size: " + string.size());
        
        //Create an iterator that starts at the back of the string array list
        ListIterator<Integer> iterator = string.listIterator(string.size());
       
        //Print the string
        while(iterator.hasPrevious()){
            System.out.print(iterator.previous());
        }
        
        System.out.println();
        //Show the user the number of states created during computation
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
