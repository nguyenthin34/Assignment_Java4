package com.poly.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class AddImage {
	public String uploadImage(HttpServletRequest request) throws ServletException, IOException {
		String uplaodforder =  request.getServletContext().getRealPath("/images");
		Path uploadpath = Paths.get(uplaodforder);
		File dir = new File(uplaodforder);
		if (!dir.exists()) { // tạo nếu chưa tồn tại
			dir.mkdirs();
			}
		// lưu các file upload vào thư mục files
		Part photo = request.getPart("photo_file"); // file hình
		String namept = photo.toString();
		String imagefilename = Path.of(photo.getSubmittedFileName()).getFileName().toString();
		photo.write(Paths.get(uploadpath.toString(), imagefilename).toString());
		return imagefilename;
    }
}
