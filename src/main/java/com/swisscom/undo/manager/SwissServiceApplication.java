package com.swisscom.undo.manager;

import com.swisscom.undo.manager.impl.SimpleTextDocument;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwissServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwissServiceApplication.class, args);
		SimpleTextDocument testDocument = new SimpleTextDocument();
		testDocument.insert(0, "abc");
		testDocument.insert(3, "aaaa");
		System.out.println(testDocument.getContent());
		testDocument.delete(1, "a");
		System.out.println(testDocument.getContent());
	}

}
