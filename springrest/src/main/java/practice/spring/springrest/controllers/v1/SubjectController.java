package practice.spring.springrest.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.spring.springrest.dto.SubjectDTO;
import static practice.spring.springrest.utils.ControllerUtils.*;

@Api(description = "This is Subject API implementation")
@RestController
@RequestMapping("/" + SUBJECT)
public class SubjectController {

    @ApiOperation(
            value = "Get Subject",
            notes = "Returns Json data with Subject details"
    )
    @GetMapping( value = "/{id}")
    public ResponseEntity<SubjectDTO> findSubjectById(@PathVariable Long id)
    {
        return null;
    }
}
