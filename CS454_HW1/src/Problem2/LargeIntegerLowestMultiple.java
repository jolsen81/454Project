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
    private ArrayList<Integer> language;
    private ArrayList<Integer> augLanguage;
    private Boolean valueExits;
    
    public LargeIntegerLowestMultiple(String integer, ArrayList<Integer> language){
        this.val = new BigInteger(integer);
        this.language = language;
        this.language.sort(null);
        this.createAugLang();
    }
    
    private void createAugLang(){
        this.augLanguage = new ArrayList<>();
        this.augLanguage.add(0);
        this.augLanguage.add(2);
        this.augLanguage.add(3);
        this.augLanguage.add(4);
        this.augLanguage.add(6);
        this.augLanguage.add(7);
        this.augLanguage.add(8);
        
        for(int i = 0; i < this.language.size(); i++){
           if(this.language.get(i)%2 != 0 && this.language.get(i) != 3 & this.language.get(i) != 7){
               this.augLanguage.add(this.language.get(i));
           }
       }
        this.augLanguage.sort(null);
    }
}
