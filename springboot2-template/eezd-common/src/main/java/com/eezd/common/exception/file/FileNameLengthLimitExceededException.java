package com.eezd.common.exception.file;

import com.eezd.common.exception.ServiceException;
import com.eezd.common.utils.MessageUtils;

/**
 * 文件名称超长限制异常类
 */
public class FileNameLengthLimitExceededException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength) {
        throw new ServiceException(MessageUtils.message("upload.filename.exceed.length", new Object[]{defaultFileNameLength}));
    }
}
