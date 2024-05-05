import { Box, Tab, Tabs, Typography } from "@mui/material";
import StatDto from "../../model/StatDto";
import { useSelector } from "react-redux";
import User from "../../model/UserDto";
import StatService from "../../service/StatService";
import { useEffect, useState } from "react";
import AllStatDto from "../../model/AllStatDto";
import StatList from "./StatList";

interface TabPanelProps {
    children?: React.ReactNode;
    index: number;
    value: number;
}

const TabPanel: React.FC<TabPanelProps> = (props: TabPanelProps) => {
    const { children, value, index, ...other } = props;
  
    return (
      <div
        className="test"
        role="tabpanel"
        hidden={value !== index}
        id={`vertical-tabpanel-${index}`}
        aria-labelledby={`vertical-tab-${index}`}
        {...other}
      >
        {value === index && (
          <Box sx={{ p: 3 }}>
            <Typography>{children}</Typography>
          </Box>
        )}
      </div>
    );
}

const AllStats: React.FC<any> = () => {
    const jwtToken = useSelector((state: any) => state.authContext.token);
    const [users, setUsers] = useState<User[]>([]);
    const [stats, setStats] = useState<[StatDto[]]>([[]]);
    const [value, setValue] = useState(0);
    const statService: StatService = new StatService();

    useEffect(() => {
        if(jwtToken != null) {
            loadStats();
        }
    }, []);

    const loadStats = async () => {
        const stats: AllStatDto = await statService.getAllStats(jwtToken);
        setUsers(stats.users);
        setStats(stats.stats);
    }

    function a11yProps(index: number) {
        return {
            id: `vertical-tab-${index}`,
            'aria-controls': `vertical-tabpanel-${index}`,
        };
    }

    const handleChange = (event: React.SyntheticEvent, newValue: number) => {
        setValue(newValue);
    };

    return (
        <Box sx={{ flexGrow: 1, bgcolor: 'background.paper', display: 'flex', height: '100%'}}>
            <Tabs
                orientation="vertical"
                value={value}
                onChange={handleChange}
                aria-label="Vertical tabs example"
                sx={{ borderRight: 1, borderColor: 'divider' }}
            >
                {users.map((key, index) => {
                    console.log(index);
                    return <Tab label={key.name} {...a11yProps(index)} />
                })}
            </Tabs>
            {users.map((key, index) => {
                return (
                    <TabPanel value={value} index={index}>
                        <StatList stats={stats.at(index) as StatDto[]} />
                    </TabPanel>
                )
            })}
        </Box>
    )
        
}

export default AllStats;