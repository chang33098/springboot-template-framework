package com.example.boot.springboottemplatestarter.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 注: @ControllerAdvice的执行优先级高于BasicErrorController
 * Created by ANdady on 2019/2/22.
 */
@Slf4j
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ExceptionController extends BasicErrorController {

    private final String ERROR_PATH = "error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    public ExceptionController(ErrorAttributes errorAttributes, ServerProperties serverProperties,
                           List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, serverProperties.getError(), errorViewResolvers);
    }

    /**
     * 页面403的异常处理 用户的权限不足以访问当前请求的资源
     * <p>
     * 打印请求的资源信息以及访问用户的信息
     *
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @return 403提示页面
     */
    @GetMapping(value = "403", produces = "text/html")
    public ModelAndView errorHtml401(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));

        log.warn("************* 403 ERROR INFO *************");
        log.warn("403异常的请求URL: {}", model.get("path"));

        return new ModelAndView("/error/404", model);
    }

    /**
     * 页面404的异常处理
     *
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @return {ModelAndView} 视图
     */
    @GetMapping(produces = "text/html", value = "404")
    public ModelAndView errorHtml404(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));

        log.warn("************* 404 ERROR INFO *************");
        log.warn("404异常的请求URL: {}", model.get("path"));

        return new ModelAndView("/error/404", model);
    }


    /**
     * 页面500的异常处理
     *
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @return {ModelAndView} 视图
     */
    @GetMapping(value = "500", produces = "text/html")
    public ModelAndView errorHtml500(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));

        log.error("************* 500 ERROR INFO *************");
        log.error("error: {}", model.get("error"));
        log.error("status: {}", model.get("status"));
        log.error("path: {}", model.get("path"));
        log.error("message: {}", model.get("message"));
        log.error("trace: {}", model.get("trace"));

        return new ModelAndView("/error/500", model);
    }

    @Override
    protected boolean isIncludeStackTrace(HttpServletRequest request, MediaType produces) {
        ErrorProperties.IncludeStacktrace include = this.serverProperties.getError().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        } else if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    @Override
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request,
                                                     boolean includeStackTrace) {
        WebRequest webRequest = new ServletWebRequest(request);
        return this.errorAttributes.getErrorAttributes(webRequest,
                includeStackTrace);
    }

    @Override
    protected boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        return parameter != null && !"false".equals(parameter.toLowerCase());
    }

    @Override
    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
