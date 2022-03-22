import React from 'react'
import { Link } from 'react-router-dom'
import BackgroundImage from '../../assets/images/admin.jpg'

export default function HomePage() {
    return (
        <header style={ headerStyle }>
        <div className="text-center">
            <h1 className="main-title home-page-title">Boxing Academy</h1>
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
        </header>
    )
}
const headerStyle = {
    width: "100%",
    height: "200vh",
    background: `url(${BackgroundImage})`,
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
    backgroundSize: "cover"
}
