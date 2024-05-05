import { Button, Stack, TextField } from "@mui/material";
import { useSelector, useDispatch } from 'react-redux'
import './signIn.css'
import { useState } from "react";
import { SignInRequest } from "../model/SignInRequest";
import SignInService from "../service/SingInService";
import { setToken, setUserId, setUserName, setUserRole } from "../context/AuthContext";
import { Navigate, redirect, useNavigate } from "react-router-dom";
import SignInResponse from "../model/SignInResponse";

const SignIn: React.FC<any> = () => {
    const [email, setEmail] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const navigate = useNavigate();
    const jwtToken = useSelector((state: any) => state.authContext.token)
    const dispatch = useDispatch()
    const signInService: SignInService = new SignInService();

    const handleEmail = (event: any) => {
        event.preventDefault();
        setEmail(event.target.value);
    }

    const handlePassword = (event: any) => {
        event.preventDefault();
        setPassword(event.target.value);
    }

    const signIn = async (event: any) => {
        event.preventDefault();
        const request: SignInRequest = new SignInRequest(email, password);
        const response: SignInResponse = await signInService.signIn(request);
        console.log(response);
        dispatch(setToken(response.jwtToken));
        dispatch(setUserRole(response.user.role));
        dispatch(setUserName(response.user.name));
        dispatch(setUserId(response.user.id));
        navigate("/home");
    }

    return (
        <div className="signInPage">
            <form id="signInForm">
                <Stack id="signInFormStack" spacing={2}>
                    <TextField className="loginInput" label="email" variant="outlined" onChange={handleEmail}></TextField>
                    <TextField className="loginInput" type={"password"} label="password" variant="outlined" onChange={handlePassword}></TextField>
                    <Button id="test" variant="outlined" onClick={signIn}>Sign In</Button>
                </Stack>
            </form>
        </div>
    )
}

export default SignIn;