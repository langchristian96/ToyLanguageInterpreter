package utils;

/**
 * Created by langchristian96 on 1/25/2017.
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import java.io.Serializable;
import java.util.*;


public class LockTable implements ILockTable,Serializable {

    private static int counter=1;
    private Map<Integer,Integer> d;
    public LockTable(){
        d=new HashMap<Integer,Integer>();
    }
    @Override
    public synchronized int add() throws InterpretorException {

        d.put(counter,-1);
        return counter++;
    }

    @Override
    public synchronized int get(int addr) {
        if(d.containsKey(addr))
        {
            return d.get(addr);
        }
        return -2;
    }

    public synchronized void set(int addr,int value){
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
