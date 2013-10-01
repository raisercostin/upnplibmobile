/*
 * ============================================================================
 *                 The Apache Software License, Version 1.1
 * ============================================================================
 *
 * Copyright (C) 2002 The Apache Software Foundation. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modifica-
 * tion, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of  source code must  retain the above copyright  notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must
 *    include the following  acknowledgment: "This product includes software
 *    developed by SuperBonBon Industries (http://www.sbbi.net/)."
 *    Alternately, this acknowledgment may appear in the software itself, if
 *    and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "UPNPLib" and "SuperBonBon Industries" must not be
 *    used to endorse or promote products derived from this software without
 *    prior written permission. For written permission, please contact
 *    info@sbbi.net.
 *
 * 5. Products  derived from this software may not be called 
 *    "SuperBonBon Industries", nor may "SBBI" appear in their name, 
 *    without prior written permission of SuperBonBon Industries.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS  FOR A PARTICULAR  PURPOSE ARE  DISCLAIMED. IN NO EVENT SHALL THE
 * APACHE SOFTWARE FOUNDATION OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT,INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLU-
 * DING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
 * OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * This software  consists of voluntary contributions made by many individuals
 * on behalf of SuperBonBon Industries. For more information on 
 * SuperBonBon Industries, please see <http://www.sbbi.net/>.
 */
package net.sbbi.upnp.xpath;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;

/**
 * Helper class to encapsulate XPath evaluator and current Node pointer, and add data retrieval methods.<br/>
 * Intended as a simple replacement for <strong>commons-jxpath</strong> class with the same name. 
 * 
 * @author mgerdov (maggus@mail.ru)
 *
 */
public class JXPathContext
{
	private final XPath xpath;
	private Node src;
	private Node pointer;
	
	private JXPathContext(Node src, XPath xpath)
	{
		this.xpath = xpath;
		this.src = src;
		this.pointer = src;
	}

	public JXPathContext(Node src)
	{
		xpath = XPathFactory.newInstance().newXPath();	// this should be called only once per thread!
		this.src = src;
		this.pointer = src;
	}
	
	public Node getPointer(String expr) throws XPathException
	{
		try
		{
			Node node = (Node)xpath.evaluate(expr,  pointer, XPathConstants.NODE);
			if(node == null)		// do not allow nulls!?	
				throw new XPathException("Null Pointer - \"" + expr + "\"");
			return node; 
		}
		catch(Exception ex)
		{
			throw new XPathException(ex);
		}
	}
	
	public void movePointer(Node pointer)
	{
		this.pointer = pointer;
	}
	
	public void resetPointer()
	{
		this.pointer = this.src;
	}
	
	public String getString(String expr) throws XPathException
	{
		try
		{
			return (String) xpath.evaluate(expr, pointer, XPathConstants.STRING);
		}
		catch(Exception ex)
		{
			throw new XPathException(ex);
		}
	}
	
	public Double getNumber(String expr) throws XPathException
	{
		try
		{
			return (Double) xpath.evaluate(expr, pointer, XPathConstants.NUMBER);
		}
		catch(Exception ex)
		{
			throw new XPathException(ex);
		}
	}

	public Boolean getBoolean(String expr) throws XPathException
	{
		try
		{
			return (Boolean) xpath.evaluate(expr, pointer, XPathConstants.BOOLEAN);
		}
		catch(Exception ex)
		{
			throw new XPathException(ex);
		}
	}
	
	/**
	 * Create new JXPathContext instance relative to given Node
	 * @param pointer new top level Node
	 * @return
	 */
	public JXPathContext getRelativeContext(Node pointer)
	{
		return new JXPathContext(pointer, xpath);
	}
}
