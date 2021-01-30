package edu.security.first.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(value = TestController.class)
public class TestControllerTest {
  @Autowired
  private MockMvc mvc;

  @Test
  public void requestTestPage() throws Exception {
    mvc.perform(get("/test"))
        .andExpect(view().name("test"))
        .andExpect(status().isOk())
        .andDo(print());
  }
}
