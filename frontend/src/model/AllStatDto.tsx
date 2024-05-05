import StatDto from "./StatDto";
import User from "./UserDto";

export default interface AllStatDto {
    users: User[];
    stats: [StatDto[]];
}