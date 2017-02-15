package utils;
import utils.*;

import java.io.Serializable;
import java.util.*;

public class SymbolTable<K,V> implements ISymbolTable<K,V>,Serializable {

	private Map<K,V> d;

	public SymbolTable(){
		d=new HashMap<K,V>();
	}
	
	public void add(K name,V value)
	{
		d.put(name, value);
		
	}
	public boolean contains(K name)
	{
		return d.containsKey(name);
	}
	public V getValue(K name)
	{
		return d.get(name);
	}
	

	public String toString(){
		return d.toString();
	}
	

	public Iterable<Map.Entry<K, V>> getAll(){
		return d.entrySet();
	}

	public Map<K,V> getContent(){
		return d;
	}

	public ISymbolTable<K,V> clone(){
	    ISymbolTable<K,V> newst=new SymbolTable<K, V>();
	    for(Map.Entry<K,V> entry:getAll()){
	        newst.add(entry.getKey(),entry.getValue());
        }
        return newst;
    }
	
}
