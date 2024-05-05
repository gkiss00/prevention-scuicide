import { Stack, TextField, Button, Input } from "@mui/material";
import { useSelector } from "react-redux";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import DocumentService from "../../service/DocumentService";
import UploadFileRequest from "../../model/UploadFileRequest";
import "./CreateDocumentForm.css"

const CreateDocumentForm: React.FC<any> = () => {
    const [title, setTitle] = useState<string>("");
    const [file, setFile] = useState<any>(null);
    const jwtToken = useSelector((state: any) => state.authContext.token);
    const role = useSelector((state: any) => state.authContext.userRole);
    const navigate = useNavigate();
    const documentService: DocumentService = new DocumentService();

    const handleTitle = (event: any) => {
        event.preventDefault();
        setTitle(event.target.value);
    }

    const handleFile = (event: any) => {
        event.preventDefault();
        setFile(event.target.files[0]);
    }

    const uploadFile = async (event: any) => {
        event.preventDefault();
        let formData: FormData = new FormData();
        formData.append("files", file);
        //formData.append('title', title);
        let jsonBodyData = { 'title': title };
        formData.append('jsonBodyData',
            new Blob([JSON.stringify(jsonBodyData)], { 
                type: 'application/json'
            }));
        await documentService.uploadFile(jwtToken, new UploadFileRequest(title, formData));
        navigate(-1);
    }

    return (
        <div className="createDocumentFormPage">
            <form id="createDocumentForm">
                <Stack id="createDocumentFormStack" spacing={2}>
                    <h2 className="createDocumentFormPageTitle">Upload document</h2>
                    <TextField id="createDocumentFormInputTitle" className="uploadDocumentFormInput" label="title" variant="outlined" onChange={handleTitle}></TextField>
                    <Input type="file" onChange={handleFile}></Input>
                    <Button id="test" variant="outlined" onClick={uploadFile}>Upload</Button>
                </Stack>
            </form>
        </div>
    )
}

export default CreateDocumentForm;