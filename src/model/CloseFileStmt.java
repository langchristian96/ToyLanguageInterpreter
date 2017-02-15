package model;
import utils.*;
import java.util.*;
import java.io.*;

public class CloseFileStmt implements Statement,Serializable{

	private String var_file_id;
	public CloseFileStmt(String v){
		this.var_file_id=v;
	}
	public PrgState execute(PrgState p){
		Integer e=p.getSymbolTable().getValue(var_file_id);
		if(e==null){
			throw new InterpretorException("The given file id does not exist in symbol table");
		}
		FileData d=p.getFileTable().getValue(e);
		if(d==null){
			throw new InterpretorException("The file data does not exist in the file table");
		}
		BufferedReader br=d.getBR();
		try{
			br.close();
			p.getFileTable().remove(e);
		}
		catch(IOException ee){
			throw new InterpretorException("Cannot close the file");
		}
		return null;
	}
	

	public String toString(){
		return "Close File: Var File ID "+var_file_id;
	}
}
