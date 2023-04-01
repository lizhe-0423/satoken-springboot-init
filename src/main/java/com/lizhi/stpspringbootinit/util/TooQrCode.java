package com.lizhi.stpspringbootinit.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝</a>
 * 二维码生成工具
 */
@Component
public class TooQrCode {
   public void generateTooQrCode(String str){
        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        // 设置前景色，既二维码颜色（青色）
        config.setForeColor(Color.CYAN.getRGB());
        // 设置背景色（灰色）
        config.setBackColor(Color.GRAY.getRGB());
       QrCodeUtil.generate(str, config, FileUtil.file("e:/qrcode.jpg"));
    }
}
