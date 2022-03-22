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

        console.log(JSON.stringify(data));
        //   let finalData = data['data'];
        //  history.push("/user/login");       

        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        };
        setTimeout(async () => {
            const response = await fetch('http://localhost:8080/admin/editcourse', requestOptions)

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
            {/* {data}; */}
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
                   <input type="AcademyName" name="AcademyName" id="academyName" value={AcademyName} onChange={changeHandler} required /> <br />
                   <p>Enter the course duration</p>
                   <input type="Academyemail" name="Academyemail" id="Academyemail" value={Academyemail} onChange={changeHandler} required /> <br />
                   <p>Enter the course Timing</p>
                   <input type="text" name="EnterAcademyLocation" id="EnterAcademyLocation" value={EnterAcademyLocation} onChange={changeHandler} required /> <br />
                   <p>Enter no of students enrolled for the course</p>
                   <input type="number" name="ContactNumber" id="ContactNumber" value={ContactNumber} onChange={changeHandler} required /> <br />
                   {/* <p>Enter the Academy Image Url</p> 
                   <input type="EnterAcademyImageUrl" name="EnterAcademyImageUrl" id="EnterAcademyImageUrl" value={EnterAcademyImageUrl} onChange={changeHandler} required /> <br /> */}
                   <p>Enter the couse description</p>
                   <input type="EnterAcademyImageUrl" name="confirmEnterAcademyImageUrl" id="confirmEnterAcademyImageUrl" value={confirmEnterAcademyImageUrl} onChange={changeHandler} required  /> <br />
                   <button  onClick={()=> setAuth(true)} id="submitButton" type="submit" name="submit">UpdateCourse</button>
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
