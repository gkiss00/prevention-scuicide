package backend.controller;

import backend.model.entity.DocFile;
import backend.model.exception.UserNotFoundException;
import backend.model.exception.UserUnauthorizedException;
import backend.model.request.UploadDocFileRequest;
import backend.service.DocFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
public class DocFileController {
    private final DocFileService docFileService;

    public DocFileController(DocFileService docFileService) {
        this.docFileService = docFileService;
    }

    /* * * * * * * * * * * * * * * * * * * *

     *             GET MAPPING             *

     * * * * * * * * * * * * * * * * * * * */

    @GetMapping("/documents")
    public ResponseEntity getAllDocuments() {
        try {
            List<DocFile> docFiles = docFileService.getAllDocFile();
            return ResponseEntity.ok(docFiles);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /* * * * * * * * * * * * * * * * * * * *

     *             POST MAPPING            *

     * * * * * * * * * * * * * * * * * * * */

    @PostMapping(
            value = "/documents",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE
            }
    )
    public ResponseEntity<Object> uploadDocument(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticationToken,
            @RequestPart("jsonBodyData") UploadDocFileRequest dataReqData,
            @RequestPart("files") MultipartFile[] files
    ) {
        try {
            docFileService.uploadDocument(authenticationToken, new UploadDocFileRequest(dataReqData.getTitle(), files[0]));
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (UserNotFoundException | UserUnauthorizedException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /* * * * * * * * * * * * * * * * * * * *

     *            DELETE MAPPING           *

     * * * * * * * * * * * * * * * * * * * */

    @DeleteMapping(value = "/documents/{id}")
    public ResponseEntity deleteDocument(
            @PathVariable String id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authenticationToken
    ) {
        try {
            docFileService.deleteDocument(id, authenticationToken);
            return ResponseEntity.ok(null);
        } catch (UserNotFoundException | UserUnauthorizedException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
