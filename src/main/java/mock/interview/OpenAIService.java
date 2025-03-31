package mock.interview;

import org.springframework.stereotype.Service;
import java.net.http.*;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;


@Service
public class OpenAIService {
    private static final String API_KEY = "sk-proj-hzzwenuf5YE8INaC2GnOyj4NdMscMQ5OLOFjJ-LreZHzpCbLZoABQtpTvazrQJqoPF6XB"
        + "ifpg_T3BlbkFJ5Mo0ieD4Bki1IXuiVBXG4JygG6nWfU4mvy2VqexTKqHSpw1uu3ri84OC7R6ND93NZpyqtNtWkA";


    public String getFeedback(String question, String answer) {
        try {
            String prompt = "You're a technical interviewer. Please evaluate this interview answer.\n"
                    + "Question: " + question + "\n"
                    + "Candidate's Answer: " + answer + "\n"
                    + "Give constructive feedback and a score from 1 to 10.";


            JSONObject requestBody = new JSONObject();
            requestBody.put("model", "gpt-3.5-turbo");
            requestBody.put("prompt", prompt);
            requestBody.put("max_tokens", 200);


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.openai.com/v1/completions"))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                    .build();


            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            JSONObject responseBody = new JSONObject(response.body());
            return responseBody.getJSONArray("choices").getJSONObject(0).getString("text").trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing response from OpenAI.";
        }
    }
}

