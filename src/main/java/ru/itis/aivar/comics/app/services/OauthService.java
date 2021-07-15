package ru.itis.aivar.comics.app.services;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.catalina.util.IOTools;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.aivar.comics.app.dto.GitHubUserDto;
import ru.itis.aivar.comics.app.exceptions.OauthServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OauthService {

    @Value("${oauth.github.client.id}")
    private String gitHubClientId;
    @Value("${oauth.github.client.secret}")
    private String gitHubClientSecret;
    @Value("${oauth.github.access.token.url}")
    private String gitHubAccessTokenUrl;
    @Value("${oauth.github.api.user}")
    private String userGitHubApi;

    public GitHubUserDto getGitHubUser(String code) {
        GitHubUserDto dto = null;
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(gitHubAccessTokenUrl);
            List<NameValuePair> valuePairs = new ArrayList<>();
            valuePairs.add(new BasicNameValuePair("client_id", gitHubClientId));
            valuePairs.add(new BasicNameValuePair("client_secret", gitHubClientSecret));
            valuePairs.add(new BasicNameValuePair("code", code));
            post.setEntity(new UrlEncodedFormEntity(valuePairs));
            post.addHeader("Accept", "application/json");
            try(CloseableHttpResponse response = httpClient.execute(post)) {
                HttpEntity entity = response.getEntity();

                InputStream is = entity.getContent();
                String content = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining("\n"));
                JSONObject jsonObject = new JSONObject(content);
                String accessToken = jsonObject.getString("access_token");

                EntityUtils.consume(entity);

                HttpGet get = new HttpGet(userGitHubApi);
                get.addHeader("Authorization", "token "+accessToken);
                try(CloseableHttpResponse userResponse = httpClient.execute(get)) {
                    HttpEntity userEntity = userResponse.getEntity();

                    InputStream userIs = userEntity.getContent();
                    String userContent = new BufferedReader(new InputStreamReader(userIs, StandardCharsets.UTF_8))
                            .lines()
                            .collect(Collectors.joining("\n"));
                    JSONObject jsonObject1 = new JSONObject(userContent);

                    EntityUtils.consume(userEntity);

                    dto = GitHubUserDto.builder()
//                            .email(jsonObject1.getString("email"))
                            .email(jsonObject1.getString("login").concat("@github.mock.com"))
                            .username(jsonObject1.getString("login"))
                            .build();
                }
            }
        } catch (IOException e) {
            throw new OauthServiceException("Problems with oauth operation");
        }
        if (dto == null){
            throw new OauthServiceException("Problems with oauth operation");
        }
        return dto;
    }
}
