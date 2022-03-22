import React,{useState} from 'react'
import { Link } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css'
import '../../App.css'
import { useHistory } from 'react-router-dom'
import BackgroundImage from '../../assets/images/admin.jpg'

const App = () => {
    const [data,setData] = useState({
        AcademyName: '',
        EnterAcademyLocation : '',
        Academyemail : '',
        ContactNumber:'',
        EnterAcademyImageUrl:'',
        confirmEnterAcademyImageUrl:'',
    } )
    const {AcademyName,EnterAcademyLocation,Academyemail,ContactNumber,EnterAcademyImageUrl,confirmEnterAcademyImageUrl} =data;
    const changeHandler = e =>{
        setData({...data,[e.target.name]:e.target.value})
    }
  let history = useHistory();
 const[auth,setAuth]=useState(false);
 const submitHandler = e=>{
    e.preventDefault();
  
    if(EnterAcademyLocation.length <5){
        alert("EnterAcademyLocation must be atleast 5 characters");
    }
   else if(ContactNumber.length !==10){
       alert("Contact number should be 10 digits");
   }
   else if (EnterAcademyImageUrl.length <8 || EnterAcademyImageUrl.length >14){
     alert("EnterAcademyImageUrl should be minimum 8 characters long");
   }
    else if(EnterAcademyImageUrl !== confirmEnterAcademyImageUrl){
        alert("EnterAcademyImageUrls are not matching");
    }
    else{
          console.log(data);
         history.push("/AdminAcademy");   
    }
  
  }
    return (
        <header style={ headerStyle }>
            {/* {data}; */}
         <div className="text-center">
            <h1 className="main-title home-page-title">Boxing Academy</h1>
            <Link to="/AdminAcademy">
                <button className="primary-button">Academy</button>
            </Link>
            <Link to="/Enrolled">
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
                   <p>Enter AcademyName</p>
                   <input type="AcademyName" name="AcademyName" id="academyName" value={AcademyName} onChange={changeHandler} required /> <br />
                   <p>Enter Academyemail</p>
                   <input type="Academyemail" name="Academyemail" id="Academyemail" value={Academyemail} onChange={changeHandler} required /> <br />
                   <p>Enter EnterAcademyLocation</p>
                   <input type="text" name="EnterAcademyLocation" id="EnterAcademyLocation" value={EnterAcademyLocation} onChange={changeHandler} required /> <br />
                   <p>Enter ContactNumber</p>
                   <input type="number" name="ContactNumber" id="ContactNumber" value={ContactNumber} onChange={changeHandler} required /> <br />
                   <p>Enter EnterAcademyImageUrl</p> 
                   <input type="EnterAcademyImageUrl" name="EnterAcademyImageUrl" id="EnterAcademyImageUrl" value={EnterAcademyImageUrl} onChange={changeHandler} required /> <br />
                   <p>Confirm EnterAcademyImageUrl</p>
                   <input type="EnterAcademyImageUrl" name="confirmEnterAcademyImageUrl" id="confirmEnterAcademyImageUrl" value={confirmEnterAcademyImageUrl} onChange={changeHandler} required  /> <br />
                   <button  onClick={()=> setAuth(true)} id="submitButton" type="submit" name="submit">AddAcademy</button>
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
