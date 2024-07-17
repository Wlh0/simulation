package org.cgn.simulation.base.web;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 全局返回对象
 * @param <T>
 */
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 5679018624309023727L;

    public static final String SUCCESS_CODE = "200";

    private String code;

    private String message;

    private T data;


    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }

}
