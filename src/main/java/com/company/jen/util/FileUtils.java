package com.company.jen.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.company.jen.domain.FileVO;
import com.company.jen.domain.UserVO;

public class FileUtils {
	
	@Autowired
	private ServletContext sc;
	
	public FileVO fileUpload(HttpServletRequest request, MultipartHttpServletRequest mRequest) throws Exception {
		String path = "c://jen";
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd");
		String datePath = sdf.format(new Date());
		String savePath = path + datePath;
		
		File f = new File(savePath);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		Iterator<String> iter = mRequest.getFileNames();
		FileVO file = null;
		while (iter.hasNext()) {
			String formFileName = iter.next();
			MultipartFile mFile = mRequest.getFile(formFileName);
			
			String oriFileName = mFile.getOriginalFilename();
			
			if (oriFileName != null && !oriFileName.equals("")) {
				file = new FileVO();
				String ext = "";
				int index = oriFileName.lastIndexOf(".");
				if (index != -1) {
					ext = oriFileName.substring(index);
				}
				String saveFileName = "jen-" + UUID.randomUUID().toString() + ext;
				file.setFileSize(mFile.getSize());
				file.setOriFileName(oriFileName);
				file.setFilePath(datePath);
				file.setRealFileName(saveFileName);
				file.setRegUserId(((UserVO) request.getSession().getAttribute("userInfo")).getUserId());
				
				mFile.transferTo(new File(savePath + "/" + saveFileName));
			}
		}
		return file;
	}
	
}
