import "./Calendar.css"
import CreateShiftForm from "./CreateShiftForm";

interface HourProps {
    dayDate: string;
    hour: number; //hh
    reload: () => void;
}


const Hour: React.FC<HourProps> = (props: HourProps) => {
    const createShift = (event: any) => {
        event.preventDefault();
        const element: HTMLElement = document.getElementById(props.dayDate + props.hour) as HTMLElement;
        element.style.display = "flex"
        element.style.alignItems = "center"
        element.style.justifyContent = "center"
    }
    return (
        <>
            <tr className="hour" onClick={createShift}>
                <td></td>
            </tr>
            <CreateShiftForm id={props.dayDate + props.hour} dayDate={props.dayDate} hour={props.hour} reload={props.reload}></CreateShiftForm>
        </>
        
    )
}

export default Hour;