package ke.co.kip.sec.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testProductCreation() throws Exception{

        this.mockMvc.perform(post("/productType/create")
                  .content("{\"name\":\"Individual\"}").contentType("application/json;charset=UTF-8")
                  .header("Authorization",obtainAccessToken())
                  .accept("application/json;charset=UTF-8"))
                  .andDo(print())
                  .andExpect(status().isOk());
    }

    private String obtainAccessToken() throws  Exception{
        MockHttpServletResponse response = this.mockMvc.perform(post("/login")
                .content("{\"username\":\"admin\", \"password\":\"123\"}"))
                .andDo(print())
                .andReturn().getResponse();
        return response.getHeader("Authorization");
    }

}
