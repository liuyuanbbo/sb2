package org.zmz.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Message implements Serializable {
    private static final long serialVersionUID = 2275564594226992724L;
    // id
    private Long id;
    // 消息
    private String msg;
    // 时间戳
    private LocalDate sendTime;
}
