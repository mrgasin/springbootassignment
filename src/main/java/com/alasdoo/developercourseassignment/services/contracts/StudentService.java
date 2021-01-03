package com.alasdoo.developercourseassignment.services.contracts;

import com.alasdoo.developercourseassignment.dtos.StudentDTO;

public interface StudentService extends CrudService<StudentDTO> {

    StudentDTO findByAccountName(String accountName);

}
