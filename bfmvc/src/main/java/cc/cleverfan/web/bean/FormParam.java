package cc.cleverfan.web.bean;

/**
 * 表单参数
 */
public class FormParam {

    private String fieldName;
    private Object fieldValue;

    public FormParam(String fieldName,Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

}
