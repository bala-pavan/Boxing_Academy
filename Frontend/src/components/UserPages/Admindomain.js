import React,{useState} from 'react'

//import TextField from "@material-ui/core/TextField"
//import InputAdornment from "@material-ui/core/InputAdornment"

import { Link } from 'react-router-dom'

import Select from 'react-select'
import 'bootstrap/dist/css/bootstrap.min.css'
import '../../App.css'
import {Redirect} from "react-router"
import { useHistory } from 'react-router-dom'

const App = () => {
    const [data,setData] = useState({
        email: '',
        username : '',
        mobileNumber:'',
        password:'',
        confirmPassword:'',
    } )
    const {email,username,mobileNumber,password,confirmPassword} =data;
    const changeHandler = e =>{
        setData({...data,[e.target.name]:e.target.value})
    }
//   const userType = [

//     { label: "User", value: 1 },
//     { label: "Admin", value: 2 },
//     ];
  let history = useHistory();
  const[auth,setAuth]=useState(false);
 const submitHandler = e=>{
    e.preventDefault();
  
    if(username.length <5){
        alert("username must be atleast 5 characters");
    }
   else if(mobileNumber.length !==10){
       alert("mobile number should be 10 digits");
   }
   else if (password.length <8 || password.length >14){
    alert("Password should be minimum 8 characters long");
  }
    else if(password !== confirmPassword){
        alert("passwords are not matching");
    }
    else {

      console.log(JSON.stringify(data));
      //   let finalData = data['data'];
      //  history.push("/user/login");       

      const requestOptions = {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(data)
      };
      setTimeout(async () => {
          const response = await fetch('http://localhost:8080/admin/signup', requestOptions)

          if (response.status >= 200 && response.status <= 299) {
              let userData = JSON.stringify(await response.json());
              console.log(userData);

              if (userData === 'false') {
                  alert('Data Not Inserted');
              }
              else if (userData === 'true') {
                  alert('Data Inserted');
                  //write here to clear
                  history.push("/login");
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
      <header>
        
        <div className="buttons text-center">
           <Link to="/user"> 
                <button className="primary-button">User</button>
             </Link> 
            {/* <Link to="/admin"> */}
                <button className="secondary-button" id="reg_btn"><span>Admin</span></button>
            {/* </Link> */}
        </div>
    
        <div className="text-center m-5-auto">
            <h2>Join us</h2>
         
            
            <form onSubmit={submitHandler}>
                   
            {/* <p>
                    <label>User Type</label><br/>
                    </p>
                    
           
  <div className="container">
    <div className="row">
      <div className="col-md-12"></div>
      <div className="col-md-12">
        <Select options={ userType }/>
      </div>
      <div className="col-md-4"></div>
    </div>
  </div>      */}

  
                   <p>Enter email</p>
                   <input type="email" name="email" id="email" value={email} onChange={changeHandler} required /> <br />
                   <p>Enter Username</p>
                   <input type="text" name="username" id="username" value={username} onChange={changeHandler} required /> <br />
                   <p>Enter MobileNumber</p>
                   <input type="number" name="mobileNumber" id="mobileNumber" value={mobileNumber} onChange={changeHandler} required /> <br />
                   <p>Enter Password</p> 
                   <input type="password" name="password" id="password" value={password} onChange={changeHandler} required /> <br />
                   <p>Confirm Password</p>
                   <input type="password" name="confirmPassword" id="confirmPassword" value={confirmPassword} onChange={changeHandler} required  /> <br />
              
                   
                   <button  onClick={()=> setAuth(true)} id="submitButton" type="submit" name="submit">Submit</button>
                </form>
                <footer>
                <p>Already user? <Link to="/Login">Login</Link>.</p>
                <p><Link to="/">Back to Homepage</Link>.</p>
            </footer>
            </div>
            </header>
    )
}
export default App