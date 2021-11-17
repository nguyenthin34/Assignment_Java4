package com.poly.helper;


import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Part;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import com.poly.helper.RRSharer.*;
public class XForm {
	/**
	* Kiểm tra sự tồn tại của tham số
	* @param name tên tham số
	* @return true nếu tồn tại, ngược lại là false
	*/
	public static boolean exist(String name) {
		return RRSharer.request().getParameter(name) != null;
	}
	/**
	* Đọc chuỗi từ tham số form
	* @param name tên tham số form
	* @param defval giá trị mặc định
	* @return Giá trị tham số hoặc defval nếu tham số không tồn tại
	*/
	public static String getString(String name, String defavl) {
		String value = RRSharer.request().getParameter(name);
		return value == null ? defavl : value;
	}
	/**
	* Đọc số nguyên từ tham số form
	* @param name tên tham số form
	* @param defval giá trị mặc định
	* @return Giá trị tham số hoặc defval nếu tham số không tồn tại
	*/
	public static int getInt(String name, int defavl) {
		String value = getString(name, String .valueOf(defavl));
		return Integer.parseInt(value);
	}
	/**
	* Đọc số thực từ tham số form
	* @param name tên tham số form
	* @param defval giá trị mặc định
	* @return Giá trị tham số hoặc defval nếu tham số không tồn tại
	*/
	public static double getDouble(String name, double defavl){
		String value = getString(name, String.valueOf(defavl));
		return Double.parseDouble(value);
	}
	/**
	* Đọc giá trị boolean từ tham số form
	* @param name tên tham số form
	* @param defval giá trị mặc định
	* @return Giá trị tham số hoặc defval nếu tham số không tồn tại
	*/	
	public static boolean getBoolean(String name, boolean defavl) {
		String value = getString(name, String.valueOf(defavl));
		return Boolean.parseBoolean(value);
	}
	/**
	* Đọc thời gian từ tham số form
	* @param name tên tham số form
	* @param defval giá trị mặc định
	* @return Giá trị tham số hoặc defval nếu tham số không tồn tại
	*/
	public static Date getDate(String name, Date defavl) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = getString(name, sdf.format(defavl));
		try {
			return sdf.parse(date);
		} catch (Exception e) {
			return defavl;
		}
	}
	/**
	* Lưu file upload vào thư mục với tên duy nhất
	* @param name tên tham số form
	* @param folder thư mục chứa file
	* @return File kết quả hoặc null nếu không upload
	*/
	public static File saveFile(String name, String forder) {
		File dir = new File(RRSharer.request().getServletContext().getRealPath(forder));
		if(!dir.exists()) {
			dir.mkdirs();
		}
		try {
			Part part = RRSharer.request().getPart(name);
			if(part != null && part.getSize() > 0) {
				String fn = System.currentTimeMillis() + part.getSubmittedFileName();
				String filename = Integer.toHexString(fn.hashCode()) + fn.substring(fn.lastIndexOf("."));
				File file = new File(dir, filename);
				part.write(file.getAbsolutePath());
				
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	* Đọc các giá trị tham số form vào các thuộc tính cùng tên của bean
	* @return Bean chứa dữ liệu form
	*/
	
}
