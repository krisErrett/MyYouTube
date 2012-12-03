<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="myyoutube.*" %>
<%@ page import="com.amazonaws.*" %>
<%@ page import="com.amazonaws.auth.*" %>
<%@ page import="com.amazonaws.services.ec2.*" %>
<%@ page import="com.amazonaws.services.ec2.model.*" %>
<%@ page import="com.amazonaws.services.s3.*" %>
<%@ page import="com.amazonaws.services.s3.model.*" %>
<%@ page import="com.amazonaws.services.simpledb.*" %>
<%@ page import="com.amazonaws.services.simpledb.model.*" %>

<%! 
	private AmazonEC2      ec2;
    private AmazonS3        s3;
 %>
<iframe width="200" height="100" frameBorder="0" src="upload.html"></iframe><br><br>
<%
    if (ec2 == null) {
        AWSCredentials credentials = new PropertiesCredentials(
            getClass().getClassLoader().getResourceAsStream("AwsCredentials.properties"));
        ec2 = new AmazonEC2Client(credentials);
        s3  = new AmazonS3Client(credentials);
    }
   
   ObjectListing objects = s3.listObjects(UploadServlet.AWS_S3_BUCKET);
   do
   {
       for (S3ObjectSummary s : objects.getObjectSummaries())
       {
           if(s.getKey().contains("mp4")) {
           %>
             <a href="#" onclick="jwplayer('container').load({ 'file':'rtmp://s318o941ger6u2.cloudfront.net/cfx/st/mp4:<%=s.getKey()%>' }).play();"><%=s.getKey()%></a><br><br>
             <%-- <a href="https://s3.amazonaws.com/randy-1354310562177/<%=s.getKey()%>"><%=s.getKey()%></a><br/><br/> --%>
           <%
           }
       }
       objects = s3.listNextBatchOfObjects(objects);
   } while (objects.isTruncated());
 %>