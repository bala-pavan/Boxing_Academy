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
			
		<Link to="/AdminEditCourse">
		<button className="primary-button">EditCourse</button>
        </Link>
        <Link to="">
		<button className="primary-button">DeleteCourse</button>
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
		<button className="primary-button">EditCourse</button>
        </Link>
        <Link to="/EnrollForm">
		<button className="primary-button">DeleteCourse</button>
        </Link>
		
            </div>
			</center>
            <Link to="/AdminAddCourse">
		<button className="primary-button">Add Course</button>
        </Link>
		<br/>
		<br/>
		</div>
		</header>
	);
}