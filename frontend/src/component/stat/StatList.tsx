import { List } from "@mui/material";
import StatDto from "../../model/StatDto";
import Stat from "./Stat";

interface StatListProps {
    stats: StatDto[];
}

const StatList: React.FC<StatListProps> = (props: StatListProps) => {
    return (
        <List className="myStatList" sx={{ width: '100%', maxWidth: 800, bgcolor: 'background.paper' }}>
            {props.stats.map((stat: StatDto) => {
                return (
                        <Stat 
                            period={stat.period}
                            hours={stat.hours}
                            minutes={stat.minutes}
                            second={stat.second}
                            time={stat.time}
                        />
                )
            })}
        </List>
        
    )
}

export default StatList;