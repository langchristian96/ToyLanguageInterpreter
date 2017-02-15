package model;

import utils.IExeStack;

import java.io.Serializable;

/**
 * Created by langchristian96 on 1/21/2017.
 */
public class SleepStmt implements Statement,Serializable {
    private int number;

    public SleepStmt(int nr){
        number=nr;
    }
    @Override
    public PrgState execute(PrgState p) {
        if(number>0){
            IExeStack e=p.getExeStack();
            e.push(new SleepStmt(number-1));
        }
        return null;
    }
    public String toString(){
        return "Sleep: "+number+" seconds ";
    }
}
