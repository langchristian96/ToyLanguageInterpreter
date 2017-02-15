package model;
import model.*;
import utils.*;

import java.io.Serializable;
import java.util.Stack;

public class PrgState implements Serializable {

	private Statement initialPrg;
	private IExeStack<Statement> exeStack;
	private Stack<ISymbolTable<String,Integer>> st;
	private IOutput<Integer> out;
	private IHeap heap;
	private IFileTable<Integer,FileData> ft;
	private IProcTable proct;
	private ILockTable lt;

	private int ID;

	public PrgState(IExeStack<Statement> ip,ISymbolTable<String,Integer> st,IOutput<Integer> out,Statement initialPrg,IFileTable<Integer,FileData> ft, IHeap hp,IProcTable proc,ILockTable lt){
		this.initialPrg=initialPrg;
		this.exeStack=ip;
		this.st=new Stack<ISymbolTable<String,Integer>>();
		this.st.push(st);
        proct=proc;
        System.out.println("PRG: "+proct);
		this.out=out;
		this.ft=ft;
		heap=hp;
		this.ID=Generator.generatePrgStateID();
		this.lt=lt;
		ip.push(initialPrg);
	}

    public PrgState(IExeStack<Statement> ip,Stack<ISymbolTable<String,Integer>> st,IOutput<Integer> out,Statement initialPrg,IFileTable<Integer,FileData> ft, IHeap hp,IProcTable proc,ILockTable lt){
        this.initialPrg=initialPrg;
        this.exeStack=ip;
        this.st=st;
        proct=proc;
        this.out=out;
        this.ft=ft;
        heap=hp;
        this.ID=Generator.generatePrgStateID();
        this.lt=lt;
        ip.push(initialPrg);
    }

    Stack<ISymbolTable<String,Integer>> getSymbolStack(){return st;}
	public ISymbolTable<String,Integer> getSymbolTable(){
		return st.peek();
	}
	public IExeStack<Statement> getExeStack(){
		return exeStack;
	}
	
	public IOutput<Integer> getOutput(){
		return out;
	}

	public IFileTable<Integer,FileData> getFileTable(){
		return ft;
	}

	public void pushSymbolTable(ISymbolTable<String,Integer> st){
	    this.st.push(st);
    }

    public void popSymbolTable(){
	    if(!this.st.isEmpty()){
	        st.pop();
        }
    }

    public ILockTable getLockTable(){return lt;}

	public IProcTable getProcTable(){return proct;}

	public IHeap getHeap(){ return heap; }
	public Statement getStatement(){
		return initialPrg;
	}
	public int getID(){
	    return ID;
    }
	public String toString(){
		return "ID: "+ID+"Statement "+this.initialPrg.toString()+" Exe Stack "+this.exeStack.toString()+" Symbol Table "+this.st.peek().toString()+" Output "+this.out.toString()+" FileTable "+this.ft.toString()+ " Heap "+this.heap.toString()+ " Proc Table: "+ proct.toString();

	}

	public boolean isNotCompleted(){
	    return !exeStack.isEmpty();
    }

    public PrgState oneStep(){
	    if(exeStack.isEmpty()){
	        throw new InterpretorException("Exe stack empty");
        }
        Statement crt=getExeStack().pop();
	    return crt.execute(this);
    }
}
