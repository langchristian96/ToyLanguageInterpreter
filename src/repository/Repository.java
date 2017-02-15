package repository;
import repository.*;
import sun.rmi.log.ReliableLog.LogFile;

import java.io.*;
import java.util.*;
import utils.*;
import model.*;
public class Repository implements IRepository {

	private List<PrgState> repo;
	private String filename;
	public Repository(String filename){
		repo=new ArrayList<>();
		this.filename=filename;
	}

	public void addPrgState(PrgState p){
		
		repo.add(p);
	}
	
//
//	public PrgState getCurrent(){
//		return repo.get(0);
//	}


	public List<PrgState> getPrgStates(){
	    return repo;
    }


    public void setPrgStates(List<PrgState> l){
	    this.repo=l;
    }

	public void logPrgStateExec(PrgState p) throws InterpretorException{
		PrintWriter logFile;
		try{		
			logFile=new PrintWriter(new FileWriter(filename,true),true);
			
	    logFile.println("ID: "+ p.getID());
		
		logFile.println("Exe Stack:");
		for(Statement s:p.getExeStack().getAll()){
			logFile.println(s);
		}
		logFile.println("Symbol Table: ");
		for(Map.Entry<String, Integer> s:p.getSymbolTable().getAll()){
			logFile.println(s.getKey()+"->"+s.getValue());
		}

		logFile.println("Output: ");
		for(Integer s:p.getOutput().getAll()){
			logFile.println(s);
		}
		logFile.println("FileTable: ");
		for(Map.Entry<Integer,FileData> s:p.getFileTable().getAll()) {
			logFile.println(s.getKey() + "->" + s.getValue());
		}

		logFile.println("Heap: ");
		for(Map.Entry<Integer,Integer> h:p.getHeap().getAll()){
		    logFile.println(h.getKey() + "->" +h.getValue());
        }

			logFile.println("Lock: ");
		for(Map.Entry<Integer,Integer> h:p.getLockTable().getAll()){
				logFile.println(""+h.getKey() + "->" +h.getValue());
			}

		logFile.close();
		}
		catch(IOException e){
			throw new InterpretorException(e.getMessage());
		}
	}

	public PrgState deSerialize(String fname) throws InterpretorException{

		try{
			ObjectInputStream str=new ObjectInputStream(new FileInputStream(fname));
			Object o=str.readObject();
			if(o instanceof  PrgState){
				return (PrgState)o;
			}
			throw new InterpretorException("Deserializing failed. ");
		}
		catch(Exception e){
			throw new InterpretorException(e.getMessage());

		}

	}

	public void Serialize(PrgState p,String fname) throws InterpretorException,IOException{
		try{
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(fname));
			oos.writeObject(p);

		}
		catch(Exception e){
			System.out.println("TEST");
			throw e;//new InterpretorException(e.getMessage());
		}
	}
	
}
