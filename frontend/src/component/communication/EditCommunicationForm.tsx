import { Stack, TextField, Button } from "@mui/material";
import CommunicationService from "../../service/CommunicationService";
import { useDispatch, useSelector } from "react-redux";
import CreateCommunicationRequest from "../../model/CreateCommunicationRequest";
import { useEffect, useState } from "react";
import './EditCommunicationForm.css'
import { useNavigate } from "react-router-dom";
import EditCommunicationRequest from "../../model/EditCommunicationRequest";
import { setEditableCommunication } from "../../context/AuthContext";

const EditCommunicationForm: React.FC<any> = () => {
    const [title, setTitle] = useState<string>("");
    const [content, setContent] = useState<string>("");
    const jwtToken = useSelector((state: any) => state.authContext.token);
    const role = useSelector((state: any) => state.authContext.userRole);
    const communication = useSelector((state: any) => state.authContext.editableCommunication);
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const communicationService: CommunicationService = new CommunicationService();

    const handleTitle = (event: any) => {
        event.preventDefault();
        setTitle(event.target.value);
    }

    const handleContent = (event: any) => {
        event.preventDefault();
        setContent(event.target.value);
    }

    const editCommunication = async (event: any) => {
        event.preventDefault();
        dispatch(setEditableCommunication(null));
        await communicationService.editCommunication(jwtToken, new EditCommunicationRequest(communication.id, title, content));
        navigate(-1);
    }

    useEffect(() => {
        setTitle(communication.title);
        setContent(communication.content);
    }, [])

    return (
        <div className="EditCommunicationFormPage">
            <form id="EditCommunicationFormForm">
                <Stack id="EditCommunicationFormFormStack" spacing={2}>
                    <h2 className="EditCommunicationFormPageTitle">Edit communication</h2>
                    <TextField id="EditCommunicationInputTitle" className="communicationInput" label="title" variant="outlined" value={title} onChange={handleTitle}></TextField>
                    <TextField id="EditCommunicationInputContent" className="communicationInput" label="content" variant="outlined" multiline value={content} onChange={handleContent}></TextField>
                    <Button id="test" variant="outlined" onClick={editCommunication}>Edit</Button>
                </Stack>
            </form>
        </div>
    )
}

export default EditCommunicationForm;