package kg.attractor.projects.instagram.service.impl;

import kg.attractor.projects.instagram.model.ErrorResponseBody;
import kg.attractor.projects.instagram.service.ErrorService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ErrorServiceImpl implements ErrorService {

    @Override
    public ErrorResponseBody makeResponse(Exception e) {
        String message = e.getMessage();
        return ErrorResponseBody.builder()
                .title(message)
                .response(Map.of("errors", List.of(message)))
                .build();
    }

    @Override
    public ErrorResponseBody makeResponse(BindingResult result) {
        Map<String, List<String>> reasons = new HashMap<>();
        result.getFieldErrors().stream().filter(e->e.getDefaultMessage() != null)
                .forEach(e->{
                    List<String> errors = new ArrayList<>();
                    errors.add(e.getDefaultMessage());
                    if(!reasons.containsKey(e.getField())) {
                        reasons.put(e.getField(), errors);
                    }

                });

        return ErrorResponseBody.builder()
                .title("Valid Error")
                .response(reasons)
                .build();
    }
}
