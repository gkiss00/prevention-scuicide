import Communication from "../model/Communication";
import CreateCommunicationRequest from "../model/CreateCommunicationRequest";
import EditCommunicationRequest from "../model/EditCommunicationRequest";


export default class CommunicationService {
    constructor() {}

    public async getMyStats(authToken: string): Promise<Communication[]> {
        authToken = "Bearer " + authToken
        const rawResponse: Response = await fetch("http://localhost:8080/PSCU/v1/communications", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : authToken
            },
            method: "GET",
        })
        const content = await rawResponse.json();
        return content as Communication[];
    }

    public async deleteCommunication(authToken: string, communicationId: string): Promise<void> {
        authToken = "Bearer " + authToken
        const rawResponse: Response = await fetch("http://localhost:8080/PSCU/v1/communications/" + communicationId, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : authToken
            },
            method: "DELETE",
        });
    }

    public async createCommunication(authToken: string, communication: CreateCommunicationRequest): Promise<void> {
        authToken = "Bearer " + authToken
        const rawResponse: Response = await fetch("http://localhost:8080/PSCU/v1/communications", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : authToken
            },
            body: JSON.stringify(communication),
            method: "POST",
        });
    }

    public async editCommunication(authToken: string, communication: EditCommunicationRequest): Promise<void> {
        authToken = "Bearer " + authToken
        const rawResponse: Response = await fetch("http://localhost:8080/PSCU/v1/communications", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : authToken
            },
            body: JSON.stringify(communication),
            method: "PUT",
        });
    }
}