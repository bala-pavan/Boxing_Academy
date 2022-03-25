import React,{useState} from 'react'
import { Link } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css'
import '../../App.css'
import { useHistory } from 'react-router-dom'
import BackgroundImage from '../../assets/images/admin.jpg'

const App = () => {
    const [data,setData] = useState({
        email: '',
        password:'',
    } )
    const {email,password} =data;
    const changeHandler = e =>{
        setData({...data,[e.target.name]:e.target.value})
    }
    let history = useHistory();
  const[auth,setAuth]=useState(false);
 const submitHandler = e=>{
    e.preventDefault();
    if (password.length <8 || password.length >14){
        alert("Password should be minimum 8 characters long");
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
            const response = await fetch('http://localhost:8080/admin/login', requestOptions)

            if (response.status >= 200 && response.status <= 299) {
                let userData = JSON.stringify(await response.json());
                console.log(userData);

                if (userData === 'false') {
                    alert('Data Not Inserted');
                }
                else if (userData === 'true') {
                    alert('Data Inserted');
                    //write here to clear
                    history.push("/AdminHomePage");
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
        <div className="text-center m-5-auto">
            <h2>Sign in to us</h2>
            <form onSubmit={submitHandler}>                <p>
                    <label>email address</label><br/>
                    <input type="email" name="email" id="email" value={email} onChange={changeHandler} required /> <br />
                </p>
                <p>
                    <label>Password</label>
                    <Link to="/forget-password"><label className="right-label">Forget password?</label></Link>
                    <br/>
                    <input type="password" name="password" id="password" value={password} onChange={changeHandler} required /> <br />

                </p>
                <p>
                <button  onClick={()=> setAuth(true)} id="LoginButton" type="submit" name="submit">Login</button>

                </p>
            </form>
            <footer>
                <p>First time? <Link to="/User">Create an account</Link>.</p>
                <p><Link to="/">Back to Homepage</Link>.</p>
            </footer>
        </div>
        </header>
    )
}
const headerStyle = {
    width: "100%",
    height: "100vh",
    background: `url(${BackgroundImage})`,
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
    backgroundSize: "cover"
}
export default App