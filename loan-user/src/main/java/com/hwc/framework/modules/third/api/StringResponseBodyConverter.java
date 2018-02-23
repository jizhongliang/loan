package com.hwc.framework.modules.third.api;


import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;

public class StringResponseBodyConverter implements Converter<ResponseBody, String> {
    @Override
    public String convert(ResponseBody value) throws IOException {
        try {
            return value.string();
        } finally {
            value.close();
        }
    }
}
