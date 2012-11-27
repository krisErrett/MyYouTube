package myyoutube;

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

@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        resp.setContentType("text/plain");
        resp.getWriter().println("Upload!");
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    String fieldname = item.getFieldName();
                    String fieldvalue = item.getString();
                    resp.setContentType("text/plain");
                    resp.getWriter().println("fieldname="+fieldname);
                    resp.getWriter().println("fieldvalue="+fieldvalue);
                    // ... (do your job here)
                } else {
                    // Process form file field (input type="file").
                    String fieldname = item.getFieldName();
                    String filename = item.getName();
                    InputStream filecontent = item.getInputStream();
                    // ... (do your job here)
                    resp.getWriter().println("fieldname="+fieldname);
                    resp.getWriter().println("fieldvalue="+filename);
                }
            }
        } catch (FileUploadException e) {
            throw new ServletException("Cannot parse multipart request.", e);
        }

        // ...
    }
}
