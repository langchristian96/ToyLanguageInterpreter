package model;
import model.*;
import utils.*;

import java.io.Serializable;

public class PrintStatement implements Statement,Serializable {

	private Expression exp;

	public PrintStatement(Expression e){
		exp=e;
	}
	
	public PrgState execute(PrgState p){
		IOutput<Integer> bla=p.getOutput();
		ISymbolTable<String, Integer> b=p.getSymbolTable();
		IHeap heap=p.getHeap();
		bla.add(exp.evaluate(b,heap));
		return null;
	}
	
	public String toString(){
		return exp.toString();
	}
	
}
