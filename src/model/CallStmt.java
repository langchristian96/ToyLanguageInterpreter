package model;

import utils.IProcTable;
import utils.ISymbolTable;

import java.util.ArrayList;

/**
 * Created by langchristian96 on 1/21/2017.
 */
public class CallStmt implements Statement {
    private String procName;
    private Expression[] exprList;

    public CallStmt(String procName,Expression[] e){
        this.procName=procName;
        this.exprList=e;
    }


    @Override
    public PrgState execute(PrgState p) {
        IProcTable pt=p.getProcTable();
        ProcData pd=pt.get(procName);
        if(pd==null)
        {
            System.out.println("NULL B");
            while(!p.getExeStack().isEmpty())
                p.getExeStack().pop();
            return null;
        }
        String[] vars=pd.getVarNames();
        ArrayList<ConstExp> ce=new ArrayList<>();
        for(Expression v:exprList){

            ce.add(new ConstExp(v.evaluate(p.getSymbolTable(),p.getHeap())));
        }
        ISymbolTable<String,Integer> newst=p.getSymbolTable().clone();
        int i=0;
        for(ConstExp e:ce){
            newst.add(vars[i++],e.evaluate(newst,p.getHeap()));
        }
        p.pushSymbolTable(newst);
        p.getExeStack().push(new ReturnStmt());
        System.out.println(pd.getStatement());
        p.getExeStack().push(pd.getStatement());




        return null;
    }
    public String toString(){
        return "Call: "+procName+" Vars: "+exprList;
    }

}
