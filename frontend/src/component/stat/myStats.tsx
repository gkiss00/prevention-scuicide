import { useSelector } from "react-redux";
import StatService from "../../service/StatService";
import { useEffect, useState } from "react";
import StatDto from "../../model/StatDto";
import { IconButton, List, ListItem, ListItemText, TextField } from "@mui/material";
import './myStats.css'
import Stat from "./Stat";
import StatList from "./StatList";

const MyStats: React.FC<any> = () => {
    const [myStats, setMyStats] = useState<StatDto[]>([]);
    const jwtToken = useSelector((state: any) => state.authContext.token);
    const statService: StatService = new StatService();

    useEffect(() => {
        if(jwtToken != null) {
            loadStats();
        }
    }, []);

    const loadStats = async () => {
        const stats: StatDto[] = await statService.getMyStats(jwtToken);
        setMyStats(stats);
    }
    
    return (
        <>
            <h2>Mes statistiques</h2>
            <StatList stats={myStats} />
        </>
    )
}

export default MyStats;