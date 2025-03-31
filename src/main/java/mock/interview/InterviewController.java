package mock.interview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/interview")
public class InterviewController {


    @Autowired
    private DatabaseService databaseService;


    @Autowired
    private OpenAIService openAIService;


    @GetMapping("/question")
    public String getInterviewQuestion(@RequestParam String role) {
        return databaseService.getQuestionByRole(role);
    }


    @PostMapping("/evaluate")
    public String evaluateAnswer(@RequestParam String role, @RequestParam String answer) {
        String question = databaseService.getQuestionByRole(role);
        return openAIService.getFeedback(question, answer);
    }
}

