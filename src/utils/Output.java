package utils;
import utils.IOutput;

import java.io.Serializable;
import java.util.*;
public class Output<E> implements IOutput<E>,Serializable {

	ArrayList<E> lista;

	public Output(){
		lista=new ArrayList<E>();
	}
	public void add(E value){
		lista.add(value);
	}
	

	public int size(){
		
		return lista.size();
	}
	public String toString(){
		return lista.toString();
	}

	public Iterable<E> getAll(){
		return lista;
	}
	public List<E> getList(){return lista;}

}