# spring-security-thymeleaf
基于thymeleaf的spring security入门demo


注： 此处加入如何在Spring Boot中配置Filter

```
@Configuration
@WebFilter(filterName = "helloFilter", urlPatterns = "/*")      //此注解用来在spring boot中配置Filter
public class HelloFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(HelloFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("[{}] has been initialized.",this.getClass().getSimpleName());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("[{}] has been filtered in [{}]",servletRequest.getRemoteAddr(), this.getClass());
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
```
