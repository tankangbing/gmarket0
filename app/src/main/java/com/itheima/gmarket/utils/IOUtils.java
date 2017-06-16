package com.itheima.gmarket.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {
	/** 关闭流 */
	public static boolean close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				LogUtil.e(e);
			}
		}
		return true;
	}
}
