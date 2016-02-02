package uk.co.inhealthcare.ops.build.jenkins.api.config;

import java.io.IOException;
import java.io.Writer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

public class XmlConfigConverter {

	public void write(Config config, Writer w) throws IOException {
		String jaxbString = config.serialize();
		w.write(jaxbString);
	}

}
