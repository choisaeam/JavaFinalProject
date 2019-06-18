package edu.handong.csee.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

public class ZipReader {

	public static void main(String[] args) {
		ZipReader zipReader = new ZipReader();
		zipReader.run(args);
	}

	private void run(String[] args) {
		//String path = args[0];
		
		readFileInZip("0001.zip");
		
	}

	public ArrayList<ArrayList<String>>[] readFileInZip(String path) {
		ArrayList<ArrayList<String>> contents[] = new ArrayList[2];
		for(int i = 0;i < 2;i ++) {
			contents[i] = new ArrayList<ArrayList<String>>();
		}
		ZipFile zipFile;
		
		try {
			zipFile = new ZipFile(path);
			Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
			int i = 0;

		    while(entries.hasMoreElements()){
		    	ZipArchiveEntry entry = entries.nextElement();
		        InputStream stream = zipFile.getInputStream(entry);
		    
		        ExcelReader myReader = new ExcelReader();
		        contents[i] = myReader.getData(stream,i);
		        i++;
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  contents;
	}
}
