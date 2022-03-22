import React from "react";
import '../../Enrolled.css'
import { Link } from 'react-router-dom'
import Select from 'react-select';

const aquaticCreatures = [
	{ label: 'Strength training', value: 'Strength training' },
	{ label: 'Gymnastics training', value: 'Gymnastics training' },
  ];
  
export default function ProfileCard() {
	return (
			
<header>
<div>
		<Link to="/Academy">
			<button className="primary-button">Academy</button>
		</Link>
		
		<Link to="/Enrolled">
			<button className="primary-button">Enrolled Course</button>
		</Link>
	
		<Link to="/">
		<button className="primary-button">Log out</button>
			</Link>
		<br/>
		<center>
		<div className="App">	
      <Select
        options={aquaticCreatures}
      />
    </div>
	</center>
		<br />
		<center>
		<div className="card-container" id="enrolledCourse">

			 <h6>Course name : Strength training</h6>
             <h6> Course Duration : 3months</h6>
             <h6> Course Available Timings : 6am to 6pm</h6>
			<h6>Number of Students : 222</h6>
            <h6>Course Description : yyyy</h6>
			
		<Link to="/EnrollForm">
		<button className="primary-button">Enroll Course</button>
        </Link>
		
            </div>
			<br />
		
		<div className="card-container" id="enrolledCourse">
        
			 <h6>Course name : Conditioning work</h6>
             <h6> Course Duration : 5months</h6>
             <h6> Course Available Timings : 6am to 6pm</h6>
			<h6>Number of Students : 122</h6>
            <h6>Course Description : yyyy</h6>
			
		<Link to="/EnrollForm">
		<button className="primary-button">Enroll Course</button>
        </Link>
		
            </div>
			</center>
		<br/>
		<br/>
		</div>
		</header>
	);
}