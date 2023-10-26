package br.com.cartpurchase.mock;

import br.com.cartpurchase.model.dto.EmailDTO;

public class EmailDTOMock {

    public EmailDTO getEmailMock() {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setOwnerRef("Owner Teste");
        emailDTO.setEmailFrom("examplefrom@example.com");
        emailDTO.setEmailTo("exampleto@example.com");

        return emailDTO;
    }

}
