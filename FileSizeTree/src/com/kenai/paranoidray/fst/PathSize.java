/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenai.paranoidray.fst;

/**
 *
 * @author Ray
 */
public class PathSize {

    private final String name;
    private long size;

    public PathSize(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return name + " (" + formatNumber(size) + ")";
    }

    void incSize(long size) {
        this.size += size;
    }

    private String formatNumber(long number) {
        String formatted;
        if (number > 1000000000) {
            formatted = Long.toString(number / 1000000000) + " GB";
        } else if (number > 1000000) {
            formatted = Long.toString(number / 1000000) + " MB";
        } else if (number > 1000) {
            formatted = Long.toString(number / 1000) + " KB";
        } else {
            formatted = Long.toString(number) + " bytes";
        }
        return (formatted);

    }
}
