package model;

import utils.ExeStack;
import utils.ISymbolTable;
import utils.*;

import java.io.Serializable;

public class IfStmt implements Statement,Serializable {

	private Expression e;
	private Statement s1;
	private Statement s2;
	public IfStmt(Expression e,Statement s1, Statement s2){
		this.e=e;
		this.s1=s1;
		this.s2=s2;
	}
	public PrgState execute(PrgState p){
		ISymbolTable t=p.getSymbolTable();
		IHeap heap=p.getHeap();
		int result=e.evaluate(t,heap);
		IExeStack<Statement> exe=p.getExeStack();
		if(result!=0){
			exe.push(s1);
		}
		else
			exe.push(s2);
		
		
		return null;
	}
	
	public String toString(){
		return "if("+e.toString()+") then "+s1.toString()+" else "+s2.toString();
	}

}