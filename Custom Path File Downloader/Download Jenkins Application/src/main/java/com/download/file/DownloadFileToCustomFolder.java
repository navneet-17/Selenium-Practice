package com.download.file;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DownloadFileToCustomFolder {

	public static void main(String[] args) throws InterruptedException {
		
		String userDirectory = System.getProperty("user.dir");
		
		String downloadFolderPath = userDirectory + "\\Jenkins Download";
		
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


	}

}


