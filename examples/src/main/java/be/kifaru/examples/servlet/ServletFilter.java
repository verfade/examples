package be.kifaru.examples.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Boolean.TRUE;

/**
 * @author Devid Verfaillie
 * @since 2015-11-18
 */
public class ServletFilter implements Filter {

    // @VisibleForTesting
    static final String ONCE_PER_REQUEST = ServletFilter.class.getName() + ".FILTERED";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // no implementation needed
    }

    protected void doBeforeNextFilter(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // put business logic here...
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (hasOncePerRequestAttribute(servletRequest)) {
            continueWithNextFilter(servletRequest, servletResponse, filterChain);
        } else {
            setOncePerRequestAttribute(servletRequest);

            try {
                doBeforeNextFilter(servletRequest, servletResponse);
                continueWithNextFilter(servletRequest, servletResponse, filterChain);
            } finally {
                executeFinallyClause(servletRequest);
            }
        }
    }

    @Override
    public void destroy() {
        // no implementation needed
    }

    private void continueWithNextFilter(ServletRequest servletRequest,
                                        ServletResponse servletResponse,
                                        FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    protected void executeFinallyClause(ServletRequest request) {
        request.removeAttribute(ONCE_PER_REQUEST);
    }

    private boolean hasOncePerRequestAttribute(ServletRequest servletRequest) {
        return null != servletRequest.getAttribute(ONCE_PER_REQUEST);
    }

    private void setOncePerRequestAttribute(ServletRequest servletRequest) {
        servletRequest.setAttribute(ONCE_PER_REQUEST, TRUE);
    }
}
