import { Stack, TextField, Button, Input } from "@mui/material";
import { useSelector } from "react-redux";
import { useEffect, useState } from "react";
import './DocumentList.css'
import { useNavigate } from "react-router-dom";
import DocumentService from "../../service/DocumentService";
import UploadFileRequest from "../../model/UploadFileRequest";
import DocumentDto from "../../model/Document";
import { Role } from "../../model/SignInResponse";
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import Document from "./Document";

const DocumentList: React.FC<any> = () => {
    const [title, setTitle] = useState<string>("");
    const [file, setFile] = useState<any>(null);
    const [documents, setDocuments] = useState<DocumentDto[]>([]);
    const jwtToken = useSelector((state: any) => state.authContext.token);
    const role = useSelector((state: any) => state.authContext.userRole);
    const navigate = useNavigate();
    const documentService: DocumentService = new DocumentService();

    const loadDocuments = async () => {
        const docs: DocumentDto[] = await documentService.getAllDocuments(jwtToken);
        setDocuments(docs);
    }

    const reload = async () => {
        await loadDocuments();
    }

    useEffect(() => {
        if(jwtToken != null) {
            loadDocuments();
        }
    }, []);

    const addCommunication = async () => {
        navigate("/documents/add")
    }

    let button;
    if(role == Role.ADMIN) {
        button = <Button id="uploadDocument" className="addCommunicationButton" variant="outlined" color="success" startIcon={<AddCircleOutlineIcon />} onClick={addCommunication}>Add Document</Button>
    } else {
        button = <></>
    }

    return (
        <div className="uploadDocumentFormPage">
            <h2>Drive</h2>
            {button}
            {documents.map((doc: DocumentDto) => {
                return (
                    <Document
                        document={doc}
                        reload={reload}
                    />
                )
            })}
        </div>
    )
}

export default DocumentList;