package model;

import utils.*;

/**
 * Created by langchristian96 on 1/23/2017.
 */
public class NewLockStmt implements Statement {
    private String var;

    public NewLockStmt(String var){
        this.var=var;
    }
    @Override
    public  PrgState execute(PrgState p) {
        GlobalLock.gLock.lock();
        ILockTable lt=p.getLockTable();
        ISymbolTable<String,Integer> st=p.getSymbolTable();
        int addr=lt.add();
        st.add(var,addr);
        GlobalLock.gLock.unlock();
        return null;
    }

    public String toString(){
        return "New Lock: "+var;
    }
}
