package com.agit.brooks2.shared.report;

/**
 *
 * @author bayutridewanto
 */
public enum ReportFormat {

    PDF("pdf"),
    XLS("xls"),
    CSV("csv"),
    TXT("txt");

    private String format;

    private ReportFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
