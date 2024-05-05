import { useSelector } from "react-redux";
import Communication from "../../model/Communication";
import CommunicationService from "../../service/CommunicationService";
import { useEffect, useState } from "react";
import Com from "./Communication";
import './CommunicationList.css'
import { Button } from "@mui/material";
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import { Role } from "../../model/SignInResponse";
import { useNavigate } from "react-router-dom";

const CommunicationList: React.FC<any> = () => {
    const [communications, setCommunications] = useState<Communication[]>([]);
    const jwtToken = useSelector((state: any) => state.authContext.token);
    const role = useSelector((state: any) => state.authContext.userRole);
    const navigate = useNavigate();
    const communicationService: CommunicationService = new CommunicationService();

    useEffect(() => {
        if(jwtToken != null) {
            loadCommunications();
        }
    }, []);

    const reload = async () => {
        await loadCommunications();
    }

    const loadCommunications = async () => {
        const stats: Communication[] = await communicationService.getMyStats(jwtToken);
        setCommunications(stats);
    }

    const addCommunication = async () => {
        navigate("/communications/add")
    }

    let button;
    if(role == Role.ADMIN) {
        button = <Button id="test1" className="addCommunicationButton" variant="outlined" color="success" startIcon={<AddCircleOutlineIcon />} onClick={addCommunication}>Add Communication</Button>
    } else {
        button = <></>
    }

    return (
        <>
        <h2>Communications</h2>
        {button}
        <div className="communications">
            {communications.map((com: Communication) => {
                return <Com
                communication={com}
                reload={reload}
                ></Com>
            })}
        </div>
        </>
        
    )
}

export default CommunicationList;