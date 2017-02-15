package model;

import utils.GlobalLock;
import utils.InterpretorException;

/**
 * Created by langchristian96 on 1/23/2017.
 */
public class UnlockStmt implements Statement {
    private String var;

    public UnlockStmt(String var){
        this.var=var;
    }


    @Override
    public PrgState execute(PrgState p) {
        GlobalLock.gLock.lock();


        if(!p.getSymbolTable().contains(var)){
            GlobalLock.gLock.unlock();
            throw new InterpretorException("the given var does not exist in the symb. table");
        }
        int ind=p.getSymbolTable().getValue(var);
        if(ind==0){
            GlobalLock.gLock.unlock();
            throw new InterpretorException("the given var does not exist in the lock table");
        }
        if (p.getLockTable().get(ind)==p.getID())
            p.getLockTable().set(ind,-1);


        GlobalLock.gLock.unlock();
        return null;
    }

    public String toString(){
        return "Unlocking: "+var;
    }

}
