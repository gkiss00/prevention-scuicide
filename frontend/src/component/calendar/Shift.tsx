import ShiftDto from "../../model/Shift";
import ShiftService from "../../service/ShiftService";
import "./Calendar.css"
import { useSelector } from "react-redux";

interface ShiftProps {
    top: string;
    height: string;
    shift: ShiftDto;
    reload: () => void;
}

const Shift: React.FC<ShiftProps> = (props: ShiftProps) => {
    const jwtToken = useSelector((state: any) => state.authContext.token);
    const shiftService: ShiftService = new ShiftService();
    
    const deleteShift = async () => {
        await shiftService.deleteShift(jwtToken, props.shift.id);
        props.reload();
    }
    
    return (
        <div className="shift" style={{top:props.top, height:props.height}}>
            <input type="button" onClick={deleteShift} className="shift-delete" value="x" />
        </div>
    )
}

export default Shift;