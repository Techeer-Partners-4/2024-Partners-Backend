package TechPartners.__Partners_Backend.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import TechPartners.__Partners_Backend.domain.Url;
import TechPartners.__Partners_Backend.dto.ReqUrlDto;
import TechPartners.__Partners_Backend.repository.UrlRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class ShortLinkControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UrlRepository urlRepository;

    @Test
    void 정상_GET_allUrl() throws Exception {

        //given
        Url testUrl = Url.builder()
            .originUrl("www.naver.com")
            .hash("testhash")
            .build();
        urlRepository.save(testUrl);

        //when
        ResultActions resultActions = mvc.perform(
            MockMvcRequestBuilders.get("/short-link")
                .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].originUrl","www.naver.com").exists())
            .andExpect(jsonPath("$[0].hash","testhash").exists());
    }

    @Test
    void 비정상_POST_createUrl_URL형식아님() throws Exception {
        //given

        //when
        ResultActions resultActions = mvc.perform(
            MockMvcRequestBuilders.post("/short-link")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(new ReqUrlDto("wwwnaver.qwer")))
        );

        resultActions
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("message").exists())
            .andExpect(jsonPath("statusCode",400).exists());
    }

    @Test
    void 비정상_GET_getUrl_없는해시값() throws Exception {
        //when
        ResultActions resultActions = mvc.perform(
            MockMvcRequestBuilders.get("/short-link/newHash")
                .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("message","There is no url resource.").exists())
            .andExpect(jsonPath("statusCode",404).exists());

    }

    @Test
    void 비정상_DELETE_deleteUrl_없는해시값() throws Exception {
        //when
        ResultActions resultActions = mvc.perform(
            MockMvcRequestBuilders.delete("/short-link/newHash")
                .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("message","There is no url resource.").exists())
            .andExpect(jsonPath("statusCode",404).exists());

    }
}