package ap.deepstroll.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ap.deepstroll.bo.Result;
import ap.deepstroll.vo.response.ResponseVO;
import ap.deepstroll.vo.response.UploadFileVO;

@Service
public class FileService {

    @Value("${tencent.cos.secretId}")
    private String secretId;

    @Value("${tencent.cos.secretKey}")
    private String secretKey;

    @Value("${tencent.cos.region}")
    private String region;

    @Value("${tencent.cos.bucket}")
    private String bucket;

    public ResponseVO uploadFile(MultipartFile mFile) {
        try {
            File file = transMultipartFileTofile(mFile);
            String url = upload(file);
            return ResponseVO.builder()
                             .data(new UploadFileVO(url))
                             .result(new Result())
                             .build();
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseVO.builder()
                             .result(new Result("server error"))
                             .build();
        }
    }

    private String getKey(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    private String upload(File file){
        int index = file.getName().lastIndexOf(".");
        String key = getKey() + file.getName().substring(index);
    	
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        COSClient cosClient = new COSClient(cred, clientConfig);
 
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, file);
        cosClient.putObject(putObjectRequest);
        cosClient.shutdown();
        // Date expiration = new Date(new Date().getTime() + 5 * 60 * 10000);
        // URL url = cosClient.generatePresignedUrl(bucket, key , expiration);
        // String result = url.getHost() + url.getPath();
        return "https://deep-stroll-1303131952.cos.ap-beijing.myqcloud.com/" + key;
        //return "https://" + result;
    }
    
    private File transMultipartFileTofile(MultipartFile mFile) throws IOException{
        String fileName = mFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        File file = File.createTempFile(fileName, suffix);
        mFile.transferTo(file);
        return file;
    }
    
}
