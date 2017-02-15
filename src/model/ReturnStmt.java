package model;

/**
 * Created by langchristian96 on 1/21/2017.
 */
public class ReturnStmt implements Statement {
    public ReturnStmt(){

    }

    @Override
    public PrgState execute(PrgState p) {
        p.popSymbolTable();
        return null;
    }

    public String toString(){
        return "Returning ";
    }
}
