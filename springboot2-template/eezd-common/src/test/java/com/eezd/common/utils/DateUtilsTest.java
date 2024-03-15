package com.eezd.common.utils;

import com.eezd.common.constant.Constants;
import com.eezd.common.utils.http.HttpUtils;
import com.eezd.common.utils.sign.Base64;
import com.eezd.common.utils.sign.Md5Utils;
import com.eezd.common.utils.text.CharsetKit;
import com.eezd.common.utils.uuid.IdUtils;
import com.eezd.common.utils.uuid.Seq;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootTest(classes = DateUtils.class)
public class DateUtilsTest {
    @Test
    void main() {
        // 通用序列
        System.out.println(Seq.getId(Seq.commSeqType));
        System.out.println(Seq.getId(Seq.commSeqType));
        System.out.println(Seq.getId(Seq.commSeqType));

    }
}
