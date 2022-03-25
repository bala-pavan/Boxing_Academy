import React,{useState} from 'react'
import { Link } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css'
import '../../App.css'
import { useHistory } from 'react-router-dom'
import BackgroundImage from '../../assets/images/admin.jpg'

const App = () => {
    const [data,setData] = useState({
        firstName:'',
        lastName:'',
        fatherName:'',
        motherName:'',
        gender:'',
        phoneNumber2:'',
        email: '',
        age: '',
        phoneNumber1:'',
        houseNo:'',
        streetName:'',
        areaName:'',
        pincode:'',
        state:'',
        nationality:''
    } )
    const {firstName,lastName,fatherName,motherName,gender,phoneNumber1,phoneNumber2,email,age,houseNo,streetName,areaName,pincode,state,nationality,EnterAcademyLocation,ContactNumber,EnterAcademyImageUrl,confirmEnterAcademyImageUrl} =data;
    const changeHandler = e =>{
        setData({...data,[e.target.name]:e.target.value})
    }
  let history = useHistory();
 const[auth,setAuth]=useState(false);
 const submitHandler = e=>{
    e.preventDefault();
  
    
    if(firstName.length <3){
        alert("firstName must be 3 characters");
      }
      else if(lastName.length <3){
        alert("lastName must be 3 characters");
  
      }
      else if(fatherName.length <3){
        alert("fatherName must be 3 characters");
      }
      else if(motherName.length <3){
        alert("motherName must be 3 characters");
      }
      else if(age.length >3){
        alert("age must be lessthan 3 characters");
  
      }
      else if(phoneNumber2.length !==10){
        alert("phoneNumber2 should be 10 digits");
      }
      else if(phoneNumber1.length !==10){
        alert("phoneNumber1 should be 10 digits");
      }
      else if(pincode.length <6 || pincode.length >6){
        alert("pinCode must be 6 characters");
      }
      else if(phoneNumber2 == phoneNumber1){
        alert("phoneNumbers should not be same");
      }
      else if(streetName.length <3){
        alert("streetName must be 3 characters");
  
      }
      else if(areaName.length <3){
        alert("areaName must be 3 characters");
  
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
            const response = await fetch('http://localhost:8080/admin/editstudent', requestOptions)

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
            {/* <h1 className="main-title home-page-title">Boxing Academy</h1> */}
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
            <p>Enter First name</p>
                   <input type="text" name="firstName" id="firstName" value={firstName} onChange={changeHandler} required /> <br />
                   <p>Enter Last name</p>
                   <input type="text" name="lastName" id="lastName" value={lastName} onChange={changeHandler} required /> <br />
                   <p>Enter Father name</p>
                   <input type="text" name="fatherName" id="fatherName" value={fatherName} onChange={changeHandler} required /> <br />
                   <p>Enter Mother name</p>
                   <input type="text" name="motherName" id="motherName" value={motherName} onChange={changeHandler} required /> <br />
                   <p>Enter male or female</p>
                   <input type="text" name="gender" id="male/female" value={gender} onChange={changeHandler} required /> <br />
                   <p>Enter email</p>
                   <input type="email" name="email" id="email" value={email} onChange={changeHandler} required /> <br />
                   {/* <input type="email" name="emailid" id="emailid" value={emailid} onChange={changeHandler} required /> <br /> */}
                   
                  <p>Enter age</p>
                   <input type="number" name="age" id="age" value={age} onChange={changeHandler} required /> <br />
                   <p>Enter phone number</p>
                   <input type="number" name="phoneNumber1" id="phoneNumber1" value={phoneNumber1} onChange={changeHandler} required /> <br />
                   <p>Enter Alternate phone number</p>
                   <input type="number" name="phoneNumber2" id="phoneNumber2" value={phoneNumber2} onChange={changeHandler} required /> <br />
                   <h6>Address Information</h6>
                  <p>Enter House No</p>
                   <input type="text" name="houseNo" id="houseNo" value={houseNo} onChange={changeHandler} required /> <br />
                   
                  <p>Enter Street Name</p>
                   <input type="text" name="streetName" id="streetName" value={streetName} onChange={changeHandler} required /> <br />
                   
                  <p>Enter Area name</p>
                   <input type="text" name="areaName" id="areaName" value={areaName} onChange={changeHandler} required /> <br />
                   
                  <p>Enter State</p>
                   <input type="text" name="state" id="state" value={state} onChange={changeHandler} required /> <br />
                   
                  <p>Enter Nationality</p>
                   <input type="text" name="nationality" id="nationality" value={nationality} onChange={changeHandler} required /> <br />
                   
                  <p>Enter Pincode</p>
                   <input type="number" name="pincode" id="pincode" value={pincode} onChange={changeHandler} required /> <br />
                   <button  onClick={()=> setAuth(true)} id="enrollNowButton" type="submit" name="submit">Update Student</button>
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
