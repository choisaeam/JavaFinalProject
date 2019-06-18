package edu.handong.csee.Utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class writerThread extends Thread{
	public BufferedWriter bf;
	public String key;
	public SaeamDataStructure<HashMap<String,ArrayList<ArrayList<String>>>> data;
	
	public writerThread(BufferedWriter bf,String key,SaeamDataStructure<HashMap<String,ArrayList<ArrayList<String>>>> data) {
		this.bf = bf;
		this.key = key;
		this.data = data;
	}
	@Override
	public void run() {
		ArrayList<ArrayList<String>> values = data.getdata().get(key);
		Iterator<ArrayList<String>> value = values.iterator();
		try {
			while(value.hasNext()) {
				ArrayList<String> val = value.next();
				Iterator<String> itr = val.iterator();
				bf.write(key + ",");
				while(itr.hasNext()) {
					String str = itr.next();
					bf.write(str);
					if(itr.hasNext()) 
						bf.write(",");
				}
				bf.newLine();
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
