import Communication from "../model/Communication";
import CreateCommunicationRequest from "../model/CreateCommunicationRequest";
import CreateSupervisionRequest from "../model/CreateSupervisionRequest";
import EditCommunicationRequest from "../model/EditCommunicationRequest";
import SupervisionDto from "../model/SupervisionDto";


export default class SupervisionService {
    constructor() {}

    public async getAllSupervisions(authToken: string): Promise<SupervisionDto[]> {
        authToken = "Bearer " + authToken
        const rawResponse: Response = await fetch("http://localhost:8080/PSCU/v1/supervisions", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : authToken
            },
            method: "GET",
        })
        const content = await rawResponse.json();
        return content as SupervisionDto[];
    }

    public async createSupervision(authToken: string, createSupervisionRequest: CreateSupervisionRequest): Promise<void> {
        authToken = "Bearer " + authToken
        const rawResponse: Response = await fetch("http://localhost:8080/PSCU/v1/supervisions", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : authToken
            },
            body: JSON.stringify(createSupervisionRequest),
            method: "POST",
        });
    }

    public async subscribeToSupervision(authToken: string, supervisionId: string): Promise<void> {
        authToken = "Bearer " + authToken
        const rawResponse: Response = await fetch("http://localhost:8080/PSCU/v1/supervisions/" + supervisionId + "/register", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : authToken
            },
            method: "PUT",
        });
    }

    public async unsubscribeToSupervision(authToken: string, supervisionId: string): Promise<void> {
        authToken = "Bearer " + authToken
        const rawResponse: Response = await fetch("http://localhost:8080/PSCU/v1/supervisions/" + supervisionId + "/unregister", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : authToken
            },
            method: "PUT",
        });
    }

    public async deleteSupervision(authToken: string, supervisionId: string): Promise<void> {
        authToken = "Bearer " + authToken
        const rawResponse: Response = await fetch("http://localhost:8080/PSCU/v1/supervisions/" + supervisionId, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : authToken
            },
            method: "DELETE",
        });
    }
}