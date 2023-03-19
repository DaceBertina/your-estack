package yourestack.epack.business.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import yourestack.epack.business.domain.RoleDTO;
import yourestack.epack.business.model.RoleEntity;
import yourestack.epack.business.service.impl.RoleServiceImpl;

@Log4j2
@Controller
@RequestMapping(value = "/api/v1/roles", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RoleController {

    private final RoleServiceImpl roleService;

    @PostMapping("/createRole")
    @Operation(summary = "Creating new role for application users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The role has been created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource not found",
                    content = @Content) })
    public ResponseEntity<RoleDTO> createNewRole (@Valid @RequestBody final RoleDTO role, final BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("New role cannot be created: error in {}", bindingResult);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

       RoleDTO newRole = roleService.createNewRole(role);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }

}
