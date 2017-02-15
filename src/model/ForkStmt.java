package model;

import utils.ExeStack;
import utils.ISymbolTable;

import java.util.Stack;

/**
 * Created by langchristian96 on 12/13/2016.
 */
public class ForkStmt implements Statement {
    private Statement stm;
    public ForkStmt(Statement s){
        stm=s;
    }
    public PrgState execute(PrgState p){
        ISymbolTable st=p.getSymbolTable();

        Stack<ISymbolTable<String,Integer>> stck=new Stack<ISymbolTable<String,Integer>>();
        for(ISymbolTable<String,Integer> ist:p.getSymbolStack())
            stck.push(ist.clone());


        PrgState newps=new PrgState(new ExeStack<Statement>(),stck,p.getOutput(),stm,p.getFileTable(),p.getHeap(),p.getProcTable(),p.getLockTable());

        System.out.println("TEST"+stm.toString());
        return newps;
    }
    public String toString(){
        return "Forking statement: {"+stm.toString()+"}";
    }
}
