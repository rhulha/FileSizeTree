/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kenai.paranoidray.fst;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Ray
 */
public class AddNode {
    private final DefaultMutableTreeNode dmtn;
    private final File path;

    public AddNode(DefaultMutableTreeNode dmtn, File path) {
        this.dmtn = dmtn;
        this.path = path;
    }

    void addFileSize( DefaultMutableTreeNode node, long size)
    {
        PathSize ps = (PathSize) node.getUserObject();
        ps.incSize(size);
        // pretty inefficient
        // would be better to calculate parent node size once
        // at the end, but hey, it seems fast still...
        if( node.getParent() != null)
            addFileSize( (DefaultMutableTreeNode) node.getParent(), size);
    }

    public void doit()
    {
        File[] listFiles = path.listFiles();
        if( listFiles != null)
        for (File file : listFiles) {
            if( file.isDirectory() )
            {
                DefaultMutableTreeNode new_dmtn = new DefaultMutableTreeNode(new PathSize(file.getName(), 0));
                dmtn.add(new_dmtn);
                FSTUtil.updateTreeUI();
                new AddNode(new_dmtn, file).doit();
            } else {
                addFileSize(dmtn, file.length());
            }
        }
    }

}
