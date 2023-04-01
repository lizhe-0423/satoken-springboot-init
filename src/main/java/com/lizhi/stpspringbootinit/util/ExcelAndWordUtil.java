package com.lizhi.stpspringbootinit.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import lombok.val;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝</a>
 * excel 和 word 工具
 */
public class ExcelAndWordUtil {
    /**
     * excel 读取
     * @return
     */
    public ExcelReader excelGet(){
        ExcelReader excelReader = ExcelUtil.getReader(FileUtil.file("d:/test.xlsx"));
        return excelReader;
    }

}
