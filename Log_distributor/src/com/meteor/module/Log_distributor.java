package com.meteor.module;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import java.util.HashMap;
/*
 * @Kimunseok
 * 2013-11-14
*/

public class Log_distributor {

	File ori_file;
	
	FileInputStream fis;
	BufferedInputStream bis;
	
	FileReader fr;
	BufferedReader bfr;
	
	FileOutputStream fos;
	

	HashMap<String, String> temp_data;
	
	String Delimiter;
	
	String Line;
	
	/*
	 * @path : File_Path 
	*/	
	public Log_distributor(String path){
		ori_file = new File(path);	
	}
	
	/*
	 * @tok	: "\n", "\t" 와 같은 구분자 설정	
	*/
	public void set_delimiter(String tok){
		this.Delimiter = tok;
	}
	
	/*
	 * File -> File(날자별)	
	*/
	public void analy_log_distribute(){
		
		try {
			fr = new FileReader(ori_file);
			bfr = new BufferedReader(fr);
			
			System.out.println(new Date() + " analy_log start");
			//한줄씩 읽음
			String header;
			temp_data = new HashMap<>();
			while( (Line = bfr.readLine()) != null){
				
				header = analy_header(Line.split(Delimiter)[0] , "MM/dd/yyyy");
				
				file_append_data( header , Line );
				
			}
			
			bfr.close();
			fr.close();
			
			
			//System.out.println( header+" : "+ temp_data.get(header));
			
			System.out.println(new Date() + " analy_log end");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * File -> Map(날자별) 후 -> File(날자별) 	
	*/
	public void analy_log_map_distribute(){
		
		try {
			fr = new FileReader(ori_file);
			BufferedReader bfr = new BufferedReader(fr);
			
			System.out.println(new Date() + " analy_log start");
			//한줄씩 읽음
			String header;
			temp_data = new HashMap<>();
			while( (Line = bfr.readLine()) != null){
				
				//System.out.println(Line.split(Delimiter)[0]);
				header = analy_header(Line.split(Delimiter)[0] , "MM/dd/yyyy");
				
				if(temp_data.containsKey(header)){
					//temp_data.put( header, temp_data.get(header)+ "\n" + Line);
					temp_data.put( header, new StringBuffer().append(temp_data.get(header)).append("\n").append(Line).toString());
				}else{
					temp_data.put( header, Line);	
				}
				//;
				
				
			}
			System.out.println(new Date() + " analy_log read end");
			bfr.close();
			fr.close();

			Set<String> set = temp_data.keySet();
			Iterator<String> iter = set.iterator();
			while(iter.hasNext()){
				header = iter.next();
				file_append_data( header , temp_data.get(header) );
				

			}

			System.out.println(new Date() + " analy_log end");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * @header	: 구분자의 맨앞 ex)03/13/2013
	 * @style	: 구분자의 형식 ex)03/13/2013("MM/dd/yyyy")
	 * @return	: 130313("yyMMdd")
	*/
	public String analy_header(String header , String style){

		//SimpleDateFormat formating = new SimpleDateFormat("MM/dd/yyyy",Locale.KOREA);
		SimpleDateFormat formating = new SimpleDateFormat(style);
		StringBuffer stb = new StringBuffer();
		
		try {
			Date date = formating.parse(header);
			formating.applyPattern("yyMMdd");
			
			stb.append( formating.format(date) );
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stb.toString();
	}
	
	public void file_append_data(String file_name,String data){
		
		File target_file = new File(file_name);
		
		try {
			fos = new FileOutputStream(target_file , true);
			try {
				//fos.write((data+"\n").getBytes());
				
				fos.write((new StringBuffer(data).append("\n")).toString().getBytes());
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
}
