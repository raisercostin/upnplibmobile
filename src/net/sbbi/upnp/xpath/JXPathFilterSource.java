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

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xml.sax.InputSource;

/**
 * Parser Filter to use with JXPath, this is used to fix some problems encountered with some UPNP devices returning buggy xml docs... This
 * parser acts like a wrapper and make some chars search and replace such as 0x0 with 0x20 to produce a valid XML doc.
 * 
 * @author mgerdov (maggus@mail.ru)
 * 
 */
public class JXPathFilterSource extends InputSource
{
	private final static Logger log = Logger.getLogger(JXPathFilterSource.class.getName());

	private final char buggyChar = (char) 0;

	public JXPathFilterSource()
	{
		super();
	}

	public JXPathFilterSource(InputStream byteStream)
	{
		super(byteStream);
		if(byteStream == null)
			return;

		StringBuffer xml = new StringBuffer();
		try
		{
			byte[] buffer = new byte[512];
			int readen = 0;
			while((readen = byteStream.read(buffer)) != -1)
			{
				xml.append(new String(buffer, 0, readen));
			}
		}
		catch(IOException ex)
		{
			log.log(Level.SEVERE, "IOException occured during XML reception", ex);
			return;
		}
		String doc = xml.toString();
		log.fine("Readen raw xml doc:\n" + doc);
		if(doc.indexOf(buggyChar) != -1)
		{
			doc = doc.replace(buggyChar, ' ');
		}

		// replace stream with new filtered one.
		setByteStream(new ByteArrayInputStream(doc.getBytes()));
	}

	public JXPathFilterSource(Reader characterStream)
	{
		super(characterStream);
		if(characterStream == null)
			return;

		StringBuffer xml = new StringBuffer();
		try
		{
			char[] buffer = new char[512];
			int readen = 0;
			while((readen = characterStream.read(buffer)) != -1)
			{
				xml.append(new String(buffer, 0, readen));
			}
		}
		catch(IOException ex)
		{
			log.log(Level.SEVERE, "IOException occured during XML reception", ex);
			return;
		}
		String doc = xml.toString();
		log.fine("Readen raw xml doc:\n" + doc);
		if(doc.indexOf(buggyChar) != -1)
		{
			doc = doc.replace(buggyChar, ' ');
		}

		// replace stream with new filtered one.
		setCharacterStream(new CharArrayReader(doc.toCharArray()));
	}

	public JXPathFilterSource(String systemId)
	{
		super(systemId);
	}
}
