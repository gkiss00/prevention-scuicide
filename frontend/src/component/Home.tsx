import { Box, Tab, Tabs, Typography } from "@mui/material";
//import { TabPanel } from '@mui/lab';
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import './Home.css'
import MyStats from "./stat/myStats";
import CommunicationList from "./communication/CommunicationList";
import { setHomeValue } from "../context/AuthContext";
import CreateUserForm from "./CreateUserForm";
import UserList from "./UserList";
import UploadDocumentForm from "./document/DocumentList";
import AllStats from "./stat/AllStats";
import SupervisionList from "./supervision/SupervisionList";
import Calendar from "./calendar/Calendar";

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

const Home: React.FC<any> = () => {
    const jwtToken = useSelector((state: any) => state.authContext.token);
    const userName = useSelector((state: any) => state.authContext.userName);
    const homeValue = useSelector((state: any) => state.authContext.homeValue);
    const [value, setValue] = useState(homeValue);
    const dispatch = useDispatch()

    function a11yProps(index: number) {
        return {
          id: `vertical-tab-${index}`,
          'aria-controls': `vertical-tabpanel-${index}`,
        };
      }

      const handleChange = (event: React.SyntheticEvent, newValue: number) => {
        setValue(newValue);
        dispatch(setHomeValue(newValue));
      };

    return (
        <>
        <header>
            <h1>Welcome {userName}</h1>
        </header>
        <Box sx={{ flexGrow: 1, bgcolor: 'background.paper', display: 'flex', height: '100%'}}>
            <Tabs
                orientation="vertical"
                value={value}
                onChange={handleChange}
                aria-label="Vertical tabs example"
                sx={{ borderRight: 1, borderColor: 'divider' }}
            >
                <Tab label="My Stats" {...a11yProps(0)} />
                <Tab label="Communications" {...a11yProps(1)} />
                <Tab label="Supervisions" {...a11yProps(2)} />
                <Tab label="Drive" {...a11yProps(3)} />
                <Tab label="Create User" {...a11yProps(4)} />
                <Tab label="Google Form" {...a11yProps(5)} />
                <Tab label="Users" {...a11yProps(6)} />
                <Tab label="All Stats" {...a11yProps(7)} />
                <Tab label="Calendar" {...a11yProps(8)} />
            </Tabs>
            <TabPanel value={value} index={0}>
                <MyStats></MyStats>
            </TabPanel>
            <TabPanel value={value} index={1}>
                <CommunicationList></CommunicationList>
            </TabPanel>
            <TabPanel value={value} index={2}>
                <SupervisionList></SupervisionList>
            </TabPanel>
            <TabPanel value={value} index={3}>
              <UploadDocumentForm></UploadDocumentForm>
            </TabPanel>
            <TabPanel value={value} index={4}>
              <CreateUserForm></CreateUserForm>
            </TabPanel>
            <TabPanel value={value} index={5}>
            TO DO
            </TabPanel>
            <TabPanel value={value} index={6}>
              <UserList></UserList>
            </TabPanel>
            <TabPanel value={value} index={7}>
              <AllStats></AllStats>
            </TabPanel>
            <TabPanel value={value} index={8}>
              <Calendar></Calendar>
            </TabPanel>
            </Box>
        </>
    )
}

export default Home;

function dispatch(arg0: any) {
  throw new Error("Function not implemented.");
}
