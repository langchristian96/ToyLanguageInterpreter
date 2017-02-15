package model;
import java.io.*;
import java.util.*;
import utils.*;

public class OpenFileStmt implements Statement,Serializable {

	private String filename;
	private String varName;
	public OpenFileStmt(String filename,String varName){
		this.filename=filename;
		this.varName=varName;
	}
	public PrgState execute(PrgState p){
		if(isOpen(filename,p.getFileTable())){
			throw new InterpretorException("File already opened.");
		}
		try{
			BufferedReader br=new BufferedReader(new FileReader(filename));
			FileData fd=new FileData(filename,br);
			int id=FileIdGenerator.generateId();
			p.getFileTable().add(id, fd);
			p.getSymbolTable().add(varName, id);
			
		}
		catch(IOException e	){
			throw new InterpretorException("Something went wrong - OPEN FILE STMT");
			
		}
		return null;
	}
	
	private boolean isOpen(String filename,IFileTable<Integer,FileData> ft){
		for(Map.Entry<Integer, FileData> it:ft.getAll()){
			if(filename.equals(it.getValue().getFilename()))
				return true;
		}
		return false;
	}
	
	public String toString(){
		return "Open File: Filename "+filename+" Varname "+varName;
	}
}
