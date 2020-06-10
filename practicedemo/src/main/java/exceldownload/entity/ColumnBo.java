package exceldownload.entity;

import java.util.List;

/**
 * @author 小白i
 * @date 2020/6/8
 */
public class ColumnBo {

    private String columnId;

    private String columnCode;

    private String columnValue;

    private long order;
    /**
     * C:字符串；N:数字
     */
    private String type;

    private boolean readOnly;

    private long columnWidth;

    private String fixed;

    private int precision;

    private List<ColumnBo> children;
}
