package backend.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("documents")
public class DocFile {
    @Id
    private String id;
    private String title;
    private String contentType;
    private String originalFileName;
    private long size;
    private Binary data;
}
