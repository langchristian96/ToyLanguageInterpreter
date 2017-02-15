package model;

import utils.GlobalLock;
import utils.InterpretorException;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by langchristian96 on 1/23/2017.
 */
public class LockStmt implements Statement {
    private String var;


    public LockStmt(String var){
        this.var=var;

    }

    @Override
    public PrgState execute(PrgState p) {

        GlobalLock.gLock.lock();
        if(!p.getSymbolTable().contains(var))
        {
            throw new InterpretorException("Symbol table does not contain the given variable");
        }
        int ind=p.getSymbolTable().getValue(var);

        int val=p.getLockTable().get(ind);
        System.out.println("Lock value: "+val+p.getID());
        if(val==-2){
            throw new InterpretorException("Lock table does not contain the given variable");
        }
        if(val==-1)
            p.getLockTable().set(ind,p.getID());
        else
            p.getExeStack().push(new LockStmt(var));


        GlobalLock.gLock.unlock();

        return null;
    }

    public String toString(){
        return "Locking: "+var;
    }
}
