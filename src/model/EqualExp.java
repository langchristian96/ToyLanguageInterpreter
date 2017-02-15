package model;

import utils.IHeap;
import utils.ISymbolTable;

import java.io.Serializable;

/**
 * Created by langchristian96 on 12/6/2016.
 */
public class EqualExp implements Expression,Serializable {
    private Expression e1,e2;

    public EqualExp(Expression e1,Expression e2){
        this.e1=e1;
        this.e2=e2;
    }

    @Override
    public int evaluate(ISymbolTable<String, Integer> s, IHeap hp) {
        boolean val=(e1.evaluate(s,hp)==e2.evaluate(s,hp));
        if(val==true)
            return 1;
        else
            return 0;
    }

    public String toString(){
        return e1.toString()+"=="+e2.toString();
    }



}
