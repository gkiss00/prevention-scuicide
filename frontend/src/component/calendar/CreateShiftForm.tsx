import { useState } from "react";
import ShiftService from "../../service/ShiftService";
import "./Calendar.css"
import { useSelector } from "react-redux";
import CreateShiftRequest from "../../model/CreateShiftRequest";

interface CreateShiftFormProps {
    id: string;
    dayDate: string;
    hour: number; //hh
    reload: () => void;
}


const CreateShiftForm: React.FC<CreateShiftFormProps> = (props: CreateShiftFormProps) => {
    const defaultStartTime: string = ("" + props.hour).padStart(2, "0") + ":00"
    const defaultEndTime: string = ("" + (props.hour + 1)).padStart(2, "0") + ":00"
    const [startTime, setStartTime] = useState<string>(defaultStartTime);
    const [endTime, setEndTime] = useState<string>(defaultEndTime);
    const jwtToken = useSelector((state: any) => state.authContext.token);
    const shiftService: ShiftService = new ShiftService();
    const createShiftAndClose = async (event: any) => {
        event.preventDefault();
        const start: number = Number(startTime.split(":")[0]) * 60 + Number(startTime.split(":")[1]);
        const end: number = Number(endTime.split(":")[0]) * 60 + Number(endTime.split(":")[1]);
        const duration = end - start;
        const shift: CreateShiftRequest = new CreateShiftRequest("Grand bureau", props.dayDate, start, end, duration);
        await shiftService.createShift(jwtToken, shift);
        props.reload();
        const element: HTMLElement = document.getElementById(props.id) as HTMLElement;
        element.style.display = "none"
        
    }

    const changeStartTime = (event: any) => {
        event.preventDefault();
        setStartTime(event.target.value)
    }

    const changeEndTime = (event: any) => {
        event.preventDefault();
        setEndTime(event.target.value)
    }

    return (
        <div id={props.id} className="grey-background">
            <div className="create-shift-form">
                <input type="text" />
                <input type="time" min="00:00" max="23:59" value={startTime} onChange={changeStartTime} required />
                <input type="time" min="00:00" max="23:59" value={endTime} onChange={changeEndTime} required />
                <input type="button" onClick={createShiftAndClose} value="save" />
            </div>
        </div>
    )
}

export default CreateShiftForm;