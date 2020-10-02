package io.swagger.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Individual;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-08T11:42:54.708Z")

@Controller
public class IndividualApiController implements IndividualApi {

    @Autowired
    private IndividualRepository individualRepository;

    private static final Logger log = LoggerFactory.getLogger(IndividualApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public IndividualApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Individual> createIndividual(@ApiParam(value = "The Individual to be created" ,required=true )  @Valid @RequestBody Individual individual) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json") || accept.contains("*/*")) {
            try {
            Individual save = individualRepository.save(individual);
            	return new ResponseEntity<Individual>(save, HttpStatus.CREATED);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Individual>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Individual>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    public ResponseEntity<Void> deleteIndividual(@ApiParam(value = "Identifier of the Individual",required=true) @PathVariable("id") String id) {
               try {
             	Individual ind = individualRepository.findOne(id);
             	if(ind!=null) {
             		individualRepository.delete(ind);
                  	return new ResponseEntity<Void>(HttpStatus.OK);
             	}else {
             		 throw new NotFoundException(404, "Record not available with "+id);
             	}
             } catch (Exception e) {
                 log.error("Couldn't serialize response for content type application/json", e);
                 return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
             }
    }

    public ResponseEntity<List<Individual>> listIndividual(@ApiParam(value = "Comma-separated properties to be provided in response") @Valid @RequestParam(value = "fields", required = false) String fields,@ApiParam(value = "Requested index for start of resources to be provided in response") @Valid @RequestParam(value = "offset", required = false) Integer offset,@ApiParam(value = "Requested number of resources to be provided in response") @Valid @RequestParam(value = "limit", required = false) Integer limit) {
            try {
            	List<Individual> list = individualRepository.findAll();
            	return new ResponseEntity<List<Individual>>(list, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Individual>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    public ResponseEntity<Individual> patchIndividual(@ApiParam(value = "Identifier of the Individual",required=true) @PathVariable("id") String id,@ApiParam(value = "The Individual to be updated" ,required=true )  @Valid @RequestBody Individual individual) throws NotFoundException {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json") || accept.contains("*/*")) {
            try {
            	Individual ind = individualRepository.findOne(id);
            	if(ind!=null) {
            		individual.setId(id);
            		Individual update = individualRepository.save(individual);
                 	return new ResponseEntity<Individual>(update, HttpStatus.CREATED);
            	}else {
            		throw new NotFoundException(404, "Record not available with "+id);
            	}
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Individual>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Individual>(HttpStatus.NOT_IMPLEMENTED);
    }
    public ResponseEntity<Individual> retrieveIndividual(@ApiParam(value = "Identifier of the Individual",required=true) @PathVariable("id") String id,@ApiParam(value = "Comma-separated properties to provide in response") @Valid @RequestParam(value = "fields", required = false) String fields) {
            try {
            	Individual individual = individualRepository.findOne(id);
            	if(individual!=null) {
            		return ResponseEntity.ok().body(individual);
            	}else {
            		throw new NotFoundException(404, "Record not available with "+id);
            	}
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Individual>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
  

}
