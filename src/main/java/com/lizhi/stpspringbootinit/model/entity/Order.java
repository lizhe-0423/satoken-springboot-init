package com.lizhi.stpspringbootinit.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝</a>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    private long id;
    private String name;
    private double price;
}
