package utils;
import java.util.*;
public interface ISymbolTable<K,V> {

	public void add(K name,V value);
	public boolean contains(K name);
	public V getValue(K name);
	public Iterable<Map.Entry<K, V>> getAll();
	public Map<K,V> getContent();
	public ISymbolTable<K,V> clone();
}
