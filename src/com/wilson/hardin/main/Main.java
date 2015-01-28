package com.wilson.hardin.main;

import java.util.ArrayList;
import java.util.List;

import com.wilson.hardin.jobs.GreatSchoolsJob;
import com.wilson.hardin.jobs.ProcessingJob;

/**
 * 
 * Executes all back end data processing jobs.
 * 
 * @author hardincm
 *
 */
public class Main {
	private static List<ProcessingJob> jobs;
	
	public static void main(String[] args) throws Exception {
		init();
		
		for (ProcessingJob job : jobs) {
			job.run();
		}
	}
	
	/**
	 * Initialize jobs.
	 */
	private static void init() {
		// Maybe setup config stuff here?
		
		jobs = new ArrayList<>();
		jobs.add(new GreatSchoolsJob());
		// Add more jobs here as we add metrics.
	}
}
