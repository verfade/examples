package be.kifaru.examples.servlet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static be.kifaru.examples.servlet.ServletFilter.ONCE_PER_REQUEST;
import static java.lang.Boolean.TRUE;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.only;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verifyNoMoreInteractions;

/**
 * Tests {@link ServletFilter}.
 *
 * @author Devid Verfaillie
 * @since 2015-11-30
 */
@RunWith(MockitoJUnitRunner.class)
public class ServletFilterTest {

    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    private HttpServletRequest request;

    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    private HttpServletResponse response;

    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    private FilterChain filterChain;

    // the System-Under-Test
    private ServletFilter servletFilter;

    @Before
    public void before() throws Exception {
        given(request.getAttribute(ONCE_PER_REQUEST)).willReturn(null);

        servletFilter = new ServletFilter();
    }

    @Test
    public void doFilter_requestWithOncePerRequestAttribute_shouldOnlyInvokeNextFilterInChain() throws Exception {
        // GIVEN
        given(request.getAttribute(ONCE_PER_REQUEST)).willReturn(TRUE);

        // WHEN
        servletFilter.doFilter(request, response, filterChain);

        // THEN
        then(request).should(only()).getAttribute(ONCE_PER_REQUEST);
        then(filterChain).should(only()).doFilter(request, response);
        verifyNoMoreInteractions(request, response, filterChain);
    }

    @Test
    public void doFilter_requestWithoutOncePerRequestAttribute_shouldSetTheAttribute() throws Exception {
        // GIVEN

        // WHEN
        servletFilter.doFilter(request, response, filterChain);

        // THEN
        then(request).should(times(1)).setAttribute(ONCE_PER_REQUEST, TRUE);
    }
}
