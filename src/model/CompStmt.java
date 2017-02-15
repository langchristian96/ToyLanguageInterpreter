package model;
import model.*;
import utils.*;

import java.io.Serializable;

public class CompStmt implements Statement,Serializable {

	private Statement first,second;
	public CompStmt(Statement f,Statement s){
		first=f;
		second=s;
		
	}
	public PrgState execute(PrgState s){
		IExeStack exe=s.getExeStack();
		exe.push(second);
		exe.push(first);
		return null;
	}
	public String toString(){
		return "{"+first+";"+second+"}";
				
	}
	
}
