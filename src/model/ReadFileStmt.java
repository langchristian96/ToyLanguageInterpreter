package model;
import utils.*;
import utils.InterpretorException;
import java.io.*;
import java.util.*;

public class ReadFileStmt implements Statement,Serializable {
	private String var_file_id,var_name;
	public ReadFileStmt(String var1,String var2){
		this.var_file_id=var1;
		this.var_name=var2;
	}
	public PrgState execute(PrgState p){
		Integer e=p.getSymbolTable().getValue(var_file_id);
		if(e==null){
			throw new InterpretorException("File id does not exist");
			
		}
		FileData d=p.getFileTable().getValue(e);
		if(d==null){
			throw new InterpretorException("The integer does not exist in the file table");
		}
		BufferedReader br=d.getBR();
		try{
			int v;
			String s=br.readLine();
			if(s==null)
				v=0;
			else
				v=Integer.parseInt(s);
			p.getSymbolTable().add(var_name, v);
			
		}
		catch(IOException|NumberFormatException ee){
			throw new InterpretorException("Something went wrong with reading from file");
		}
		return null;
	}

	public String toString(){
		return "Read File: "+var_file_id+"  "+var_name;
	}
}
