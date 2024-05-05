import CreateUserRequest from "../model/CreateUserRequest";
import UserDto from "../model/UserDto";

export default class UserService {

    public async getAllUsers(authToken: string, active: Boolean | null): Promise<UserDto[]> {
        authToken = "Bearer " + authToken
        let url = "http://localhost:8080/PSCU/v1/users";
        if(active != null) {
            url += "?active=" + active;
        }
        const rawResponse: Response = await fetch(url, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : authToken
            },
            method: "GET",
        });
        const content = await rawResponse.json();
        return content as UserDto[];
    }

    public async createUser(authToken: string, communication: CreateUserRequest): Promise<void> {
        authToken = "Bearer " + authToken
        const rawResponse: Response = await fetch("http://localhost:8080/PSCU/v1/users", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : authToken
            },
            body: JSON.stringify(communication),
            method: "POST",
        });
    }
}