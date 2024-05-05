import StatDto from "../../model/StatDto";
import "./Stat.css"

const Stat: React.FC<StatDto> = (props: StatDto) => {
    return (
        <div className="myStatListItem" id={props.period}>
            <img className="myStatListItemBackgorund" src={require("./../../img/flowers.jpeg")}></img>
            <p>{props.period}</p>
            <p>{`${props.hours} / 12`}</p>
        </div>
    )
}

export default Stat;