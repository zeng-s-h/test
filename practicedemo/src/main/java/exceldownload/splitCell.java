package exceldownload;

/**
 * @author 小白i
 * @date 2020/5/12
 */
public class splitCell {

    private String key;

    private String parentKey;

    private String value;

    private int columnIndex;

    private int rowIndex;

    public splitCell() {
    }

    public splitCell(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public splitCell(String key, String parentKey, String value, int columnIndex, int rowIndex) {
        this.key = key;
        this.parentKey = parentKey;
        this.value = value;
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }
}
