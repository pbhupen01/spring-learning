package practice.spring.springrest.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.spring.springrest.dto.SubjectDTO;
import practice.spring.springrest.services.SubjectService;

import java.util.List;

import static practice.spring.springrest.utils.ControllerUtils.*;

@Api(description = "This is Subject API implementation")
@RestController
@RequestMapping("/" + VERSION + SUBJECTS)
public class SubjectController {

    SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService)
    {
        this.subjectService = subjectService;
    }

    @ApiOperation(
            value = "Get Subject",
            notes = "Returns Json data with Subject details"
    )
    @GetMapping( value = "/{id}")
    public ResponseEntity<SubjectDTO> findSubjectById(@PathVariable Long id)
    {
        return null;
    }

    @ApiOperation(
            value = "Create Subject",
            notes = "Returns Json data with Subject details"
    )
    @PostMapping
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody SubjectDTO subjectDTO)
    {
        return null;
    }

    @ApiOperation(
            value = "Update Subject",
            notes = "Returns Json data with Subject details"
    )
    @PatchMapping
    public ResponseEntity<SubjectDTO> updateSubject(@RequestBody SubjectDTO subjectDTO)
    {
        return null;
    }

    @ApiOperation(
            value = "Delete Subject",
            notes = "Returns Json data with Subject details"
    )
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping( value = "/{id}")
    public void deleteSubjectById(@PathVariable Long id)
    {
    }

    @ApiOperation(
            value = "Get List of Subjects",
            notes = "Returns Json data with Subject details"
    )
    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getSubjects()
    {
        return null;
    }
}
