package com.ayadykin.li;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

@WebServlet("/FileUploadHandler")
@MultipartConfig
public class FileUploadHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String rootPath = System.getProperty("catalina.home");
//	private static final Log logger = LogFactory.getLog(FileUploadHandler.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// process only if its multipart content

		String appPath = request.getServletContext().getRealPath("");

		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory())
						.parseRequest(request);
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String path = rootPath + File.separator
								+ new File(item.getName()).getName();
						File file = new File(path);
						if (file.length() > 40 * 1024) {
							request.setAttribute("error", "To big file size, max 40Kb");
							request.getRequestDispatcher("/error.jsp").forward(request, response);
						} else {
							item.write(file);
							request.setAttribute("message", "File Uploaded Successfully");
							request.setAttribute("path", File.createTempFile("","").getAbsolutePath());
							request.setAttribute("size", appPath);
							request.getRequestDispatcher("/result.jsp").forward(request, response);
						}
					}
				}

			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to " + ex);
			}

		} else {
			request.setAttribute("message", "Sorry this Servlet only handles file upload request");
		}
	}
}
