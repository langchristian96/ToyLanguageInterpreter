package utils;
import model.*;

import java.util.List;

public interface IOutput<E> {
	
	public void add(E value);
	public int size();
	public Iterable<E> getAll();
	public List<E> getList();
}
