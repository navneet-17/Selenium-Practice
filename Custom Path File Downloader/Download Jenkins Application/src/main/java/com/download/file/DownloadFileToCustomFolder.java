package com.download.file;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DownloadFileToCustomFolder {

	public static void main(String[] args) throws InterruptedException {
		
		String userDirectory = System.getProperty("user.dir");
		
		// File Separator ensures that the path is now machine independent!
		String downloadFolderPath = userDirectory + File.separator + "Jenkins Download";
		
		File jenkinsDownloadDir = new File(downloadFolderPath);
		
		if(!jenkinsDownloadDir.exists()) {
			System.out.println(" Jenkins Download Folder Does not exist.");
			
			if(jenkinsDownloadDir.mkdir()) {
				System.out.println(" Jenkins Download Folder created successfully!");				
			}
		}
		else {
			System.out.println(" Jenkins Download Folder already exists!");
		}
		
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("download.default_directory", downloadFolderPath);
		prefs.put("download.prompt_for_download", false);
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("prefs", prefs);
		
		WebDriver wd = new ChromeDriver(chromeOptions);
		wd.get("https://get.jenkins.io/war-stable/2.516.2/jenkins.war");
		
		
		String expectedFile = "jenkins.war";
		//Check if the file has been successfully downloaded.
		File jenkinsFile = new File(jenkinsDownloadDir,expectedFile);
		int elapsedTime = 0;
		int maxWaitTime = 30;
		System.out.println(" Waiting for the Jenkins file to be downloaded .... ");
		
		while(maxWaitTime > elapsedTime && !jenkinsFile.exists()) {
			elapsedTime ++;
			Thread.sleep(1000);
		}
		
		if (maxWaitTime > elapsedTime)
			System.out.println(" Jenkins application file : '"+ expectedFile +"' was successfully downloaded in "+ elapsedTime + " seconds!");
		else
			System.err.println(" Jenkins application file : '"+ expectedFile +"' could not be downloaded in "+ maxWaitTime + " seconds!");
	   
		Thread.sleep(5);
		wd.quit();
	}
}


