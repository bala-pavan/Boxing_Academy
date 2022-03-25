import React,{useState} from 'react'
import { Link } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css'
import '../../App.css'
import { useHistory } from 'react-router-dom'
import BackgroundImage from '../../assets/images/admin.jpg'

const App = () => {
    const [data,setData] = useState({
        coursename: '',
        courseduration : '',
        coursedescription:'',
    } )
    const {coursename,courseduration,coursedescription} =data;
    const changeHandler = e =>{
        setData({...data,[e.target.name]:e.target.value})
    }
  let history = useHistory();
 const[auth,setAuth]=useState(false);
 const submitHandler = e=>{
    e.preventDefault();
  
    if(coursename.length <5){
        alert("EnterAcademyNamw must be atleast 5 characters");
    }
   
    else{

        console.log(JSON.stringify(data));
        //   let finalData = data['data'];
        //  history.push("/user/login");       

        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        };
        setTimeout(async () => {
            const response = await fetch('http://localhost:8080/admin/addcourse', requestOptions)

            if (response.status >= 200 && response.status <= 299) {
                let userData = JSON.stringify(await response.json());
                console.log(userData);

                if (userData === 'false') {
                    alert('Data Not Inserted');
                }
                else if (userData === 'true') {
                    alert('Data Inserted');
                    //write here to clear
                    history.push("/AdminAcademy");
                }
                else {
                    alert(userData);
                }
            }
            else {
                let userData = (await response.json());
                alert(userData);
            }

        }, 3000);

    }

}
    return (
        <header style={ headerStyle }>
                     <div className="text-center">
            <Link to="/AdminAcademy">
                <button className="primary-button">Academy</button>
            </Link>
            <Link to="/AdminCourse">
                <button className="primary-button">Course</button>
            </Link>
            <Link to="/AdminStudentsList">
                <button className="primary-button">Students</button>
            </Link>
            <Link to="/">
            <button className="primary-button">Log out</button>
                </Link>
        </div>
        <div className="text-center m-5-auto">
            <form onSubmit={submitHandler}>
                   <p>Enter the course Name</p>
                   <input type="coursename" name="coursename" id="coursename" value={coursename} onChange={changeHandler} required /> <br />
                   <p>Enter the course duration</p>
                   <input type="courseduration" name="courseduration" id="courseduration" value={courseduration} onChange={changeHandler} required /> <br />
                   
                  
                   {/* <p>Enter the Academy Image Url</p> 
                   <input type="EnterAcademyImageUrl" name="EnterAcademyImageUrl" id="EnterAcademyImageUrl" value={EnterAcademyImageUrl} onChange={changeHandler} required /> <br /> */}
                   <p>Enter the course description</p>
                   <input type="coursedescription" name="coursedescription" id="coursedescription" value={coursedescription} onChange={changeHandler} required  /> <br />
                   <button  onClick={()=> setAuth(true)} id="submitButton" type="submit" name="submit">AddCourse</button>
                </form>
            </div>
            </header>
    );
    
}
const headerStyle = {
    width: "100%",
    height: "vh",
    background: `url(${BackgroundImage})`,
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
    backgroundSize: "cover"
}


export default App
