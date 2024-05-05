import { Role } from "./SignInResponse";

export default interface UserDto {
    id: string;
    name: string;
    email: string;
    role: Role;
    active: boolean;
}