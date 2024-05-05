export enum Role {
    USER = "USER",
    ADMIN = "ADMIN"
}

export interface userDto {
    id: string;
    email: string;
    role: Role;
    name: string;
}

export default interface SignInResponse {
    jwtToken: string;
    user: userDto;
}