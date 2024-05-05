import { URL } from "url";
import { BACKEND_URL } from "../constant/Constant";
import { SignInRequest } from "../model/SignInRequest";
import SignInResponse from "../model/SignInResponse";

export default class SignInService {
    constructor() {}

    public async signIn(request: SignInRequest): Promise<SignInResponse> {
        const rawResponse = await fetch("http://localhost:8080/PSCU/v1/authenticate", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(request)
        })
        console.log(rawResponse.headers);
        const content = await rawResponse.json();
        console.log(content);
        return content as SignInResponse;
    }
}