package model;
import model.*;
import utils.*;

import java.io.Serializable;


public class AssignStmt implements Statement,Serializable {

	private String var;
	private Expression expr;
	public AssignStmt(String v,Expression e){
		var=v;
		expr=e;
	}
	public PrgState execute(PrgState p){
		ISymbolTable t=p.getSymbolTable();
		IHeap heap=p.getHeap();
		int result=expr.evaluate(t,heap);
		t.add(var,result);
		return null;
	}
	
	public String toString(){
		return "Assign Stmt:{"+var+"="+expr+"}";
	}

}
