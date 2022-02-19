package com.esmooc.legion.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitRep {
        private String ecName;
        private String apId;
        private String secretKey;
        private String content;
        private String mobile;
        private String sign;
        private String addSerial;
        private String mac;
    }