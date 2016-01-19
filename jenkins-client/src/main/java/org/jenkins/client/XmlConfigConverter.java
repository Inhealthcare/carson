package org.jenkins.client;

import java.io.IOException;
import java.io.Writer;

import org.jenkins.client.api.Config;

public class XmlConfigConverter {

	public void write(Config config, Writer w) throws IOException {
		String jaxbString = config.serialize();
		w.write(jaxbString);
	}

}
