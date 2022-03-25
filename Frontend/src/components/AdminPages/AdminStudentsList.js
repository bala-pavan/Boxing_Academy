import '../../Enrolled.css'
import { Link } from 'react-router-dom'
import React from "react"
import { TableContainer, Table, TableBody, TableCell, TableHead, TableRow, Paper } from '@material-ui/core'
export default function StudentList() {
	return (
		<TableContainer component={Paper}>
			<Table>
				<TableHead className="table">
					<TableRow>
						<TableCell>Student ID</TableCell>
						<TableCell>Student Name</TableCell>
						<TableCell>Enrolled Course</TableCell>
						<TableCell>Mobile Number</TableCell>
						
						<TableCell>Action</TableCell>
						
					</TableRow>
				</TableHead>
				<TableBody>
					<TableRow>
						<TableCell>12322213</TableCell>
						<TableCell>Arul</TableCell>
						<TableCell>Boxing drills</TableCell>
						<TableCell>3393992020</TableCell>
						<TableCell> 
						<p><Link to="/AdminEditStudent">edit</Link>/<Link to="/">delete</Link></p>

						</TableCell>
					</TableRow>
					<TableRow>
						<TableCell>2321232</TableCell>
						<TableCell>Kavya</TableCell>
						<TableCell>Strength Training</TableCell>
						<TableCell>3393556200</TableCell>
						<TableCell> 
						<p><Link to="/AdminEditStudent">edit</Link>/<Link to="/">delete</Link></p>
						</TableCell>
					</TableRow>
					<TableRow>
						<TableCell>2321123</TableCell>
						<TableCell>Manoj</TableCell>
						<TableCell>Conditioning work</TableCell>
						<TableCell>3393992027</TableCell>
						<TableCell> 
						<p><Link to="/AdminEditStudent">edit</Link>/<Link to="/">delete</Link></p>

						</TableCell>
					</TableRow>
				</TableBody>
			</Table>
		</TableContainer>
	)
}


