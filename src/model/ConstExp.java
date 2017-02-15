package model;
import model.Expression;
import utils.*;

import java.io.Serializable;

public class ConstExp implements Expression,Serializable {
	private int value;
	public ConstExp(int value){
		this.value=value;
	}
	public int evaluate(ISymbolTable<String,Integer> s,IHeap hp){
		return value;
	}
	public String toString(){
		return ""+value;
	}
	
	

}
