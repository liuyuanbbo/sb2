package org.zmz.c.service.dataopen.sqlenum;

public enum HiveStorageInputFormatEnum {
    TEXTFILE("org.apache.hadoop.mapred.TextInputFormat"),
    SEQUENCEFILE("org.apache.hadoop.mapred.SequenceFileInputFormat"),
    ORC("org.apache.hadoop.hive.ql.io.orc.OrcInputFormat"),
    PARQUET("org.apache.hadoop.hive.ql.io.parquet.MapredParquetInputFormat"),
    AVRO("org.apache.hadoop.hive.ql.io.avro.AvroContainerInputFormat"),
    RCFILE("org.apache.hadoop.hive.ql.io.RCFileInputFormat"),
    JSONFILE(""), OUTPUTFORMAT("");

    private String inputFormat;

    HiveStorageInputFormatEnum(String inputFormat) {
        this.inputFormat = inputFormat;
    }

    public static String getStored(String inputFormat) {
        HiveStorageInputFormatEnum[] storageInputFormatEnum = HiveStorageInputFormatEnum.values();
        for (HiveStorageInputFormatEnum storageInputFormat : storageInputFormatEnum) {
            if (storageInputFormat.getInputFormat().equalsIgnoreCase(inputFormat)) {
                return storageInputFormat.name();
            }
        }
        return null;
    }

    public String getInputFormat() {
        return inputFormat;
    }
}