package cc.cleverfan.web.utils;

import cc.cleverfan.web.bean.FormParam;
import cc.cleverfan.web.bean.Param;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 解析请求参数,form表单数据
 */
public class ParameterUtil {

    public static Param createParam(HttpServletRequest request) throws IOException {
        List<FormParam> formParams = new ArrayList<>();
        formParams.addAll(parseParameterNames(request));
        return new Param(formParams);
    }

    private static List<FormParam> parseParameterNames(HttpServletRequest request) {
        List<FormParam> formParams = new ArrayList<>();
        //将发送请求页面中form表单所具有name属性的表单对象获取
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String fieldName = paramNames.nextElement();
            String[] fieldValues = request.getParameterValues(fieldName);
            if (ArrayUtil.isNotEmpty(fieldValues)) {
                Object fieldValue;
                if (fieldValues.length == 1) {
                    fieldValue = fieldValues[0];
                } else {
                    StringBuilder sb = new StringBuilder("");
                    for (int i = 0; i < fieldValues.length; ++i) {
                        sb.append(fieldValues[i]);
                        if (i != fieldValues.length - 1) {
                            sb.append(StringUtil.separator);
                        }
                    }
                    fieldValue = sb.toString();
                }
                formParams.add(new FormParam(fieldName, fieldValue));
            }
        }
        return formParams;
    }

}
