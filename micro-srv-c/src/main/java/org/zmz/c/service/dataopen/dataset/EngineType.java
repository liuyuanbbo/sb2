package org.zmz.c.service.dataopen.dataset;

public final class EngineType {

    private EngineType() {
    }

    public static final String MEMORY = "Memory";

    public static final String MERGE_TREE = "MergeTree";

    /**
     * whalehouse独有引擎
     */
    public static final String CNCH_MERGE_TREE = "CnchMergeTree";
}