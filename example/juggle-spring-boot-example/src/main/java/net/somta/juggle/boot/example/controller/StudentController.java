package net.somta.juggle.boot.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.juggle.boot.example.dto.StudentDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author husong
 */
@Tag(name = "学生接口")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Operation(summary = "获取用户信息")
    @GetMapping("/getStudentById")
    public StudentDTO getStudentById(Long studentId){
        StudentDTO studentDto = new StudentDTO();
        studentDto.setId(studentId);
        studentDto.setName("明天的地平线");
        return studentDto;
    }
}
