package com.example.moviedb;

import com.example.moviedb.web.MovieController;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MovieControllerTest2 {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    MovieController movieController;

    private static final String URL = "http://localhost:8080/api/1.0/movies";

    @Autowired
    private MockMvc mvc;

    private String movieNoId;
    private String movieWithId;

    public String testObjectStringNoId(String title,
                                       String description,
                                       String releaseYear,
                                       String duration,
                                       String rating,
                                       String likes,
                                       String dislikes){
        String movie =  "{\n" +
                "    \"title\": \""+title+"\",\n" +
                "    \"description\": \""+description+"\",\n" +
                "    \"releaseYear\": \""+releaseYear+"\",\n" +
                "    \"duration\": "+duration+",\n" +
                "    \"rating\": \""+rating+"\",\n" +
                "    \"likes\": "+likes+",\n" +
                "    \"dislikes\": "+dislikes+"\n" +
                "}";
        return movie;
    }

    public String testObjectStringWithId(String id,
                                         String title,
                                         String description,
                                         String releaseYear,
                                         String duration,
                                         String rating,
                                         String likes,
                                         String dislikes){
        String movie = "{\n" +
                "    \"id\": "+id+",\n" +
                "    \"title\": \""+title+"\",\n" +
                "    \"description\": \""+description+"\",\n" +
                "    \"releaseYear\": \""+releaseYear+"\",\n" +
                "    \"duration\": "+duration+",\n" +
                "    \"rating\": "+rating+",\n" +
                "    \"likes\": "+likes+",\n" +
                "    \"disLikes\": "+dislikes+"\n" +
                "}";
        return movie;
    }

    @Before
    public void setUp() throws Exception {

        movieNoId = testObjectStringNoId("Moon",
                "People travel to Moon",
                "2022-01-01",
                "60",
                "7.0",
                "2000",
                "0");

        movieWithId =  testObjectStringWithId(
                "1",
                "Moon",
                "People travel to Moon",
                "2022-01-01",
                "60",
                "7.0",
                "2000",
                "0"
        );





        mvc.perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(movieNoId))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }






//    @Test
//    @Order(3)
//    public void testGetMovieByIdMethodPositive() throws Exception {
//        System.out.println("Test 3 "+ "testGetMovieByIdMethodPositive()");
//        mvc.perform(
//                        MockMvcRequestBuilders
//                                .post(URL)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(movieNoId))
//                .andExpect(MockMvcResultMatchers.status().isCreated());
//        mvc.perform(
//                        MockMvcRequestBuilders
//                                .get(URL+"/1")
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json(movieWithId));
//    }



    @Test
    @Order(5)
    public void testDeleteMovieByIdMethodPositive() throws Exception {
        System.out.println("Test 5 "+ "testDeleteMovieByIdMethodPositive()");
        mvc.perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(movieNoId))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        mvc.perform(
                        MockMvcRequestBuilders
                                .delete(URL+"/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
