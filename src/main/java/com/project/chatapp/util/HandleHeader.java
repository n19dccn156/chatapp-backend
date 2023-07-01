package com.project.chatapp.util;

import com.project.chatapp.config.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

public class HandleHeader {
    public static String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
          throw new UnauthorizedException();
        }
    }
}
