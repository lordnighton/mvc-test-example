package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TemplateFactory templateFactory;

    @SpyBean
    private AccountService accountService;

    @Test
    public void shouldMockSessionFactory() throws Exception {
        RestTemplate template = mock(RestTemplate.class);
        given(templateFactory.createSession()).willReturn(template);
//        given(template.getForEntity("google.com", String.class)).willReturn(new ResponseEntity("body", HttpStatus.OK));
        given(template.getForEntity(anyString(), any(Class.class))).willReturn(new ResponseEntity("body", HttpStatus.OK));

        MockHttpServletResponse response = mvc.perform(
                get("/accounts")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("body");

        verify(accountService, times(1)).getAccount();
        verify(templateFactory, times(1)).createSession();
        verifyNoMoreInteractions(accountService);
    }


}
