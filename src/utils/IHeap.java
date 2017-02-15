package utils;

import java.util.Map;

public interface IHeap {

	public int add(int a);
	public void set(int addr,int val);
	public int get(int addr);
	public void setContent(Map<Integer,Integer> d);
	public Iterable<Map.Entry<Integer, Integer>> getAll();
	public Map<Integer,Integer> getContent();
}
