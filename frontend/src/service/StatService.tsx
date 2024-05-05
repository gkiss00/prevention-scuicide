import AllStatDto from "../model/AllStatDto";
import StatDto from "../model/StatDto";
import User from "../model/UserDto";

export default class StatService {
    constructor() {}

    public async getAllStats(authToken: string): Promise<AllStatDto> {
        console.log(authToken);
        authToken = "Bearer " + authToken
        console.log(authToken);
        const rawResponse: Response = await fetch("http://localhost:8080/PSCU/v1/stats", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : authToken
            },
            method: "GET",
        })
        console.log(rawResponse.status);
        const content = await rawResponse.json();
        console.log(content);
        return content as AllStatDto;
    }

    public async getMyStats(authToken: string): Promise<StatDto[]> {
        console.log(authToken);
        authToken = "Bearer " + authToken
        console.log(authToken);
        const rawResponse: Response = await fetch("http://localhost:8080/PSCU/v1/stats/mine", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : authToken
            },
            method: "GET",
        });
        const content = await rawResponse.json();
        return content as StatDto[];
    }
}