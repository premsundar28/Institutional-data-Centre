import React from 'react';
import Cookies from 'js-cookie';
import { useNavigate } from 'react-router-dom';

const Logout = () => {
  const navigate = useNavigate(); 
  const handleLogout = () => {
    
    Cookies.remove('jwt_token');
    window.location.reload(); // Refresh the page

    
  };

  return (
    <div>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default Logout;
