import React from 'react';
import Login from './Login';
import Logout from './Logout';
import Cookies from 'js-cookie';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import StudentForm from './StudentForm';
import AdminDashboard from './AdminDashboard';
import StudentDashboard from './StudentDashboard';
import FaultyDashboard from './FaultyDashboard.jsx';


const App = () => {
  
  const[tokenid,setTokenId] = React.useState('')
  React.useEffect(() =>{
    setTokenId(Cookies.get('jwt_token'))
  } ,[])
  const getData = (token) =>{
    console.log(token); Cookies.set('jwt_token', token); 
    setTokenId(token)
  }
  return (
    <BrowserRouter>
    <div>
      {tokenid !== undefined ? (
        <Routes>
          <Route path="/StudentForm" element={<StudentForm />} />
          <Route path="/" element={<Login />} />
          <Route path="/Logout" element={<Logout />} />
          <Route path="/AdminDashboard" element={<AdminDashboard />} />
          <Route path="/StudentDashboard" element={<StudentDashboard />} />
          <Route path="/FaultyDashboard" element={<FaultyDashboard />} />
        </Routes>
      ) : (
        <Login getData={getData} />
      )}
    </div>
  </BrowserRouter>
  );
};

export default App;
