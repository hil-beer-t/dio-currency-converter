package com.currencyconverter.api.client;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostClientTest {

    private static String BASE_URL = "https://jsonplaceholder.typicode.com";

    private static String POST_RESPONSE = "[{\n" +
            "    \"userId\": 1,\n" +
            "    \"id\": 1,\n" +
            "    \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
            "    \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
            "  }]";

    private PostClient postClient;

    @Test
    public void whenGetClientThenReturnOK(){
        this.builderFeignClient(new MockClient().ok(
                HttpMethod.GET,
                BASE_URL.concat("/posts"),
                POST_RESPONSE
        ));

        List<PostDTO> postDTOList = postClient.getAll();

        assertFalse(postDTOList.isEmpty());
        assertTrue(!postDTOList.isEmpty());
        assertEquals(postDTOList.get(0).getUserId(), 1);
    }

    private void builderFeignClient(MockClient mockClient){
        postClient = Feign.builder()
                .client(mockClient)
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new SpringMvcContract())
                .target(PostClient.class, BASE_URL);
    }
}