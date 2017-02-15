package repository;
import utils.*;

import java.io.IOException;
import java.util.List;

import model.*;
public interface IRepository {

	public void logPrgStateExec(PrgState p) throws IOException;
	void addPrgState(PrgState p);
	//PrgState getCurrent();
	public List<PrgState> getPrgStates();
	public void setPrgStates(List<PrgState> l);
	public PrgState deSerialize(String fname);
	public void Serialize(PrgState p,String fname) throws IOException;
}
