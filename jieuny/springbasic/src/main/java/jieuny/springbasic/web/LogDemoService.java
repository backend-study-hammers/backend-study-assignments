package jieuny.springbasic.web;

import jieuny.springbasic.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    private final MyLogger myLogger;

    public void logic(String id){
        myLogger.log("Service Id = "+ id);
    }

}
