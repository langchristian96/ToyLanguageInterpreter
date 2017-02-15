package utils;
import model.*;

public interface IExeStack<E> {

	public void push(E s);
	public E pop();
	public boolean isEmpty();
	public Iterable<E> getAll();
	public IExeStack<E> clone();
}
