import React from "react";
import '../../Academy.css'
import { Link } from 'react-router-dom'

import BackgroundImg from '../../assets/images/RK.jpg'

import BackgroundImage from '../../assets/images/Delco.jpg'
import Select from 'react-select';

const aquaticCreatures = [
	{ label: 'Delco Boxing Academy', value: 'Delco Boxing Academy' },
	{ label: 'RK Boxing Academy', value: 'RK Boxing Academy' },
  ];


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
			<center>
		<div className="App">	
      <Select
        options={aquaticCreatures}
      />
    </div>
	</center>
			</header>
		<br/>
		<br />
		
		<center>
		
		<Link to="/Courses">
			<button>
	
		
		<div className="card-container">
			<header>
				<img src={BackgroundImage} alt="Delco Boxing Academy" />
				<h3 className="bold-text">Delco Boxing Academy</h3>
			</header>
			
			<div className="social-container">
				<div className="Place">
					<h2 className="bold-text">	Place:Banglore
			</h2>
				</div>
				
				<div className="Rating">
					<h2 className="bold-text">Rating:4.5</h2>
					
				</div>
			</div>
			</div>
			</button>
			</Link>
		<br/>
		<br/>
		<Link to="/Courses">
			<button>
	
		
		<div className="card-container">

			<header>
			<img src={BackgroundImg} alt="Delco Boxing Academy" />

				<h3 className="bold-text">RK Boxing Academy</h3>
			</header>
			
			<div className="social-container">
				
				<div className="Place">
					<h2 className="bold-text">	Place:Chennai
			</h2>
				</div>
				
				<div className="Rating">
					<h2 className="bold-text">Rating:4.4</h2>
					
				</div>
			</div>
		</div>
		</button>
			</Link>
		</center>
		
		</div>
	);
}

