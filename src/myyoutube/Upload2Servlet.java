package myyoutube;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;

@SuppressWarnings("serial")
public class Upload2Servlet extends HttpServlet
{
    public static final String AWS_S3_BUCKET = "aws.s3.bucket";
    public static final String AWS_ACCESS_KEY = "AKIAIYA4NVASTTYAM4QA";
    public static final String AWS_SECRET_KEY = "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";
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        resp.setContentType("text/plain");
        resp.getWriter().println("Upload!");
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        AWSCredentials awsCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
        AmazonS3 s3 = new AmazonS3Client(awsCredentials);
        
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");
        out.println("<h1>Servlet File Upload Example using Commons File Upload</h1>");
        out.println();

        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        /*
         * Set the size threshold, above which content will be stored on disk.
         */
        fileItemFactory.setSizeThreshold(5 * 1024 * 1024); // 1 MB

        ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
        try
        {
            List items = uploadHandler.parseRequest(req);  
            Iterator itr = items.iterator();
            while (itr.hasNext())
            {
                FileItem item = (FileItem) itr.next();
                /*
                 * Handle Form Fields.
                 */
                if (item.isFormField())
                {
                    out.println("File Name = " + item.getFieldName()
                            + ", Value = " + item.getString());
                } else
                {
                    // Handle Uploaded files.
                    out.println("Field Name = " + item.getFieldName()
                            + ", File Name = " + item.getName()
                            + ", Content type = " + item.getContentType()
                            + ", File Size = " + item.getSize());
                    /*
                     * Write file to the ultimate location.
                     */
                    File file = new File(item.getName());
                    s3.putObject(new PutObjectRequest("randy.kjsdkfjsd", item.getName(), file));
                    item.write(file);
                }
                out.close();
            }
        } catch (FileUploadException ex)
        {
            throw new RuntimeException(ex);
            //log("Error encountered while parsing the request", ex);
        } catch (Exception ex)
        {
            throw new RuntimeException(ex);
            //log("Error encountered while uploading file", ex);
        }
        
        /*
        
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
                    InputStream file = item.getInputStream();
                    String result = sendToS3(file);
                    resp.getWriter().println("fieldname="+fieldname);
                    resp.getWriter().println("file="+filename);
                    resp.getWriter().println("result="+result);
                }
            }
        } catch (FileUploadException e) {
            throw new ServletException("Cannot parse multipart request.", e);
        }
        */
    }
    
    public String sendToS3(InputStream in)
    {
        try
        {
            AWSCredentials awsCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
            AmazonS3 s3 = new AmazonS3Client(awsCredentials);

            // create bucket
            String bucketName = "cloud-sample-bucket";
            s3.createBucket(bucketName);

            // set key
            String key = "object-name.txt";

            // set value
            File file = File.createTempFile("temp", ".txt");
            file.deleteOnExit();

            OutputStream out = new FileOutputStream(file);
            int read = 0;
            byte[] bytes = new byte[3145728];
            while ((read = in.read(bytes)) != -1)
            {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            s3.putObject(new PutObjectRequest(bucketName, key, file));
            return "Success";
        } catch (IOException e)
        {
            e.printStackTrace(System.out);
            return e.getMessage();
        }

    }
}
