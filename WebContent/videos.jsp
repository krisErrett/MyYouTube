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
<!-- ... -->
<%! 
	private AmazonEC2      ec2;
    private AmazonS3        s3;
 %>

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
           %>
             <!-- <a href="#" onclick="jwplayer().load({ 'file':'mp4:getting-started' }).play(); return false;">Click Here</a><br><br>-->
             <a href="https://s3.amazonaws.com/randy-1354310562177/<%=s.getKey()%>">
               <%=s.getKey()%>
             </a>
             <br/><br/>
           <%
       }
       objects = s3.listNextBatchOfObjects(objects);
   } while (objects.isTruncated());
 %>