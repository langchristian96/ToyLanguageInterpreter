package model;
import model.Expression;
import utils.*;

import java.io.Serializable;

public class ArithExp implements Expression,Serializable {
	private char operator;
	private Expression operand1,operand2;
	
	public ArithExp(char oper,Expression first,Expression second){
		this.operator=oper;
		this.operand1=first;
		this.operand2=second;
	}
	public int evaluate(ISymbolTable table,IHeap hp){
		int resultfirst=operand1.evaluate(table,hp);
		int resultsecond=operand2.evaluate(table,hp);
		
		switch(operator){
		case '+': return resultfirst+resultsecond;
		case '-': return resultfirst-resultsecond;
		case '*': return resultfirst*resultsecond;
		case '/': if(resultsecond!=0){
			return resultfirst/resultsecond;
		}
		else{
			throw new ArithmeticException("cannot divide by zero");
		}
		default: throw new RuntimeException("Illegal operator");
		}
	}
	
	public String toString(){
		return ""+operand1+operator+operand2;
	}
	
	

}
