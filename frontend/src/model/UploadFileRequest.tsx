export default class UploadFileRequest {
    title: string;
    file: any;

    constructor(title: string, file: any) {
        this.title = title;
        this.file = file;
    }
}