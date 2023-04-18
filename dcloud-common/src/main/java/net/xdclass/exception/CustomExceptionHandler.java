package net.xdclass.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.util.JsonData;

/**
 * @Author starbug
 * @Description
 * @Datetime 2023/4/18 21:35
 */
@Slf4j
@ControllerAdvice
//@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JsonData handler(Exception e) {
        if (e instanceof BizException) {
            BizException bizException = (BizException) e;
            log.error("[业务异常]{}", e);
            return JsonData.buildCodeAndMsg(bizException.getCode(), bizException.getMessage());
        } else {
            log.error("[系统异常]{}", e);
            return JsonData.buildError("系统异常");
        }
    }

}
