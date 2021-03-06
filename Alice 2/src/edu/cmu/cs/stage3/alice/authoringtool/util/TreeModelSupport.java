/*
 * Copyright (c) 1999-2003, Carnegie Mellon University. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 * 
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * 3. Products derived from the software may not be called "Alice",
 *    nor may "Alice" appear in their name, without prior written
 *    permission of Carnegie Mellon University.
 * 
 * 4. All advertising materials mentioning features or use of this software
 *    must display the following acknowledgement:
 *    "This product includes software developed by Carnegie Mellon University"
 */

package edu.cmu.cs.stage3.alice.authoringtool.util;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

/**
 * From the Swing Connection article: http://java.sun.com/products/jfc/tsc/articles/jtree/
 *
 * I don't know why these classes aren't in the standard API
 */
public class TreeModelSupport {
	private Vector vector = new Vector();

	public void addTreeModelListener( TreeModelListener listener ) {
		if ( listener != null && !vector.contains( listener ) ) {
			vector.addElement( listener );
		}
	}

	public void removeTreeModelListener( TreeModelListener listener ) {
		if ( listener != null ) {
			vector.removeElement( listener );
		}
	}

	public void fireTreeNodesChanged( final TreeModelEvent e ) {
		javax.swing.SwingUtilities.invokeLater( new Runnable() { // HACK: JTree is really picky about things being changed in the event-dispatch thread
			public void run() {
				Enumeration listeners = vector.elements();
				while ( listeners.hasMoreElements() ) {
					TreeModelListener listener = (TreeModelListener)listeners.nextElement();
					listener.treeNodesChanged( e );
				}
			}
		} );
	}

	public void fireTreeNodesInserted( final TreeModelEvent e ) {
		javax.swing.SwingUtilities.invokeLater( new Runnable() { // HACK: JTree is really picky about things being changed in the event-dispatch thread
			public void run() {
				Enumeration listeners = vector.elements();
				while ( listeners.hasMoreElements() ) {
					TreeModelListener listener = (TreeModelListener)listeners.nextElement();
					listener.treeNodesInserted( e );
				}
			}
		} );
	}

	public void fireTreeNodesRemoved( final TreeModelEvent e ) {
		javax.swing.SwingUtilities.invokeLater( new Runnable() { // HACK: JTree is really picky about things being changed in the event-dispatch thread
			public void run() {
				Enumeration listeners = vector.elements();
				while ( listeners.hasMoreElements() ) {
					TreeModelListener listener = (TreeModelListener)listeners.nextElement();
					listener.treeNodesRemoved( e );
				}
			}
		} );
	}

	public void fireTreeStructureChanged( final TreeModelEvent e ) {
		javax.swing.SwingUtilities.invokeLater( new Runnable() { // HACK: JTree is really picky about things being changed in the event-dispatch thread
			public void run() {
				Enumeration listeners = vector.elements();
				while ( listeners.hasMoreElements() ) {
					TreeModelListener listener = (TreeModelListener)listeners.nextElement();
					if (listener != null && e != null)
						listener.treeStructureChanged( e );
				}
			}
		} );
	}
}
