package backend.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadDocFileRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private MultipartFile file;
}
