import { Stack, TextField, Button } from "@mui/material";
import CommunicationService from "../service/CommunicationService";
import { useSelector } from "react-redux";
import CreateCommunicationRequest from "../model/CreateCommunicationRequest";
import { useState } from "react";
import './CreateUserForm.css'
import { useNavigate } from "react-router-dom";
import UserService from "../service/UserService";
import CreateUserRequest from "../model/CreateUserRequest";

const CreateUserForm: React.FC<any> = () => {
    const [title, setTitle] = useState<string>("");
    const [content, setContent] = useState<string>("");
    const jwtToken = useSelector((state: any) => state.authContext.token);
    const role = useSelector((state: any) => state.authContext.userRole);
    const navigate = useNavigate();
    const userService: UserService = new UserService();

    const handleEmail = (event: any) => {
        event.preventDefault();
        setTitle(event.target.value);
    }

    const handleName = (event: any) => {
        event.preventDefault();
        setContent(event.target.value);
    }

    const addCommunication = async (event: any) => {
        event.preventDefault();
        await userService.createUser(jwtToken, new CreateUserRequest(title, content));
    }

    return (
        <div className="createUserFormPage">
            <form id="createUserForm">
                <Stack id="createUserFormStack" spacing={2}>
                    <h2 className="createUserFormPageTitle">Add new user</h2>
                    <TextField id="createUserFormInputTitle" className="createUserFormInput" label="email" variant="outlined" onChange={handleEmail}></TextField>
                    <TextField id="createUserFormInputContent" className="createUserFormInput" label="name" variant="outlined" onChange={handleName}></TextField>
                    <Button id="test" variant="outlined" onClick={addCommunication}>Add</Button>
                </Stack>
            </form>
        </div>
    )
}

export default CreateUserForm;