package org.zmz.c.dto.dataopen;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Zmz
 */
@Getter
@Setter
public class IndexDto implements Serializable {

    private Long indexId;

    private String indexName;

    private String indexCode;

    private Long metaTableId;

    private String tableCode;

    private String state;

    private String indexType;

    private Long comAcctId;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IndexDto indexDto = (IndexDto) o;
        return Objects.equals(indexId, indexDto.indexId) && Objects.equals(indexName, indexDto.indexName)
            && Objects.equals(indexCode, indexDto.indexCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexId, indexName, indexCode);
    }
}