package cc.cleverfan.web.bean;

import cc.cleverfan.web.utils.CollectionUtil;
import cc.cleverfan.web.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求参数对象
 */
public class Param {

    private List<FormParam> formParams;

    public Param(List<FormParam> formParams) {
        this.formParams = formParams;
    }

    /**
     * 获取请求参数映射
     *
     * @return
     */
    public Map<String, Object> getFieldMap() {
        Map<String, Object> fieldMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(formParams)) {
            formParams.forEach(formParam -> {
                String fieldName = formParam.getFieldName();
                Object fieldValue = formParam.getFieldValue();
                if (fieldMap.containsKey(fieldName)) {
                    fieldValue = fieldMap.get(fieldName) + StringUtil.separator + fieldValue;
                }
                fieldMap.put(fieldName, fieldValue);
            });

        }
        return fieldMap;
    }

    /**
     * 判断参数是否为空
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return CollectionUtil.isEmpty(formParams);
    }

}
