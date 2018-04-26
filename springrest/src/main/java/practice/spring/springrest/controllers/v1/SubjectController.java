package practice.spring.springrest.controllers.v1;

import com.sun.net.httpserver.HttpsConfigurator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.spring.springrest.converter.SubjectDTOtoSubject;
import practice.spring.springrest.converter.SubjectToSubjectDTO;
import practice.spring.springrest.domain.Subject;
import practice.spring.springrest.dto.SubjectDTO;
import practice.spring.springrest.services.SubjectService;

import java.util.List;
import java.util.stream.Collectors;

import static practice.spring.springrest.utils.ControllerUtils.*;

@Slf4j
@Api(description = "This is Subject API implementation")
@RestController
@RequestMapping("/" + VERSION + SUBJECTS)
public class SubjectController {

    SubjectService subjectService;
    SubjectToSubjectDTO subjectToSubjectDTO;
    SubjectDTOtoSubject subjectDTOtoSubject;

    @Autowired
    public SubjectController(SubjectService subjectService
            , SubjectToSubjectDTO subjectToSubjectDTO, SubjectDTOtoSubject subjectDTOtoSubject)
    {
        this.subjectService = subjectService;
        this.subjectDTOtoSubject = subjectDTOtoSubject;
        this.subjectToSubjectDTO = subjectToSubjectDTO;
    }

    @ApiOperation(
            value = "Get Subject",
            notes = "Returns Json data with Subject details"
    )
    @GetMapping( value = "/{id}")
    public ResponseEntity<SubjectDTO> findSubjectById(@PathVariable Long id)
    {
        log.debug("query for Subject by Id " + id);
        Subject subject = subjectService.findById(id);
        SubjectDTO dto = subjectToSubjectDTO.convert(subject);
        return createResponseEntity(dto, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create Subject",
            notes = "Returns Json data with Subject details"
    )
    @PostMapping
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody SubjectDTO subjectDTO)
    {
        log.debug("create subject request " + subjectDTO);
        Subject subject = subjectService.createSubject(subjectDTOtoSubject.convert(subjectDTO));
        return createResponseEntity(subjectToSubjectDTO.convert(subject), HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Update Subject",
            notes = "Returns Json data with Subject details"
    )
    @PatchMapping
    public ResponseEntity<SubjectDTO> updateSubject(@RequestBody SubjectDTO subjectDTO)
    {
        log.debug("update subject request " + subjectDTO);
        Subject subject = subjectService.updateSubject(subjectDTOtoSubject.convert(subjectDTO));
        return createResponseEntity(subjectToSubjectDTO.convert(subject), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Delete Subject",
            notes = "Returns Json data with Subject details"
    )
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping( value = "/{id}")
    public void deleteSubjectById(@PathVariable Long id)
    {
        log.debug("delete for Subject by Id " + id);
        subjectService.deleteById(id);
    }

    @ApiOperation(
            value = "Get List of Subjects",
            notes = "Returns Json data with Subject details"
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<SubjectDTO>> getSubjects()
    {
        log.debug("get all subjects");
        List<SubjectDTO> dtos = subjectService.getAllSubjects().stream().map(subjectToSubjectDTO::convert).collect(Collectors.toList());
        return createResponseEntity(dtos, HttpStatus.OK);
    }
}
