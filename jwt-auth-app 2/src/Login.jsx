import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';



const Login = ({getData}) => {
  const navigate = useNavigate(); 
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  
  const handleLogin = async () => {
    
    try {
      const response = await axios.post('http://localhost:8080/authenticate', {
        'username': username, 'password': password
      }).then(res => {
      getData(res.data)
      const decodedToken = jwtDecode(res.data);
      console.log('User Role:', decodedToken);

      const userRole = decodedToken.roles; 
       
      switch (userRole) {
        case 'admin':
          navigate('/StudentForm');
          break;
        case 'student':
          navigate('/StudentDashboard');
          break;
        case 'faculty':
          navigate('/FaultyDashboard');
          break;
        default:
          break;
      }
    
      })
    } catch (error) {
    }
  };

  return (
    <div className="login-container">
      <div className="login-frame">
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button onClick={handleLogin}>Login</button>
      </div>
    </div>
  );
};

export default Login;