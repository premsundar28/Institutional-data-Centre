import React, { useState } from 'react';

const SignUpForm = () => {
  // Define state variables to store user input
  const [formData, setFormData] = useState({
    email: '',
    username: '',
    password: '',
    roles: 'user', 
  });

  // Function to handle form submission
  const handleFormSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/newUser', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        // Handle successful registration, e.g., show a success message or redirect

        // Reset the form data to its initial state
        setFormData({
          email: '',
          username: '',
          password: '',
          role: 'user',
        });
      } else {
        // Handle registration error, e.g., display an error message
      }
    } catch (error) {
      // Handle network or other errors
    }
  };

  // Function to update state when input fields change
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const formStyle = {
    maxWidth: '400px',
    margin: '0 auto',
    padding: '20px',
    border: '1px solid #ccc',
    borderRadius: '5px',
    backgroundColor: '#f9f9f9',
  };

  const labelStyle = {
    display: 'block',
    marginBottom: '5px',
  };

  const inputStyle = {
    width: '100%',
    padding: '10px',
    marginBottom: '10px',
    borderRadius: '3px',
    border: '1px solid #ccc',
  };

  const buttonStyle = {
    background: '#007BFF',
    color: '#fff',
    padding: '10px 15px',
    border: 'none',
    borderRadius: '3px',
    cursor: 'pointer',
  };

  return (
    <div>
      <h2>Sign Up</h2>
      <form onSubmit={handleFormSubmit} style={formStyle}>
        <div>
          <label style={labelStyle}>Email:</label>
          <input
            type="text"
            name="email"
            value={formData.email}
            onChange={handleInputChange}
            style={inputStyle}
          />
        </div>
        <div>
          <label style={labelStyle}>Username:</label>
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleInputChange}
            style={inputStyle}
          />
        </div>
        <div>
          <label style={labelStyle}>Password:</label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleInputChange}
            style={inputStyle}
          />
        </div>
        <button type="submit" style={buttonStyle}>
          Sign Up
        </button>
      </form>
    </div>
  );
};

export default SignUpForm;
