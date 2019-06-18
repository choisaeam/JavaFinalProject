package edu.handong.csee.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import edu.handong.csee.reader.ZipReader;

public class Utils {
	
	public void getData(String dataPath,SaeamDataStructure<HashMap<String,ArrayList<ArrayList<String>>>> data, SaeamDataStructure<HashMap<String,ArrayList<ArrayList<String>>>> data2 ) {

		ZipReader zip = new ZipReader();
		File dir = new File(dataPath);
		File[] fileList = dir.listFiles(); 
		
		for(File file : fileList){
			if(file.isFile()){
				ArrayList<ArrayList<String>> contents[] = new ArrayList[2];
				String path = file.getPath();
				String name = file.getName().split(".z")[0];
				contents = zip.readFileInZip(path);
				
				data.getdata().put(name,contents[0]);
				data2.getdata().put(name,contents[1]);
			}
		}

	}
	
	public void writeCSV(String resultPath, int option, SaeamDataStructure<HashMap<String,ArrayList<ArrayList<String>>>> data) throws InterruptedException {
		try {
			File file = new File(resultPath);
			BufferedWriter bf = new BufferedWriter(new FileWriter(file));
			if(option == 1) {
				bf.write("학생이름,제목(반드시 요약문 양식에 입력한 제목과 같아야 함.),표/그림 일련번호,자료유형,자료에 나온 표나 그림 설명(캡션),자료가 나온 쪽번호");
			}else
				bf.write("제목,요약문 (300자 내외),핵심어 , 조회날짜, 실제자료조회 출처 (웹자료링크), 원출처(기관명 등),제작자 Copyright 소유처)");
			bf.newLine();
			
			Iterator<String> keys = data.getdata().keySet().iterator();

			ArrayList<writerThread> threads = new ArrayList<writerThread>();
			while(keys.hasNext()){
				String key = keys.next();
				writerThread thread = new writerThread(bf,key,data);
				thread.run();
				threads.add(thread);
			}
			for(Thread t : threads) {
				t.join();
			}
			
			bf.close();
		}catch(FileNotFoundException e) {
			try {
				File tmp = new File(resultPath);
				tmp.getParentFile().mkdirs();
				tmp.createNewFile();
			}catch(IOException err) {
				System.out.println(err);
			}
		}catch(IOException e) {
			System.out.println(e);
		}
	}
}
