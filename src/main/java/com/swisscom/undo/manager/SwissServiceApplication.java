package com.swisscom.undo.manager;

import com.swisscom.undo.manager.impl.DeleteOperationChange;
import com.swisscom.undo.manager.impl.InsertOperationChange;
import com.swisscom.undo.manager.impl.SimpleTextDocument;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwissServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwissServiceApplication.class, args);

		SimpleTextDocument simpleTextDocument = new SimpleTextDocument(new StringBuilder("Test string!"), 5);
		InsertOperationChange insertOperationChange = new InsertOperationChange(5, " ");
//		insertOperationChange.apply(simpleTextDocument);
//		System.out.println(simpleTextDocument.getContent().toString());
//		System.out.println(simpleTextDocument.getDot());
//		insertOperationChange.apply(simpleTextDocument);
//		System.out.println(simpleTextDocument.getContent().toString());
//		System.out.println(simpleTextDocument.getDot());
//		insertOperationChange.revert(simpleTextDocument);
//		System.out.println(simpleTextDocument.getContent().toString());
//		System.out.println(simpleTextDocument.getDot());
		insertOperationChange.revert(simpleTextDocument);
		System.out.println(simpleTextDocument.getContent().toString());
		System.out.println(simpleTextDocument.getDot());

//		DeleteOperationChange deleteOperationChange = new DeleteOperationChange(3, "es");
//		deleteOperationChange.apply(simpleTextDocument);
//		System.out.println(simpleTextDocument.getContent().toString());
//		System.out.println(simpleTextDocument.getDot());
//		deleteOperationChange.revert(simpleTextDocument);
//		System.out.println(simpleTextDocument.getContent().toString());
//		System.out.println(simpleTextDocument.getDot());
	}
}
