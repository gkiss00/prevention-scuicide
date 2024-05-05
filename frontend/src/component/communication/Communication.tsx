import { useDispatch, useSelector } from "react-redux";
import Com from "../../model/Communication";
import './Communication.css'
import { Role } from "../../model/SignInResponse";
import { Button } from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import CommunicationService from "../../service/CommunicationService";
import { useNavigate } from "react-router-dom";
import { setEditableCommunication } from "../../context/AuthContext";

interface CommunicationProps {
    communication: Com;
    reload: () => void;
}

const Communication: React.FC<CommunicationProps> = (props: CommunicationProps) => {
    const role = useSelector((state: any) => state.authContext.userRole);
    const jwtToken = useSelector((state: any) => state.authContext.token);
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const communicationService: CommunicationService = new CommunicationService();

    const editCommunication = async (event:any) => {
        event.preventDefault();
        dispatch(setEditableCommunication(props));
        navigate("/communications/edit");
    }

    const deleteCommunication = async (event:any) => {
        event.preventDefault();
        await communicationService.deleteCommunication(jwtToken, props.communication.id);
        props.reload();
    }

    let buttons;
    if(role != Role.ADMIN) {
        buttons = <></>
    } else {
        buttons = <div className="communicationButtons">
            <Button variant="outlined" startIcon={<EditIcon />} onClick={editCommunication}>Edit</Button>
            <Button variant="outlined" color="error" startIcon={<DeleteIcon />} onClick={deleteCommunication}>Delete</Button>
        </div>
    }

    return (
        <div className="communication">
            <div className="communicationHeader">
                <h3 className="communicationTitle">{props.communication.title}</h3>
                <p className="communicationDate">{props.communication.date}</p>
            </div>
            <p className="communicationContent">{props.communication.content}</p>
            <div className="communicationFooter">
                <p></p>
                <p className="communicationAuthor">{props.communication.author}</p>
            </div>
            {buttons}
        </div>
    )
}

export default Communication;