import React, { useState } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';

const StudentForm = () => {
  const [studentData, setStudentData] = useState({
    studentId: '',
    studentName: '',
    studentMobileNumber: '',
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setStudentData({
      ...studentData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    const jwtToken = Cookies.get('jwt_token');
    
    try {
      const response = await axios.post(
        'http://localhost:8080/Student/addStudent',
        studentData,
        {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
          },
        }
      );

      // Handle successful response (e.g., display a success message)
      console.log('Student added:', response.data);
    } catch (error) {
      // Handle errors (e.g., display an error message)
      console.error('Error adding student:', error);
    }
  };

  return (
    <div>
      <h2>Add Student</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="studentId">Student ID</label>
          <input
            type="number"
            id="studentId"
            name="studentId"
            value={studentData.studentId}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label htmlFor="studentName">Student Name</label>
          <input
            type="text"
            id="studentName"
            name="studentName"
            value={studentData.studentName}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label htmlFor="studentMobileNumber">Mobile Number</label>
          <input
            type="number"
            id="studentMobileNumber"
            name="studentMobileNumber"
            value={studentData.studentMobileNumber}
            onChange={handleInputChange}
          />
        </div>
        <button type="submit">Add Student</button>
      </form>
    </div>
  );
};

export default StudentForm;
