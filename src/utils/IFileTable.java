package utils;

import java.util.Map;

public interface IFileTable<K,V> {

	public void add(K k,V v);
	public V getValue(K k);
	public void remove(K k);
	public Iterable<Map.Entry<K, V>> getAll();
	
	
}
