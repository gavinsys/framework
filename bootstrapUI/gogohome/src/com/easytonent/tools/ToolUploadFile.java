package com.easytonent.tools;

import java.util.List;

import com.jfinal.upload.UploadFile;

public class ToolUploadFile {
	public static String filePathByParameterName(List<UploadFile> files, String parameterName){
		for(UploadFile file:files){
			if(parameterName.equals(file.getParameterName())){
				return file.getUploadPath();
			}
		}
		return null;
	}
}
