import { useDispatch, useSelector } from "react-redux";
import './Supervision.css'
import { Role } from "../../model/SignInResponse";
import { Button } from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import CommunicationService from "../../service/CommunicationService";
import { useNavigate } from "react-router-dom";
import { setEditableCommunication } from "../../context/AuthContext";
import SupervisionDto from "../../model/SupervisionDto";
import SupervisionService from "../../service/SupervisionService";

interface SupervisionProps {
    supervision: SupervisionDto;
    reload: () => void;
}

const Supervision: React.FC<SupervisionProps> = (props: SupervisionProps) => {
    const role = useSelector((state: any) => state.authContext.userRole);
    const jwtToken = useSelector((state: any) => state.authContext.token);
    const userId = useSelector((state: any) => state.authContext.userId);
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const supervisionService: SupervisionService = new SupervisionService();

    const editSupervision = async (event:any) => {
        event.preventDefault();
        dispatch(setEditableCommunication(props));
        navigate("/communications/edit");
    }

    const subscribeToSupervision = async (event:any) => {
        event.preventDefault();
        await supervisionService.subscribeToSupervision(jwtToken, props.supervision.id);
        props.reload();
    }

    const unsubscribeToSupervision = async (event:any) => {
        event.preventDefault();
        await supervisionService.unsubscribeToSupervision(jwtToken, props.supervision.id);
        props.reload();
    }

    const deleteSupervision = async (event:any) => {
        event.preventDefault();
        await supervisionService.deleteSupervision(jwtToken, props.supervision.id);
        props.reload();
    }

    let buttons;
    if(role != Role.ADMIN) {
        console.log(props.supervision.attendees, userId);
        console.log(props.supervision.attendees.indexOf(userId) < 0);
        if(props.supervision.attendees.indexOf(userId) < 0) {
            buttons = <div className="communicationButtons">
                <Button variant="outlined" onClick={subscribeToSupervision}>Subscribe</Button>
            </div>
        } else {
            buttons = <div className="communicationButtons">
                <Button variant="outlined" color="error" onClick={unsubscribeToSupervision}>Unsubscribe</Button>
            </div>
        }
    } else {
        buttons = <div className="communicationButtons">
            <Button variant="outlined" startIcon={<EditIcon />} onClick={editSupervision}>Edit</Button>
            <Button variant="outlined" color="error" startIcon={<DeleteIcon />} onClick={deleteSupervision}>Delete</Button>
        </div>
    }

    return (
        <div className="supervision">
            <div className="supervisionHeader">
                <h3 className="supervisionTitle">{props.supervision.title} given by {props.supervision.formatter}</h3>
                <p className="supervisionDate">{props.supervision.date}</p>
            </div>
            <div className="supervisionAttendees">
                {props.supervision.attendees.map((attender) => {
                    return <p>{attender}</p>
                })}
            </div>
            {buttons}
        </div>
    )
}

export default Supervision;