package com.alasdoo.developercourseassignment.mappers;

import com.alasdoo.developercourseassignment.dtos.DeveloperCourseDTO;
import com.alasdoo.developercourseassignment.entities.DeveloperCourse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeveloperCourseMapper {

    public DeveloperCourseDTO transformToDTO(DeveloperCourse developerCourseSrc) {
        DeveloperCourseDTO developerCourseDTO = new DeveloperCourseDTO();
        BeanUtils.copyProperties(developerCourseSrc, developerCourseDTO);
        return developerCourseDTO;
    }

    public DeveloperCourse transformToEntity(DeveloperCourseDTO developerCourseSrc) {
        DeveloperCourse developerCourse = new DeveloperCourse();
        BeanUtils.copyProperties(developerCourseSrc, developerCourse);
        return developerCourse;
    }

    public List<DeveloperCourseDTO> transformToListOfDTO(List<DeveloperCourse> developerCourseSrc) {
        List<DeveloperCourseDTO> developerCourseDTO = new ArrayList<>(developerCourseSrc.size());
        for (DeveloperCourse developerCourse : developerCourseSrc) {
            developerCourseDTO.add(transformToDTO(developerCourse));
        }
        return developerCourseDTO;
    }

}
