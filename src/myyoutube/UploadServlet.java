package myyoutube;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet
{
    public static final String AWS_S3_BUCKET = "my.you.tube.bucket.com";
    
    private final AWSCredentials CREDS;
    private final AmazonS3 S3;
      
    public UploadServlet() throws IOException
    {
        CREDS = new PropertiesCredentials(getClass().getClassLoader()
                .getResourceAsStream("AwsCredentials.properties"));
        S3 = new AmazonS3Client(CREDS);
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        resp.setContentType("text/html");
        ObjectListing objects = S3.listObjects(AWS_S3_BUCKET);
        do
        {
            for (S3ObjectSummary objectSummary : objects.getObjectSummaries())
            {
                resp.getWriter().println(objectSummary.getKey() + "<br>");
            }
            objects = S3.listNextBatchOfObjects(objects);
        } while (objects.isTruncated());
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        resp.setContentType("text/plain");
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    S3.putObject (
                        new PutObjectRequest ( 
                                AWS_S3_BUCKET, item.getName(), 
                                item.getInputStream(), makeS3MetaData(item))
                            .withCannedAcl(CannedAccessControlList.PublicRead)
                    );
                }
            }
            resp.getWriter().println("<script>window.top.location.href = \"home.jsp\"; </script>");
        } catch (FileUploadException e) {
            throw new ServletException("Cannot parse multipart request.", e);
        }
    }

    private ObjectMetadata makeS3MetaData(FileItem item) throws IOException
    {
        // Figure out content length for S3 header
        byte[] contentBytes = IOUtils.toByteArray(item.getInputStream());
        Long contentLength = Long.valueOf(contentBytes.length);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(contentLength);
        return metadata;
    }
}
