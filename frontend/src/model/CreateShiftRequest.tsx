export default class CreateShiftRequest {
    place: string;
    day: string;
    startTime: number;
    endTime: number;
    duration: number;

    constructor (
        place: string,
        day: string,
        startTime: number,
        endTime: number,
        duration: number,
    ) {
        this.place = place;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }
}