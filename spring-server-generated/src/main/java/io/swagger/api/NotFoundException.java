package io.swagger.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-08T11:42:54.708Z")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends ApiException {
	private static final long serialVersionUID = 1L;
	private int code;
	    public NotFoundException (int code, String msg) {
	        super(code, msg);
	        this.code = code;
	    }
}
