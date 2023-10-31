import React, { useState, useEffect } from 'react';
import Cookies from 'js-cookie';

function StudentForm() {
  const [student, setStudent] = useState({
    rollNo: '',
    name: '',
    department: '',
    validUpTo: '',
    mailId: '',
    cgpa: '',
    linkedin: '',
    github: '',
    certificates: [],
    projects: [],
    skills: [],
    internships: [],
  });

  // State to store the JWT token
  const [jwtToken, setJwtToken] = useState('');

  // Function to retrieve the JWT token from the cookie
  const getJwtTokenFromCookie = () => {
    const token = Cookies.get('jwt_token'); // Replace 'jwt_token' with your cookie name
    if (token) {
      setJwtToken(token);
    }
  };

  useEffect(() => {
    getJwtTokenFromCookie(); // Call this function when the component mounts
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setStudent((prevStudent) => ({
      ...prevStudent,
      [name]: value,
    }));
  };

  const handleAddCertificate = () => {
    setStudent((prevStudent) => ({
      ...prevStudent,
      certificates: [
        ...prevStudent.certificates,
        {
          certificateId: '',
          name: '',
          dateIssued: '',
        },
      ],
    }));
  };

  const handleRemoveCertificate = (index) => {
    const updatedCertificates = [...student.certificates];
    updatedCertificates.splice(index, 1);
    setStudent((prevStudent) => ({
      ...prevStudent,
      certificates: updatedCertificates,
    }));
  };

  const handleAddProject = () => {
    setStudent((prevStudent) => ({
      ...prevStudent,
      projects: [...prevStudent.projects, ''],
    }));
  };

  const handleRemoveProject = (index) => {
    const updatedProjects = [...student.projects];
    updatedProjects.splice(index, 1);
    setStudent((prevStudent) => ({
      ...prevStudent,
      projects: updatedProjects,
    }));
  };

  const handleAddSkill = () => {
    setStudent((prevStudent) => ({
      ...prevStudent,
      skills: [...prevStudent.skills, ''],
    }));
  };

  const handleRemoveSkill = (index) => {
    const updatedSkills = [...student.skills];
    updatedSkills.splice(index, 1);
    setStudent((prevStudent) => ({
      ...prevStudent,
      skills: updatedSkills,
    }));
  };

  const handleAddInternship = () => {
    setStudent((prevStudent) => ({
      ...prevStudent,
      internships: [
        ...prevStudent.internships,
        {
          company: '',
          role: '',
          skillOrFramework: '',
          startDate: '',
          endDate: '',
        },
      ],
    }));
  };

  const handleRemoveInternship = (index) => {
    const updatedInternships = [...student.internships];
    updatedInternships.splice(index, 1);
    setStudent((prevStudent) => ({
      ...prevStudent,
      internships: updatedInternships,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const apiUrl = 'http://localhost:8080/addStudent'; // Replace with your API URL

    try {
      const response = await fetch(apiUrl, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${jwtToken}`, // Include the JWT token in the Authorization header
        },
        body: JSON.stringify(student),
      });

      if (response.ok) {
        // Student data submitted successfully. Handle success here.
        console.log('Student data submitted successfully');
      } else {
        // Handle errors here.
        console.error('Error submitting student data');
      }
    } catch (error) {
      console.error('API request error:', error);
    }
  };

  return (
    <div>
      <h2>Add Student</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="rollNo">Roll No:</label>
          <input
            type="text"
            id="rollNo"
            name="rollNo"
            value={student.rollNo}
            onChange={handleChange}
          />
        </div>
        <div>
          <label htmlFor="name">Name:</label>
          <input
            type="text"
            id="name"
            name="name"
            value={student.name}
            onChange={handleChange}
          />
        </div>
        {/* Add more form fields for capturing student data here */}
        <div>
          <button type="button" onClick={handleAddCertificate}>
            Add Certificate
          </button>
          {student.certificates.map((certificate, index) => (
            <div key={index}>
              <label htmlFor={`certificate-${index}-name`}>Certificate Name:</label>
              <input
                type="text"
                id={`certificate-${index}-name`}
                name={`certificates[${index}].name`}
                value={certificate.name}
                onChange={(e) => handleCertificateChange(e, index)}
              />
              <button type="button" onClick={() => handleRemoveCertificate(index)}>
                Remove Certificate
              </button>
            </div>
          ))}
        </div>
        <div>
          {/* Add similar sections for projects, skills, and internships */}
        </div>
        <div>
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  );
}

export default StudentForm;
