//package com.innoventes.test.app.customvalidation;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//import java.util.regex.Pattern;
//
//public class WebsiteURLValidator implements ConstraintValidator<ValidWebsiteURL, String> {
//
//    private static final Pattern VALID_WEBSITE_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+\\.(com|in)$");
//
//    @Override
//    public void initialize(ValidWebsiteURL constraintAnnotation) {
//    }
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        return value != null && VALID_WEBSITE_PATTERN.matcher(value).matches();
//    }
//}
//
