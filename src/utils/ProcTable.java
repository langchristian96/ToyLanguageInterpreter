package utils;

import model.ProcData;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by langchristian96 on 1/21/2017.
 */
public class ProcTable implements IProcTable {


    private Map<String,ProcData> d;

    public ProcTable(){
        d=new HashMap<String,ProcData>();
    }

    @Override
    public void add(String name, ProcData pd) {
        d.put(name,pd);
    }

    @Override
    public ProcData get(String name) {
        if(d.containsKey(name)){
            return d.get(name);
        }
        return null;
    }

    public String toString(){
        return d.toString();
    }

    @Override
    public Iterable<Map.Entry<String, ProcData>> getAll() {
        return d.entrySet();
    }

    @Override
    public Map<String, ProcData> getContent() {
        return d;
    }
}
