package com.example.boot.springboottemplatesecurity.security;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Created by EDZ on 2019/8/22.
 */
@Component
public class MyLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
}
