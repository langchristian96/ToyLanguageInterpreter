package model;
import model.Expression;
import utils.*;

import java.io.Serializable;

public class VarExp implements Expression,Serializable {
	private String name;
	public VarExp(String name){
		this.name=name;
	}
	public int evaluate(ISymbolTable<String,Integer> s,IHeap hp){
		if(s.contains(name)){
			return s.getValue(name);
		}
		else{
			throw new RuntimeException("The variable does not exist");
		}
		
	}
	public String toString(){
		return name;
	}
	
	

}