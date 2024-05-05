import CreateShiftRequest from "../model/CreateShiftRequest";
import ShiftDto from "../model/Shift";


export default class ShiftService {
    constructor() {}

    public async getShifts(authToken: string, startTime: string, endTime: string): Promise<ShiftDto[]> {
        authToken = "Bearer " + authToken
        const URL = "http://localhost:8080/PSCU/v1/shifts?startDate=" + startTime + "&endDate=" + endTime;
        console.log("URL ::", URL);
        const rawResponse: Response = await fetch(URL, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : authToken
            },
            method: "GET",
        })
        const content = await rawResponse.json();
        return content as ShiftDto[];
    }

    public async createShift(authToken: string, shift: CreateShiftRequest): Promise<ShiftDto> {
        authToken = "Bearer " + authToken
        const rawResponse: Response = await fetch("http://localhost:8080/PSCU/v1/shifts", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : authToken,
            },
            method: "POST",
            body: JSON.stringify(shift)
        });
        const content = await rawResponse.json();
        return content as ShiftDto;
    }

    public async deleteShift(authToken: string, shiftId: String): Promise<ShiftDto> {
        authToken = "Bearer " + authToken
        const rawResponse: Response = await fetch("http://localhost:8080/PSCU/v1/shifts/" + shiftId, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : authToken,
            },
            method: "DELETE"
        });
        const content = await rawResponse.json();
        return content as ShiftDto;
    }
}