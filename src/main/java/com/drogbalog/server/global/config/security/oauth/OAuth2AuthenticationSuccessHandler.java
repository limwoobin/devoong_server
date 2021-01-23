//package com.drogbalog.server.global.config.security.oauth;
//
//import com.drogbalog.server.domain.user.domain.response.JwtResponse;
//import com.drogbalog.server.global.config.security.jwt.JwtTokenProvider;
//import com.drogbalog.server.global.exception.BadRequestException;
//import com.drogbalog.server.global.util.CookieUtils;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.net.URI;
//import java.util.List;
//import java.util.Optional;
//
//import static com.drogbalog.server.global.config.security.oauth.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;
//
//@Component
//@RequiredArgsConstructor
//@Log4j2
//public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//    private final JwtTokenProvider jwtTokenProvider;
//    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
//
//    @Value("${app.oauth2.authorizedRedirectUris}")
//    private final List<String> authorizedRedirectUris;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        String targetUrl = determineTargetUrl(request , response , authentication);
//        if (response.isCommitted()) {
//            log.info("Response has already been committed. Unable to redirect to {}" , targetUrl);
//            return;
//        }
//
//        clearAuthenticationAttributes(request , response);
//        getRedirectStrategy().sendRedirect(request , response , targetUrl);
//    }
//
//    protected String determineTargetUrl(HttpServletRequest request , HttpServletResponse response , Authentication authentication) {
//        Optional<String> redirectUri = CookieUtils.getCookie(request , REDIRECT_URI_PARAM_COOKIE_NAME)
//            .map(Cookie::getValue);
//
//        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
//            throw new BadRequestException("Unauthorized Redirect URI and can't proceed with the authentication");
//        }
//
//        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
//        JwtResponse jwtResponse = jwtTokenProvider.generateTokens(request.getParameter("email"));
//        return UriComponentsBuilder.fromUriString(targetUrl)
//                .queryParam("jwtResponse" , jwtResponse)
//                .build().toUriString();
//    }
//
//    protected void clearAuthenticationAttributes(HttpServletRequest request , HttpServletResponse response) {
//        super.clearAuthenticationAttributes(request);
//        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
//    }
//
//    private boolean isAuthorizedRedirectUri(String uri) {
//        URI clientRedirectUri = URI.create(uri);
//
//        return authorizedRedirectUris.stream().anyMatch(authorizedRedirectUri  -> {
//            URI authorizedURI = URI.create(authorizedRedirectUri);
//            return authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
//                    && authorizedURI.getPort() == clientRedirectUri.getPort();
//        });
//    }
//}
