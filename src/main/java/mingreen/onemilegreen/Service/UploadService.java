package mingreen.onemilegreen.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadService {
    @Value("one-mile-green")
    private String bucket;

    private final AmazonS3Client amazonS3Client;

    public String uploadFiles(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID()+"-"+file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        String fileUrl=amazonS3Client.getUrl(bucket,fileName).toString();

        amazonS3Client.putObject(bucket,fileName,file.getInputStream(),metadata);

        return fileUrl;
    }
}
