package com.freewiretech.enterprise.asset.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.freewiretech.enterprise.asset.dto.BoostGroupDTO;
import com.freewiretech.enterprise.asset.dto.BoostGroupList;
import com.freewiretech.enterprise.asset.service.BoostGroupService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Validated
@Tag(name = "Boost Group API")
public class BoostGroupController {

  @Autowired private BoostGroupService propertyService;

  @Operation(
      summary = "Get boost group by Id",
      operationId = "getBoostGroupById",
      description = "Get boost group by Id",
      tags = {})
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Boost Group successfully retrieved"),
        @ApiResponse(responseCode = "400", description = "Unable to retrieve Boost Group")
      })
  @GetMapping(value = "/boostGroup/{id}")
  public ResponseEntity<BoostGroupDTO> getBoostGroupById(@PathVariable Integer id) {
    BoostGroupDTO boostGroup = propertyService.getBoostGroupById(id);
    if (boostGroup != null) {
      return new ResponseEntity<>(boostGroup, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Operation(
      summary = "Get paginated list of boost groups",
      operationId = "getListOfBoostGroups",
      description = "Get paginated list of boost groups",
      tags = {})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "List of boost groups successfully retrieved"),
        @ApiResponse(responseCode = "400", description = "Unable to retrieve list of properties")
      })
  @GetMapping(value = "/boostGroup/list")
  public ResponseEntity<BoostGroupList> getListOfBoostGroups(Pageable pageable) {
    Pageable pageableCriteria =
        PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
    BoostGroupList propertyList = propertyService.getBoostGroups(pageableCriteria);
    if (propertyList != null) {
      return new ResponseEntity<>(propertyList, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
