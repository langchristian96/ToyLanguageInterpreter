package model;

import utils.IHeap;
import utils.ISymbolTable;
import utils.InterpretorException;

import java.io.Serializable;

/**
 * Created by langchristian96 on 12/4/2016.
 */
public class ReadHeapExp implements Expression,Serializable {

    private String  varname;

    public ReadHeapExp(String vname){
        varname=vname;
    }

    @Override
    public int evaluate(ISymbolTable<String, Integer> s, IHeap hp) throws InterpretorException {
        if(!s.contains(varname)){
            throw new InterpretorException("The given variable does not exist!\n");
        }
        int addr=s.getValue(varname);

        return hp.get(addr);
    }

    public String toString(){
        return "Read heap:"+varname;
    }
}
