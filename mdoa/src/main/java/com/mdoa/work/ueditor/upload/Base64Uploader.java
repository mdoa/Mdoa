package com.mdoa.work.ueditor.upload;


import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.mdoa.work.ueditor.PathFormat;
import com.mdoa.work.ueditor.define.AppInfo;
import com.mdoa.work.ueditor.define.BaseState;
import com.mdoa.work.ueditor.define.FileType;
import com.mdoa.work.ueditor.define.State;

public final class Base64Uploader {

	public static State save(String content, Map<String, Object> conf) {
		
		byte[] data = decode(content);

		long maxSize = ((Long) conf.get("maxSize")).longValue();

		if (!validSize(data, maxSize)) {
			return new BaseState(false, AppInfo.MAX_SIZE);
		}

		String suffix = FileType.getSuffix("JPG");

		String savePath = PathFormat.parse((String) conf.get("savePath"),
				(String) conf.get("filename"));
		
		savePath = savePath + suffix;
		String physicalPath = (String) conf.get("rootPath") + savePath;
		State storageState = null;
		if (conf.get("style").equals("scrawl")) {
			storageState = StorageManager.saveBinaryFile(data, "D:/workUeditor/scrawl/"+savePath);
		}else if (conf.get("style").equals("file")) {
			storageState = StorageManager.saveBinaryFile(data, "D:/workUeditor/file/"+savePath);
		}else if (conf.get("style").equals("viedo")) {
			storageState = StorageManager.saveBinaryFile(data, "D:/workUeditor/viedo/"+savePath);
		}else{
			storageState = StorageManager.saveBinaryFile(data, "D:/workUeditor/image/"+savePath);
		}
		
		if (storageState.isSuccess()) {
			storageState.putInfo("url", PathFormat.format(savePath));
			storageState.putInfo("type", suffix);
			storageState.putInfo("original", "");
		}

		return storageState;
	}

	private static byte[] decode(String content) {
		return Base64.decodeBase64(content);
	}

	private static boolean validSize(byte[] data, long length) {
		return data.length <= length;
	}
	
}