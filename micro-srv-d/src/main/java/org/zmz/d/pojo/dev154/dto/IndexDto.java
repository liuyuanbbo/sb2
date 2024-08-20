package org.zmz.d.pojo.dev154.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

@Getter
@Setter
public class IndexDto {
    private String indexName;
    private String indexCode;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexDto indexDto = (IndexDto) o;
        return Objects.equals(indexName, indexDto.indexName) && Objects.equals(indexCode, indexDto.indexCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexName, indexCode);
    }
}
