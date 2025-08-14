package com.Ayush.TaskManager.Services;

import com.Ayush.TaskManager.api.response.WeatherResponse;
import com.Ayush.TaskManager.cache.AppCache;
import com.Ayush.TaskManager.constants.PlaceHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apikey;

    private String api;


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;


    public WeatherResponse getWeather(String city){
        String finalApi = appCache.App_Cache.get(PlaceHolder.WEATHER_API).replace(PlaceHolder.CITY,city).replace(PlaceHolder.API_KEY,apikey);

//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("key","value");
//        User user = User.builder().userName("Ayush").password("Ayush").build();
//        HttpEntity<User>httpEntity = new HttpEntity<>(user,httpHeaders);
//        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.POST,httpEntity, WeatherResponse.class);
        WeatherResponse redisResponse = redisService.getFromRedis(city, WeatherResponse.class);
        if(redisResponse!=null){
            return redisResponse;
        }
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET,null, WeatherResponse.class);
        redisService.setInRedis(city,response.getBody(),3000l);
        return response.getBody();
    }


//    public ResponseEntity<?> textToSpeech() throws IOException {
//        String apiUrl = "http://api.elevenlabs.io/v1/text-to-speech/JBFqnCBsd6RMkjVDRZzb?output_format=mp3_44100_128";
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("xi-api-key", "sk_809dd02f828a39c60b312d9e8b5e46c944b82609b7fa4b61");
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//        String requestBody = """
//            {
//              "text": "The first move is what sets everything in motion.",
//              "model_id": "eleven_multilingual_v2"
//            }
//            """;
//
//        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, httpHeaders);
//        ResponseEntity<byte[]> response = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, byte[].class);
//        log.info("Response Status: {}", response);
//        if (response.getBody() != null) {
//            Files.write(Path.of("output.mp3"), response.getBody());
//            return new ResponseEntity<>(true,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
//
//    }


}
