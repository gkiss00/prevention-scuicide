export default class CreateSupervisionRequest {
    title: string;
    date: string;

    constructor(title: string, date: string) {
        this.title = title;
        this.date = date;
    }
}