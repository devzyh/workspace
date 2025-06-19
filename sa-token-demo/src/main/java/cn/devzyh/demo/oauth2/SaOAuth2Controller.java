package cn.devzyh.demo.oauth2;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.oauth2.error.SaOAuth2ErrorCode;
import cn.dev33.satoken.oauth2.exception.SaOAuth2Exception;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Consts;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Handle;
import cn.dev33.satoken.util.SaResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class SaOAuth2Controller {

    // 全局异常拦截
    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handlerException(SaOAuth2Exception e) {
        e.printStackTrace();
        return errorMessage(e);
    }

    // 处理所有OAuth相关请求
    @RequestMapping("/oauth2/*")
    public Object request() {
        System.out.println("------- 进入请求: " + SaHolder.getRequest().getUrl());

        Object obj = SaOAuth2Handle.serverRequest();
        if (obj instanceof SaResult) {
            return formatToken(obj);
        }
        if (obj instanceof String && obj.toString().equals(SaOAuth2Consts.NOT_HANDLE)) {
            throw new SaOAuth2Exception("not handle");
        }

        return obj;
    }

    private Object formatToken(Object obj) {
        SaResult result = (SaResult) obj;
        if (result.getCode() != SaResult.CODE_SUCCESS) {
            return result;
        }
        LinkedHashMap<String, Object> tokenMap = null;
        if (result.getData() instanceof LinkedHashMap<?, ?>) {
            tokenMap = (LinkedHashMap<String, Object>) result.getData();
        }
        if (tokenMap == null || tokenMap.isEmpty()) {
            return result;
        }

        if (tokenMap.containsKey("client_token")) {
            tokenMap.putFirst("access_token", tokenMap.remove("client_token"));
        }
        tokenMap.putLast("token_type", "Bearer");

        return tokenMap;
    }

    private ResponseEntity<Map<String, String>> errorMessage(SaOAuth2Exception e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "invalid_request";

        switch (e.getCode()) {
            case SaOAuth2ErrorCode.CODE_30101:
            case SaOAuth2ErrorCode.CODE_30105:
            case SaOAuth2ErrorCode.CODE_30118:
            case SaOAuth2ErrorCode.CODE_30122:
            case SaOAuth2ErrorCode.CODE_30124:
                message = "unauthorized_client";
                break;
            case SaOAuth2ErrorCode.CODE_30106:
            case SaOAuth2ErrorCode.CODE_30107:
            case SaOAuth2ErrorCode.CODE_30110:
            case SaOAuth2ErrorCode.CODE_30111:
            case SaOAuth2ErrorCode.CODE_30115:
            case SaOAuth2ErrorCode.CODE_30119:
                message = "access_denied";
                break;
            case SaOAuth2ErrorCode.CODE_30125:
            case SaOAuth2ErrorCode.CODE_30131:
            case SaOAuth2ErrorCode.CODE_30132:
            case SaOAuth2ErrorCode.CODE_30133:
            case SaOAuth2ErrorCode.CODE_30134:
                message = "unsupported_response_type";
                break;
            case SaOAuth2ErrorCode.CODE_30102:
            case SaOAuth2ErrorCode.CODE_30108:
            case SaOAuth2ErrorCode.CODE_30109:
            case SaOAuth2ErrorCode.CODE_30112:
            case SaOAuth2ErrorCode.CODE_30116:
                message = "invalid_scope";
                break;
        }

        Map<String, String> result = new LinkedHashMap<>();
        result.put("error", message);
        result.put("error_description", e.getMessage());
        result.put("error_uri", SaHolder.getRequest().getUrl());

        return new ResponseEntity<>(result, status);
    }

}
