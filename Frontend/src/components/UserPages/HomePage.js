import React from 'react'
import { Link } from 'react-router-dom'

export default function HomePage() {
    return (
        <header>
        <div className="text-center">
            <h1 className="main-title home-page-title">Boxing Academy</h1>
            <Link to="/Academy">
                <button className="primary-button">Academy</button>
            </Link>
            <Link to="/Enrolled">
                <button className="primary-button">Enrolled Course</button>
            </Link>
            <Link to="/">
            <button className="primary-button">Log out</button>
                </Link>
            
        </div>
        </header>
    )
}

