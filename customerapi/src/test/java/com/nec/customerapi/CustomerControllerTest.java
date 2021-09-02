package com.nec.customerapi;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.nec.customerapi.models.Customer;





@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = CustomerapiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles(value = "integration")
public class CustomerControllerTest {
	@Rule
    public WireMockRule wireMockRule = new WireMockRule(9998);

	 @Autowired
	    private TestRestTemplate testRestTemplate;


    @Before
    public void setUp() {
        mockRemoteService();
    }
    @Test
    public void testAllCustomers() {
    	//real test case
        ResponseEntity<List<Customer>> responseEntity = testRestTemplate.exchange("http://localhost:7070/customers/v1.0", HttpMethod.GET,null,
                new ParameterizedTypeReference<List<Customer>>(){}
        );
        List<Customer> customerList = responseEntity.getBody();
        System.out.println("CustomerList--->" + customerList.size());
    }

    private void mockRemoteService() {
    	//pre configured response
        stubFor(get(urlEqualTo("/customers/v1.0"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("Tested mock......")
                ));
        
    }
}
