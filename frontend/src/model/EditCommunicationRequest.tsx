export default class EditCommunicationRequest {
    id: string;
    title: string;
    content: string;

    constructor(id: string, title: string, content: string) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}