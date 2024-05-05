import UploadFileRequest from "../model/UploadFileRequest";
import Document from "../model/Document";

export default class DocumentService {
    public async getAllDocuments(authToken: string): Promise<Document[]> {
        authToken = "Bearer " + authToken;
        const rawResponse: Response = await fetch("http://localhost:8080/PSCU/v1/documents", {
            headers: {
                //'Accept': 'multipart/form-data application/json',
                //'Content-Type': 'multipart/form-data',
                'Authorization' : authToken
            },
            method: "GET",
        });
        const content = await rawResponse.json();
        return content as Document[];
    }

    public async uploadFile(authToken: string, file: UploadFileRequest): Promise<void> {
        authToken = "Bearer " + authToken;
        const rawResponse: Response = await fetch("http://localhost:8080/PSCU/v1/documents", {
            headers: {
                //'Accept': 'multipart/form-data application/json',
                //'Content-Type': 'multipart/form-data',
                'Authorization' : authToken
            },
            body: file.file,
            method: "POST",
        });
    }

    public async deleteDocument(authToken: string, documentId: string): Promise<void> {
        authToken = "Bearer " + authToken;
        const rawResponse: Response = await fetch("http://localhost:8080/PSCU/v1/documents/" + documentId, {
            headers: {
                //'Accept': 'multipart/form-data application/json',
                //'Content-Type': 'multipart/form-data',
                'Authorization' : authToken
            },
            method: "DELETE",
        });
    }
}