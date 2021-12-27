package com.veilsun.constructkey.client;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

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

    public String uploadFiles(String fileName, File file) throws Exception{
        if (fileName == null) throw new Exception("File name must be provided");
        PutObjectResult result = s3Client.putObject(rootBucket, fileName, file);
        return result.getETag();
    }
}
