package com.hemanth.AllinOneProgram.service;

import com.hemanth.AllinOneProgram.controller.StudentController;
import com.hemanth.AllinOneProgram.dto.StudentDto;
import com.hemanth.AllinOneProgram.exception.StudentIdNotFoundException;
import com.hemanth.AllinOneProgram.exception.StudentNameNotFoundException;
import com.hemanth.AllinOneProgram.model.Student;
import com.hemanth.AllinOneProgram.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService
{
    @Autowired
    StudentRepository sr;

    Logger logger = LoggerFactory.getLogger(StudentController.class);
    public List<StudentDto> getAllStudents()
    {


        List<Student> snew=sr.findAll();

        List<StudentDto> sto=new ArrayList<>();

        for (Student studdemo : snew)
        {
            StudentDto newstudent = new StudentDto(studdemo.getUsn(),studdemo.getName(),studdemo.getBranch(),studdemo.getEmail());

            sto.add(newstudent);
        }

        return sto;
    }




    public StudentDto addStudents(StudentDto s1)
    {

        String names=s1.getName();
        if(names.isEmpty())
            throw new StudentNameNotFoundException("Name is Not Found");

        Student sdata=new Student(s1.getUsn(), s1.getName(), s1.getBranch(), s1.getEmail());

        sr.save(sdata);

        return s1;
    }


    public void deleteStudents(StudentDto stud)
    {

        logger.info("Inside the delete Service  Method");
        sr.deleteAll();
    }


    public void deleteStudentsId(int id)
    {

        logger.info("Inside the deleteById Service  Method");
       if(id <0)
           throw new StudentIdNotFoundException("Id Enetered is not Proper");
        sr.deleteById(id);

    }


    public void updateStudent(StudentDto st)
    {

        logger.info("Inside the update Service  Method");
        Student sdata=new Student(st.getUsn(), st.getName(), st.getBranch(), st.getEmail());
        sr.save(sdata);

    }


    public Optional<Student> getById(int id) throws RuntimeException
    {

        logger.info("Inside the GetById Service  Method");
        if(id <0)
            throw new StudentIdNotFoundException("The id Entered is Invalid");
         Optional<Student> result=sr.findById(id);
        return result;

    }
}
