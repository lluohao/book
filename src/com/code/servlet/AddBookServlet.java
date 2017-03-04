package com.code.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.code.entity.Book;
import com.code.service.IBookService;
import com.code.service.ITypeService;
import com.code.service.fact.ServiceFactory;
import com.code.util.PathUtil;
import com.code.util.UploadUtil;

public class AddBookServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookName = "";
		String price = "";
		String author = "";
		String discount = "";
		String type = "";
		String stock = "";
		String status = "";
		String describe = "";
		String time = "";

		int typeId = 0;
		String imgPath = "";
		String bookPath = "";
		String tempPath = "F:\\data\\temp";
		String savePath = "";

		File tmpFile = new File(tempPath);
		if (!tmpFile.exists()) {
			// 创建临时目录
			tmpFile.mkdir();
		}

		// 消息提示
		String message = "上传成功";
		String filename="";
		try {
			// 使用Apache文件上传组件处理文件上传步骤：
			// 1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
			factory.setSizeThreshold(1024 * 100);// 设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
			// 设置上传时生成的临时文件的保存目录
			factory.setRepository(tmpFile);
			// 2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 监听文件上传进度
			upload.setProgressListener(new ProgressListener() {
				public void update(long pBytesRead, long pContentLength,
						int arg2) {
					System.out.println("文件大小为：" + pContentLength + ",当前已处理："
							+ pBytesRead);
				}
			});
			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			// 3、判断提交上来的数据是否是上传表单的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				// 按照传统方式获取数据
				return;
			}

			// 设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
			upload.setFileSizeMax(1024 * 1024);
			// 设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
			upload.setSizeMax(1024 * 1024 * 100);
			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				// 如果fileitem中封装的是普通输入项的数据
				if (item.isFormField()) {
					String name = item.getFieldName();
					// 解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					// value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
					switch (name) {
					case "bookName":
						bookName = value;
						break;
					case "author":
						author = value;
						break;
					case "price":
						price = value;
						break;
					case "discount":
						discount = value;
						break;
					case "time":
						time = value;
						break;
					case "stock":
						stock = value;
						break;
					case "describe":
						describe = value;
						break;
					case "type":
						type = value;
						break;
					case "status":
						status = value;
						break;
					default:
						break;
					}

				} else {// 如果fileitem中封装的是上传文件
					// 得到上传的文件名称，
					filename = item.getName();
					System.out.println(filename);
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
					// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename
							.substring(filename.lastIndexOf("\\") + 1);
					// 得到上传文件的扩展名
					String fileExtName = filename.substring(filename
							.lastIndexOf(".") + 1);
					// 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
					System.out.println("上传的文件的扩展名是：" + fileExtName);
					// 获取item中的上传文件的输入流
					InputStream in = item.getInputStream();

					// 设置上传文件到的目录

					typeId = Integer.parseInt(type);

					ITypeService typeService = ServiceFactory.getInstance()
							.newTypeService();
					imgPath = typeService.getLocation("img", typeId);
					bookPath = typeService.getLocation("book", typeId);
					if (fileExtName.matches("^(jpg|gif|png)$")) {
						savePath = imgPath;
					} else if (fileExtName.matches("^(pdf|txt|doc)$")) {
						savePath = bookPath;
					}
					File f = new File(savePath);
					if (!f.exists()) {
						// 创建目录
						f.mkdirs();
					}
					// 创建一个文件输出流
					FileOutputStream out = new FileOutputStream(savePath + "\\"
							+ filename);
					// 创建一个缓冲区
					byte buffer[] = new byte[1024];
					// 判断输入流中的数据是否已经读完的标识
					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					int len = 0;
					while ((len = in.read(buffer)) > 0) {
						// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
						// + filename)当中
						out.write(buffer, 0, len);
					}
					// 关闭输入流
					in.close();
					// 关闭输出流
					out.close(); // 删除处理文件上传时生成的临时文件 //item.delete(); message =
									// "文件上传成功！";
				}
			}
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			e.printStackTrace();
			System.out.println("单个文件超出最大值！！！");
			return;
		} catch (FileUploadBase.SizeLimitExceededException e) {
			e.printStackTrace();
			System.out.println("上传文件的总的大小超出限制的最大值！！！");
			return;
		} catch (Exception e) {
			message = "文件上传失败！";
			e.printStackTrace();
		}

		// 添加书籍
		int priceInt = 0;

		int statusInt = 0;
		int stockInt = 0;
		int discountInt = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Timestamp t = null;
		try {
			priceInt = Integer.parseInt(price);
			typeId = Integer.parseInt(type);
			statusInt = Integer.parseInt(status);
			stockInt = Integer.parseInt(stock);
			discountInt = Integer.parseInt(discount);
			Date date = sdf.parse(time);
			t = new Timestamp(date.getTime());
		} catch (Exception e) {
		}

		Book book = new Book();
		book.setAuthor(author);
		bookPath=PathUtil.toRePath(bookPath)+filename;
		book.setContent(bookPath);
		book.setDiscount(discountInt);
		book.setDiscribe(describe);
		book.setImg(imgPath+"\\filename");
		book.setName(bookName);
		book.setPrice(priceInt);
		book.setStatus(statusInt);
		book.setStock(stockInt);
		book.setTime(t);
		book.setType(typeId);

		IBookService service = ServiceFactory.getInstance().newBookService();
		int bookId = service.addBook(book);

		if (bookId > 0) {
			request.setAttribute("msg", "添加成功");
		} else {
			request.setAttribute("msg", "添加失败");
		}
		request.getRequestDispatcher("/ManagerAddBook.jsp").forward(request, response);
	}
}
