package com.dangdang.common.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zengyuzhi
 * @date 2020/10/31 下午2:47
 */
@Data
public class JwtRequest implements Serializable {
    private String openId;
}
