/*
 * Problem 2
 */
package Problem2;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Jeremy Olsen & Cody Mccants
 */
public class DFASolver {
    private int x;
    private int strLength;
    private ArrayList<Integer> S;
    private int[][] DFA;
    private Boolean valueExists;
    public DFASolver(int val, ArrayList<Integer> set){
        this.strLength = 0;
        this.x = val;
        this.S = set;
        this.DFA = new int[val+1][set.size()];
        this.valueExists = true;
//        System.out.println("creating transition table");
        this.transition();
//        System.out.println("finding minString");
        this.minString();
    }
    
    private void transition(){
//        //Start State for DFA
//        for(int i = 0; i < this.S.size(); i++){
//            if(this.S.get(i) != 0){
//                this.DFA[x][i] = this.S.get(i);
//            }
//        }
        
        for(int i = 0; i < this.DFA.length; i++){
            for(int j = 0; j < this.S.size(); j++){
                this.DFA[i][j] = (i*10+this.S.get(j))%x;
            }
        }
    }
    
    private void minString(){
        ArrayList<Integer> q = new ArrayList<Integer>();
        Boolean[] visited = new Boolean[this.x+1];
        int[] parent = new int[this.x+1];
        int[] value = new int[this.x+1];
        for(int i = 0; i < visited.length; i++){
            visited[i] = false;
        }
        
        for(int i = 0; i < this.S.size(); i++){
            if(this.S.get(i) != 0 && this.S.get(i)%this.x == 0){
                this.strLength++;
                return;
            }
        }
        //insert the starting state into the queue
        q.add(x);
        
        //the parent of the starting state doesn't exist, set to -1
        parent[x] = -1;
        
        //set the starting state to visited
        visited[x] = true;
        
        //Boolean to see if program is past first state to allow for zeroes
        //to be part of the set S
        Boolean pastFirstState = false;
        while(q.size() > 0 && !visited[0]){
            int u = q.remove(0);
            for(int i = 0; i < this.DFA[u].length; i++){
                if(pastFirstState || this.DFA[u][i] > 0){
                    if(!visited[this.DFA[u][i]]){
                        parent[this.DFA[u][i]] = u;
                        value[this.DFA[u][i]] = this.S.get(i);
                        visited[this.DFA[u][i]] = true;
                        q.add(this.DFA[u][i]);
                    }
                }
            }
            pastFirstState = true;
        }
        
        if(!visited[0]){
//            System.out.println("No value exists");
            this.valueExists = false;
        }
        else{
            ArrayList<Integer> minString = new ArrayList<Integer>();
            minString.add(value[0]);
            int pIndex = parent[0];
            while(parent[pIndex] != -1){
                minString.add(0, value[pIndex]);
                pIndex = parent[pIndex];
            }
//            System.out.print("The smallest string is ");
            for(int i = 0; i < minString.size(); i++){
//                System.out.print(minString.get(i));
                this.strLength++;
            }
        }
//        System.out.println();
    }
    
    public int stringLength(){
        return this.strLength;
    }
    
    public Boolean valueExists(){
        return this.valueExists;
    }
    
    public static void main(String[] args) throws IOException{
//        System.out.print("Enter integer value: ");
        Scanner reader = new Scanner(System.in);
//        int x = reader.nextInt();
        System.out.print("Enter integer set (leave space between integers and end with non-numerical value e.g. 2 3 a): ");
        Scanner reader2 = new Scanner(System.in);
         ArrayList<Integer> set = new ArrayList<Integer>();
         
         ArrayList<Integer> subSet = new ArrayList<>();
        subSet.add(0);
        subSet.add(2);
        subSet.add(3);
        subSet.add(4);
        subSet.add(6);
        subSet.add(7);
        subSet.add(8);
         
         while(reader2.hasNextInt()){     
            int setVal = reader2.nextInt();
            if(setVal > -1 && setVal < 10 && !set.contains(setVal)){
                set.add(setVal);
            }
            else{
                System.out.println(setVal + " exluded from set (either integer is not 1-9 or integer already in set).");
            }
//            reader2 = new Scanner(System.in);
        }
//        }
//        set.add(reader2.nextInt());
       set.sort(null);
       for(int i = 0; i < set.size(); i++){
           System.out.println(set.get(i));
       }
       
       for(int i = 0; i < set.size(); i++){
           if(set.get(i)%2 != 0 && set.get(i) != 3 & set.get(i) != 7){
               subSet.add(set.get(i));
           }
       }
       
       subSet.sort(null);
//        set.add(0);
        for(int j = 1; j < 10000000; j++){
            DFASolver large = new DFASolver(j, set);
            System.out.println(large.stringLength());
            for(int i = 1; i <= j%100000; i++){
                    if(set.size() > 0 && i > 0){
                            DFASolver solve = new DFASolver(7, subSet);
                //            System.out.println(solve.stringLength());
                            if(solve.stringLength() > large.stringLength() && large.valueExists){
                                System.out.println("Large: " + j + " Length: " + large.stringLength());
                                System.out.println("Solve: " + i + " Length: " + solve.stringLength());
                                System.exit(i);
                            }
                    }
                    else{
                        System.out.println("Invalid integer given or empty set. Exiting program.");
                        System.exit(0);
                    }
                }
        }
    }
}
