import { useEffect, useState } from "react";
import "./Calendar.css"
import Day from "./Day";

const Calendar: React.FC<any> = () => {
    const [currentDate, setCurrentDate] = useState<Date>(new Date());
    const [week, setWeek] = useState<Date[]>([]);
    useEffect(() => {
        loadWeek();
    }, [currentDate]);

    const goPreviousWeek = () => {
        const date: Date = new Date();
        date.setTime(currentDate.getTime() - (7 * 24 * 3600 * 1000))
        setCurrentDate(date);
    }

    const goNextWeek = () => {
        const date: Date = new Date();
        date.setTime(currentDate.getTime() + (7 * 24 * 3600 * 1000))
        setCurrentDate(date);
    }

    const loadWeek = () => {
        let week: Date[] = [];
        let day = currentDate.getDay();
        const FIRST_DAY_OF_WEEK: Date = new Date();
        FIRST_DAY_OF_WEEK.setTime(currentDate.getTime() - (24 * 3600 * 1000 * day))
        for(let i = 1; i < 8; ++i) {
            let date = new Date();
            date.setTime(FIRST_DAY_OF_WEEK.getTime() + (24 * 3600 * 1000 * i));
            week.push(date);
        }
        setWeek(week);
    }

    return (
        <div>
            <div className="calendar">
                {
                    week.map((day) => {
                        const year: String = "" + day.getFullYear();
                        const month: String = ("" + (day.getMonth() + 1)).padStart(2, "0");
                        const da: String = ("" + (day.getDate())).padStart(2, "0");
                        const dayDate: string = year + "-" + month + "-" + da;
                        return <Day dayDate={dayDate}></Day>
                    })
                }
            </div>
            <input type="button" onClick={goPreviousWeek} value="Previous week"/>
            <input type="button" onClick={goNextWeek} value="Next week"/>
        </div>
        
    )
}

export default Calendar;