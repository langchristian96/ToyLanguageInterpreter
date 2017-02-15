package utils;

import java.io.Serializable;
import java.util.*;

public class FileTable<K,V> implements IFileTable<K, V>,Serializable{
	private Map<K,V> d;

	public FileTable(){
		d=new HashMap<K,V>();
	}
	
	public void add(K name,V value)
	{
		d.put(name, value);
		
	}
	public void remove(K name){
		d.remove(name);
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
}
