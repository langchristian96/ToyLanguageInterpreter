package utils;
import java.io.BufferedReader;
import java.io.Serializable;
import java.util.*;

public class FileData implements Serializable {

	private String filename;
	private BufferedReader br;
	public FileData(String name,BufferedReader b){
		filename=name;
		br=b;
	}
	public void setFilename(String fn){
		this.filename=fn;
	}
	public String getFilename(){
		return filename;
	}
	public void setBR(BufferedReader b){
		br=b;
		
	}
	public BufferedReader getBR(){
		return br;
	}
	public String toString(){
		return filename;
	}
}
