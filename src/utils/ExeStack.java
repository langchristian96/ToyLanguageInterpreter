package utils;
import utils.*;

import java.io.Serializable;
import java.util.*;

import model.Statement;

public class ExeStack<E> implements IExeStack<E>,Serializable {

	private Stack<E> exs;
	private Deque<E> d;

	public ExeStack(){
		exs=new Stack<E>();
		this.d=new ArrayDeque<>();
	}
	public void push(E s){
		exs.push(s);
	}
	public E pop(){
		if(!exs.isEmpty())
			return exs.pop();
		throw new RuntimeException("The stack is empty!");
	}
	public boolean isEmpty(){
		if(exs.isEmpty())
			return true;
		return false;
	}

	public String toString(){
		return exs.toString();
	}
	public Iterable<E> getAll(){
		return exs;
		
	}

	public IExeStack<E> clone(){
		IExeStack<E> nst=new ExeStack<E>();
		for(E el:getAll()){
			nst.push(el);
		}
		return nst;
	}

}
