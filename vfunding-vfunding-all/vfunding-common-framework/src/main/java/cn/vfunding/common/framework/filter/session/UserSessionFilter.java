package cn.vfunding.common.framework.filter.session;

import cn.vfunding.common.framework.server.EmployeeSession;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserSessionFilter
  implements Filter
{
  private static ThreadLocal<HttpSession> threadLocalHttpSession = new ThreadLocal();

  public static HttpSession getHttpSession() {
    return (HttpSession)threadLocalHttpSession.get();
  }

  public void destroy()
  {
  }

  public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
    throws IOException, ServletException
  {
    HttpSession session = ((HttpServletRequest)arg0).getSession();
    threadLocalHttpSession.set(session);

    EmployeeSession userSession = (EmployeeSession)session.getAttribute("employee");
    EmployeeSession.setEmployeeSession(userSession);

    arg2.doFilter(arg0, arg1);
  }

  public void init(FilterConfig arg0)
    throws ServletException
  {
  }
}