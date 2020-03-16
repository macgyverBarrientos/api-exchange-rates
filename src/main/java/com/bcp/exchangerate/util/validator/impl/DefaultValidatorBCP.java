package com.bcp.exchangerate.util.validator.impl;

import com.bcp.exchangerate.util.validator.Validator;
import com.bcp.exchangerate.util.gce.BusinessServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.*;

@Component
public class DefaultValidatorBCP implements Validator {
    private static final Log LOG = LogFactory.getLog(DefaultValidatorBCP.class);
    private javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public DefaultValidatorBCP() {
    }

    public <T> void validate(T object, Class<?>... groups) {
        if (object == null) {
            throw new BusinessServiceException("mandatoryParametersMissing");
        } else {
            Set<ConstraintViolation<T>> constraintViolations = new HashSet();
            Iterator constraintViolationIterator;
            if (object instanceof Collection) {
                constraintViolationIterator = ((Collection)object).iterator();

                while(constraintViolationIterator.hasNext()) {
                    T element = (T) constraintViolationIterator.next();
                    constraintViolations.addAll(this.validator.validate(element, groups));
                }
            } else {
                constraintViolations.addAll(this.validator.validate(object, groups));
            }

            if (!constraintViolations.isEmpty()) {
                constraintViolationIterator = constraintViolations.iterator();
                ConstraintViolation<T> constraintViolation = (ConstraintViolation)constraintViolationIterator.next();
                String propertyPath = constraintViolation.getPropertyPath().toString();
                String message = propertyPath.concat(" ").concat(constraintViolation.getMessage());
                LOG.error(String.format("Error en validación de campo %s. Mensaje: %s.", propertyPath, message));
                List<?> constraintValidatorClasses = constraintViolation.getConstraintDescriptor().getConstraintValidatorClasses();
                if (constraintValidatorClasses != null && !constraintValidatorClasses.isEmpty() && constraintValidatorClasses.get(0) == NotNullValidator.class) {
                    throw new BusinessServiceException("mandatoryParametersMissing");
                } else {
                    throw new BusinessServiceException("wrongParameters");
                }
            }
        }
    }
}