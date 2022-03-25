import React from 'react'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import Admindomain from "./components/UserPages/Admindomain"
import Userdomain from "./components/UserPages/Userdomain"
import Courses from "./components/UserPages/Courses"
import Academy from "./components/UserPages/Academy"
import Enrolled from "./components/UserPages/Enrolled"
import EnrollForm from "./components/UserPages/EnrollForm"
import LandingPage from './components/UserPages/LandingPage'
import LoginPage from './components/UserPages/LoginPage'
import ForgetPasswordPage from './components/UserPages/ForgetPasswordPage'
import HomePage from './components/UserPages/HomePage'
import AdminLoginPage from './components/AdminPages/AdminLoginPage'
import AdminHomePage from './components/AdminPages/AdminHomePage'
import AdminAcademy from './components/AdminPages/AdminAcademy'
import AddAcademy from './components/AdminPages/AddAcademy'
import AdminEditPage from './components/AdminPages/AdminEditPage'
import AdminCourse from './components/AdminPages/AdminCourse'
import AdminAddCourse from './components/AdminPages/AdminAddCourse'
import AdminEditCourse from './components/AdminPages/AdminEditCourse'
import AdminStudentsList from './components/AdminPages/AdminStudentsList'
import AdminAddStudent from './components/AdminPages/AdminAddStudent'
import AdminEditStudent from './components/AdminPages/AdminEditStudent'
import './App.css'


export default function App() {
    return (
        
        <Router>
            <div>
                <Switch>
                    <Route exact path="/" component={ LandingPage } />
                    <Route path="/login" component={ LoginPage } />
                    <Route path="/admin" component={ Admindomain} />
                    <Route path="/user" component={ Userdomain } />
                    <Route path="/Courses" component={Courses}/>
                    <Route path="/Academy" component={Academy}/>
                    <Route path="/Enrolled" component={Enrolled}/>
                    <Route path="/EnrollForm" component={EnrollForm}/>
                    <Route path="/forget-password" component={ ForgetPasswordPage } />
                    <Route path="/home" component={ HomePage } />
                    <Route path="/AdminLoginPage" component={ AdminLoginPage } />
                    <Route path="/AdminHomePage" component={ AdminHomePage } />
                    <Route path="/AdminAcademy" component={ AdminAcademy } />
                    <Route path="/AddAcademy" component={ AddAcademy } />
                    <Route path="/AdminEditPage" component={ AdminEditPage} />
                    <Route path="/AdminCourse" component={ AdminCourse} />
                    <Route path="/AdminAddCourse" component={ AdminAddCourse} />
                    <Route path="/AdminEditCourse" component={ AdminEditCourse} />
                    <Route path="/AdminStudentsList" component={ AdminStudentsList} />
                    <Route path="/AdminAddStudent" component={ AdminAddStudent} />
                    <Route path="/AdminEditStudent" component={ AdminEditStudent} />
                </Switch>
            </div>
            
        </Router>
    )
}

