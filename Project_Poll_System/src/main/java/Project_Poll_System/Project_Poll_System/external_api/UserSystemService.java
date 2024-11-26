package Project_Poll_System.Project_Poll_System.external_api;

import Project_Poll_System.Project_Poll_System.model.Answer;
//import Project_Poll_System.Project_Poll_System.model.UserIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
       name =  "externalapi",
       url = "${externalApi.users.url}"
)

public interface UserSystemService {

        @GetMapping(value = "", params = "id")
        Integer isRegistered(@RequestParam("id") Integer id);
}


