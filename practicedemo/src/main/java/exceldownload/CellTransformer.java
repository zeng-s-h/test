package exceldownload;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 小白i
 * @date 2020/5/12
 */
public class CellTransformer {

    private final List<splitCell> cellContents;

    private final int firstRowIndex;

    private final int rowSize;

    private Map<String, splitCell> cellInfoMap = new LinkedHashMap<>();

    public CellTransformer(List<splitCell> cellContents, int firstRowIndex, int rowSize, Map<String, splitCell> cellInfoMap) {
        this.cellContents = cellContents;
        this.firstRowIndex = firstRowIndex;
        this.rowSize = rowSize;
        this.cellInfoMap = cellInfoMap;
    }
}
