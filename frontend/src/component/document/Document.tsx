import { useSelector } from "react-redux";
import DocumentDto from "../../model/Document";
import { Role } from "../../model/SignInResponse";
import { Button } from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
import "./Document.css"
import DocumentService from "../../service/DocumentService";

interface DocumentProps {
    document: DocumentDto;
    reload: () => void;
}

const Document: React.FC<DocumentProps> = (props: DocumentProps) => {
    const jwtToken = useSelector((state: any) => state.authContext.token);
    const role = useSelector((state: any) => state.authContext.userRole);
    const documentService: DocumentService = new DocumentService();

    const deleteDocument = async (event:any) => {
        event.preventDefault();
        await documentService.deleteDocument(jwtToken, props.document.id);
        props.reload();
    }

    let buttons;
    if(role != Role.ADMIN) {
        buttons = <div className="documentsButtons">
            <a className="documentDownloadButton" href={`data:application/pdf;base64,${props.document.data.data}`} download={props.document.originalFileName}><Button variant="outlined">Download</Button></a>
        </div>
    } else {
        buttons = <div className="documentsButtons">
            <a className="documentDownloadButton" href={`data:application/pdf;base64,${props.document.data.data}`} download={props.document.originalFileName}><Button variant="outlined">Download</Button></a>
            <Button variant="outlined" color="error" startIcon={<DeleteIcon />} onClick={deleteDocument}>Delete</Button>
        </div>
    }

    return (
        <div className="documentListItem" id={props.document.id}>
            <p className="documentListItemName">{props.document.title}</p>
            <p className="documentListItemName">{props.document.originalFileName}</p>
            <p className="documentListItemName">{props.document.size}</p>
            {buttons}
        </div>
    )
}

export default Document;