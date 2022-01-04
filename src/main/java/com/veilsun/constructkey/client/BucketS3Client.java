package com.veilsun.constructkey.client;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Component
public class BucketS3Client {

    @Value("${aws.s3.root.bucket}")
    String rootBucket;

    AmazonS3 s3Client;

    @PostConstruct
    private void initS3BucketClient(){
        s3Client = AmazonS3ClientBuilder
                .standard()
                .withRegion("us-east-1")
                .build();
    }

    public String uploadFiles(String fileName, MultipartFile file, Optional<Map<String, String>> optionalMetaData) throws Exception{
        if (fileName == null) throw new Exception("File name must be provided");
        InputStream incomingFileInputStream = null;
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetaData.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(objectMetadata::addUserMetadata);
            }
        });
        try {
            incomingFileInputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PutObjectResult result = s3Client.putObject(rootBucket, fileName,incomingFileInputStream, objectMetadata);
        return result.getETag();
    }

    public List<S3ObjectSummary> listAllFiles(){
        return s3Client.listObjects(rootBucket).getObjectSummaries();
    }

    public S3Object getFile(String bucketId, String key){
        return s3Client.getObject(bucketId, key);
    }

    public Boolean deleteFile(String objectName){
        s3Client.deleteObject(rootBucket, objectName);
        return true;
    }
}
