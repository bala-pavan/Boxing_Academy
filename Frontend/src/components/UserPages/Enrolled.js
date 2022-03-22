import React from "react";
import '../../Enrolled.css'
import { Link } from 'react-router-dom'



export default function ProfileCard() {
	return (
	
	<div>
			<header>

		<Link to="/Academy">
			<button className="primary-button">Academy</button>
		</Link>
		
		<Link to="/Enrolled">
			<button className="primary-button">Enrolled Course</button>
		</Link>
	
		<Link to="/">
		<button className="primary-button">Log out</button>
			</Link>
			</header>
		<br/>
		<br/>
		<center>
		<div className="card-container" id="enrolledCourse">
        <center>
		<header>
			 <h6>Course name : Strength training</h6>
             <h6> Joined Date : dd/mm/yyyy</h6>
             <h6> Course end date : dd/mm/yyyy</h6>
		<button className="primary-button">My Learning</button>
		</header>
		</center>
            </div>
			</center>
		<br/>
		<br/>
		</div>
	);
}

