package br.com.cartpurchase.model.dto;

import br.com.cartpurchase.mock.EmailDTOMock;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EmailDTOTest {

    @Test
    public void testValidEmailDTO() {
        EmailDTO emailDTO = new EmailDTOMock().getEmailMock();
        emailDTO.setOwnerRef("Owner Ref");
        emailDTO.setEmailFrom("emailfrom@example.com");
        emailDTO.setEmailTo("emailto@example.com");
        emailDTO.setSubject("Subject");
        emailDTO.setText("Text");

        assertFalse(emailDTO.getEmailTo().isBlank());
        assertFalse(emailDTO.getEmailFrom().isBlank());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<EmailDTO>> violations = validator.validate(emailDTO);
        assertTrue(violations.isEmpty());

        assertEquals("Owner Ref", emailDTO.getOwnerRef());
        assertEquals("emailfrom@example.com", emailDTO.getEmailFrom());
        assertEquals("emailto@example.com", emailDTO.getEmailTo());
        assertEquals("Subject", emailDTO.getSubject());
        assertEquals("Text", emailDTO.getText());

    }

}