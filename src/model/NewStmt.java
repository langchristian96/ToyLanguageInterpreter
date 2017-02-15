package model;

import utils.IHeap;
import utils.ISymbolTable;

import java.io.Serializable;

/**
 * Created by langchristian96 on 12/4/2016.
 */
public class NewStmt implements Statement,Serializable {
    private String var_name;
    private Expression expr;

    public NewStmt(String vname,Expression ex){
        this.var_name=vname;
        this.expr=ex;
    }

    public PrgState execute(PrgState p){
        ISymbolTable<String,Integer> st=p.getSymbolTable();
        IHeap heap=p.getHeap();
        int addr=heap.add(expr.evaluate(st,heap));
        st.add(var_name,addr);

        return null;

    }

    public String toString(){
        return "New Statement: {Variable name: "+var_name+" Expression: "+expr+"}";
    }

}
