package com.example.boot.springboottemplatestarter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
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
import java.util.Map;

/**
 * Created by ANdady on 2019/2/22.
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ViewErrorController implements ErrorController {

    private final Logger logger = LoggerFactory.getLogger(ViewErrorController.class);

    private final String ERROR_PATH = "error";

    private ErrorAttributes errorAttributes;

    @Autowired
    private ServerProperties serverProperties;


    @Autowired
    public ViewErrorController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    /**
     * 页面404的异常处理
     *
     * @param request HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @return {ModelAndView} 视图
     */
    @GetMapping(produces = "text/html",value = "404")
    public ModelAndView errorHtml404(HttpServletRequest request,
                                     HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));

        logger.warn("************* 400 ERROR INFO *************");
        logger.warn("404异常的请求URL:   {}", model.get("path"));

        return new ModelAndView("/error/404", model);
    }

    /**
     * 页面500的异常处理
     *
     * @param request HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @return {ModelAndView} 视图
     */
    @GetMapping(value = "500", produces = "text/html")
    public ModelAndView errorHtml500(HttpServletRequest request,
                                     HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));

        logger.error("************* 500 ERROR INFO *************");
        logger.error("error:    {}", model.get("error"));
        logger.error("status:   {}", model.get("status"));
        logger.error("path:     {}", model.get("path"));
        logger.error("message:  {}", model.get("message"));
        logger.error("trace:    {}", model.get("trace"));

        return new ModelAndView("/error/500", model);
    }

    private boolean isIncludeStackTrace(HttpServletRequest request,
                                        MediaType produces) {
        ErrorProperties.IncludeStacktrace include = this.serverProperties.getError().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        } else if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request,
                                                   boolean includeStackTrace) {
        WebRequest webRequest = new ServletWebRequest(request);
        return this.errorAttributes.getErrorAttributes(webRequest,
                includeStackTrace);
    }

    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        return parameter != null && !"false".equals(parameter.toLowerCase());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
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
