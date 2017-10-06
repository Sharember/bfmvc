package cc.cleverfan.web;

import cc.cleverfan.web.bean.*;
import cc.cleverfan.web.utils.JsonUtil;
import cc.cleverfan.web.utils.StringUtil;
import cc.cleverfan.web.helper.ConfigHelper;
import cc.cleverfan.web.helper.ControllerHelper;
import cc.cleverfan.web.utils.ParameterUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * 请求转发器
 * @author chengfan
 */
@WebServlet(urlPatterns = "/",loadOnStartup = 0)
public class DispatherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        Loader.init();//初始化框架&应用
        ServletContext sc = config.getServletContext();
        //注册JSP的Servlet
        ServletRegistration jspServlet = sc.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        //注册处理静态资源的Servlet
        ServletRegistration defaultServlet = sc.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求方法
        String requestMethod = req.getMethod().toLowerCase(Locale.ENGLISH);
        //请求路径url
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        String requestPath = null;
        if (contextPath != null && contextPath.length() > 0) {
            requestPath = url.substring(contextPath.length());
        }
        //获取处理处理这个请求的handler
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
       // System.out.println(requestMethod + "  " + requestPath);
        if (handler != null) {
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanContainer.getBean(controllerClass.getName());
            //解析请求参数
            Param param = ParameterUtil.createParam(req);
            Object result;//请求返回对象
            Method method = handler.getMethod();//处理请求的方法
            if (param.isEmpty()) {
                result = BeanFactory.invokeMethod(controllerBean, method);
            } else {
                result = BeanFactory.invokeMethod(controllerBean, method, param);
            }
            if (result instanceof ModelAndView) {
                handleViewResult((ModelAndView) result, req, resp);
            } else {
                handleDataResult((Data) result, resp);
            }
        }
    }

    //返回为JSP页面
    private static void handleViewResult(ModelAndView view, HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String path = view.getPath();
        if (StringUtil.isNotEmpty(path)) {
            if (path.startsWith("/")) {
                resp.sendRedirect(req.getContextPath() + path);
            } else {
                view.getAttribute()
                        .entrySet()
                        .forEach(entry -> req.setAttribute(entry.getKey(), entry.getValue()));

                //forward将页面响应转发到ConfigHelper.getAppJspPath() + path
                req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(req, resp);
            }
        }
    }

    //返回JSON数据
    private static void handleDataResult(Data data, HttpServletResponse resp)
            throws IOException {
        Object model = data.getData();
        if (model != null) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = resp.getWriter();
            String json = JsonUtil.toJSON(model);
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }

}
