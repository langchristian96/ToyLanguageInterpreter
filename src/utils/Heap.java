package utils;

import java.io.Serializable;
import java.util.*;


public class Heap implements IHeap,Serializable{

    private static int counter=1;
    private Map<Integer,Integer> d;
    public Heap(){
        d=new HashMap<Integer,Integer>();
    }
    @Override
    public int add(int a) {

        d.put(counter,a);
        return counter++;
    }

    @Override
    public int get(int addr) {
        if(d.containsKey(addr))
        {
            return d.get(addr);
        }
        return 0;
    }

    public void set(int addr,int value){
        if(d.containsKey(addr)){
            d.put(addr,value);
        }

    }

    public void setContent(Map<Integer,Integer> d){
        this.d=d;
    }
    public String toString(){
        return d.toString();
    }


    public Iterable<Map.Entry<Integer,Integer>> getAll(){
        return d.entrySet();
    }


    public Map<Integer,Integer> getContent(){
        return d;
    }

}
