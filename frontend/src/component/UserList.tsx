import { useSelector } from "react-redux";
import { useEffect, useState } from "react";
import { IconButton, List, ListItem, ListItemText, NativeSelect, TextField } from "@mui/material";
import './UserList.css'
import UserService from "../service/UserService";
import UserDto from "../model/UserDto";
import { NightShelter } from "@mui/icons-material";

const statusMap: Map<String, Boolean | null> = new Map([
    ["10", null],
    ["20", true],
    ["30", false],
]);

const UserList: React.FC<any> = () => {
    const [users, setUsers] = useState<UserDto[]>([]);
    const jwtToken = useSelector((state: any) => state.authContext.token);
    const userService: UserService = new UserService();

    useEffect(() => {
        console.log(jwtToken);
        if(jwtToken != null) {
            loadUsers();
        }
    }, []);

    const loadUsers = async () => {
        const users: UserDto[] = await userService.getAllUsers(jwtToken, null);
        setUsers(users);
    }

    const changeStatus = async (event: any) => {
        event.preventDefault();
        const status = statusMap.get(event.target.value) as (Boolean | null);
        const users: UserDto[] = await userService.getAllUsers(jwtToken, status);
        setUsers(users);
    }

    return (
        <>
        <h2>Users</h2>
        <List className="userList" sx={{ width: '100%', maxWidth: 800, bgcolor: 'background.paper' }}>
        <NativeSelect
            defaultValue={10}
            inputProps={{
            name: 'age',
            id: 'uncontrolled-native',
            }}
            onChange={changeStatus}
        >
            <option value={10}>All</option>
            <option value={20}>Active</option>
            <option value={30}>Inactive</option>
        </NativeSelect>
            {users.map((user: UserDto) => {
                return (
                    <div className="myStatListItem" id={user.id}>
                        <p>{user.name}</p>
                        <p>{user.role}</p>
                    </div>
                )
            })}
        </List>
        </>
        
    )
}

export default UserList;