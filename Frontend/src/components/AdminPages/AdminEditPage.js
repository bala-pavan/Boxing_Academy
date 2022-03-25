import React,{useState} from 'react'
import { Link } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css'
import '../../App.css'
import { useHistory } from 'react-router-dom'
import BackgroundImage from '../../assets/images/admin.jpg'

const App = () => {
    const [data,setData] = useState({
        institutename: '',
        instituteaddress : '',
        email : '',
        moblie:'',
        imageurl:'',
        institutedescription:'',
    } )
    const {institutename,instituteaddress,email,moblie,imageurl,institutedescription} =data;
    const changeHandler = e =>{
        setData({...data,[e.target.name]:e.target.value})
    }
  let history = useHistory();
 const[auth,setAuth]=useState(false);
 const submitHandler = e=>{
    e.preventDefault();
  
    if(instituteaddress.length <5){
        alert("EnterAcademyLocation must be atleast 5 characters");
    }
   else if(moblie.length !==10){
       alert("Contact number should be 10 digits");
   }
   else if (imageurl.length <8 || imageurl.length >14){
     alert("EnterAcademyImageUrl should be minimum 8 characters long");
   }
    else if(imageurl !== institutedescription){
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
            const response = await fetch('http://localhost:8080/admin/editintitute', requestOptions)

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
         <h2>Edit Academy</h2>
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
                   <p>Enter Academy name</p>
                   <input type="institutename" name="institutename" id="institutename" value={institutename} onChange={changeHandler} required /> <br />
                   <p>Enter the Academy email</p>
                   <input type="email" name="email" id="email" value={email} onChange={changeHandler} required /> <br />
                   <p>Enter Academy Location</p>
                   <input type="text" name="instituteaddress" id="instituteaddress" value={instituteaddress} onChange={changeHandler} required /> <br />
                   <p>Enter the Contact Number</p>
                   <input type="number" name="moblie" id="moblie" value={moblie} onChange={changeHandler} required /> <br />
                   <p>Enter the Academy Image Url</p> 
                   <input type="imageurl" name="imageurl" id="imageurl" value={imageurl} onChange={changeHandler} required /> <br />
                   <p>Enter Academy description</p>
                   <input type="EnterAcademyImageUrl" name="institutedescription" id="institutedescription" value={institutedescription} onChange={changeHandler} required  /> <br />
                   <button  onClick={()=> setAuth(true)} id="submitButton" type="submit" name="submit">UpdateAcademy</button>
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
