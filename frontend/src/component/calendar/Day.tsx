import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import "./Calendar.css"
import Hour from "./Hour";
import ShiftService from "../../service/ShiftService";
import ShiftDto from "../../model/Shift";
import Shift from "./Shift";

interface DayProps {
    dayDate: string; //format yyyy-MM-dd
}

const TOTAL: number = 24 * 60;

const Day: React.FC<DayProps> = (props: DayProps) => {
    const jwtToken = useSelector((state: any) => state.authContext.token);
    // const [shifts, setShifts] = useState<ShiftDto[]>([])
    const [xxx, setXxx] = useState<JSX.Element[]>([]);
    const shiftService: ShiftService = new ShiftService();
    useEffect(() => {
        loadShift();
    }, []);

    
    const loadShift = async () => {
        const test: JSX.Element[] = [];
        const dayShifts: ShiftDto[] = await shiftService.getShifts(jwtToken, props.dayDate, props.dayDate);
        // setShifts(dayShifts);
        // const table: HTMLElement = document.getElementById(props.dayDate) as HTMLElement;
        for(let i = 0; i < dayShifts.length; ++i) {
            const shift = dayShifts[i];
            const shiftElement = <Shift shift={shift} top={(shift.startTime / TOTAL) * 100 + "%"} height={(shift.duration / TOTAL) * 100 + "%"} reload={loadShift}></Shift>
            test.push(shiftElement);
        }
        setXxx(test);
    }

    return (
        <div>
            <p>DATE : {props.dayDate}</p>
            <table className="day" id={props.dayDate}>
            {
                Array.from({ length: 24 }, (_, k) => (
                    <Hour dayDate={props.dayDate} hour={k} reload={loadShift}></Hour>
                ))
            }
            {xxx.map((t) => {
                return t;
            })}
            </table>
        </div>
        
    )
}

export default Day;