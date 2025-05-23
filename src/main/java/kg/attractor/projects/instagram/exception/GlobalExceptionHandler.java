package kg.attractor.projects.instagram.exception;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.projects.instagram.model.ErrorResponseBody;
import kg.attractor.projects.instagram.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorService errorService;

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(Model model, HttpServletRequest request) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseBody> validationHandler(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(errorService.makeResponse(e.getBindingResult()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentHandler(Model model, HttpServletRequest request) {
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
        return "errors/error";
    }

    @ModelAttribute("user")
    public void currentUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!auth.getPrincipal().equals("anonymousUser")) {
            model.addAttribute("authenticated", auth.getName());
        }

    }

}
