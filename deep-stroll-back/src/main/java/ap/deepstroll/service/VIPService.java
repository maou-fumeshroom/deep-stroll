package ap.deepstroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("VIPService")
public class VIPService extends UserService {
    @Autowired


    public Map getHotDrawing(){
        return null;
    }

    public Map getHotArticle(){
        return null;
    }
}
