package it.academy.rest;

import it.academy.RestTestConfiguration;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ContextConfiguration(classes = RestTestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class ProductRestServiceTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @org.junit.Test
    public void readProducts() throws Exception {
        final MvcResult mvcResult = mockMvc
                .perform(
                        get("/products")
                                .param("pageNum", "1")
                                .param("pageSize", "10")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @org.junit.Test
    public void readProduct() {
    }

    @org.junit.Test
    public void createProduct() {
    }

    @org.junit.Test
    public void updateProduct() {
    }

    @org.junit.Test
    public void deleteProduct() {
    }
}