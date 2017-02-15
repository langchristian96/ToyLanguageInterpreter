package model;

import utils.IExeStack;
import utils.IHeap;
import utils.ISymbolTable;

/**
 * Created by langchristian96 on 12/7/2016.
 */
public class WhileStmt implements Statement {

    private Statement st;
    private Expression expr;

    public WhileStmt(Expression e,Statement s){
        st=s;
        expr=e;
    }



    @Override
    public PrgState execute(PrgState p) {

        ISymbolTable t=p.getSymbolTable();
        IHeap heap=p.getHeap();
        int result=expr.evaluate(t,heap);
        IExeStack<Statement> exe=p.getExeStack();
        if(expr.evaluate(t,heap)==1){
            exe.push(this);
            exe.push(st);
        }


        return null;
    }

    public String toString(){
        return "While("+expr.toString()+") {"+st.toString()+" }";
    }
}
