package it.academy.web.controller;

import it.academy.web.WebShopConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ContextConfiguration(classes = WebShopConfiguration.class)
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class HomeControllerTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void home() throws Exception {
        final ModelAndView modelAndView = mockMvc
                .perform(get("/"))
                .andReturn()
                .getModelAndView();

        assertTrue(modelAndView.getModel().containsKey("promoList"));
        assertEquals("index", modelAndView.getViewName());
    }

    @Test
    public void add() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("productName", "Test Product");
        map.add("description", "Test Description");

        final ModelAndView modelAndView = mockMvc
                .perform(
                        post("/product/add")
                                .params(map))
                .andReturn().getModelAndView();
        assertNotNull(modelAndView);
        assertTrue(modelAndView.getViewName().contains("redirect:/product"));
    }
}