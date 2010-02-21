/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenai.paranoidray.fst;

import java.awt.Color;
import java.awt.Container;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author Ray
 */
public class FSTUtil extends Thread {

    private static JTree tree;
    private final File path;

    public FSTUtil(JTree tree, File path) {
        FSTUtil.tree = tree;
        this.path = path;
    }

    public static DefaultPieDataset createSampleDataset() {

        final DefaultPieDataset result = new DefaultPieDataset();
        /*
        result.setValue("Java", new Double(43.2));
        result.setValue("Visual Basic", new Double(10.0));
        result.setValue("C/C++", new Double(17.5));
        result.setValue("PHP", new Double(32.5));
        result.setValue("Perl", new Double(1.0));
         */
        return result;
    }

     public static JFreeChart createChart(final PieDataset dataset) {

        final JFreeChart chart = ChartFactory.createPieChart(
            null,  // chart title
            dataset,                // data
            false,                   // include legend
            true,
            false
        );

        final PiePlot plot = (PiePlot) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.8f);
        plot.setNoDataMessage("No data to display");
        return chart;

    }

    public static void finished() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Container p = tree.getParent();
                while( true)
                {
                    if(  p instanceof JFrame)
                    {
                        ((JFrame) p).setTitle("FileSizeTree (DONE)");
                        tree.setBackground(Color.white);
                        break;
                    }
                    p = p.getParent();
                }
                FileSizeTree.sortPath((DefaultMutableTreeNode)tree.getModel().getRoot());
                tree.updateUI();
            }
        });
    }

    public static void updateTreeUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                tree.updateUI();
            }
        });
    }

    @Override
    public void run() {
        AddNode an = new AddNode((DefaultMutableTreeNode) tree.getModel().getRoot(), path);
        an.doit();
        
        finished();
    }
}
