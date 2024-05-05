import { useSelector } from "react-redux";
import Communication from "../../model/Communication";
import { useEffect, useState } from "react";
import './SupervisionList.css'
import { Button } from "@mui/material";
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import { Role } from "../../model/SignInResponse";
import { useNavigate } from "react-router-dom";
import SupervisionService from "../../service/SupervisionService";
import SupervisionDto from "../../model/SupervisionDto";
import Supervision from "./Supervision";

const SupervisionList: React.FC<any> = () => {
    const [supervisions, setSupervisions] = useState<SupervisionDto[]>([]);
    const jwtToken = useSelector((state: any) => state.authContext.token);
    const role = useSelector((state: any) => state.authContext.userRole);
    const navigate = useNavigate();
    const supervisionService: SupervisionService = new SupervisionService();

    useEffect(() => {
        if(jwtToken != null) {
            loadSupervisions();
        }
    }, []);

    const reload = async () => {
        await loadSupervisions();
    }

    const loadSupervisions = async () => {
        const supervisionsDto: SupervisionDto[] = await supervisionService.getAllSupervisions(jwtToken);
        setSupervisions(supervisionsDto);
    }

    const addSupervision = async () => {
        navigate("/communications/add")
    }

    let button;
    if(role == Role.ADMIN) {
        button = <Button id="test1" className="addCommunicationButton" variant="outlined" color="success" startIcon={<AddCircleOutlineIcon />} onClick={addSupervision}>Add Supervision</Button>
    } else {
        button = <></>
    }

    return (
        <>
        <h2>Supervisions</h2>
        {button}
        <div className="supervisions">
            {supervisions.map((supervision: SupervisionDto) => {
                return <Supervision
                supervision={supervision}
                reload={reload}
                ></Supervision>
            })}
        </div>
        </>
        
    )
}

export default SupervisionList;