package kg.attractor.projects.instagram.service;

import kg.attractor.projects.instagram.model.ErrorResponseBody;
import org.springframework.validation.BindingResult;

public interface ErrorService {
   ErrorResponseBody makeResponse(Exception e);
   ErrorResponseBody makeResponse(BindingResult result);

}
