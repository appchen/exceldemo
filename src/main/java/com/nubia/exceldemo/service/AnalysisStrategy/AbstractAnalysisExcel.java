package com.nubia.exceldemo.service.AnalysisStrategy;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 解析Excel的顶层抽象类
 */
public abstract class AbstractAnalysisExcel {
    /**
     * 顶层抽象方法
     * @param file
     * @return
     */
    public abstract List<Map<Integer, List<String>>> analysisExcel(File file);
}
