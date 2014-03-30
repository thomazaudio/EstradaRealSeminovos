package Modelo;



import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

/**
 * Servlet implementation class FileUploader
 */
public class FileUploader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) 
						  throws ServletException, IOException {
	
		System.out.println("chegou aqui");
		
		String ajaxUpdateResult = "";
		
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			
			for (FileItem item : items) {
				
				if (item.isFormField()) {
					ajaxUpdateResult += "Field " + item.getFieldName() + " with value: " + item.getString() + " is successfully read\n\r";
				} else {
					String fileName = item.getName();
					InputStream content = item.getInputStream();

					response.setContentType("text/plain");
					response.setCharacterEncoding("UTF-8");

					
				

					ajaxUpdateResult += "File " + fileName + " is successfully uploaded\n\r";
					
				}
			}
		} catch (FileUploadException e) {
			throw new ServletException("Parsing file upload failed.", e);
		}

		response.getWriter().print(ajaxUpdateResult);
	}

}
