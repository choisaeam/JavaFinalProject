package edu.handong.csee.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Exceptions  extends Exception {
	public Exceptions(String name) {
		try {
			File file = new File("error.csv");
			BufferedWriter bf = new BufferedWriter(new FileWriter(file));
			bf.write(name);
			bf.close();
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	public Exceptions() {
		
	}
}

