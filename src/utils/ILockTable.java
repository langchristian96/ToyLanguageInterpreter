package utils;

import java.util.Map;

/**
 * Created by langchristian96 on 1/25/2017.
 */

public interface ILockTable {

    public int add();
    public void set(int addr,int val);
    public int get(int addr);
    public void setContent(Map<Integer,Integer> d);
    public Iterable<Map.Entry<Integer, Integer>> getAll();
    public Map<Integer,Integer> getContent();
    public String toString();
}
