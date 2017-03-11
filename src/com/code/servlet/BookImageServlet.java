package com.code.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.entity.Book;
import com.code.image.BookImage;
import com.code.service.IBookService;
import com.code.service.fact.ServiceFactory;
import com.code.util.PathUtil;

public class BookImageServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		IBookService service = ServiceFactory.getInstance().newBookService();
		resp.setContentType("image/jpeg");
		int id = -1;
		OutputStream os=null;
		try {
			id = Integer.parseInt(req.getParameter("bookId"));
			 os = resp.getOutputStream();
		} catch (Exception e) {
		}
		BufferedImage image=null;
		Book book = service.findBookById(id);
		if(book==null||book.getImg()==null||"".equals(book.getImg())){
			if(book==null){
				book = new Book();
				book.setName("皓叶电子书");
				book.setAuthor("皓叶");
			}
			BookImage bookImage=new BookImage(book);
			image=bookImage.getImage();
			ImageIO.write(image, "jpg",os);
		}else{
			File file = new File(PathUtil.toAbsPath(book.getImg()));
			if(!file.exists()){
				BookImage bookImage=new BookImage(book);
				image=bookImage.getImage();
				ImageIO.write(image, "jpg",os);
			}else{
				ImageIO.write(ImageIO.read(new File(PathUtil.toAbsPath(book.getImg()))), "jpg", os);
			}
		}
		
	}
}
