package kz.project.one;

import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class TestService {
    @Autowired
    ServiceFeignClient serviceFeignClient;
    @PostConstruct
    public  void test() {

        System.out.println(serviceFeignClient.byUsername("ShSalambayev").getStatusCode());
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8060/rest")
                .asyncResponseTimeout(10000) // long polling timeout
                .build();

        // subscribe to an external task topic as specified in the process

        client.subscribe("initiator")
                .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
                .handler((externalTask, externalTaskService) -> {
                    // Put your business logic here
                    String initiatorUserId =externalTask.getVariable("initiator");
                    Example initiatorProfile = serviceFeignClient.byUsername(initiatorUserId).getBody();

                    String teamLeadUserId = initiatorProfile.getTeamlead();

                    Map<String,Object> newVars = new HashMap<>();

                    newVars.put("teamLeadUserId",teamLeadUserId);
                    externalTaskService.complete(externalTask,newVars);


//                    Get a process variable
//                    String item = (String) externalTask.getVariable("item");
//                    Long amount = (Long) externalTask.getVariable("amount");


                    // Complete the task
                    externalTaskService.complete(externalTask);
                })
                .open();
    }
}
