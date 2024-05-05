package backend.service;

import backend.model.Role;
import backend.model.entity.DocFile;
import backend.model.entity.User;
import backend.model.exception.TeamUpApiException;
import backend.model.exception.UserNotFoundException;
import backend.model.exception.UserUnauthorizedException;
import backend.model.request.UploadDocFileRequest;
import backend.repository.DocFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class DocFileService {
    private final UserService userService;
    private final DocFileRepository docFileRepository;

    public DocFileService(UserService userService, DocFileRepository docFileRepository) {
        this.userService = userService;
        this.docFileRepository = docFileRepository;
    }

    public List<DocFile> getAllDocFile() {
        return docFileRepository.findAll();
    }


    public void uploadDocument(
            String authenticationToken,
            UploadDocFileRequest request
    ) throws UserNotFoundException, UserUnauthorizedException, TeamUpApiException {
        User user = userService.getUserFromToken(authenticationToken);
        if(user.getRole() != Role.ADMIN) {
            throw new UserUnauthorizedException("User not allowed to do this action");
        }
        try {
            DocFile docFile = new DocFile();
            docFile.setTitle(request.getTitle());
            docFile.setContentType(request.getFile().getContentType());
            docFile.setOriginalFileName(request.getFile().getOriginalFilename());
            docFile.setSize(request.getFile().getSize());
            docFile.setData(new Binary(BsonBinarySubType.BINARY, request.getFile().getBytes()));
            docFileRepository.save(docFile);
        } catch (IOException exception) {
            log.error(exception.getMessage());
            throw new TeamUpApiException(exception.getMessage());
        }
    }

    public void deleteDocument(
            String documentId,
            String authenticationToken
    ) throws UserNotFoundException, UserUnauthorizedException {
        User user = userService.getUserFromToken(authenticationToken);
        if(user.getRole() != Role.ADMIN) {
            throw new UserUnauthorizedException("User not allowed to do this action");
        }
        docFileRepository.deleteById(documentId);
    }
}
