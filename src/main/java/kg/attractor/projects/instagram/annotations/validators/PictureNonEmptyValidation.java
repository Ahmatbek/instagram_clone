package kg.attractor.projects.instagram.annotations.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.attractor.projects.instagram.annotations.NonEmptyPicture;
import org.springframework.web.multipart.MultipartFile;

public class PictureNonEmptyValidation implements ConstraintValidator<NonEmptyPicture, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        return file != null && !file.isEmpty();
    }
}
