package model;

import utils.IHeap;
import utils.ISymbolTable;
import utils.InterpretorException;

import java.io.Serializable;

/**
 * Created by langchristian96 on 12/4/2016.
 */
public class WriteHeapStmt implements Statement,Serializable {

    private String varname;
    private Expression expr;

    public WriteHeapStmt(String varname,Expression expr){
        this.varname=varname;
        this.expr=expr;
    }



    @Override
    public PrgState execute(PrgState p) throws InterpretorException {
        ISymbolTable<String, Integer> st=p.getSymbolTable();
        IHeap hp=p.getHeap();
        if(!st.contains(varname)){
            throw new InterpretorException("Variable does not exist");
        }
        int result=expr.evaluate(st,hp);
        hp.set(st.getValue(varname),result);

        return null;
    }


    public String toString(){
        return "Write heap {Variable name: "+varname+"Expression: "+expr+"}";
    }
}
