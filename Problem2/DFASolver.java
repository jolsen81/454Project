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
    private ArrayList<Integer> S;
    private int[][] DFA;
    public DFASolver(int val, ArrayList<Integer> set){
        this.x = val;
        this.S = set;
        this.DFA = new int[val+1][set.size()];
        System.out.println("creating transition table");
        this.transition();
        System.out.println("finding minString");
        this.minString();
    }
    
    private void transition(){
        for(int i = 0; i < this.S.size(); i++){
            this.DFA[x][i] = this.S.get(i);
        }
        
        for(int i = 0; i < this.x; i++){
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
        
        //insert the starting state into the queue
        q.add(x);
        
        //the parent of the starting state doesn't exist, set to -1
        parent[x] = -1;
        
        //set the starting state to visited
        visited[x] = true;
        
        while(q.size() > 0 && !visited[0]){
            int u = q.remove(0);
            for(int i = 0; i < this.DFA[u].length; i++){
                if(!visited[this.DFA[u][i]]){
                    parent[this.DFA[u][i]] = u;
                    value[this.DFA[u][i]] = this.S.get(i);
                    visited[this.DFA[u][i]] = true;
                    q.add(this.DFA[u][i]);
                }
            }
        }
        
        if(!visited[0]){
            System.out.println("No value exists");
        }
        else{
            ArrayList<Integer> minString = new ArrayList<Integer>();
            minString.add(value[0]);
            int pIndex = parent[0];
            while(parent[pIndex] != -1){
                minString.add(0, value[pIndex]);
                pIndex = parent[pIndex];
            }
            System.out.print("The smallest string is ");
            for(int i = 0; i < minString.size(); i++){
                System.out.print(minString.get(i));
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) throws IOException{
        System.out.print("Enter integer value: ");
        Scanner reader = new Scanner(System.in);
        int x = reader.nextInt();
        System.out.print("Enter integer set (leave space between integers and end with non-numerical value e.g. 2 3 a): ");
        Scanner reader2 = new Scanner(System.in);
         ArrayList<Integer> set = new ArrayList<Integer>();
         
         while(reader2.hasNextInt()){     
            int setVal = reader2.nextInt();
            if(setVal > 0 && setVal < 10 && !set.contains(setVal)){
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
//        set.add(0);
    if(set.size() > 0 && x > 0){
            DFASolver solve = new DFASolver(x, set);
    }
    else{
        System.out.println("Invalid integer given or empty set. Exiting program.");
    }
    }
}
