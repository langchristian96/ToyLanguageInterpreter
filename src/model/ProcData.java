package model;

/**
 * Created by langchristian96 on 1/21/2017.
 */
public class ProcData {
    String[] varNames;
    Statement statement;

    public ProcData(String[] vn,Statement stm){
        this.varNames=vn;
        this.statement=stm;
    }

    public String[] getVarNames(){
        return varNames;
    }

    public Statement getStatement(){
        return statement;
    }

    public String toString(){
        return "Variables: " + varNames +"; Statement: "+statement;
    }


}
