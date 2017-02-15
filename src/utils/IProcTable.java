package utils;

/**
 * Created by langchristian96 on 1/21/2017.
 */
import model.ProcData;

import java.util.Map;

public interface IProcTable {

    public void add(String name, ProcData pd);
    public ProcData get(String name);
    public Iterable<Map.Entry<String, ProcData>> getAll();
    public Map<String, ProcData> getContent();
}