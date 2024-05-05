import { Stack, TextField, Button } from "@mui/material";
import CommunicationService from "../../service/CommunicationService";
import { useSelector } from "react-redux";
import CreateCommunicationRequest from "../../model/CreateCommunicationRequest";
import { useState } from "react";
import './CommunicationForm.css'
import { useNavigate } from "react-router-dom";

const CommunicationForm: React.FC<any> = () => {
    const [title, setTitle] = useState<string>("");
    const [content, setContent] = useState<string>("");
    const jwtToken = useSelector((state: any) => state.authContext.token);
    const role = useSelector((state: any) => state.authContext.userRole);
    const navigate = useNavigate();
    const communicationService: CommunicationService = new CommunicationService();

    const handleTitle = (event: any) => {
        event.preventDefault();
        setTitle(event.target.value);
    }

    const handleContent = (event: any) => {
        event.preventDefault();
        setContent(event.target.value);
    }

    const addCommunication = async (event: any) => {
        event.preventDefault();
        await communicationService.createCommunication(jwtToken, new CreateCommunicationRequest(title, content));
        navigate(-1);
    }

    return (
        <div className="communicationFormPage">
            <form id="communicationFormForm">
                <Stack id="communicationFormFormStack" spacing={2}>
                    <h2 className="communicationFormPageTitle">Add new communication</h2>
                    <TextField id="communicationInputTitle" className="communicationInput" label="title" variant="outlined" onChange={handleTitle}></TextField>
                    <TextField id="communicationInputContent" className="communicationInput" label="content" variant="outlined" multiline onChange={handleContent}></TextField>
                    <Button id="test" variant="outlined" onClick={addCommunication}>Add</Button>
                </Stack>
            </form>
        </div>
    )
}

export default CommunicationForm;