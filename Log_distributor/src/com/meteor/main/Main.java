package com.meteor.main;

import com.meteor.module.Log_distributor;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Log_distributor log_dis;
		
		//log_dis = new Log_distributor("c:/aaa");

		//log_dis = new Log_distributor("c:/Crew 11000 port.txt");
		//log_dis = new Log_distributor("c:/test.txt");
		//log_dis = new Log_distributor("e:/test.txt");
		/**/
		if(args.length>0){
			log_dis = new Log_distributor(args[0]);	
		}else{
			log_dis = new Log_distributor("log.txt");	
		}
		
		
		
		log_dis.set_delimiter("\t");
		
		log_dis.analy_log_distribute();
		
		
		
		//log_dis.analy_log_map_distribute();

		
		
	}

}
