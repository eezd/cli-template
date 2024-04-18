package com.eezd.common.exception.file;

import com.eezd.common.exception.ServiceException;
import com.eezd.common.utils.MessageUtils;

/**
 * 文件名称超长限制异常类
 */
public class FileSizeLimitExceededException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize) {
        throw new ServiceException(MessageUtils.message("pload.exceed.maxSize", new Object[]{defaultMaxSize}));
    }
}
