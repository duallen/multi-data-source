package cn.allen.multidatasource.mdscomponent.handler;

import cn.allen.multidatasource.mdscomponent.exception.MultiDsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author allen
 * @date 2020-04-21
 */
@ControllerAdvice
public class ExceptionHandler {

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler({MultiDsException.class})
    public Map<String,Object> dealException(Exception e) {
        Map<String,Object> eMap = new HashMap<>();

        if (e instanceof MultiDsException) {
            MultiDsException multiDsException = (MultiDsException) e;
            eMap.put("code",multiDsException.getExceptionCode());
            eMap.put("errMsg",multiDsException.getMsg());
        }
        return eMap;
    }
}
